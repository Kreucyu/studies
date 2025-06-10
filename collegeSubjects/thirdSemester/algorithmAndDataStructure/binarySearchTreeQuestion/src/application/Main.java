package application;

import model.ArvoreBinariaBusca;

public class Main {
    public static void main(String[] args) {
        System.out.println("Caso 1: Inserção e estrutura da árvore");
        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();
        int[] dados = {50, 30, 70, 40, 25, 75, 65, 35, 60};
        for (int v : dados) {
            arvore.inserir(v);
        }
        System.out.println("Resultado: " + arvore);

        System.out.println("\nCaso 2: Remover nó folha (40)");
        ArvoreBinariaBusca<Integer> arvore2 = new ArvoreBinariaBusca<>();
        for (int v : new int[]{50, 30, 25, 40}) {
            arvore2.inserir(v);
        }
        arvore2.retirar(40);
        System.out.println("Resultado: " + arvore2);

        System.out.println("\nCaso 3: Remover nó com um filho (71)");
        ArvoreBinariaBusca<Integer> arvore3 = new ArvoreBinariaBusca<>();
        for (int v : new int[]{80, 52, 90, 48, 71, 63, 67}) {
            arvore3.inserir(v);
        }
        arvore3.retirar(71);
        System.out.println("Resultado: " + arvore3);

        System.out.println("\nCaso 4: Remover nó com dois filhos (50)");
        ArvoreBinariaBusca<Integer> arvore4 = new ArvoreBinariaBusca<>();
        for (int v : new int[]{50, 30, 70, 40, 25, 75, 65, 35, 60}) {
            arvore4.inserir(v);
        }
        arvore4.retirar(50);
        System.out.println("Resultado: " + arvore4);
    }
}

