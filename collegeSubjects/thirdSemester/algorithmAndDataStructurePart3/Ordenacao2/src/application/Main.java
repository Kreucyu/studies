package application;

import entities.OrdenacaoBolha;
import entities.OrdenacaoMergeSort;
import entities.OrdenacaoQuickSort;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {5, 2, 8, 1, 9, 4, 7, 3, 6};
        String[] stringArr = {"banana", "apple", "kiwi", "orange", "grape"};
        Integer[] arrOcorrencia = {1, 3, 2, 1, 3, 3, 2, 1, 4};
        Integer[] arrNegativos = {-5, 2, -8, 1, 9, -4, 7, -3, 6};
        Integer[] arrParImpar = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] arrUltimoDigito = {12, 23, 31, 44, 55, 67, 78, 89, 90};

        // Exemplo com QuickSort
        OrdenacaoQuickSort<Integer> quickSort = new OrdenacaoQuickSort<>(Integer.class);
        quickSort.setInfo(arr.clone());
        quickSort.ordenar();
        System.out.println("QuickSort (crescente): " + java.util.Arrays.toString(quickSort.getInfo()));
        System.out.println("Trocas QuickSort: " + quickSort.getTrocas());
        System.out.println("Comparações QuickSort: " + quickSort.getComparacoes());
        System.out.println("Está ordenado? " + quickSort.estaOrdenado());

        quickSort.setInfo(arr.clone());
        quickSort.ordenarDecrescente();
        System.out.println("QuickSort (decrescente): " + java.util.Arrays.toString(quickSort.getInfo()));

        quickSort.setInfo(arr.clone());
        quickSort.ordenarSegmento(1, 4);
        System.out.println("QuickSort (segmento 1-4): " + java.util.Arrays.toString(quickSort.getInfo()));


        // Exemplo com MergeSort
        OrdenacaoMergeSort<Integer> mergeSort = new OrdenacaoMergeSort<>(Integer.class);
        mergeSort.setInfo(arr.clone());
        mergeSort.ordenar();
        System.out.println("\nMergeSort (crescente): " + java.util.Arrays.toString(mergeSort.getInfo()));
        System.out.println("Trocas MergeSort: " + mergeSort.getTrocas());
        System.out.println("Comparações MergeSort: " + mergeSort.getComparacoes());

        // Exemplo com Bubble Sort
        OrdenacaoBolha<Integer> bubbleSort = new OrdenacaoBolha<>(Integer.class);
        bubbleSort.setInfo(arr.clone());
        bubbleSort.ordenar();
        System.out.println("\nBubble Sort (crescente): " + java.util.Arrays.toString(bubbleSort.getInfo()));
        System.out.println("Trocas Bubble Sort: " + bubbleSort.getTrocas());
        System.out.println("Comparações Bubble Sort: " + bubbleSort.getComparacoes());


        // Exemplo de Ordenar por Ocorrência
        // Usando Bubble Sort para demonstrar o método na classe abstrata.
        OrdenacaoBolha<Integer> bubbleOcorrencia = new OrdenacaoBolha<>(Integer.class);
        bubbleOcorrencia.setInfo(arrOcorrencia.clone());
        bubbleOcorrencia.ordenarPorOcorrencia();
        System.out.println("\nOrdenado por Ocorrência: " + java.util.Arrays.toString(bubbleOcorrencia.getInfo()));

        // Exemplo de Ordenar String por Tamanho
        OrdenacaoBolha<String> bubbleStringTamanho = new OrdenacaoBolha<>(String.class);
        bubbleStringTamanho.setInfo(stringArr.clone());
        bubbleStringTamanho.ordenarStringPorTamanho();
        System.out.println("String por Tamanho: " + java.util.Arrays.toString(bubbleStringTamanho.getInfo()));

        // Exemplo de Ordenar por Pares e Ímpares
        OrdenacaoBolha<Integer> bubbleParImpar = new OrdenacaoBolha<>(Integer.class);
        bubbleParImpar.setInfo(arrParImpar.clone());
        bubbleParImpar.ordenarPorParImpar();
        System.out.println("Ordenado por Pares/Impares: " + java.util.Arrays.toString(bubbleParImpar.getInfo()));

        // Exemplo de Ordenar por Último Dígito
        OrdenacaoBolha<Integer> bubbleUltimoDigito = new OrdenacaoBolha<>(Integer.class);
        bubbleUltimoDigito.setInfo(arrUltimoDigito.clone());
        bubbleUltimoDigito.ordenarPorUltimoDigito();
        System.out.println("Ordenado por Último Dígito: " + java.util.Arrays.toString(bubbleUltimoDigito.getInfo()));

        // Exemplo de Ordenar Negativos
        OrdenacaoBolha<Integer> bubbleNegativos = new OrdenacaoBolha<>(Integer.class);
        bubbleNegativos.setInfo(arrNegativos.clone());
        bubbleNegativos.ordenarNegativos();
        System.out.println("Ordenado Negativos: " + java.util.Arrays.toString(bubbleNegativos.getInfo()));

        // Exemplo de Intercalar Vetores Ordenados
        Integer[] arr1 = {1, 3, 5};
        Integer[] arr2 = {2, 4, 6, 8};
        OrdenacaoQuickSort<Integer> quickIntercalar = new OrdenacaoQuickSort<>(Integer.class);
        quickIntercalar.setInfo(arr1);
        Integer[] intercalado = quickIntercalar.intercalarVetoresOrdenados(arr2);
        System.out.println("\nVetores Intercalados: " + java.util.Arrays.toString(intercalado));

        // Exemplo de São Permutações
        Integer[] perm1 = {1, 2, 3};
        Integer[] perm2 = {3, 1, 2};
        Integer[] perm3 = {1, 2, 4};
        OrdenacaoQuickSort<Integer> quickPermutacao = new OrdenacaoQuickSort<>(Integer.class);
        quickPermutacao.setInfo(perm1);
        System.out.println("São permutações (1,2,3 e 3,1,2)? " + quickPermutacao.saoPermutacoes(perm2));
        quickPermutacao.setInfo(perm1);
        System.out.println("São permutações (1,2,3 e 1,2,4)? " + quickPermutacao.saoPermutacoes(perm3));

        // Exemplo de Mediana
        OrdenacaoQuickSort<Integer> quickMediana = new OrdenacaoQuickSort<>(Integer.class);
        quickMediana.setInfo(arr.clone());
        // Para calcular a mediana, o array deve estar ordenado
        quickMediana.ordenar(); // ou chamar qualquer outro metodo de ordenação
        System.out.println("\nMediana: " + quickMediana.calcularMediana());

        // Exemplo de Obter Índice de Ordenação
        OrdenacaoQuickSort<Integer> quickIndices = new OrdenacaoQuickSort<>(Integer.class);
        quickIndices.setInfo(arr.clone());
        Integer[] indicesOrdenados = quickIndices.obterIndiceOrdenacao();
        System.out.println("Índices de Ordenação para " + java.util.Arrays.toString(arr) + ": " + java.util.Arrays.toString(indicesOrdenados));
    }
}