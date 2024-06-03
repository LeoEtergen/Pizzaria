import javax.swing.JOptionPane;

public class ListaEncadeada {
    private NoSimples primeiro, ultimo;
    private int numero_nos = 0;
    private int tamanho;

    public ListaEncadeada() {
        primeiro = null;
        ultimo = null;
    }

    public void insereNo_inicio(NoSimples novoNo) {
        novoNo.setProx(primeiro);
        if (primeiro == null && ultimo == null) {
            ultimo = novoNo;
        }
        primeiro = novoNo;
        numero_nos++;
    }

    public void insereNo_fim(NoSimples novoNo) {
        novoNo.setProx(null);
        if (primeiro == null)
            primeiro = novoNo;
        if (ultimo != null)
            ultimo.setProx(novoNo);
        ultimo = novoNo;
        numero_nos++;
    }

    public NoSimples buscarNo(int codigo) {
        NoSimples atual = primeiro;
        while (atual != null) {
            if (atual.getConteudo().getCodigo() == codigo) {
                return atual;
            }
            atual = atual.getProx();
        }
        return null;
    }

    public void remove(Object conteudo) {
        NoSimples atual = primeiro;
        NoSimples anterior = null;

        // Procurar o nó que contém o conteúdo a ser removido
        while (atual != null && !atual.getConteudo().equals(conteudo)) {
            anterior = atual;
            atual = atual.getProx();
        }

        // Se o nó foi encontrado
        if (atual != null) {
            // Se o nó a ser removido é o primeiro da lista
            if (anterior == null) {
                primeiro = atual.getProx();
            } else {
                anterior.setProx(atual.getProx());
            }
            // Atualizar o último nó, se necessário
            if (atual == ultimo) {
                ultimo = anterior;
            }
            tamanho--; // Atualizar o tamanho da lista
        }
    }

    public void ExibeLista() {
        NoSimples temp_no = primeiro;
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lista de Pedidos:\n");
        while (temp_no != null) {
            Pedido pedido = (Pedido) temp_no.getConteudo();
            stringBuilder.append("Código: ").append(pedido.getCodigo()).append("\n");
            stringBuilder.append("Sabor: ").append(pedido.getSabor()).append("\n");
            stringBuilder.append("Tamanho: ").append(pedido.getTamanho()).append("\n");
            stringBuilder.append("Endereço de entrega: ").append(pedido.getEndereco()).append("\n");
            stringBuilder.append("Distância: ").append(pedido.getDistancia()).append(" km\n\n");
            temp_no = temp_no.getProx();
            i++;
        }
        JOptionPane.showMessageDialog(null, stringBuilder.toString());
    }
}
