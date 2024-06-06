import javax.swing.JOptionPane;

public class Pilha {
    private int topo;
    private int tamanho;
    private Pedido[] vetor;

    public Pilha(int tam) {
        topo = -1; 
        tamanho = tam;
        vetor = new Pedido[tam];
    }

    public boolean vazia() {
        return topo == -1;
    }

    public boolean cheia() {
        return topo == tamanho - 1;
    }

    public void empilhar(Pedido elem) {
        if (!cheia()) {
            topo++;
            vetor[topo] = elem;
        } else {
            JOptionPane.showMessageDialog(null, "O número de pedidos na pilha esgotou!");
        }
    }

    public Pedido desempilhar() {
        Pedido valorDesempilhado;
        if (vazia()) {
            valorDesempilhado = null;
        } else {
            valorDesempilhado = vetor[topo];
            topo--;
        }
        return valorDesempilhado;
    }

    public String exibePilha() {
        StringBuilder elementos = new StringBuilder();
        elementos.append("Pedidos para Entrega (do mais próximo ao mais distante):\n");
        if (vazia()) {
            elementos.append("Não há nenhum pedido na pilha para entrega!");
        } else {
            for (int i = topo; i >= 0; i--) {
                Pedido pedido = (Pedido) vetor[i];
                elementos.append("Pedido ").append(pedido.getCodigo()).append(": \n");
                elementos.append("Sabor: ").append(pedido.getSabor()).append("\n");
                elementos.append("Tamanho: ").append(pedido.getTamanho()).append("\n");
                elementos.append("Endereço de entrega: ").append(pedido.getEndereco()).append("\n");
                elementos.append("Distância: ").append(pedido.getDistancia()).append(" km\n\n");
            }
        }
        return elementos.toString();
    }

    
}
