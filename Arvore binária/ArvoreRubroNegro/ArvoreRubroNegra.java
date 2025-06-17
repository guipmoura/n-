 public class ArvoreRubroNegra {
    private NoRubroNegro raiz;

    public void inserir(int chave) {
    NoRubroNegro novoNo = new NoRubroNegro(chave);
    novoNo.esquerda = null;
    novoNo.direita = null;
    novoNo.cor = Cor.VERMELHO;

    NoRubroNegro y = null;
    NoRubroNegro x = raiz;

    while (x != null) {
        y = x;
        if (novoNo.chave < x.chave) {
            x = x.esquerda;
        } else {
            x = x.direita;
        }
    }

    novoNo.pai = y;

    if (y == null) {
        raiz = novoNo;
    } else if (novoNo.chave < y.chave) {
        y.esquerda = novoNo;
    } else {
        y.direita = novoNo;
    }

    insertFix(novoNo);
}
  private NoRubroNegro minimum(NoRubroNegro node) {
    while (node.esquerda != null) {
        node = node.esquerda;
    }
    return node;
}


    private void rotacaoEsquerda(NoRubroNegro no) {
        NoRubroNegro direita = no.direita;
        no.direita = direita.esquerda;

        if (direita.esquerda != null)
            direita.esquerda.pai = no;

        direita.pai = no.pai;

        if (no.pai == null) {
            raiz = direita;
        } else if (no == no.pai.esquerda) {
            no.pai.esquerda = direita;
        } else {
            no.pai.direita = direita;
        }

        direita.esquerda = no;
        no.pai = direita;
    }

    private void rotacaoDireita(NoRubroNegro no) {
        NoRubroNegro esquerda = no.esquerda;
        no.esquerda = esquerda.direita;

        if (esquerda.direita != null)
            esquerda.direita.pai = no;

        esquerda.pai = no.pai;

        if (no.pai == null) {
            raiz = esquerda;
        } else if (no == no.pai.esquerda) {
            no.pai.esquerda = esquerda;
        } else {
            no.pai.direita = esquerda;
        }

        esquerda.direita = no;
        no.pai = esquerda;
    }

    private void insertFix(NoRubroNegro k) {
    while (k != raiz && k.pai.cor == Cor.VERMELHO) {
        if (k.pai == k.pai.pai.esquerda) {
            NoRubroNegro tio = k.pai.pai.direita;

            if (tio != null && tio.cor == Cor.VERMELHO) {
                k.pai.cor = Cor.PRETO;
                tio.cor = Cor.PRETO;
                k.pai.pai.cor = Cor.VERMELHO;
                k = k.pai.pai;
            } else {
                if (k == k.pai.direita) {
                    k = k.pai;
                    rotacaoEsquerda(k);
                }
                k.pai.cor = Cor.PRETO;
                k.pai.pai.cor = Cor.VERMELHO;
                rotacaoDireita(k.pai.pai);
            }
        } else {
            NoRubroNegro tio = k.pai.pai.esquerda;

            if (tio != null && tio.cor == Cor.VERMELHO) {
                k.pai.cor = Cor.PRETO;
                tio.cor = Cor.PRETO;
                k.pai.pai.cor = Cor.VERMELHO;
                k = k.pai.pai;
            } else {
                if (k == k.pai.esquerda) {
                    k = k.pai;
                    rotacaoDireita(k);
                }
                k.pai.cor = Cor.PRETO;
                k.pai.pai.cor = Cor.VERMELHO;
                rotacaoEsquerda(k.pai.pai);
            }
        }
    }
    raiz.cor = Cor.PRETO;
}


    public void imprimirArvore() {
        imprimirRecursivo(raiz);
    }
    private void imprimirRecursivo(NoRubroNegro no) {
        if (no != null) {
            System.out.println(no.chave + " (" + (no.cor == Cor.VERMELHO ? "V" : "P") + ")");
            imprimirRecursivo(no.esquerda);
            imprimirRecursivo(no.direita);
        }
    }
}
