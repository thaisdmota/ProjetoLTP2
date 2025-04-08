package projeto1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Cliente {
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

class Pacote {
    String nome;
    String destino;
    double valorPassagem;
    double valorDiaria;

    public Pacote(String nome, String destino, double passagem, double diaria) {
        this.nome = nome;
        this.destino = destino;
        this.valorPassagem = passagem;
        this.valorDiaria = diaria;
    }

    public String toString() {
        return nome + " - Destino: " + destino + " | Passagem: R$" + valorPassagem + " | Diária: R$" + valorDiaria;
    }
}

public class AgenciaViagens {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Pacote> pacotes = new ArrayList<>();
    
    public static void main(String[] args) {
        // Pacotes pré-definidos

        pacotes.add(new Pacote("Rio de Janeiro", "Rio de Janeiro-RJ", 1000.00, 500.00));
        pacotes.add(new Pacote("Amazônia", "Manaus-AM", 1200.00, 450.00));
        pacotes.add(new Pacote("Salvador", "Salvador-BA", 900.00, 400.00));
        pacotes.add(new Pacote("Lençóis Maranhenses", "Maranhão-MA", 1000.00, 450.00));
        pacotes.add(new Pacote ("São Paulo", "São Paulo-SP", 950.00, 400.00));
        pacotes.add(new Pacote("Brasília", "Brasília-DF", 900.00, 550.00));
        pacotes.add(new Pacote ("Belo Horizonte", "Belo Horizonte-MG", 1000.00, 500.00));
        while (true) {
            String[] opcoes = {"Clientes", "Pacotes", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Menu Principal", "Agência de Viagens",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                menuClientes();
            } else if (escolha == 1) {
                menuPacotes();
            } else {
                break;
            }
        }
    }

    static void menuClientes() {
        String[] opcoes = {"Cadastrar", "Visualizar", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Clientes", "Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            String[] tipoCliente = {"Nacional", "Estrangeiro"};
            int tipo = JOptionPane.showOptionDialog(null, "Você é cliente nacional ou estrangeiro? ", "Cadastro",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);

            Cliente cliente;
            if (tipo == 0) {
                cliente = new ClienteNacional();
                ((ClienteNacional) cliente).cpf = JOptionPane.showInputDialog("Qual é o seu CPF?");
            } else {
                cliente = new ClienteEstrangeiro();
                ((ClienteEstrangeiro) cliente).passaporte = JOptionPane.showInputDialog("Qual é o número do seu passaporte?");
            }

            cliente.nome = JOptionPane.showInputDialog("Qual é o seu nome?");
            cliente.telefone = JOptionPane.showInputDialog("Qual é o seu telefone?");
            cliente.email = JOptionPane.showInputDialog("Qual é o seu email?");
            clientes.add(cliente);
    

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else if (escolha == 1) {
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            } else {
                StringBuilder lista = new StringBuilder();
                int contador = 1;
                for (Cliente c : clientes) {
                    lista.append("Cliente " + contador + "\n Nome: ").append(c.nome).append(" \n Email: ").append(c.email).append("\n\n");
                    contador++;
                }

                JOptionPane.showMessageDialog(null, lista.toString(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    static void menuPacotes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado. Por favor, cadastre-se primeiro.");
            menuClientes();
            if (clientes.isEmpty()) return; // Ainda não cadastrou ninguém? Sai fora
        }

        // Escolha do cliente
        String[] nomesClientes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            nomesClientes[i] = clientes.get(i).nome;
        }

        String nomeSelecionado = (String) JOptionPane.showInputDialog(
                null,
                "Selecione seu nome:",
                "Identificação do Cliente",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesClientes,
                nomesClientes[0]
        );

        if (nomeSelecionado == null) return;

        Cliente clienteSelecionado = null;
        for (Cliente c : clientes) {
            if (c.nome.equals(nomeSelecionado)) {
                clienteSelecionado = c;
                break;
            }
        }

        if (clienteSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        // Seleção de pacote
        Pacote[] opcoes = pacotes.toArray(new Pacote[0]);

        Pacote selecionado = (Pacote) JOptionPane.showInputDialog(
                null,
                "Escolha um pacote:",
                "Pacotes Disponíveis",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (selecionado == null) return;

        String[] tipos = {
            "Luxo (Aumenta o preço em 40%)",
            "Aventura (Aumenta o preço em 20%)",
            "Cultural (Aumenta o preço em 10%)",
            "Padrão (Aumenta o preço em 0%)"
        };

        int tipo = JOptionPane.showOptionDialog(
                null,
                "Escolha o tipo do pacote:",
                "Tipo do Pacote",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        if (tipo == -1) return;

        int dias;
        try {
            dias = Integer.parseInt(JOptionPane.showInputDialog("Quantos dias de viagem?"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Número de dias inválido.");
            return;
        }

        double multiplicador = switch (tipo) {
            case 0 -> 1.40;
            case 1 -> 1.20;
            case 2 -> 1.10;
            case 3 -> 1.0;
            default -> 1.0;
        };

        double valorFinal = (selecionado.valorPassagem + (selecionado.valorDiaria * dias)) * multiplicador;

        JOptionPane.showMessageDialog(null,
                "Resumo da Viagem:\n" +
                "Cliente: " + clienteSelecionado.nome + "\n" +
                "Destino: " + selecionado.destino + "\n" +
                "Tipo: " + tipos[tipo] + "\n" +
                "Dias: " + dias + "\n" +
                "Valor Total: R$ " + String.format("%.2f", valorFinal));
    }
}
