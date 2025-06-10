package application;

import java.util.Arrays;

import entities.OrdenacaoAbstract;
import entities.OrdenacaoBolha;
import entities.OrdenacaoBolhaOtimizada;
import entities.OrdenacaoMergeSort;
import entities.OrdenacaoQuickSort;

public class App {
    public static void main(String[] args) {
        Integer[] vetor = {70, 2, 88, 15, 90, 30};

        testar("Bolha", new OrdenacaoBolha<>(), vetor.clone());
        testar("Bolha Otimizada", new OrdenacaoBolhaOtimizada<>(), vetor.clone());
        testar("QuickSort", new OrdenacaoQuickSort<>(), vetor.clone());
        testar("MergeSort", new OrdenacaoMergeSort<>(), vetor.clone());
    }

    private static void testar(String nome, OrdenacaoAbstract<Integer> ordenacao, Integer[] vetor) {
        ordenacao.setInfo(vetor);
        ordenacao.ordenar();
        System.out.println(nome + ": " + Arrays.toString(ordenacao.getInfo()));
    }
}

