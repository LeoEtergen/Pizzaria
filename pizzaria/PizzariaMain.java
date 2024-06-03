import java.util.Arrays;
import javax.swing.JOptionPane;

public class PizzariaMain {
    private static int pedidoCounter = 1;
    private static Pilha pilhaEntregas = new Pilha(100);
    private static Fila filaPreparo = new Fila(100);
    private static ListaEncadeada listaPedidos = new ListaEncadeada();
    private static ListaEncadeada pedidosEntregues = new ListaEncadeada();
    private static int entregasRealizadas = 0;

    public static void main(String[] args) {

        // Decidimos colocar 3 pedidos iniciais para facilitar o teste do sistema.
        adicionarPedidoInicial("Margherita", "Grande", "Rua A, 123", 5);
        adicionarPedidoInicial("Pepperoni", "Média", "Rua B, 456", 3);
        adicionarPedidoInicial("Quatro Queijos", "Pequena", "Rua C, 789", 7);

        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(
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
                            "10 - Sair"));

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
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (op != 11);
    }

    private static void adicionarPedido() {
        String sabor = JOptionPane.showInputDialog("Informe o sabor da pizza:");
        String tamanho = JOptionPane.showInputDialog("Informe o tamanho da pizza:");
        String endereco = JOptionPane.showInputDialog("Informe o endereço de entrega:");
        int distancia = Integer.parseInt(JOptionPane.showInputDialog("Informe a distância em km:"));

        Pedido novoPedido = new Pedido(pedidoCounter++, sabor, tamanho, endereco, distancia);
        listaPedidos.insereNo_fim(new NoSimples(novoPedido));
        JOptionPane.showMessageDialog(null, "Pedido adicionado com sucesso!\nNúmero do pedido: " + (pedidoCounter - 1));
    }

    private static void adicionarPedidoInicial(String sabor, String tamanho, String endereco, int distancia) {
        Pedido novoPedido = new Pedido(pedidoCounter++, sabor, tamanho, endereco, distancia);
        listaPedidos.insereNo_fim(new NoSimples(novoPedido));
    }

    private static void cancelarPedido() {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do pedido a ser cancelado:"));
        NoSimples pedidoNo = listaPedidos.buscarNo(codigo);
        if (pedidoNo != null) {
            listaPedidos.remove(pedidoNo); // Remove o pedido da lista de todos os pedidos
            JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
        }
    }

    private static void listarPedidos() {
        listaPedidos.ExibeLista();
    }

    private static void incluirParaPreparo() {
        int[] codigosInseridos = new int[3];

        for (int i = 0; i < 3; i++) {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do pedido a ser incluído para preparo:"));
            NoSimples pedidoNo = listaPedidos.buscarNo(codigo);

            if (pedidoNo != null) {
                Pedido pedido = (Pedido) pedidoNo.getConteudo();
                if (!pedido.isInPreparo()) {
                    filaPreparo.enfileirar(pedido);
                    pedido.setInPreparo(true);
                    codigosInseridos[i] = codigo;
                } else {
                    JOptionPane.showMessageDialog(null, "Pedido com código " + codigo + " já foi incluído na fila de preparo!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pedido com código " + codigo + " não encontrado!");
            }
        }

        JOptionPane.showMessageDialog(null, "Pedidos com os códigos " + Arrays.toString(codigosInseridos) + " incluídos para preparo com sucesso!");
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
    
        // Ordenar os pedidos manualmente por distância (do mais distante para o menos distante)
        for (int i = 0; i < index - 1; i++) {
            for (int j = i + 1; j < index; j++) {
                if (pedidos[i].getDistancia() < pedidos[j].getDistancia()) {
                    Pedido temp = pedidos[i];
                    pedidos[i] = pedidos[j];
                    pedidos[j] = temp;
                }
            }
        }
    
        // Empilhar os pedidos ordenados na pilha de entregas
        for (int i = 0; i < index; i++) {
            pilhaEntregas.empilhar(pedidos[i]);
        }
    
        JOptionPane.showMessageDialog(null, index + " pedidos prontos para entrega!");
    }
    

    private static void informarEntregaRealizada() {
        if (entregasRealizadas >= 2) {
            JOptionPane.showMessageDialog(null, "Limite de entregas realizadas alcançado!");
            return;
        }

        int entregasParaRealizar = 3;
        while (entregasParaRealizar > 0) {
            Pedido pedido = pilhaEntregas.desempilhar();
            if (pedido != null) {
                pedidosEntregues.insereNo_fim(new NoSimples(pedido));
                entregasParaRealizar--;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum pedido para entregar!");
                break;
            }
        }

        entregasRealizadas++;
        JOptionPane.showMessageDialog(null, "Entrega(s) realizada(s) com sucesso!");
    }

    private static void listarPedidosPreparo() {
        filaPreparo.ExibeFila();
    }

    private static void listarPedidosProntosParaEntrega() {
        String relatorio = pilhaEntregas.ExibePilha();
        JOptionPane.showMessageDialog(null, relatorio);
    }

    private static void listarPedidosEntregues() {
        pedidosEntregues.ExibeLista();
    }
}
