public class NoRubroNegro {
    public int chave;
    public NoRubroNegro esquerda, direita, pai;
    public boolean cor;

    public static final boolean VERMELHO = true;
    public static final boolean PRETO = false;

    public NoRubroNegro(int chave) {
        this.chave = chave;
        this.cor = VERMELHO;
    }
}
