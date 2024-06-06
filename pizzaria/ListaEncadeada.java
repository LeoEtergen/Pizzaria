import javax.swing.JOptionPane;

public class ListaEncadeada {

    /* Declaração dos ponteiros para os "nós simples", que são elementos (pedidos)
     que apontam para o próximo, e do contador de nós da lista que estamos fazendo */
    private NoSimples primeiro, ultimo;
    private int numero_nos = 0;

    public ListaEncadeada() {
        primeiro = null;
        ultimo = null;
    }

    public NoSimples getPrimeiro() {
        return primeiro;
    }

    public boolean vazia() {
        return primeiro == null;
    }    

    /* Método que, como o nome sugere, insere um nó no começo da lista. o Novo nó aponta
     para o primeiro, e caso tanto o primeiro quanto o último forem vazios, o último nó 
     também será igual ao novo nó. */

    public void insereNo_inicio(NoSimples novoNo) {
        novoNo.setProx(primeiro);
        if (primeiro == null && ultimo == null) {
            ultimo = novoNo;
        }
        primeiro = novoNo;
        numero_nos++;
    }
    
    /* Faz a mesma coisa, só que inserindo no fim. Faz a análise usando if, para caso
    o primeiro nó ser vazio e caso o último não for vazio.*/
    public void insereNo_fim(NoSimples novoNo) {
        novoNo.setProx(null);
        if (primeiro == null)
            primeiro = novoNo;
        if (ultimo != null)
            ultimo.setProx(novoNo);
        ultimo = novoNo;
        numero_nos++;
    }

    /* Faz a busca do nó através do código do pedido. */

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

    public void removePorCodigo(int codigo) {
        NoSimples atual = primeiro;
        NoSimples anterior = null;

        while (atual != null && atual.getConteudo().getCodigo() != codigo) {
            anterior = atual;
            atual = atual.getProx();
        }

        if (atual != null) {
            if (anterior == null) {
                primeiro = atual.getProx();
            } else {
                anterior.setProx(atual.getProx());
            }
            if (atual == ultimo) {
                ultimo = anterior;
            }
            numero_nos--;
        }
    }

    public void exibeLista() {
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
