public class Pedido {
    private int codigo;
    private String sabor;
    private String tamanho;
    private String endereco;
    private int distancia;
    private boolean inPreparo;

    public Pedido(int codigo, String sabor, String tamanho, String endereco, int distancia) {
        this.codigo = codigo;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.endereco = endereco;
        this.distancia = distancia;
    }

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
