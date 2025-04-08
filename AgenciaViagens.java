package projeto1;

import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

abstract class Cliente {
    String nome;
    String telefone;
    String email;
}

class ClienteNacional extends Cliente {
    String cpf;
}

class ClienteEstrangeiro extends Cliente {
    String passaporte;
}

enum TipoPacote {
    AVENTURA, LUXO, CULTURAL
}

abstract class PacoteViagem {
    String nomePacote;
    String destino;
    int duracaoDias;
    double precoBase;
    double precocacrescimo;
    TipoPacote tipo;

    public double calcularPrecoFinal() {
        double acrescimo = switch (tipo) {
            case AVENTURA -> 1.2;
            case LUXO -> 1.4;
            case CULTURAL -> 1.1;
        };
        return precocacrescimo = precoBase * acrescimo;
    }
}

abstract class ServicosAdicionais {
    String descricao;
    double preco;
}

class Transfer extends ServicosAdicionais {
    String origem;
    String destino;
}

class Passeio extends ServicosAdicionais {
    String local;
    int duracaoHoras;
}

class AluguelCarro extends ServicosAdicionais {
    String tipoVeiculo;
    int dias;
}

class Reserva {
    Cliente cliente;
    PacoteViagem pacote;
    List<ServicosAdicionais> servicos;
    LocalDate data;

    public Reserva(Cliente cliente, PacoteViagem pacote, List<ServicosAdicionais> servicos) {
        this.cliente = cliente;
        this.pacote = pacote;
        this.servicos = servicos;
        this.data = LocalDate.now();
    }

    public void exibirResumo() {
        System.out.println("\nCliente: " + cliente.nome);
        System.out.println("Pacote: " + pacote.nomePacote + " - " + pacote.destino);
        System.out.println("Duração: " + pacote.duracaoDias + " dias");
        System.out.println("Preço do pacote: R$ " + pacote.calcularPrecoFinal());

        double totalServicos = 0;
        if (servicos == null || servicos.isEmpty()) {
            System.out.println("Não há serviços adicionais.");
        } else {
            System.out.println("Serviços Adicionais:");
            for (ServicosAdicionais s : servicos) {
                System.out.println("- " + s.descricao + " | R$ " + s.preco);
                totalServicos += s.preco;
            }
        }

        double precoTotal = pacote.calcularPrecoFinal() + totalServicos;
        System.out.println("Preço de serviços adicionais: " +totalServicos);
        System.out.println("Valor total da reserva: R$ " + precoTotal);
    }
}

public class AgenciaViagens {

    static Map<String, Reserva> reservasPorCliente = new HashMap<>();

    public static void buscarReservaPorNomeCliente(String nome) {
        Reserva reserva = reservasPorCliente.get(nome);
        if (reserva != null) {
            System.out.println("\nReserva encontrada para: " + nome);
            reserva.exibirResumo();
        } else {
            System.out.println("\nNenhuma reserva encontrada para o cliente: " + nome);
        }
    }

    public static void main(String[] args) {

        // Cliente 1
        ClienteNacional cliente1 = new ClienteNacional();
        cliente1.nome = "Thaís da Mota";
        cliente1.telefone = "061900000000";
        cliente1.email = "thais.dmota@sempreceub.com";
        cliente1.cpf = "00000000000";

        PacoteViagem pacote1 = new PacoteViagem() {};
        pacote1.nomePacote = "Férias no Brasil";
        pacote1.destino = "Rio de Janeiro";
        pacote1.duracaoDias = 7;
        pacote1.precoBase = 9200.00;
        pacote1.tipo = TipoPacote.LUXO;

        Transfer transfer1 = new Transfer();
        transfer1.descricao = "Viagem do aeroporto até o hotel.";
        transfer1.preco = 120.00;
        transfer1.origem = "Aeroporto Santos Dumont";
        transfer1.destino = "Hotel Ibis";

        Passeio passeio1 = new Passeio();
        passeio1.descricao = "Um lindo passeio pelo Cristo Redentor.";
        passeio1.preco = 500.00;
        passeio1.duracaoHoras = 4;
        passeio1.local = "Cristo Redentor e Mirante Dona Marta.";

        Reserva reserva1 = new Reserva(cliente1, pacote1, List.of(transfer1, passeio1));
        reservasPorCliente.put(cliente1.nome, reserva1);

        // Cliente 2
        ClienteEstrangeiro cliente2 = new ClienteEstrangeiro();
        cliente2.nome = "Juan Dinenno";
        cliente2.telefone = "0000000000";
        cliente2.email = "juan.dinenno@gmail.com";
        cliente2.passaporte = "444333";

        PacoteViagem pacote2 = new PacoteViagem() {};
        pacote2.nomePacote = "Pacote Disney";
        pacote2.destino = "Orlando";
        pacote2.duracaoDias = 10;
        pacote2.precoBase = 25000.00;
        pacote2.tipo = TipoPacote.AVENTURA;

        Reserva reserva2 = new Reserva(cliente2, pacote2, null);
        reservasPorCliente.put(cliente2.nome, reserva2);


        buscarReservaPorNomeCliente("Thaís da Mota");
        buscarReservaPorNomeCliente("Juan Dinenno");
        buscarReservaPorNomeCliente("Carlos Silva"); // Não existe
    }
}
