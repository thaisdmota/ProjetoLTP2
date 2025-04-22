package projeto1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Cliente {
    String nome, telefone, email;
}

class ClienteNacional extends Cliente {
    String cpf;
}

class ClienteEstrangeiro extends Cliente {
    String passaporte;
}

class Pacote {
    String nome, destino, tipo;
    double valorPassagem, valorDiaria;

    public Pacote(String nome, String destino, double passagem, double diaria, String tipo) {
        this.nome = nome;
        this.destino = destino;
        this.valorPassagem = passagem;
        this.valorDiaria = diaria;
        this.tipo = tipo;
    }

    public String toString() {
        return nome + " - " + destino + " | Tipo: " + tipo +
               " | Passagem: R$" + valorPassagem + " | Diária: R$" + valorDiaria;
    }

}

class Reserva {
    Cliente cliente;
    Pacote pacote;
    int dias, quantidadePessoas;
    double valorFinal;
    List<ServicoAdicional> servicosAdicionais;

    public Reserva(Cliente cliente, Pacote pacote, int dias, int quantidadePessoas, double valorFinal, List<ServicoAdicional> servicosAdicionais) {
        this.cliente = cliente;
        this.pacote = pacote;
        this.dias = dias;
        this.quantidadePessoas = quantidadePessoas;
        this.valorFinal = valorFinal;
        this.servicosAdicionais = servicosAdicionais;
    }
}

class ServicoAdicional {
    String nome;
    double preco;

    public ServicoAdicional(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String toString() {
        return nome + " - R$" + preco;
    }
}

public class AgenciaViagens {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Pacote> pacotes = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static List<ServicoAdicional> servicosAdicionais = new ArrayList<>();
    static List<String> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
    	pacotes.add(new Pacote("Rio de Janeiro", "RJ", 5500, 6000, "Luxo"));
    	pacotes.add(new Pacote("Amazônia", "AM", 1200, 450, "Aventura"));
    	pacotes.add(new Pacote("Salvador", "BA", 900, 400, "Cultural"));
    	pacotes.add(new Pacote("Lençóis Maranhenses", "MA", 1000, 450, "Aventura"));
    	pacotes.add(new Pacote("São Paulo", "SP", 950, 400, "Padrão"));
    	pacotes.add(new Pacote("Brasília", "DF", 900, 550, "Cultural"));
    	pacotes.add(new Pacote("Belo Horizonte", "MG", 4200, 5200, "Luxo"));


        funcionarios.add("admin:1234");
        if (!loginFuncionario()) return;
        

        servicosAdicionais.add(new ServicoAdicional("Translado aeroporto-hotel", 150.0));
		servicosAdicionais.add(new ServicoAdicional("Acesso à Academia", 350.0));


        while (true) {
            String[] opcoes = {"Clientes", "Pacotes", "Serviços Adicionais", "Reservas", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Menu Principal", "Agência de Viagens",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
            if (escolha == 0) menuClientes();
            else if (escolha == 1) menuPacotes();
            else if (escolha == 2) menuServicosAdicionais();
            else if (escolha == 3) mostrarReservas();
            else break;
        }
    }

    static boolean loginFuncionario() {
        String usuario = JOptionPane.showInputDialog("Usuário:");
        String senha = JOptionPane.showInputDialog("Senha:");
        return funcionarios.stream().anyMatch(f -> f.equals(usuario + ":" + senha));
    }

    static void menuClientes() {
        String[] opcoes = {"Cadastrar", "Visualizar", "Buscar", "Excluir", "Ver Pacotes Contratados", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Clientes", "Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (escolha) {
            case 0 -> cadastrarCliente();
            case 1 -> visualizarClientes();
            case 2 -> buscarCliente();
            case 3 -> excluirCliente();
            case 4 -> visualizarPacotesCliente();
        }
    }

    static void menuPacotes() {
        String[] opcoes = {"Visualizar", "Buscar", "Excluir", "Adicionar", "Reservar", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Pacotes", "Pacotes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (escolha) {
            case 0 -> visualizarPacotes();
            case 1 -> buscarPacote();
            case 2 -> excluirPacote();
            case 3 -> adicionarPacote();
            case 4 -> fazerReserva();
        }
    }

    static void menuServicosAdicionais() {
        String[] opcoes = {"Incluir", "Listar", "Buscar", "Excluir", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Serviços Adicionais", "Serviços Adicionais",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
        switch (escolha) {
            case 0 -> incluirServico();
            case 1 -> listarServicos();
            case 2 -> buscarServico();
            case 3 -> excluirServico();
        }
    }

    static void incluirServico() {
        String nome = JOptionPane.showInputDialog("Nome do serviço:");
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do serviço:"));
        servicosAdicionais.add(new ServicoAdicional(nome, preco));
        JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso!");
    }

    static void listarServicos() {
        if (servicosAdicionais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum serviço adicional disponível.");
            return;
        }
        StringBuilder lista = new StringBuilder();
        for (ServicoAdicional s : servicosAdicionais) {
            lista.append(s.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    static void buscarServico() {
        String nome = JOptionPane.showInputDialog("Nome do serviço:");
        StringBuilder res = new StringBuilder();
        servicosAdicionais.stream().filter(s -> s.nome.equalsIgnoreCase(nome)).forEach(s -> {
            res.append(s.toString()).append("\n");
        });
        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Serviço não encontrado.");
    }

    static void excluirServico() {
        if (servicosAdicionais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum serviço adicional disponível.");
            return;
        }

        String[] nomes = servicosAdicionais.stream().map(s -> s.nome).toArray(String[]::new);
        String nome = (String) JOptionPane.showInputDialog(null, "Selecione o serviço:", "Excluir Serviço", JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);
        ServicoAdicional s = servicosAdicionais.stream().filter(serv -> serv.nome.equals(nome)).findFirst().orElse(null);
        if (s == null) return;

        boolean vinculado = reservas.stream().anyMatch(r -> r.servicosAdicionais.contains(s));
        if (vinculado) {
            JOptionPane.showMessageDialog(null, "Este serviço está vinculado a uma reserva e não pode ser excluído.");
            return;
        }

        servicosAdicionais.remove(s);
        JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso.");
    }


    static void mostrarReservas() {
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva realizada.");
            return;
        }
        StringBuilder lista = new StringBuilder();
        int i = 1;
        for (Reserva r : reservas) {
            lista.append("Reserva ").append(i++).append("\nCliente: ").append(r.cliente.nome)
                    .append("\nPacote: ").append(r.pacote.nome).append("\nValor: R$").append(r.valorFinal).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    static void cadastrarCliente() {
        String[] tipoCliente = {"Nacional", "Estrangeiro"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de cliente:", "Cadastro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);
        Cliente cliente;
        if (tipo == 0) {
            cliente = new ClienteNacional();
            ((ClienteNacional) cliente).cpf = JOptionPane.showInputDialog("CPF:");
        } else {
            cliente = new ClienteEstrangeiro();
            ((ClienteEstrangeiro) cliente).passaporte = JOptionPane.showInputDialog("Passaporte:");
        }
        cliente.nome = JOptionPane.showInputDialog("Nome:");
        cliente.telefone = JOptionPane.showInputDialog("Telefone:");
        cliente.email = JOptionPane.showInputDialog("Email:");
        clientes.add(cliente);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    static void visualizarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente.");
            return;
        }
        StringBuilder lista = new StringBuilder();
        int i = 1;
        for (Cliente c : clientes) {
            lista.append("Cliente ").append(i++).append("\nNome: ").append(c.nome).append("\nEmail: ").append(c.email);
            if (c instanceof ClienteNacional) lista.append("\nCPF: ").append(((ClienteNacional) c).cpf);
            if (c instanceof ClienteEstrangeiro) lista.append("\nPassaporte: ").append(((ClienteEstrangeiro) c).passaporte);
            lista.append("\n\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    static void buscarCliente() {
        String nome = JOptionPane.showInputDialog("Nome do cliente:");
        StringBuilder res = new StringBuilder();
        clientes.stream().filter(c -> c.nome.equalsIgnoreCase(nome)).forEach(c -> {
            res.append("Nome: ").append(c.nome).append("\nEmail: ").append(c.email);
            if (c instanceof ClienteNacional) res.append("\nCPF: ").append(((ClienteNacional) c).cpf);
            if (c instanceof ClienteEstrangeiro) res.append("\nPassaporte: ").append(((ClienteEstrangeiro) c).passaporte);
            res.append("\n\n");
        });
        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Cliente não encontrado.");
    }

    static void excluirCliente() {
        if (clientes.isEmpty()) return;
        String[] nomes = clientes.stream().map(c -> c.nome).toArray(String[]::new);
        String nome = (String) JOptionPane.showInputDialog(null, "Selecione o cliente:", "Excluir Cliente", JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);
        Cliente c = clientes.stream().filter(cl -> cl.nome.equals(nome)).findFirst().orElse(null);
        if (c == null) return;
        boolean temReserva = reservas.stream().anyMatch(r -> r.cliente.equals(c));
        if (temReserva) {
            JOptionPane.showMessageDialog(null, "Este cliente tem reservas e não pode ser excluído.");
            return;
        }
        clientes.remove(c);
        JOptionPane.showMessageDialog(null, "Cliente removido.");
    }

    static void visualizarPacotesCliente() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
        Cliente cliente = clientes.stream().filter(c -> c.nome.equals(nomeCliente)).findFirst().orElse(null);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        StringBuilder pacotesCliente = new StringBuilder();
        reservas.stream()
                .filter(r -> r.cliente.equals(cliente))
                .forEach(r -> pacotesCliente.append("Pacote: ").append(r.pacote.nome).append("\nValor: R$").append(r.valorFinal).append("\n\n"));
        JOptionPane.showMessageDialog(null, pacotesCliente.length() > 0 ? pacotesCliente.toString() : "Este cliente não possui pacotes contratados.");
    }

    static void visualizarPacotes() {
        if (pacotes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pacote disponível.");
            return;
        }
        StringBuilder lista = new StringBuilder();
        int i = 1;
        for (Pacote p : pacotes) {
            lista.append(i++).append(". ").append(p.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    static void buscarPacote() {
        String nomePacote = JOptionPane.showInputDialog("Digite o nome do pacote:");
        if (nomePacote == null || nomePacote.trim().isEmpty()) return;

        StringBuilder res = new StringBuilder();

        pacotes.stream()
                .filter(p -> p.nome.equalsIgnoreCase(nomePacote))
                .forEach(p -> {
                    res.append(p.toString()).append("\n\n");

                    List<Reserva> reservasDoPacote = reservas.stream()
                            .filter(r -> r.pacote.equals(p))
                            .toList();

                    if (reservasDoPacote.isEmpty()) {
                        res.append("Nenhum cliente contratou este pacote.\n");
                    } else {
                        res.append("Clientes que contrataram este pacote:\n");
                        for (Reserva r : reservasDoPacote) {
                            res.append("- ").append(r.cliente.nome).append("\n");
                        }
                    }
                });

        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Pacote não encontrado.");
    }


    static void excluirPacote() {
        if (pacotes.isEmpty()) return;
        String[] nomes = pacotes.stream().map(p -> p.nome).toArray(String[]::new);
        String nome = (String) JOptionPane.showInputDialog(null, "Selecione o pacote:", "Excluir Pacote", JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);
        Pacote p = pacotes.stream().filter(pa -> pa.nome.equals(nome)).findFirst().orElse(null);
        if (p == null) return;
        boolean temReserva = reservas.stream().anyMatch(r -> r.pacote.equals(p));
        if (temReserva) {
            JOptionPane.showMessageDialog(null, "Este pacote possui reservas e não pode ser excluído.");
            return;
        }
        pacotes.remove(p);
        JOptionPane.showMessageDialog(null, "Pacote removido.");
    }

    static void adicionarPacote() {
    	String nome = JOptionPane.showInputDialog("Nome do pacote:");
    	String destino = JOptionPane.showInputDialog("Destino:");
    	double passagem = Double.parseDouble(JOptionPane.showInputDialog("Valor da passagem:"));
    	double diaria = Double.parseDouble(JOptionPane.showInputDialog("Valor da diária por pessoa:"));

    	String[] tipos = {"Luxo", "Cultural", "Aventura", "Padrão"};
    	String tipo = (String) JOptionPane.showInputDialog(null, "Tipo do pacote:", "Tipo",
    	        JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

    	if (tipo == null) return;

    	pacotes.add(new Pacote(nome, destino, passagem, diaria, tipo));
    	JOptionPane.showMessageDialog(null, "Pacote adicionado.");

    }

    static void fazerReserva() {
        if (clientes.isEmpty() || pacotes.isEmpty()) return;
        String[] nomesClientes = clientes.stream().map(c -> c.nome).toArray(String[]::new);
        String nome = (String) JOptionPane.showInputDialog(null, "Cliente:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, nomesClientes, nomesClientes[0]);
        Cliente c = clientes.stream().filter(cl -> cl.nome.equals(nome)).findFirst().orElse(null);
        if (c == null) return;

        Pacote[] opcoes = pacotes.toArray(new Pacote[0]);
        Pacote p = (Pacote) JOptionPane.showInputDialog(null, "Pacote:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (p == null) return;

        int dias = Integer.parseInt(JOptionPane.showInputDialog("Quantos dias?"));

        
        int qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Quantas pessoas?"));

        List<ServicoAdicional> servicosEscolhidos = new ArrayList<>();
        int continuar = JOptionPane.YES_OPTION;
        while (continuar == JOptionPane.YES_OPTION) {
            String[] servicos = servicosAdicionais.stream().map(s -> s.nome).toArray(String[]::new);
            String servicoEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha um serviço adicional:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, servicos, servicos[0]);
            ServicoAdicional servico = servicosAdicionais.stream().filter(s -> s.nome.equals(servicoEscolhido)).findFirst().orElse(null);
            if (servico != null) servicosEscolhidos.add(servico);
            continuar = JOptionPane.showConfirmDialog(null, "Adicionar mais serviços?", "Reserva", JOptionPane.YES_NO_OPTION);
        }

        double valorFinal = (p.valorPassagem * qtdPessoas) + (p.valorDiaria * dias * qtdPessoas);

        for (ServicoAdicional sa : servicosEscolhidos) {
            valorFinal += sa.preco;
        }

        reservas.add(new Reserva(c, p, dias, qtdPessoas, valorFinal, servicosEscolhidos));
        JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
    }
}
