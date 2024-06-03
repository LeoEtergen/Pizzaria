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

    public boolean contem(Object conteudo) {
        for (int i = inicio; i < fim; i++) {
            if (vetor[i].equals(conteudo)) {
                return true;
            }
        }
        return false;
    }

    public void ExibeFila() {
        StringBuilder mensagem = new StringBuilder("Pedidos na fila para preparo:\n");
        for (int i = inicio, count = 0; count < total; i = (i + 1) % tamanho, count++) {
            mensagem.append("Pedido ").append(count + 1).append(": ").append(vetor[i]).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensagem.toString());
    }
    
}
