import javax.swing.JOptionPane;

public class PizzariaMain {
    private static int pedidoCounter = 1;
    private static Pilha pilhaEntregas = new Pilha(120);
    private static Fila filaPreparo = new Fila(120);
    private static ListaEncadeada listaPedidos = new ListaEncadeada();
    private static ListaEncadeada pedidosEntregues = new ListaEncadeada();

    /* Acima nós inicializamos a pilha e fila definindo tamanhos limite, iniciamos duas listas encadeadas
    (uma para os pedidos gerais e outra para os pedidos entregues) e um contador de pedidos. */ 

    public static void main(String[] args) {

        // Decidimos adicionar esses 3 pedidos iniciais para facilitar os testes do sistema.

        adicionarPedidoInicial("Frango", "Grande", "Rua Playstation, 2222", 5);
        adicionarPedidoInicial("Pepperoni", "Comum", "Rua Sonic Mario, 1000", 3);
        adicionarPedidoInicial("Quatro Queijos", "Broto", "Rua Castlevania, 780", 7);

        /* Aqui nós fizemos um loop de escolha com a opção (op) e o switch case para o usuário 
         * selecionar o que ele que fazer no menu. Achamos bom também por uma condição que encerra o 
         * programa caso o usuário aperte no cancelar ou aperte esc vendo se o que está escrito
         * é nulo e uma condição if que verifica se o que ele digitou é vazio ou não (e que corta os 
         * espaços com trim antes), nesse caso fazendo ele continuar no menu sem acontecer nada. 
         * Também limitamos a área de número que ele pode digitar, para tratar o erro caso ele digite 
         * um número fora do alcance dos cases.
        */
        int op = 0; 
        do {
            String input = JOptionPane.showInputDialog(
                    "Menu:\n" +
                            "1 - Adicionar novo pedido\n" +
                            "2 - Cancelar pedido\n" +
                            "3 - Listar todos os pedidos\n" +
                            "4 - Incluir pedidos para preparo\n" +
                            "5 - Listar pedidos em preparo\n" +
                            "6 - Incluir pedidos para entrega\n" +
                            "7 - Listar pedidos prontos para entrega\n" +
                            "8 - Informar entrega realizada\n" +
                            "9 - Listar pedidos entregues\n" +
                            "10 - Sair"
            );
    
            if (input == null) {
                break; 
            }
    
            if (input.trim().isEmpty()) {
                continue;
            }
    
            op = Integer.parseInt(input);
    
            if (op < 1 || op > 10) {
                JOptionPane.showMessageDialog(null, "Opção inválida");
                continue; // Continua no menu se a opção digitada for inválida
            }
    
            switch (op) {
                case 1:
                    adicionarPedido();
                    break;
                case 2:
                    cancelarPedido();
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    incluirParaPreparo();
                    break;
                case 5:
                    listarPedidosPreparo();
                    break;
                case 6:
                    incluirParaEntrega();
                    break;
                case 7:
                    listarPedidosProntosParaEntrega();
                    break;
                case 8:
                    informarEntregaRealizada();
                    break;
                case 9:
                    listarPedidosEntregues();
                    break;
                case 10:
                    break;
            }
        } while (op != 10);
    }

    /* Abaixo definimos arrays para sabores e tamanhos, para limitar e formatar a entrada fornecida pelo 
    usuário, com uma caixa de seleção para ambos e uma verificação para caso for nulo (ou seja, caso
    o usuário apertar em cancelar em qualquer momento, ele volte para o menu inicial. Também fizemos
    a partede do endereço e distância da entrega do pedido, mas dessa vez pedindo para o usuário
    digitar, com tratamento de tamanho e tipo da distância. Ao final, é criado um novo objeto com o
    construtor Pedido, cria um novo nó na lista encadeada geral, adicionando no fim da lista o novo
    pedido.*/

    private static void adicionarPedido() {
        String[] sabores = {"Frango", "Calabresa", "Portuguesa", "Milho", "Pepperoni", "Quatro Queijos"};
        String sabor = (String) JOptionPane.showInputDialog(null, "Selecione o sabor da pizza:", 
                "Sabor da Pizza", JOptionPane.QUESTION_MESSAGE, null, sabores, sabores[0]);
        if (sabor == null) return;
    
        String[] tamanhos = {"Broto", "Comum", "Grande"};
        String tamanho = (String) JOptionPane.showInputDialog(null, "Selecione o tamanho da pizza:", 
                "Tamanho da Pizza", JOptionPane.QUESTION_MESSAGE, null, tamanhos, tamanhos[0]);
        if (tamanho == null) return;
    
        String endereco;
        while (true) {
            endereco = JOptionPane.showInputDialog("Informe o endereço de entrega:");
            if (endereco == null || !endereco.trim().isEmpty()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, digite o endereço de entrega.");
            }
        }
        if (endereco == null) return;
        
        int distancia;
        while (true) {
            String distanciaStr = JOptionPane.showInputDialog("Informe a distância em km (apenas números de dois dígitos):");
            if (distanciaStr == null) return;
            try {
                distancia = Integer.parseInt(distanciaStr);
                if (distancia >= 1 && distancia <= 99) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Distância deve ser um número de dois dígitos.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número de dois dígitos.");
            }
        }
    
        Pedido novoPedido = new Pedido(pedidoCounter++, sabor, tamanho, endereco, distancia);
        listaPedidos.insereNo_fim(new NoSimples(novoPedido));
        JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!\nNúmero do pedido: " + (pedidoCounter - 1));
    }
    
    /* Método que fizemos para criar um novo pedido logo ao iniciar o sistema, para facilitar nossos testes,
    como dito anteriormente lá em cima.
    */

    private static void adicionarPedidoInicial(String sabor, String tamanho, String endereco, int distancia) {
        Pedido novoPedido = new Pedido(pedidoCounter++, sabor, tamanho, endereco, distancia);
        listaPedidos.insereNo_fim(new NoSimples(novoPedido));
    }

    /* Método de cancelar pedido, que cancela um pedido registrado na lista geral. Tem verificações
     * para caso seja nulo ou sem espaços e vazio, fazendo voltar para o menu, e para caso já esteja
     * na lista de preparo ou qualquer parte posterior no sistema.
    */

    private static void cancelarPedido() {
        String input = JOptionPane.showInputDialog("Informe o código do pedido a ser cancelado:");
        if (input == null || input.trim().isEmpty()) {
            return;
        }
    
        int codigo = Integer.parseInt(input);
        NoSimples pedidoNo = listaPedidos.buscarNo(codigo);
    
        if (pedidoNo != null) {
            Pedido pedido = (Pedido) pedidoNo.getConteudo();
    
            if (!pedido.isInPreparo() && !filaPreparo.contemPedido(codigo)) {
                listaPedidos.removePorCodigo(codigo);
                filaPreparo.removePorCodigo(codigo); 
                JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Este pedido não pode ser cancelado, pois já está em preparo ou em processos adiante!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
        }
    }

    /* FUNCIONALIDADE EXTRA - Optamos por criar um método que calcule o frete das entregas, para
     * calcular o lucro que teremos com as atividades de delivery da pizzaria. Nesse método, estamos
     * criando umas condições if's para verificar a distância e denifir o conteúdo da variável frete,
     * contendo o preço do frete.
     */

    private static double calcularFrete(int distancia) {
        double frete = 0;
        
        if (distancia >= 0 && distancia <= 5) {
            frete = 8;
        } else if (distancia >= 6 && distancia <= 9) {
            frete = 15;
        } else if (distancia >= 10 && distancia <= 15) {
            frete = 25;
        } else if (distancia >= 16 && distancia <= 30) {
            frete = 60;
        } else if (distancia >= 31 && distancia <= 45) {
            frete = 90;
        } else if (distancia >= 46 && distancia <= 60) {
            frete = 130;
        } else if (distancia >= 61 && distancia <= 75) {
            frete = 150;
        } else if (distancia > 76) {
            frete = 180;
        }
        return frete;
    }
    
    /* Aqui formamos uma lista com um histórico de todos os pedidos já feitos, formada com
    a instância listaPedidos e o método exibeLista() da classe ListaEncadeada.java. */

    private static void listarPedidos() {
        if (listaPedidos.vazia()) {
            JOptionPane.showMessageDialog(null, "Não há nenhum pedido realizado.");
        } else {
            StringBuilder relatorio = new StringBuilder();
            for (NoSimples no = listaPedidos.getPrimeiro(); no != null; no = no.getProx()) {
                Pedido pedido = (Pedido) no.getConteudo();
                double frete = calcularFrete(pedido.getDistancia());
                relatorio.append("Pedido: ").append(pedido.getCodigo()).append("\n")
                        .append("Sabor: ").append(pedido.getSabor()).append("\n")
                        .append("Tamanho: ").append(pedido.getTamanho()).append("\n")
                        .append("Endereço: ").append(pedido.getEndereco()).append("\n")
                        .append("Distância: ").append(pedido.getDistancia()).append(" km").append("\n")
                        .append("Frete: R$").append(String.format("%.2f", frete)).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, relatorio.toString());
        }
    }
    
    private static void incluirParaPreparo() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        NoSimples atual = listaPedidos.getPrimeiro();
        while (atual != null && count < 3) {
            Pedido pedido = (Pedido) atual.getConteudo();
            if (!pedido.isInPreparo()) {
                filaPreparo.enfileirar(pedido);
                pedido.setInPreparo(true);
                count++;
                sb.append(pedido.getCodigo());
                if (count < 3) {
                    sb.append(", ");
                } else {
                    sb.append(".");
                }
            }
            atual = atual.getProx();
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "Não há pedidos para preparo no momento.");
        } else {
            JOptionPane.showMessageDialog(null, count + " pedidos incluídos para preparo!\nCódigos dos pedidos: " + sb.toString());
        }
    }
    
    /* Esse método usa o método exibeFila() de e a instância filaPreparo da classe Fila.java para
    exibir a fila de pedidos que estão em preparo no momento. */

    private static void listarPedidosPreparo() {
        if (filaPreparo.vazia()) {
            JOptionPane.showMessageDialog(null, "Não há nenhum pedido na lista de preparo ainda.");
        } else {
            filaPreparo.exibeFila();
        }
    }
    
    private static void incluirParaEntrega() {
        Pedido[] pedidos = new Pedido[3];
        int index = 0;
        while (!filaPreparo.vazia() && index < 3) {
            Pedido pedido = filaPreparo.desenfileirar();
            if (pedido != null) {
                pedidos[index++] = pedido;
            }
        }

        /* Essa parte ordena os pedidos do menos distante para o mais distante através da verificação
        do elemento atual com o próximo elemento da fila. */
        for (int i = 0; i < index - 1; i++) {
            for (int j = i + 1; j < index; j++) {
                if (pedidos[i].getDistancia() < pedidos[j].getDistancia()) {
                    Pedido temp = pedidos[i];
                    pedidos[i] = pedidos[j];
                    pedidos[j] = temp;
                }
            }
        }

        // Esse trecho empilha os pedidos na pilha de entregas.
        for (int i = 0; i < index; i++) {
            pilhaEntregas.empilhar(pedidos[i]);
        }
        JOptionPane.showMessageDialog(null, index + " pedidos prontos para entrega!");
    }

    private static void listarPedidosProntosParaEntrega() {
        if (pilhaEntregas.vazia()) {
            JOptionPane.showMessageDialog(null, "Infelizmente nenhum pedido está pronto para entrega.");
        } else {
            String relatorio = pilhaEntregas.exibePilha();
            JOptionPane.showMessageDialog(null, relatorio);
        }
    }

    /* Esse método "informa" que a entrega foi realizada, ou seja, é como se o motoboy tivesse ido
    entregar 3 pedidos e já voltado. Após ele voltar, é feita a gravação da entrega dos 3 pedidos
    que ele foi entregar. */

    private static void informarEntregaRealizada() {
        int entregasParaRealizar = 3;
        int totalEntregasRealizadas = 0; 
        while (entregasParaRealizar > 0) {
            Pedido pedido = pilhaEntregas.desempilhar();
            if (pedido != null) {
                pedidosEntregues.insereNo_fim(new NoSimples(pedido));
                listaPedidos.removePorCodigo(pedido.getCodigo()); 
                entregasParaRealizar--;
                totalEntregasRealizadas++; 
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum pedido para entregar!");
                break;
            }
        }
        if (totalEntregasRealizadas > 0) { 
            JOptionPane.showMessageDialog(null, "Entrega(s) realizada(s) com sucesso!");
        }
    }
    
    /* Relatório com todas as entregas já feitas é formado através
    da instância pedidosEntregues e o método exibeLista() da classe ListaEncadeada.java. */

    private static void listarPedidosEntregues() {
        if (pedidosEntregues.vazia()) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido foi entregue ainda.");
        } else {
            pedidosEntregues.exibeLista();
        }
    }
}