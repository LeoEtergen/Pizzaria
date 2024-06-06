import javax.swing.JOptionPane;

public class Fila {
    private int tamanho;
    private int inicio;
    private int fim;
    private int total;
    private Pedido[] vetor;

    public Fila(int tam) {
        inicio = 0;
        fim = 0;
        total = 0;
        vetor = new Pedido[tam];
        tamanho = tam;
    }

    public boolean vazia() {
        return total == 0;
    }

    public boolean cheia() {
        return total == tamanho;
    }

    public void enfileirar(Pedido elem) {
        if (!cheia()) {
            vetor[fim] = elem;
            fim = (fim + 1) % tamanho;
            total++;
        } else {
            JOptionPane.showMessageDialog(null, "Fila Cheia!");
        }
    }

    public Pedido desenfileirar() {
        Pedido elem;
        if (!vazia()) {
            elem = vetor[inicio];
            inicio = (inicio + 1) % tamanho;
            total--;
        } else {
            elem = null;
        }
        return elem;
    }

    public void removePorCodigo(int codigo) {
        if (!vazia()) {
            int count = total;
            for (int i = inicio; count > 0; i = (i + 1) % tamanho, count--) {
                if (vetor[i].getCodigo() == codigo) {
                    for (int j = i; j != fim; j = (j + 1) % tamanho) {
                        vetor[j] = vetor[(j + 1) % tamanho];
                    }
                    fim = (fim - 1 + tamanho) % tamanho;
                    total--;
                    break;
                }
            }
        }
    }

    public void exibeFila() {
        StringBuilder mensagem = new StringBuilder("Pedidos na fila para preparo:\n");
        for (int i = inicio, count = 0; count < total; i = (i + 1) % tamanho, count++) {
            Pedido pedido = vetor[i];
            mensagem.append("Pedido ").append(pedido.getCodigo()).append("\n");
            mensagem.append("   Sabor: ").append(pedido.getSabor()).append("\n");
            mensagem.append("   Tamanho: ").append(pedido.getTamanho()).append("\n");
            mensagem.append("   Endereço: ").append(pedido.getEndereco()).append("\n");
            mensagem.append("   Distância: ").append(pedido.getDistancia()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }

    public boolean contemPedido(int codigo) {
        for (int i = inicio, count = 0; count < total; i = (i + 1) % tamanho, count++) {
            if (vetor[i].getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    
}
