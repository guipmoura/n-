public class emOrdem {

    static class No {
        char valor;
        No esquerda, direita;

        No(char valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }
    static void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            emOrdem(no.direita);
        }
    }
    public static void main(String[] args) {
        No raiz = new No('A');
        raiz.esquerda = new No('B');
        raiz.direita = new No('C');
        raiz.esquerda.esquerda = new No('D');
        raiz.esquerda.direita = new No('E');
        raiz.direita.direita = new No('F');
        System.out.println("Em-ordem:");
        emOrdem(raiz);
    }
}
