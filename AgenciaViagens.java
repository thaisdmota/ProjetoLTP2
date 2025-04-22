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

class Reserva {
    Cliente cliente;
    Pacote pacote;
    int dias;
    int quantidadePessoas;
    double valorFinal;

    public Reserva(Cliente cliente, Pacote pacote, int dias, int quantidadePessoas, double valorFinal) {
        this.cliente = cliente;
        this.pacote = pacote;
        this.dias = dias;
        this.quantidadePessoas = quantidadePessoas;
        this.valorFinal = valorFinal;
    }
}

public class AgenciaViagens {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Pacote> pacotes = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static List<String> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
        // Adicionando os pacotes inicialmente
        pacotes.add(new Pacote("Rio de Janeiro", "Rio de Janeiro-RJ", 1000.00, 500.00));
        pacotes.add(new Pacote("Amazônia", "Manaus-AM", 1200.00, 450.00));
        pacotes.add(new Pacote("Salvador", "Salvador-BA", 900.00, 400.00));
        pacotes.add(new Pacote("Lençóis Maranhenses", "Maranhão-MA", 1000.00, 450.00));
        pacotes.add(new Pacote("São Paulo", "São Paulo-SP", 950.00, 400.00));
        pacotes.add(new Pacote("Brasília", "Brasília-DF", 900.00, 550.00));
        pacotes.add(new Pacote("Belo Horizonte", "Belo Horizonte-MG", 1000.00, 500.00));

        if (!loginFuncionario()) {
            JOptionPane.showMessageDialog(null, "Acesso negado. Funcionário não autenticado.");
            return;
        }

        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");

        while (true) {
            String[] opcoes = {"Clientes", "Pacotes", "Reservas", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Menu Principal", "Agência de Viagens",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha == 0) {
                menuClientes();
            } else if (escolha == 1) {
                menuPacotes();
            } else if (escolha == 2) {
                mostrarReservas();
            } else {
                break;
            }
        }
    }

    static boolean loginFuncionario() {
        String usuario = JOptionPane.showInputDialog("Digite o nome de usuário:");
        String senha = JOptionPane.showInputDialog("Digite a senha:");

        if (usuario == null || senha == null || usuario.isEmpty() || senha.isEmpty()) {
            return false;
        }

        funcionarios.add("admin:1234");

        for (String func : funcionarios) {
            String[] credenciais = func.split(":");
            if (credenciais[0].equals(usuario) && credenciais[1].equals(senha)) {
                return true;
            }
        }

        return false;
    }

    static void menuClientes() {
        String[] opcoes = {"Cadastrar", "Visualizar", "Buscar", "Excluir", "Ver Pacotes Contratados", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Clientes", "Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            cadastrarCliente();
        } else if (escolha == 1) {
            visualizarClientes();
        } else if (escolha == 2) {
            buscarCliente();
        } else if (escolha == 3) {
            excluirCliente();
        } else if (escolha == 4) {
            visualizarPacotesCliente();
        }
    }

    static void cadastrarCliente() {
        String[] tipoCliente = {"Nacional", "Estrangeiro"};
        int tipo = JOptionPane.showOptionDialog(null, "Você é cliente nacional ou estrangeiro?", "Cadastro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);

        Cliente cliente;
        if (tipo == 0) {
            cliente = new ClienteNacional();
            String cpf = JOptionPane.showInputDialog("Qual o CPF do cliente? Somente números.");
            if (cpf == null || !cpf.matches("\\d+")) return;
            ((ClienteNacional) cliente).cpf = cpf;
        } else {
            cliente = new ClienteEstrangeiro();
            String passaporte = JOptionPane.showInputDialog("Qual o número do passaporte do cliente?");
            if (passaporte == null || passaporte.trim().isEmpty()) return;
            ((ClienteEstrangeiro) cliente).passaporte = passaporte;
        }

        String nome = JOptionPane.showInputDialog("Qual o nome do cliente?");
        if (nome == null || nome.trim().isEmpty()) return;
        cliente.nome = nome;

        String telefone = JOptionPane.showInputDialog("Qual o telefone do cliente? Somente números.");
        if (telefone == null || !telefone.matches("\\d+")) return;
        cliente.telefone = telefone;

        String email = JOptionPane.showInputDialog("Qual o e-mail do cliente?");
        if (email == null || email.trim().isEmpty()) return;
        cliente.email = email;

        clientes.add(cliente);
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    }

    static void visualizarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
        } else {
            StringBuilder lista = new StringBuilder();
            int contador = 1;
            for (Cliente c : clientes) {
                lista.append("Cliente " + contador + "\nNome: ").append(c.nome).append("\nEmail: ").append(c.email).append("\n");
                if (c instanceof ClienteNacional) {
                    lista.append("CPF: ").append(((ClienteNacional) c).cpf).append("\n");
                } else if (c instanceof ClienteEstrangeiro) {
                    lista.append("Passaporte: ").append(((ClienteEstrangeiro) c).passaporte).append("\n");
                }
                lista.append("\n");
                contador++;
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static void visualizarPacotesCliente() {
        StringBuilder lista = new StringBuilder();
        int contador = 1;
        for (Pacote p : pacotes) {
            lista.append("Pacote " + contador + "\nNome: ").append(p.nome).append("\nDestino: ").append(p.destino)
                    .append("\nValor Passagem: R$").append(p.valorPassagem).append("\nValor Diária: R$")
                    .append(p.valorDiaria).append("\n\n");
            contador++;
        }
        JOptionPane.showMessageDialog(null, lista.toString(), "Pacotes Disponíveis", JOptionPane.INFORMATION_MESSAGE);
    }

    static void menuPacotes() {
        String[] opcoes = {"Adicionar Pacote", "Visualizar Pacotes", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Pacotes", "Pacotes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            adicionarPacote();
        } else if (escolha == 1) {
            visualizarPacotes();
        }
    }

    static void adicionarPacote() {
        String nome = JOptionPane.showInputDialog("Qual o nome do pacote?");
        String destino = JOptionPane.showInputDialog("Qual o destino do pacote?");
        String valorPassagemStr = JOptionPane.showInputDialog("Qual o valor da passagem?");
        String valorDiariaStr = JOptionPane.showInputDialog("Qual o valor da diária?");

        if (nome == null || destino == null || valorPassagemStr == null || valorDiariaStr == null) return;

        double valorPassagem = Double.parseDouble(valorPassagemStr);
        double valorDiaria = Double.parseDouble(valorDiariaStr);

        Pacote pacote = new Pacote(nome, destino, valorPassagem, valorDiaria);
        pacotes.add(pacote);

        JOptionPane.showMessageDialog(null, "Pacote adicionado com sucesso!");
    }

    static void visualizarPacotes() {
        if (pacotes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pacote disponível.");
        } else {
            StringBuilder lista = new StringBuilder();
            int contador = 1;
            for (Pacote p : pacotes) {
                lista.append("Pacote " + contador + "\nNome: ").append(p.nome).append("\nDestino: ").append(p.destino)
                        .append("\nValor Passagem: R$").append(p.valorPassagem).append("\nValor Diária: R$")
                        .append(p.valorDiaria).append("\n\n");
                contador++;
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Pacotes Disponíveis", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static void mostrarReservas() {
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada.");
        } else {
            StringBuilder lista = new StringBuilder();
            int contador = 1;
            for (Reserva r : reservas) {
                lista.append("Reserva " + contador + "\nCliente: " + r.cliente.nome + "\nPacote: " + r.pacote.nome +
                        "\nDestino: " + r.pacote.destino + "\nValor Final: R$" + r.valorFinal + "\n\n");
                contador++;
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Reservas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void excluirCliente() {}
    private static void buscarCliente() {}
}
