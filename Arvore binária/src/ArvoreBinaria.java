public class arvorePreordem {

    static class No {
        String valor;
        No esquerda, direita;

        public No(String valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }
    public void percorrerPreOrdem(No node) {
        if (node == null) return;
        System.out.print(node.valor + " ");
        percorrerPreOrdem(node.esquerda);
        percorrerPreOrdem(node.direita);
    }
    public static void main(String[] args) {
        No a = new No("A");
        No b = new No("B");
        No c = new No("C");
        No d = new No("D");
        No e = new No("E");
        No f = new No("F");

        a.esquerda = b;
        a.direita = c;
        b.esquerda = d;
        b.direita = e;
        c.direita = f;

        arvorePreordem arvore = new arvorePreordem();
        System.out.print("Pré-ordem: ");
        arvore.percorrerPreOrdem(a);
    }
}
