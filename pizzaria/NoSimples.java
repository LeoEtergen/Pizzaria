public class NoSimples {
    private Pedido conteudo;
    private NoSimples prox;

    public NoSimples(Pedido conteudo) {
        this.conteudo = conteudo;
        this.prox = null;
    }

    public Pedido getConteudo() {
        return conteudo;
    }

    public void setConteudo(Pedido conteudo) {
        this.conteudo = conteudo;
    }

    public NoSimples getProx() {
        return prox;
    }

    public void setProx(NoSimples prox) {
        this.prox = prox;
    }
}
