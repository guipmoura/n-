public class ArvoreRubroNegra {
    private NoRubroNegro raiz;

    public void inserir(int chave) {
        NoRubroNegro novoNo = new NoRubroNegro(chave);
        raiz = inserirBST(raiz, novoNo);
        corrigirViolacao(novoNo);
    }

    private NoRubroNegro inserirBST(NoRubroNegro raiz, NoRubroNegro no) {
        if (raiz == null) return no;

        if (no.chave < raiz.chave) {
            raiz.esquerda = inserirBST(raiz.esquerda, no);
            raiz.esquerda.pai = raiz;
        } else if (no.chave > raiz.chave) {
            raiz.direita = inserirBST(raiz.direita, no);
            raiz.direita.pai = raiz;
        }

        return raiz;
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

    private void corrigirViolacao(NoRubroNegro no) {
        while (no != raiz && no.pai.cor == Cor.VERMELHO) {
            NoRubroNegro pai = no.pai;
            NoRubroNegro avo = pai.pai;

            if (pai == avo.esquerda) {
                NoRubroNegro tio = avo.direita;

                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    no = avo;
                } else {
                    if (no == pai.direita) {
                        no = pai;
                        rotacaoEsquerda(no);
                    }
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoDireita(avo);
                }
            } else {
                NoRubroNegro tio = avo.esquerda;

                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    no = avo;
                } else {
                    if (no == pai.esquerda) {
                        no = pai;
                        rotacaoDireita(no);
                    }
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoEsquerda(avo);
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
