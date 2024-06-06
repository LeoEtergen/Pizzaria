public class Pedido {

    /* Declaração dos atributos da classe pedido, e decidimos colocar o tamanho da pizza 
    como um atributo extra do que foi pedido. */
    private int codigo;
    private String sabor;
    private String tamanho;
    private String endereco;
    private int distancia;
    private boolean inPreparo;

    /* Esse é o contrutor do pedido, que pega os atributos criados e ajuda na inicialização do
    novo pedido (que é um objeto). */
    public Pedido(int codigo, String sabor, String tamanho, String endereco, int distancia) {
        this.codigo = codigo;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.endereco = endereco;
        this.distancia = distancia;
    }

    // Aqui estão os métodos "get", que serve para pegar os elementos dos atributos de um objeto.

    public int getCodigo() {
        return codigo;
    }

    public String getSabor() {
        return sabor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getDistancia() {
        return distancia;
    }

    public boolean isInPreparo() {
        return inPreparo;
    }

    public void setInPreparo(boolean inPreparo) {
        this.inPreparo = inPreparo;
    }
    
    /* Aqui o método toString() está sobrescrevendo o método padrão da superclasse. Isso é bom, pq
    ajuda a personalizar a definição de um pedido. */
    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", sabor='" + sabor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", endereco='" + endereco + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
