package application;

import entities.ArvoreBinariaBusca;

public class Teste {
    public static void main(String[] args) {
        System.out.println("Teste 1 (vazia): " + arvoreVazia().isDegenerada());               // true
        System.out.println("Teste 2 (raiz): " + raizSomente().isDegenerada());                // true
        System.out.println("Teste 3 (linha esquerda): " + linhaEsquerda().isDegenerada());    // true
        System.out.println("Teste 4 (linha direita): " + linhaDireita().isDegenerada());      // true
        System.out.println("Teste 5 (alternando lados): " + alternando().isDegenerada());     // true
        System.out.println("Teste 6 (raiz com dois filhos): " + raizDoisFilhos().isDegenerada()); // false
        System.out.println("Teste 7 (filho com dois filhos): " + filhoDoisFilhos().isDegenerada()); // false
        System.out.println("Teste 8 (árvore cheia): " + arvoreCheia().isDegenerada());        // false
    }

    // Teste 1: Árvore vazia
    public static ArvoreBinariaBusca<Integer> arvoreVazia() {
        return new ArvoreBinariaBusca<>();
    }

    // Teste 2: Somente a raiz
    public static ArvoreBinariaBusca<Integer> raizSomente() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        a.inserir(10);
        return a;
    }

    // Teste 3: Linha para esquerda (números decrescentes)
    public static ArvoreBinariaBusca<Integer> linhaEsquerda() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {40, 30, 20, 10};
        for (int v : valores) a.inserir(v);
        return a;
    }

    // Teste 4: Linha para direita (números crescentes)
    public static ArvoreBinariaBusca<Integer> linhaDireita() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {10, 20, 30, 40};
        for (int v : valores) a.inserir(v);
        return a;
    }

    // Teste 5: Alternando lados com estrutura degenerada
    public static ArvoreBinariaBusca<Integer> alternando() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {10, 20, 15, 17}; // Cria curva: 10 -> direita 20 -> esquerda 15 -> direita 17
        for (int v : valores) a.inserir(v);
        return a;
    }

    // Teste 6: Raiz com dois filhos
    public static ArvoreBinariaBusca<Integer> raizDoisFilhos() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {10, 5, 15};
        for (int v : valores) a.inserir(v);
        return a;
    }

    // Teste 7: Filho com dois filhos
    public static ArvoreBinariaBusca<Integer> filhoDoisFilhos() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {10, 15, 12, 17}; // nó 15 com dois filhos (12 e 17)
        for (int v : valores) a.inserir(v);
        return a;
    }

    // Teste 8: Árvore cheia até nível 2
    public static ArvoreBinariaBusca<Integer> arvoreCheia() {
        ArvoreBinariaBusca<Integer> a = new ArvoreBinariaBusca<>();
        int[] valores = {20, 10, 30, 5, 15, 25, 35};
        for (int v : valores) a.inserir(v);
        return a;
    }
}
