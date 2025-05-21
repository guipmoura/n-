public class ArvoreBinaria {

    public int contarNos(No node) {
        if (node == null) return 0;
        return 1 + contarNos(node.esquerda) + contarNos(node.direita);
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

        ArvoreBinaria arvore = new ArvoreBinaria();
        int totalNos = arvore.contarNos(a);

        System.out.println("Quantidade de nós da árvore" + totalNos);


    }
}

