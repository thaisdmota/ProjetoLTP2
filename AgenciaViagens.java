package projeto1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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

        funcionarios.add("admin:1234");
        if (!loginFuncionario()) return;

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

        ServicoAdicional novoServico = new ServicoPersonalizado(nome, preco);
        servicosAdicionais.add(novoServico);

        // Inserir no banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO servicos_adicionais (nome, preco, tipo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setDouble(2, preco);
                stmt.setString(3, "Personalizado");
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso!");
    }


    static void listarServicos() {
        StringBuilder lista = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT nome, preco FROM servicos_adicionais";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    lista.append(nome).append(" - R$").append(preco).append("\n");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar serviços: " + e.getMessage());
            return;
        }

        if (lista.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum serviço adicional disponível.");
        } else {
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }


    static void buscarServico() {
        String nomeBusca = JOptionPane.showInputDialog("Nome do serviço:");
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) return;

        StringBuilder res = new StringBuilder();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT nome, preco FROM servicos_adicionais WHERE LOWER(nome) LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + nomeBusca.toLowerCase() + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        res.append(rs.getString("nome"))
                           .append(" - R$")
                           .append(rs.getDouble("preco"))
                           .append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar serviço: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Serviço não encontrado.");
    }


    static void excluirServico() {
        List<String> nomesServicos = new ArrayList<>();

        // 1. Carrega nomes diretamente do banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT nome FROM servicos_adicionais";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    nomesServicos.add(rs.getString("nome"));
                }
            }

            if (nomesServicos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum serviço adicional disponível.");
                return;
            }

            String nomeSelecionado = (String) JOptionPane.showInputDialog(
                    null, "Selecione o serviço:", "Excluir Serviço",
                    JOptionPane.PLAIN_MESSAGE, null,
                    nomesServicos.toArray(), nomesServicos.get(0)
            );

            if (nomeSelecionado == null) return;

            // 2. Verificar vínculo com reservas
            String verificar = "SELECT COUNT(*) FROM reserva_servicos rs " +
                               "JOIN servicos_adicionais sa ON rs.servico_id = sa.id WHERE sa.nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(verificar)) {
                stmt.setString(1, nomeSelecionado);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Este serviço está vinculado a uma reserva e não pode ser excluído.");
                        return;
                    }
                }
            }

            // 3. Excluir do banco
            String excluir = "DELETE FROM servicos_adicionais WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(excluir)) {
                stmt.setString(1, nomeSelecionado);
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir serviço: " + e.getMessage());
        }
    }
    static void mostrarReservas() {
        StringBuilder lista = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = """
                SELECT r.id, c.nome AS cliente_nome, p.nome AS pacote_nome, r.valor_final
                FROM reservas r
                JOIN clientes c ON r.cliente_id = c.id
                JOIN pacotes p ON r.pacote_id = p.id
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                int i = 1;
                while (rs.next()) {
                    lista.append("Reserva ").append(i++)
                          .append("\nCliente: ").append(rs.getString("cliente_nome"))
                          .append("\nPacote: ").append(rs.getString("pacote_nome"))
                          .append("\nValor: R$").append(rs.getDouble("valor_final"))
                          .append("\n\n");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar reservas: " + e.getMessage());
            return;
        }

        if (lista.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma reserva realizada.");
        } else {
            JOptionPane.showMessageDialog(null, lista.toString());
        }
    }

    static void cadastrarCliente() {
        String[] tipoCliente = {"Nacional", "Estrangeiro"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de cliente:", "Cadastro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);

        if (tipo == JOptionPane.CLOSED_OPTION) return;

        String tipoStr = tipo == 0 ? "NACIONAL" : "ESTRANGEIRO";
        String documento = null;

        if (tipo == 0) {
            do {
                documento = JOptionPane.showInputDialog("CPF (somente números):");
                if (documento == null) return;
                if (!documento.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(null, "CPF inválido!");
                    documento = null;
                }
            } while (documento == null);
        } else {
            do {
                documento = JOptionPane.showInputDialog("Passaporte:");
                if (documento == null) return;
                if (documento.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Passaporte obrigatório.");
                    documento = null;
                }
            } while (documento == null);
        }

        String nome = JOptionPane.showInputDialog("Nome:");
        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome é obrigatório.");
            return;
        }

        String telefone = JOptionPane.showInputDialog("Telefone (somente números):");
        if (telefone == null || !telefone.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Telefone inválido.");
            return;
        }

        String email = JOptionPane.showInputDialog("Email:");
        if (email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email é obrigatório.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO clientes (tipo, nome, telefone, email, cpf, passaporte) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipoStr);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            if (tipo == 0) {
                stmt.setString(5, documento); // CPF
                stmt.setNull(6, java.sql.Types.VARCHAR); // Passaporte
            } else {
                stmt.setNull(5, java.sql.Types.VARCHAR); // CPF
                stmt.setString(6, documento); // Passaporte
            }

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void visualizarClientes() {
        StringBuilder lista = new StringBuilder();
        int i = 1;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM clientes";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String tipo = rs.getString("tipo");
                String cpf = rs.getString("cpf");
                String passaporte = rs.getString("passaporte");

                lista.append("Cliente ").append(i++).append("\nNome: ").append(nome)
                        .append("\nEmail: ").append(email);

                if ("NACIONAL".equalsIgnoreCase(tipo)) {
                    lista.append("\nCPF: ").append(cpf);
                } else if ("ESTRANGEIRO".equalsIgnoreCase(tipo)) {
                    lista.append("\nPassaporte: ").append(passaporte);
                }
                lista.append("\n\n");
            }

            if (lista.length() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            } else {
                JOptionPane.showMessageDialog(null, lista.toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }


    static void buscarCliente() {
        String nomeBusca = JOptionPane.showInputDialog("Nome do cliente:");
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) return;

        StringBuilder res = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM clientes WHERE LOWER(nome) LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + nomeBusca.toLowerCase() + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        res.append("Nome: ").append(rs.getString("nome"))
                           .append("\nEmail: ").append(rs.getString("email"))
                           .append("\nTelefone: ").append(rs.getString("telefone"));

                        String tipo = rs.getString("tipo");
                        if (tipo.equals("NACIONAL")) {
                            res.append("\nCPF: ").append(rs.getString("cpf"));
                        } else if (tipo.equals("ESTRANGEIRO")) {
                            res.append("\nPassaporte: ").append(rs.getString("passaporte"));
                        }

                        res.append("\n\n");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Cliente não encontrado.");
    }


    static void excluirCliente() {
        List<String> nomesClientes = new ArrayList<>();

        // 1. Pegar todos os nomes direto do banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT nome FROM clientes";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    nomesClientes.add(rs.getString("nome"));
                }
            }

            if (nomesClientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum cliente disponível.");
                return;
            }

            String nomeSelecionado = (String) JOptionPane.showInputDialog(
                    null, "Selecione o cliente:", "Excluir Cliente",
                    JOptionPane.PLAIN_MESSAGE, null,
                    nomesClientes.toArray(), nomesClientes.get(0)
            );

            if (nomeSelecionado == null) return;

            // 2. Verificar se tem reserva
            String verificar = "SELECT COUNT(*) FROM reservas r JOIN clientes c ON r.cliente_id = c.id WHERE c.nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(verificar)) {
                stmt.setString(1, nomeSelecionado);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Este cliente tem reservas e não pode ser excluído.");
                        return;
                    }
                }
            }

            // 3. Excluir
            String excluir = "DELETE FROM clientes WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(excluir)) {
                stmt.setString(1, nomeSelecionado);
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage());
        }
    }


    static void visualizarPacotesCliente() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) return;

        StringBuilder pacotesCliente = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {

            // Buscar ID do cliente pelo nome
            String sqlCliente = "SELECT id FROM clientes WHERE nome = ?";
            int clienteId;

            try (PreparedStatement stmt = conn.prepareStatement(sqlCliente)) {
                stmt.setString(1, nomeCliente);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        clienteId = rs.getInt("id");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                        return;
                    }
                }
            }

            // Buscar reservas do cliente
            String sqlReservas = """
                SELECT p.nome AS pacote_nome, r.valor_final
                FROM reservas r
                JOIN pacotes p ON r.pacote_id = p.id
                WHERE r.cliente_id = ?
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sqlReservas)) {
                stmt.setInt(1, clienteId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        pacotesCliente.append("Pacote: ")
                                .append(rs.getString("pacote_nome"))
                                .append("\nValor: R$")
                                .append(rs.getDouble("valor_final"))
                                .append("\n\n");
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pacotes do cliente: " + e.getMessage());
            return;
        }

        if (pacotesCliente.length() == 0) {
            JOptionPane.showMessageDialog(null, "Este cliente não possui pacotes contratados.");
        } else {
            JOptionPane.showMessageDialog(null, pacotesCliente.toString());
        }
    }


    static void visualizarPacotes() {
        StringBuilder lista = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT nome, destino, tipo, valor_passagem, valor_diaria, duracao_dias FROM pacotes";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                int i = 1;
                while (rs.next()) {
                    lista.append(i++)
                         .append(". ")
                         .append(rs.getString("nome")).append(" - ").append(rs.getString("destino"))
                         .append(" | Tipo: ").append(rs.getString("tipo"))
                         .append(" | Passagem: R$").append(rs.getDouble("valor_passagem"))
                         .append(" | Diária: R$").append(rs.getDouble("valor_diaria"))
                         .append(" | Duração: ").append(rs.getInt("duracao_dias")).append(" dias")
                         .append("\n");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao visualizar pacotes: " + e.getMessage());
            return;
        }

        if (lista.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum pacote disponível.");
        } else {
            JOptionPane.showMessageDialog(null, lista.toString());
        }
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

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sqlPacote = "SELECT id, nome, destino, tipo, valor_passagem, valor_diaria, duracao_dias FROM pacotes WHERE LOWER(nome) LIKE ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlPacote)) {
                stmt.setString(1, "%" + nomePacote.toLowerCase() + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");

                        res.append("Pacote: ").append(rs.getString("nome"))
                           .append(" - ").append(rs.getString("destino"))
                           .append("\nTipo: ").append(rs.getString("tipo"))
                           .append("\nPassagem: R$").append(rs.getDouble("valor_passagem"))
                           .append(" | Diária: R$").append(rs.getDouble("valor_diaria"))
                           .append(" | Duração: ").append(rs.getInt("duracao_dias")).append(" dias\n");

                        // Buscar clientes que contrataram esse pacote
                        String sqlClientes = "SELECT c.nome FROM reservas r " +
                                             "JOIN clientes c ON r.cliente_id = c.id " +
                                             "WHERE r.pacote_id = ?";
                        try (PreparedStatement stmtClientes = conn.prepareStatement(sqlClientes)) {
                            stmtClientes.setInt(1, id);
                            try (ResultSet rsClientes = stmtClientes.executeQuery()) {
                                boolean temClientes = false;
                                res.append("Clientes que contrataram este pacote:\n");
                                while (rsClientes.next()) {
                                    res.append("- ").append(rsClientes.getString("nome")).append("\n");
                                    temClientes = true;
                                }
                                if (!temClientes) {
                                    res.append("Nenhum cliente contratou este pacote.\n");
                                }
                            }
                        }

                        res.append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar pacote: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(null, res.length() > 0 ? res.toString() : "Pacote não encontrado.");
    }



    static void excluirPacote() {
        List<String> nomesPacotes = new ArrayList<>();
        List<Integer> idsPacotes = new ArrayList<>();

        // Carrega os pacotes do banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nome, tipo FROM pacotes";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String tipo = rs.getString("tipo");
                    nomesPacotes.add(nome + " (" + tipo + ")");
                    idsPacotes.add(id);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar pacotes: " + e.getMessage());
            return;
        }

        if (nomesPacotes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pacote disponível no momento.");
            return;
        }

        String nomeSelecionado = (String) JOptionPane.showInputDialog(
                null, "Selecione o pacote:", "Excluir Pacote",
                JOptionPane.PLAIN_MESSAGE, null,
                nomesPacotes.toArray(new String[0]), nomesPacotes.get(0));

        if (nomeSelecionado == null) return;

        int indexSelecionado = nomesPacotes.indexOf(nomeSelecionado);
        int idSelecionado = idsPacotes.get(indexSelecionado);

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Verifica se o pacote está em alguma reserva
            String sqlVerifica = "SELECT COUNT(*) FROM reservas WHERE pacote_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlVerifica)) {
                stmt.setInt(1, idSelecionado);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Este pacote possui reservas e não pode ser excluído.");
                        return;
                    }
                }
            }

            // Exclui o pacote
            String sqlDelete = "DELETE FROM pacotes WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                stmt.setInt(1, idSelecionado);
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Pacote removido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pacote: " + e.getMessage());
        }
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
        String diariaStr = JOptionPane.showInputDialog("Valor da diária por pessoa:");
        String duracaoStr = JOptionPane.showInputDialog("Duração da viagem (em dias):");

        double passagem, diaria;
        int duracaoDias;
        try {
            passagem = Double.parseDouble(passagemStr);
            diaria = Double.parseDouble(diariaStr);
            duracaoDias = Integer.parseInt(duracaoStr);
            if (passagem < 0 || diaria < 0 || duracaoDias <= 0) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valores inválidos para passagem, diária ou duração.");
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

        // Conecta ao banco e insere o pacote
        String sql = "INSERT INTO pacotes (nome, destino, tipo, valor_passagem, valor_diaria, duracao_dias) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, destino);
            stmt.setString(3, tipo.toUpperCase()); // Compatível com ENUM no banco
            stmt.setDouble(4, passagem);
            stmt.setDouble(5, diaria);
            stmt.setInt(6, duracaoDias);
            stmt.executeUpdate();

            // Também adiciona na lista local (opcional, para uso atual no programa)
            pacotes.add(novoPacote);

            JOptionPane.showMessageDialog(null, "Pacote adicionado com sucesso ao banco de dados!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + e.getMessage());
        }
    }



    static void fazerReserva() {
        // Obter clientes do banco
        List<String> nomesClientes = new ArrayList<>();
        List<Integer> idsClientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nome FROM clientes";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    idsClientes.add(rs.getInt("id"));
                    nomesClientes.add(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar clientes: " + e.getMessage());
            return;
        }

        if (nomesClientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastre um cliente primeiro!");
            return;
        }

        // Obter pacotes do banco
        List<String> nomesPacotes = new ArrayList<>();
        List<Integer> idsPacotes = new ArrayList<>();
        List<Integer> duracoes = new ArrayList<>();
        List<Double> valoresPassagem = new ArrayList<>();
        List<Double> valoresDiaria = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nome, tipo, valor_passagem, valor_diaria, duracao_dias FROM pacotes";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String tipo = rs.getString("tipo");
                    nomesPacotes.add(nome + " (" + tipo + ")");
                    idsPacotes.add(id);
                    valoresPassagem.add(rs.getDouble("valor_passagem"));
                    valoresDiaria.add(rs.getDouble("valor_diaria"));
                    duracoes.add(rs.getInt("duracao_dias"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar pacotes: " + e.getMessage());
            return;
        }

        if (nomesPacotes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pacote disponível.");
            return;
        }

        String nomeCliente = (String) JOptionPane.showInputDialog(null, "Cliente:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, nomesClientes.toArray(), nomesClientes.get(0));
        if (nomeCliente == null) return;
        int indexCliente = nomesClientes.indexOf(nomeCliente);
        int clienteId = idsClientes.get(indexCliente);

        String nomePacote = (String) JOptionPane.showInputDialog(null, "Pacote:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, nomesPacotes.toArray(), nomesPacotes.get(0));
        if (nomePacote == null) return;
        int indexPacote = nomesPacotes.indexOf(nomePacote);
        int pacoteId = idsPacotes.get(indexPacote);
        int duracaoDias = duracoes.get(indexPacote);
        double valorPassagem = valoresPassagem.get(indexPacote);
        double valorDiaria = valoresDiaria.get(indexPacote);

        int qtdPessoas;
        try {
            qtdPessoas = Integer.parseInt(JOptionPane.showInputDialog("Quantas pessoas?"));
            if (qtdPessoas <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade de pessoas inválida.");
            return;
        }

        // Obter serviços do banco
        List<String> nomesServicos = new ArrayList<>();
        List<Integer> idsServicos = new ArrayList<>();
        List<Double> precosServicos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, nome, preco FROM servicos_adicionais";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    idsServicos.add(rs.getInt("id"));
                    nomesServicos.add(rs.getString("nome"));
                    precosServicos.add(rs.getDouble("preco"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar serviços adicionais: " + e.getMessage());
            return;
        }

        List<Integer> servicosEscolhidos = new ArrayList<>();
        double totalServicos = 0;
        int continuar = JOptionPane.YES_OPTION;
        while (continuar == JOptionPane.YES_OPTION) {
            String servicoSelecionado = (String) JOptionPane.showInputDialog(null, "Escolha um serviço adicional:", "Reserva", JOptionPane.PLAIN_MESSAGE, null, nomesServicos.toArray(), nomesServicos.get(0));
            if (servicoSelecionado == null) break;
            int index = nomesServicos.indexOf(servicoSelecionado);
            int servicoId = idsServicos.get(index);
            if (servicosEscolhidos.contains(servicoId)) {
                JOptionPane.showMessageDialog(null, "Este serviço já foi adicionado.");
            } else {
                servicosEscolhidos.add(servicoId);
                totalServicos += precosServicos.get(index) * qtdPessoas;
            }
            continuar = JOptionPane.showConfirmDialog(null, "Adicionar mais serviços?", "Reserva", JOptionPane.YES_NO_OPTION);
        }

        double valorFinal = (valorPassagem * qtdPessoas) + (valorDiaria * duracaoDias * qtdPessoas) + totalServicos;

        // Inserir reserva no banco
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Garantir transação

            // Inserir na tabela reservas
            String inserirReserva = "INSERT INTO reservas (cliente_id, pacote_id, dias, quantidade_pessoas, valor_final) VALUES (?, ?, ?, ?, ?)";
            int reservaId;
            try (PreparedStatement stmt = conn.prepareStatement(inserirReserva, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, clienteId);
                stmt.setInt(2, pacoteId);
                stmt.setInt(3, duracaoDias);
                stmt.setInt(4, qtdPessoas);
                stmt.setDouble(5, valorFinal);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    reservaId = rs.getInt(1);
                } else {
                    throw new SQLException("Erro ao obter ID da reserva.");
                }
            }

            // Inserir serviços da reserva
            String inserirServicos = "INSERT INTO reserva_servicos (reserva_id, servico_id) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(inserirServicos)) {
                for (int servicoId : servicosEscolhidos) {
                    stmt.setInt(1, reservaId);
                    stmt.setInt(2, servicoId);
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            conn.commit();
            JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar reserva: " + e.getMessage());
        }
    }

}
