package projeto1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

abstract class Cliente {
    String nome, telefone, email;
}

	class ClienteNacional extends Cliente {
	    String cpf;
	}
	
	class ClienteEstrangeiro extends Cliente {
	    String passaporte;
	}

abstract class Pacote {
    String nome, destino;
    double valorPassagem, valorDiaria;
    int duracaoDias;

    public Pacote(String nome, String destino, double valorPassagem, double valorDiaria, int duracaoDias) {
        this.nome = nome;
        this.destino = destino;
        this.valorPassagem = valorPassagem;
        this.valorDiaria = valorDiaria;
        this.duracaoDias = duracaoDias;
    }

    public abstract String getTipo(); 

    @Override
    public String toString() {
        return nome + " - " + destino +
               " | Tipo: " + getTipo() +
               " | Passagem: R$" + valorPassagem +
               " | Diária: R$" + valorDiaria +
               " | Duração: " + duracaoDias + " dias";
    }
}
			class PacoteLuxo extends Pacote {
			    public PacoteLuxo(String nome, String destino, double passagem, double diaria, int duracaoDias) {
			        super(nome, destino, passagem, diaria, duracaoDias);
			    }
			
			    @Override
			    public String getTipo() {
			        return "Luxo";
			    }
			}
			
			class PacoteCultural extends Pacote {
			    public PacoteCultural(String nome, String destino, double passagem, double diaria, int duracaoDias) {
			        super(nome, destino, passagem, diaria, duracaoDias);
			    }
			
			    @Override
			    public String getTipo() {
			        return "Cultural";
			    }
			}
			
			class PacoteAventura extends Pacote {
			    public PacoteAventura(String nome, String destino, double passagem, double diaria, int duracaoDias) {
			        super(nome, destino, passagem, diaria, duracaoDias);
			    }
			
			    @Override
			    public String getTipo() {
			        return "Aventura";
			    }
			}
			
			class PacotePadrao extends Pacote {
			    public PacotePadrao(String nome, String destino, double passagem, double diaria, int duracaoDias) {
			        super(nome, destino, passagem, diaria, duracaoDias);
			    }
			
			    @Override
			    public String getTipo() {
			        return "Padrão";
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

abstract class ServicoAdicional {
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

		class ServicoTranslado extends ServicoAdicional {
		    public ServicoTranslado() {
		        super("Translado aeroporto-hotel", 150.0);
		    }
		}
		
		class ServicoAcessoAcademia extends ServicoAdicional {
		    public ServicoAcessoAcademia() {
		        super("Acesso à Academia", 350.0);
		    }
		}
		
		class ServicoPersonalizado extends ServicoAdicional {
		    public ServicoPersonalizado(String nome, double preco) {
		        super(nome, preco);
		    }
		}

public class AgenciaViagens {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Pacote> pacotes = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static List<ServicoAdicional> servicosAdicionais = new ArrayList<>();
    static List<String> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
    	// pacotes pré definidos.
    	pacotes.add(new PacoteLuxo("Rio de Janeiro", "RJ", 5500, 6000, 5));
    	pacotes.add(new PacoteAventura("Amazônia", "AM", 1200, 450, 4));
    	pacotes.add(new PacoteCultural("Salvador", "BA", 900, 400, 6));
    	pacotes.add(new PacoteAventura("Lençóis Maranhenses", "MA", 1000, 450, 7));
    	pacotes.add(new PacotePadrao("São Paulo", "SP", 950, 400, 3));
    	pacotes.add(new PacoteCultural("Brasília", "DF", 900, 550, 4));
    	pacotes.add(new PacoteLuxo("Belo Horizonte", "MG", 4200, 5200, 5));


        funcionarios.add("admin:1234");
        if (!loginFuncionario()) return;
        
        servicosAdicionais.add(new ServicoTranslado());
        servicosAdicionais.add(new ServicoAcessoAcademia());



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
    	String[] opcoes = {"Visualizar", "Buscar", "Excluir", "Adicionar", "Reservar", "Detalhes dos Tipos", "Voltar"};
    	int escolha = JOptionPane.showOptionDialog(null, "Menu Pacotes", "Pacotes",
    	        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

    	switch (escolha) {
    	    case 0 -> visualizarPacotes();
    	    case 1 -> buscarPacote();
    	    case 2 -> excluirPacote();
    	    case 3 -> adicionarPacote();
    	    case 4 -> fazerReserva();
    	    case 5 -> tipoDetalhes(); 
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
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome obrigatório.");
            return;
        }

        String precoStr = JOptionPane.showInputDialog("Preço do serviço:");
        double preco;
        try {
            preco = Double.parseDouble(precoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Preço inválido.");
            return;
        }

        servicosAdicionais.add(new ServicoPersonalizado(nome, preco));
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
            String cpf;
            do {
                cpf = JOptionPane.showInputDialog("CPF (somente números):");
                if (cpf == null) return; 
                if (!cpf.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(null, "CPF inválido!");
                    cpf = null;
                }
            } while (cpf == null);
            ((ClienteNacional) cliente).cpf = cpf;

        } else { 
            cliente = new ClienteEstrangeiro();
            String passaporte;
            do {
                passaporte = JOptionPane.showInputDialog("Passaporte:");
                if (passaporte == null) return; // Cancelado
                if (passaporte.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O número do passaporte é obrigatório.");
                    passaporte = null;
                }
            } while (passaporte == null);
            ((ClienteEstrangeiro) cliente).passaporte = passaporte;
        }

        String nome = JOptionPane.showInputDialog("Nome:");
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome é obrigatório.");
            return;
        }

        String telefone = JOptionPane.showInputDialog("Telefone:");
        if (telefone == null || telefone.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Telefone é obrigatório.");
            return;
        }

        String email = JOptionPane.showInputDialog("Email:");
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email é obrigatório.");
            return;
        }

        cliente.nome = nome;
        cliente.telefone = telefone;
        cliente.email = email;

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
            lista.append(i++)
                 .append(". ")
                 .append(p.nome).append(" - ").append(p.destino)
                 .append(" | Tipo: ").append(p.getTipo())
                 .append(" | Passagem: R$").append(p.valorPassagem)
                 .append(" | Diária: R$").append(p.valorDiaria)
                 .append(" | Duração: ").append(p.duracaoDias).append(" dias")
                 .append("\n");
        }

        JOptionPane.showMessageDialog(null, lista.toString());
    }


    
    static void tipoDetalhes() {
        String detalhes = """
            Tipos de Pacotes:

            Luxo:
            - Hospedagem 5 estrelas.
            - Refeições gourmet.
            - Guias particulares.

            Cultural:
            - Visitas a museus e monumentos.
            - Aulas e workshops culturais.
            - Refeições culturais.

            Aventura:
            - Trilhas, esportes radicais e passeios ecológicos.
            - Equipamentos de segurança.
            - Guias de aventura.

            Padrão:
            - Apenas a passagem e hospedagem.
            - Inclui apenas o serviços adicionais que o cliente escolher.
            """;
        JOptionPane.showMessageDialog(null, detalhes, "Detalhes dos Tipos de Pacote", JOptionPane.INFORMATION_MESSAGE);
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
    	if (pacotes.isEmpty()) {
    	    JOptionPane.showMessageDialog(null, "Nenhum pacote disponível no momento.");
    	    return;
    	}

    	String[] nomes = pacotes.stream()
    	    .map(p -> p.nome + " (" + p.getTipo() + ")")
    	    .toArray(String[]::new);

    	String nomeSelecionado = (String) JOptionPane.showInputDialog(null, "Selecione o pacote:", "Excluir Pacote", JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);

    	Pacote p = pacotes.stream()
    	    .filter(pa -> (pa.nome + " (" + pa.getTipo() + ")").equals(nomeSelecionado))
    	    .findFirst().orElse(null);

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
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome do pacote é obrigatório.");
            return;
        }

        String destino = JOptionPane.showInputDialog("Destino:");
        if (destino == null || destino.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Destino do pacote é obrigatório.");
            return;
        }
        
        

        String passagemStr = JOptionPane.showInputDialog("Valor da passagem:");
        if (passagemStr == null || passagemStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Valor da passagem é obrigatório.");
            return;
        }
        
        

        String diariaStr = JOptionPane.showInputDialog("Valor da diária por pessoa:");
        if (diariaStr == null || diariaStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Valor da diária é obrigatório.");
            return;
        }
        
        String duracaoStr = JOptionPane.showInputDialog("Duração da viagem (em dias):");
        if (duracaoStr == null || duracaoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Duração da viagem é obrigatória.");
            return;
        }

        int duracaoDias;
        try {
            duracaoDias = Integer.parseInt(duracaoStr);
            if (duracaoDias <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Duração inválida.");
            return;
        }

        double passagem, diaria;
        try {
            passagem = Double.parseDouble(passagemStr);
            diaria = Double.parseDouble(diariaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valores inválidos para passagem ou diária.");
            return;
        }

        String[] tipos = {"Luxo", "Cultural", "Aventura", "Padrão"};
        String tipo = (String) JOptionPane.showInputDialog(null, "Tipo do pacote:", "Tipo",
                JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

        if (tipo == null || tipo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tipo do pacote é obrigatório.");
            return;
        }
        
        Pacote novoPacote;
        switch (tipo) {
            case "Luxo" -> novoPacote = new PacoteLuxo(nome, destino, passagem, diaria, duracaoDias);
            case "Cultural" -> novoPacote = new PacoteCultural(nome, destino, passagem, diaria, duracaoDias);
            case "Aventura" -> novoPacote = new PacoteAventura(nome, destino, passagem, diaria, duracaoDias);
            default -> novoPacote = new PacotePadrao(nome, destino, passagem, diaria, duracaoDias);
        }
        pacotes.add(novoPacote);

        JOptionPane.showMessageDialog(null, "Pacote adicionado com sucesso!");
    }


    static void fazerReserva() {
    	if (clientes.isEmpty()) {
    	    JOptionPane.showMessageDialog(null, "Cadastre um cliente primeiro!");
    	    return;
    	}
    	if (pacotes.isEmpty()) {
    	    JOptionPane.showMessageDialog(null, "Nenhum pacote disponível no momento.");
    	    return;
    	}

        String[] nomesClientes = clientes.stream().map(c -> c.nome).toArray(String[]::new);
        String nome = (String) JOptionPane.showInputDialog(null, "Cliente:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, nomesClientes, nomesClientes[0]);
        Cliente c = clientes.stream().filter(cl -> cl.nome.equals(nome)).findFirst().orElse(null);
        if (c == null) return;
        String[] nomesPacotes = pacotes.stream()
        	    .map(pac -> pac.nome + " (" + pac.getTipo() + ")")
        	    .toArray(String[]::new);


        String nomePacoteSelecionado = (String) JOptionPane.showInputDialog(
                null,
                "Pacote:",
                "Reserva",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesPacotes,
                nomesPacotes[0]
        );

        Pacote p = pacotes.stream()
        	    .filter(pa -> (pa.nome + " (" + pa.getTipo() + ")").equals(nomePacoteSelecionado))
        	    .findFirst()
        	    .orElse(null);


        if (p == null) return;

        
        int qtdPessoas;
        try {
            qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Quantas pessoas?"));
            if (qtdPessoas <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade de pessoas inválida.");
            return;
        }

        List<ServicoAdicional> servicosEscolhidos = new ArrayList<>();
        int continuar = JOptionPane.YES_OPTION;
        while (continuar == JOptionPane.YES_OPTION) {
            String[] servicos = servicosAdicionais.stream().map(s -> s.nome).toArray(String[]::new);
            String servicoEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha um serviço adicional:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, servicos, servicos[0]);
            ServicoAdicional servico = servicosAdicionais.stream().filter(s -> s.nome.equals(servicoEscolhido)).findFirst().orElse(null);
            
            if (servico != null) {
                if (servicosEscolhidos.contains(servico)) {
                    JOptionPane.showMessageDialog(null, "Este serviço já foi adicionado.");
                } else {
                    servicosEscolhidos.add(servico);
                }
            }
            continuar = JOptionPane.showConfirmDialog(null, "Adicionar mais serviços?", "Reserva", JOptionPane.YES_NO_OPTION);
        }

        double valorFinal = (p.valorPassagem * qtdPessoas) + (p.valorDiaria * p.duracaoDias * qtdPessoas);


        for (ServicoAdicional sa : servicosEscolhidos) {        	
            valorFinal += sa.preco * qtdPessoas;
        }

        reservas.add(new Reserva(c, p, p.duracaoDias, qtdPessoas, valorFinal, servicosEscolhidos));

        JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
    }
}
