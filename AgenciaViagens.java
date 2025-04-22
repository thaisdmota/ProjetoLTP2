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

    public static void main(String[] args) {
        pacotes.add(new Pacote("Rio de Janeiro", "Rio de Janeiro-RJ", 1000.00, 500.00));
        pacotes.add(new Pacote("Amazônia", "Manaus-AM", 1200.00, 450.00));
        pacotes.add(new Pacote("Salvador", "Salvador-BA", 900.00, 400.00));
        pacotes.add(new Pacote("Lençóis Maranhenses", "Maranhão-MA", 1000.00, 450.00));
        pacotes.add(new Pacote("São Paulo", "São Paulo-SP", 950.00, 400.00));
        pacotes.add(new Pacote("Brasília", "Brasília-DF", 900.00, 550.00));
        pacotes.add(new Pacote("Belo Horizonte", "Belo Horizonte-MG", 1000.00, 500.00));

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

    static void menuClientes() {
    	String[] opcoes = {"Cadastrar", "Visualizar", "Buscar", "Excluir", "Ver Pacotes Contratados", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Menu Clientes", "Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            String[] tipoCliente = {"Nacional", "Estrangeiro"};
            int tipo = JOptionPane.showOptionDialog(null, "Você é cliente nacional ou estrangeiro? ", "Cadastro",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);

            Cliente cliente;
            if (tipo == 0) {
                cliente = new ClienteNacional();
                String cpf = JOptionPane.showInputDialog("Qual é o seu CPF? Somente números.");
                if (cpf == null || !cpf.matches("\\d+")) return;
                ((ClienteNacional) cliente).cpf = cpf;
            } else {
                cliente = new ClienteEstrangeiro();
                String passaporte = JOptionPane.showInputDialog("Qual é o número do seu passaporte?");
                if (passaporte == null || passaporte.trim().isEmpty()) return;
                ((ClienteEstrangeiro) cliente).passaporte = passaporte;
            }

            String nome = JOptionPane.showInputDialog("Qual é o seu nome?");
            if (nome == null || nome.trim().isEmpty()) return;
            cliente.nome = nome;

            String telefone = JOptionPane.showInputDialog("Qual é o seu telefone? Somente números.");
            if (telefone == null || !telefone.matches("\\d+")) return;
            cliente.telefone = telefone;

            String email = JOptionPane.showInputDialog("Qual é o seu email?");
            if (email == null || email.trim().isEmpty()) return;
            cliente.email = email;

            clientes.add(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else if (escolha == 1) {
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
        else if (escolha == 2) {
            buscarCliente();
        } else if (escolha == 3) {
            excluirCliente();
        }
        else if (escolha == 4) {
            visualizarPacotesCliente();
        }
    }

    private static void visualizarPacotesCliente() {
		// TODO Auto-generated method stub
    	if (clientes.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
	        return;
	    }

	    String[] nomesClientes = new String[clientes.size()];
	    for (int i = 0; i < clientes.size(); i++) {
	        nomesClientes[i] = clientes.get(i).nome;
	    }

	    String nomeSelecionado = (String) JOptionPane.showInputDialog(
	            null,
	            "Selecione o cliente:",
	            "Visualizar Pacotes Contratados",
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

	    StringBuilder pacotesCliente = new StringBuilder();
	    int contador = 1;
	    for (Reserva r : reservas) {
	        if (r.cliente.equals(clienteSelecionado)) {
	            pacotesCliente.append("Reserva ").append(contador++)
	                .append("\nDestino: ").append(r.pacote.destino)
	                .append("\nDias: ").append(r.dias)
	                .append("\nPessoas: ").append(r.quantidadePessoas)
	                .append("\nValor: R$").append(String.format("%.2f", r.valorFinal))
	                .append("\n\n");
	        }
	    }

	    if (pacotesCliente.length() == 0) {
	        JOptionPane.showMessageDialog(null, "Este cliente não possui pacotes contratados.");
	    } else {
	        JOptionPane.showMessageDialog(null, pacotesCliente.toString(), "Pacotes Contratados", JOptionPane.INFORMATION_MESSAGE);
	    }
	}


	private static void excluirCliente() {
		if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há clientes cadastrados.");
            return;
        }

        String[] nomes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            nomes[i] = clientes.get(i).nome;
        }

        String nomeSelecionado = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o cliente que deseja excluir:",
                "Excluir Cliente",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomes,
                nomes[0]
        );

        if (nomeSelecionado == null) return;

        Cliente clienteParaRemover = null;
        for (Cliente c : clientes) {
            if (c.nome.equals(nomeSelecionado)) {
                clienteParaRemover = c;
                break;
            }
        }

        if (clienteParaRemover != null) {
        	boolean temReserva = false;
        	for (Reserva r : reservas) {
        	    if (r.cliente.equals(clienteParaRemover)) {
        	        temReserva = true;
        	        break;
        	    }
        	}

        

            if (temReserva) {
                JOptionPane.showMessageDialog(null,
                        "Este cliente possui uma ou mais reservas e não pode ser excluído.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja excluir o cliente " + clienteParaRemover.nome + "?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                clientes.remove(clienteParaRemover);
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
            }
        }
		
	}

	private static void buscarCliente() {
		// TODO Auto-generated method stub
		String nomeBusca = JOptionPane.showInputDialog("Digite o nome do cliente a buscar:");
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) return;

        StringBuilder resultado = new StringBuilder();
        int contador = 1;
        for (Cliente c : clientes) {
            if (c.nome.equalsIgnoreCase(nomeBusca.trim())) {
                resultado.append("Cliente ").append(contador++)
                        .append("\nNome: ").append(c.nome)
                        .append("\nEmail: ").append(c.email);
                if (c instanceof ClienteNacional) {
                    resultado.append("\nCPF: ").append(((ClienteNacional) c).cpf);
                } else if (c instanceof ClienteEstrangeiro) {
                    resultado.append("\nPassaporte: ").append(((ClienteEstrangeiro) c).passaporte);
                }
                resultado.append("\n\n");
            }
        }

        if (resultado.length() > 0) {
            JOptionPane.showMessageDialog(null, resultado.toString(), "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
	}

	static void menuPacotes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado. Por favor, cadastre-se primeiro.");
            menuClientes();
            if (clientes.isEmpty()) return;
        }

        String[] nomesClientes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            nomesClientes[i] = clientes.get(i).nome;
        }

        String nomeSelecionado = (String) JOptionPane.showInputDialog(
                null, "Selecione seu nome:", "Identificação do Cliente",
                JOptionPane.PLAIN_MESSAGE, null, nomesClientes, nomesClientes[0]
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

        Pacote[] opcoes = pacotes.toArray(new Pacote[0]);
        Pacote selecionado = (Pacote) JOptionPane.showInputDialog(
                null, "Escolha um pacote:", "Pacotes Disponíveis",
                JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
        );

        if (selecionado == null) return;

        String[] tipos = {
            "Luxo (Aumenta o preço em 40%)",
            "Aventura (Aumenta o preço em 20%)",
            "Cultural (Aumenta o preço em 10%)",
            "Padrão (Aumenta o preço em 0%)"
        };

        int tipo = JOptionPane.showOptionDialog(
                null, "Escolha o tipo do pacote:", "Tipo do Pacote",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, tipos, tipos[0]
        );

        if (tipo == -1) return;

        int dias;
        try {
            String input = JOptionPane.showInputDialog("Quantos dias de viagem?");
            if (input == null || input.trim().isEmpty()) return;
            dias = Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Número de dias inválido.");
            return;
        }

        int quantidade;
        try {
            String input = JOptionPane.showInputDialog("Quantas pessoas irão viajar?");
            if (input == null || input.trim().isEmpty()) return;
            quantidade = Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Número de pessoas inválido.");
            return;
        }

        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");

        int refeicoes = JOptionPane.showConfirmDialog(null, "Deseja incluir refeições por pessoa? (+R$50/dia por pessoa)", "Serviços Adicionais", JOptionPane.YES_NO_OPTION);
        int translado = JOptionPane.showConfirmDialog(null, "Deseja incluir translado aeroporto-hotel por pessoa? (+R$40 por pessoa)", "Serviços Adicionais", JOptionPane.YES_NO_OPTION);

        double multiplicador = switch (tipo) {
            case 0 -> 1.40;
            case 1 -> 1.20;
            case 2 -> 1.10;
            default -> 1.0;
        };

        double valorBase = (selecionado.valorPassagem + (selecionado.valorDiaria * dias));
        double valorPorPessoa = valorBase * multiplicador;

        if (refeicoes == JOptionPane.YES_OPTION) {
            valorPorPessoa += 50 * dias;
        }
        if (translado == JOptionPane.YES_OPTION) {
            valorPorPessoa += 40;
        }

        double valorFinal = valorPorPessoa * quantidade;

        reservas.add(new Reserva(clienteSelecionado, selecionado, dias, quantidade, valorFinal));

        JOptionPane.showMessageDialog(null,
                "Resumo da Viagem:\n" +
                        "Cliente: " + clienteSelecionado.nome + "\n" +
                        "Destino: " + selecionado.destino + "\n" +
                        "Tipo: " + tipos[tipo] + "\n" +
                        "Dias: " + dias + "\n" +
                        "Pessoas: " + quantidade + "\n" +
                        "Valor Total: R$ " + String.format("%.2f", valorFinal));
    }

    static void mostrarReservas() {
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva feita.");
        } else {
            StringBuilder resumo = new StringBuilder();
            for (Reserva r : reservas) {
                resumo.append("Cliente: ").append(r.cliente.nome)
                        .append("\nDestino: ").append(r.pacote.destino)
                        .append("\nDias: ").append(r.dias)
                        .append("\nPessoas: ").append(r.quantidadePessoas)
                        .append("\nValor: R$").append(String.format("%.2f", r.valorFinal))
                        .append("\n\n");
            }
            JOptionPane.showMessageDialog(null, resumo.toString());
        }
    }
}
