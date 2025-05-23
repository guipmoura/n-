import java.util.LinkedList;
import java.util.Queue;

public class emNivel {
    static class No {
        char valor;
        No esquerda, direita;

        No(char valor) {
            this.valor = valor;
            esquerda = direita = null;
        }
    }
    static void nivelPorNivel(No raiz) {
        if (raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.valor + " ");

            if (atual.esquerda != null) {
                fila.add(atual.esquerda);
            }
            if (atual.direita != null) {
                fila.add(atual.direita);
            }
        }
    }

    public static void main(String[] args) {
        No raiz = new No('A');
        raiz.esquerda = new No('B');
        raiz.direita = new No('C');
        raiz.esquerda.esquerda = new No('D');
        raiz.esquerda.direita = new No('E');
        raiz.direita.direita = new No('F');

        System.out.println("Nível por nível:");
        nivelPorNivel(raiz);
    }
}
