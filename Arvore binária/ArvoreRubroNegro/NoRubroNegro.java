public class NoRubroNegro {
    public int chave;
    public NoRubroNegro esquerda, direita, pai;
    public Cor cor;

    public NoRubroNegro(int chave) {
        this.chave = chave;
        this.cor = Cor.VERMELHO;
    }
}
