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
    private void toString(NoRubroNegro no) {
        if (no != null) {
            System.out.println(no.chave + " (" + (no.cor == Cor.VERMELHO ? "V" : "P") + ")");
            imprimirRecursivo(no.esquerda);
            imprimirRecursivo(no.direita);
        }
    }
  public void delete(int chave) {
    NoRubroNegro z = searchTree(root, chave);
    if (z == null) return;

    NoRubroNegro y = z;
    boolean yOriginalColor = y.cor;
    NoRubroNegro x;

    if (z.esquerda == null) {
        x = z.direita;
        transplant(z, z.direita);
    } else if (z.direita == null) {
        x = z.esquerda;
        transplant(z, z.esquerda);
    } else {
        y = minimum(z.direita);
        yOriginalColor = y.cor;
        x = y.direita;
        if (y.pai == z) {
            if (x != null) {
                x.pai = y;
            }
        } else {
            transplant(y, y.direita);
            y.direita = z.direita;
            if (y.direita != null) {
                y.direita.pai = y;
            }
        }

        transplant(z, y);
        y.esquerda = z.esquerda;
        if (y.esquerda != null) {
            y.esquerda.pai = y;
        }
        y.cor = z.cor;
    }

    if (yOriginalColor == NoRubroNegro.PRETO) {
        deleteFix(x);
    }
 }
  private void transplant(NoRubroNegro u, NoRubroNegro v) {
    if (u.pai == null) {
        root = v;
    } else if (u == u.pai.esquerda) {
        u.pai.esquerda = v;
    } else {
        u.pai.direita = v;
    }
    if (v != null) {
        v.pai = u.pai;
    }
}
private NoRubroNegro searchTree(NoRubroNegro node, int chave) {
    if (node == null || chave == node.chave) {
        return node;
    }

    if (chave < node.chave) {
        return searchTree(node.esquerda, chave);
    } else {
        return searchTree(node.direita, chave);
    }
  }
  private void deleteFix(NoRubroNegro x) {
    while (x != raiz && (x == null || x.cor == Cor.PRETO)) {
        if (x == x.pai.esquerda) {
            NoRubroNegro w = x.pai.direita;  

            if (w.cor == Cor.VERMELHO) {
                w.cor = Cor.PRETO;
                x.pai.cor = Cor.VERMELHO;
                rotacaoEsquerda(x.pai);
                w = x.pai.direita;
            }

            if ((w.esquerda == null || w.esquerda.cor == Cor.PRETO) &&
                (w.direita == null || w.direita.cor == Cor.PRETO)) {
                w.cor = Cor.VERMELHO;
                x = x.pai;
            } else {
                if (w.direita == null || w.direita.cor == Cor.PRETO) {
                    if (w.esquerda != null) {
                        w.esquerda.cor = Cor.PRETO;
                    }
                    w.cor = Cor.VERMELHO;
                    rotacaoDireita(w);
                    w = x.pai.direita;
                }
                w.cor = x.pai.cor;
                x.pai.cor = Cor.PRETO;
                if (w.direita != null) {
                    w.direita.cor = Cor.PRETO;
                }
                rotacaoEsquerda(x.pai);
                x = raiz;
            }
        } else {
            NoRubroNegro w = x.pai.esquerda; 

            if (w.cor == Cor.VERMELHO) {
                w.cor = Cor.PRETO;
                x.pai.cor = Cor.VERMELHO;
                rotacaoDireita(x.pai);
                w = x.pai.esquerda;
            }

            if ((w.direita == null || w.direita.cor == Cor.PRETO) &&
                (w.esquerda == null || w.esquerda.cor == Cor.PRETO)) {
                w.cor = Cor.VERMELHO;
                x = x.pai;
            } else {
                if (w.esquerda == null || w.esquerda.cor == Cor.PRETO) {
                    if (w.direita != null) {
                        w.direita.cor = Cor.PRETO;
                    }
                    w.cor = Cor.VERMELHO;
                    rotacaoEsquerda(w);
                    w = x.pai.esquerda;
                }
                w.cor = x.pai.cor;
                x.pai.cor = Cor.PRETO;
                if (w.esquerda != null) {
                    w.esquerda.cor = Cor.PRETO;
                }
                rotacaoDireita(x.pai);
                x = raiz;
            }
        }
    }
    if (x != null) {
        x.cor = Cor.PRETO;
    }
 }
  public void inorder() {
    inorderHelper(raiz);
    System.out.println();
 }
  private void inorderHelper(NoRubroNegro no) {
    if (no != null) {
        inorderHelper(no.esquerda);
        System.out.print(no.chave + " (" + (no.cor == Cor.VERMELHO ? "V" : "P") + ") ");
        inorderHelper(no.direita);
    }
}
