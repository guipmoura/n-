class No {
    int valor;
    No esquerda;
    No direita;

    public No(int valor) {
        this.valor = valor;
        esquerda = null;
        direita = null;
    }
}
public class ContarNosFolha{
    public int contarNosFolha(No node) {
        if (node == null) {
            return 0;
        }
        if (node.esquerda == null && node.direita == null) {
            return 1;
        }
        return contarNosFolha(node.esquerda) + contarNosFolha(node.direita);
    }
    public static void main(String[] args) {
        No a = new No(1);
        No b = new No(2);
        No c = new No(3);
        No d = new No(4);
        No e = new No(5);
        No f = new No(6);

        a.esquerda = b;
        a.direita = c;
        b.esquerda = d;
        b.direita = e;
        c.direita = f;

        ContarNosFolha arvore = new ContarNosFolha();
        int totalNosFolha = arvore.contarNosFolha(a);

        System.out.println("Nós folha da árvore: " + totalNosFolha);
    }
}
