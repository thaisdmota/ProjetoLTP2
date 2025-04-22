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
public Pacote(String nome, String destino, double passagem, double
diaria) {
this.nome = nome;
this.destino = destino;
this.valorPassagem = passagem;
this.valorDiaria = diaria;
}
public String toString() {
return nome + &quot; - Destino: &quot; + destino + &quot; | Passagem: R$&quot; +
valorPassagem + &quot; | Diária: R$&quot; + valorDiaria;
}
}
class Reserva {
Cliente cliente;
Pacote pacote;
int dias;
int quantidadePessoas;

double valorFinal;
public Reserva(Cliente cliente, Pacote pacote, int dias, int
quantidadePessoas, double valorFinal) {
this.cliente = cliente;
this.pacote = pacote;
this.dias = dias;
this.quantidadePessoas = quantidadePessoas;
this.valorFinal = valorFinal;
}
}
public class AgenciaViagens {
static List&lt;Cliente&gt; clientes = new ArrayList&lt;&gt;();
static List&lt;Pacote&gt; pacotes = new ArrayList&lt;&gt;();
static List&lt;Reserva&gt; reservas = new ArrayList&lt;&gt;();
public static void main(String[] args) {
pacotes.add(new Pacote(&quot;Rio de Janeiro&quot;, &quot;Rio de Janeiro-RJ&quot;,
1000.00, 500.00));
pacotes.add(new Pacote(&quot;Amazônia&quot;, &quot;Manaus-AM&quot;, 1200.00,
450.00));
pacotes.add(new Pacote(&quot;Salvador&quot;, &quot;Salvador-BA&quot;, 900.00,
400.00));
pacotes.add(new Pacote(&quot;Lençóis Maranhenses&quot;, &quot;Maranhão-MA&quot;,
1000.00, 450.00));
pacotes.add(new Pacote(&quot;São Paulo&quot;, &quot;São Paulo-SP&quot;, 950.00,
400.00));
pacotes.add(new Pacote(&quot;Brasília&quot;, &quot;Brasília-DF&quot;, 900.00,
550.00));
pacotes.add(new Pacote(&quot;Belo Horizonte&quot;, &quot;Belo Horizonte-MG&quot;,
1000.00, 500.00));
UIManager.put(&quot;OptionPane.yesButtonText&quot;, &quot;Sim&quot;);
UIManager.put(&quot;OptionPane.noButtonText&quot;, &quot;Não&quot;);
while (true) {
String[] opcoes = {&quot;Clientes&quot;, &quot;Pacotes&quot;, &quot;Reservas&quot;,
&quot;Sair&quot;};
int escolha = JOptionPane.showOptionDialog(null, &quot;Menu
Principal&quot;, &quot;Agência de Viagens&quot;,
JOptionPane.DEFAULT_OPTION,
JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

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
String[] opcoes = {&quot;Cadastrar&quot;, &quot;Visualizar&quot;, &quot;Voltar&quot;};
int escolha = JOptionPane.showOptionDialog(null, &quot;Menu
Clientes&quot;, &quot;Clientes&quot;,
JOptionPane.DEFAULT_OPTION,
JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
if (escolha == 0) {
String[] tipoCliente = {&quot;Nacional&quot;, &quot;Estrangeiro&quot;};
int tipo = JOptionPane.showOptionDialog(null, &quot;Você é
cliente nacional ou estrangeiro? &quot;, &quot;Cadastro&quot;,
JOptionPane.DEFAULT_OPTION,
JOptionPane.INFORMATION_MESSAGE, null, tipoCliente, tipoCliente[0]);
Cliente cliente;
if (tipo == 0) {
cliente = new ClienteNacional();
String cpf = JOptionPane.showInputDialog(&quot;Qual é o seu
CPF? Somente números.&quot;);
if (cpf == null || !cpf.matches(&quot;\\d+&quot;)) return;
((ClienteNacional) cliente).cpf = cpf;
} else {
cliente = new ClienteEstrangeiro();
String passaporte = JOptionPane.showInputDialog(&quot;Qual é
o número do seu passaporte?&quot;);
if (passaporte == null || passaporte.trim().isEmpty())
return;
((ClienteEstrangeiro) cliente).passaporte = passaporte;
}

String nome = JOptionPane.showInputDialog(&quot;Qual é o seu
nome?&quot;);
if (nome == null || nome.trim().isEmpty()) return;
cliente.nome = nome;
String telefone = JOptionPane.showInputDialog(&quot;Qual é o seu
telefone? Somente números.&quot;);
if (telefone == null || !telefone.matches(&quot;\\d+&quot;)) return;
cliente.telefone = telefone;
String email = JOptionPane.showInputDialog(&quot;Qual é o seu
email?&quot;);
if (email == null || email.trim().isEmpty()) return;
cliente.email = email;
clientes.add(cliente);
JOptionPane.showMessageDialog(null, &quot;Cliente cadastrado com
sucesso!&quot;);
} else if (escolha == 1) {
if (clientes.isEmpty()) {
JOptionPane.showMessageDialog(null, &quot;Nenhum cliente
cadastrado.&quot;);
} else {
StringBuilder lista = new StringBuilder();
int contador = 1;
for (Cliente c : clientes) {
lista.append(&quot;Cliente &quot; + contador + &quot;\nNome:
&quot;).append(c.nome).append(&quot;\nEmail: &quot;).append(c.email).append(&quot;\n&quot;);
if (c instanceof ClienteNacional) {
lista.append(&quot;CPF: &quot;).append(((ClienteNacional)
c).cpf).append(&quot;\n&quot;);
} else if (c instanceof ClienteEstrangeiro) {
lista.append(&quot;Passaporte:
&quot;).append(((ClienteEstrangeiro) c).passaporte).append(&quot;\n&quot;);
}
lista.append(&quot;\n&quot;);
contador++;
}
JOptionPane.showMessageDialog(null, lista.toString(),
&quot;Clientes&quot;, JOptionPane.INFORMATION_MESSAGE);
}
}
}

static void menuPacotes() {
if (clientes.isEmpty()) {
JOptionPane.showMessageDialog(null, &quot;Nenhum cliente
cadastrado. Por favor, cadastre-se primeiro.&quot;);
menuClientes();
if (clientes.isEmpty()) return;
}
String[] nomesClientes = new String[clientes.size()];
for (int i = 0; i &lt; clientes.size(); i++) {
nomesClientes[i] = clientes.get(i).nome;
}
String nomeSelecionado = (String) JOptionPane.showInputDialog(
null, &quot;Selecione seu nome:&quot;, &quot;Identificação do
Cliente&quot;,
JOptionPane.PLAIN_MESSAGE, null, nomesClientes,
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
JOptionPane.showMessageDialog(null, &quot;Cliente não
encontrado.&quot;);
return;
}
Pacote[] opcoes = pacotes.toArray(new Pacote[0]);
Pacote selecionado = (Pacote) JOptionPane.showInputDialog(
null, &quot;Escolha um pacote:&quot;, &quot;Pacotes Disponíveis&quot;,
JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]
);

if (selecionado == null) return;
String[] tipos = {
&quot;Luxo (Aumenta o preço em 40%)&quot;,
&quot;Aventura (Aumenta o preço em 20%)&quot;,
&quot;Cultural (Aumenta o preço em 10%)&quot;,
&quot;Padrão (Aumenta o preço em 0%)&quot;
};
int tipo = JOptionPane.showOptionDialog(
null, &quot;Escolha o tipo do pacote:&quot;, &quot;Tipo do Pacote&quot;,
JOptionPane.DEFAULT_OPTION,
JOptionPane.QUESTION_MESSAGE,
null, tipos, tipos[0]
);
if (tipo == -1) return;
int dias;
try {
String input = JOptionPane.showInputDialog(&quot;Quantos dias de
viagem?&quot;);
if (input == null || input.trim().isEmpty()) return;
dias = Integer.parseInt(input);
} catch (Exception e) {
JOptionPane.showMessageDialog(null, &quot;Número de dias
inválido.&quot;);
return;
}
int quantidade;
try {
String input = JOptionPane.showInputDialog(&quot;Quantas pessoas
irão viajar?&quot;);
if (input == null || input.trim().isEmpty()) return;
quantidade = Integer.parseInt(input);
} catch (Exception e) {
JOptionPane.showMessageDialog(null, &quot;Número de pessoas
inválido.&quot;);
return;
}
UIManager.put(&quot;OptionPane.yesButtonText&quot;, &quot;Sim&quot;);

UIManager.put(&quot;OptionPane.noButtonText&quot;, &quot;Não&quot;);
int refeicoes = JOptionPane.showConfirmDialog(null, &quot;Deseja
incluir refeições por pessoa? (+R$50/dia por pessoa)&quot;, &quot;Serviços
Adicionais&quot;, JOptionPane.YES_NO_OPTION);
int translado = JOptionPane.showConfirmDialog(null, &quot;Deseja
incluir translado aeroporto-hotel por pessoa? (+R$40 por pessoa)&quot;,
&quot;Serviços Adicionais&quot;, JOptionPane.YES_NO_OPTION);
double multiplicador = switch (tipo) {
case 0 -&gt; 1.40;
case 1 -&gt; 1.20;
case 2 -&gt; 1.10;
default -&gt; 1.0;
};
double valorBase = (selecionado.valorPassagem +
(selecionado.valorDiaria * dias));
double valorPorPessoa = valorBase * multiplicador;
if (refeicoes == JOptionPane.YES_OPTION) {
valorPorPessoa += 50 * dias;
}
if (translado == JOptionPane.YES_OPTION) {
valorPorPessoa += 40;
}
double valorFinal = valorPorPessoa * quantidade;
reservas.add(new Reserva(clienteSelecionado, selecionado, dias,
quantidade, valorFinal));
JOptionPane.showMessageDialog(null,
&quot;Resumo da Viagem:\n&quot; +
&quot;Cliente: &quot; + clienteSelecionado.nome + &quot;\n&quot; +
&quot;Destino: &quot; + selecionado.destino + &quot;\n&quot; +
&quot;Tipo: &quot; + tipos[tipo] + &quot;\n&quot; +
&quot;Dias: &quot; + dias + &quot;\n&quot; +
&quot;Pessoas: &quot; + quantidade + &quot;\n&quot; +
&quot;Valor Total: R$ &quot; + String.format(&quot;%.2f&quot;,
valorFinal));
}

static void mostrarReservas() {
if (reservas.isEmpty()) {
JOptionPane.showMessageDialog(null, &quot;Nenhuma reserva
feita.&quot;);
} else {
StringBuilder resumo = new StringBuilder();
for (Reserva r : reservas) {
resumo.append(&quot;Cliente: &quot;).append(r.cliente.nome)
.append(&quot;\nDestino: &quot;).append(r.pacote.destino)
.append(&quot;\nDias: &quot;).append(r.dias)
.append(&quot;\nPessoas:
&quot;).append(r.quantidadePessoas)
.append(&quot;\nValor:
R$&quot;).append(String.format(&quot;%.2f&quot;, r.valorFinal))
.append(&quot;\n\n&quot;);
}
JOptionPane.showMessageDialog(null, resumo.toString());
}
}
}
