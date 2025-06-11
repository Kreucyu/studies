package application;

import entities.BuscaBinaria;
import entities.BuscaBinariaRecursiva;
import entities.BuscaLinear;
import entities.BuscaLinearVetorOrdenado;

public class BuscaBinariaTest {

    public static void main(String[] args) {
        System.out.println("--- Cenário de Testes para Classes de Busca (Sem JUnit) ---");
        System.out.println("----------------------------------------------------------\n");

        // Dados de Teste Comuns
        Integer[] arrOrdenado = {1, 5, 8, 12, 15, 20, 25, 30, 30, 35, 40, 40, 40, 45, 50};
        Integer[] arrNaoOrdenado = {20, 5, 1, 40, 15, 30, 8, 25, 50, 12, 45, 35, 30, 40, 40}; // Linear search only
        Integer[] arrVazio = {};
        Integer[] arrUnico = {10};
        Integer[] arrTodosIguais = {7, 7, 7, 7, 7};

        System.out.println("----- Testando BuscaLinear -----");
        testBuscaLinear(arrNaoOrdenado, arrVazio, arrUnico, arrTodosIguais);
        System.out.println("\n");

        System.out.println("----- Testando BuscaLinearVetorOrdenado -----");
        testBuscaLinearVetorOrdenado(arrOrdenado, arrVazio, arrUnico, arrTodosIguais);
        System.out.println("\n");

        System.out.println("----- Testando BuscaBinaria (Iterativa) -----");
        testBuscaBinaria(arrOrdenado, arrVazio, arrUnico, arrTodosIguais);
        System.out.println("\n");

        System.out.println("----- Testando BuscaBinariaRecursiva -----");
        testBuscaBinariaRecursiva(arrOrdenado, arrVazio, arrUnico, arrTodosIguais);
        System.out.println("\n");
    }

    private static void testBuscaLinear(Integer[] arrNaoOrdenado, Integer[] arrVazio, Integer[] arrUnico, Integer[] arrTodosIguais) {
        BuscaLinear<Integer> buscaLinear = new BuscaLinear<>();

        System.out.println("-> Testes Básicos de Busca (buscar):");
        buscaLinear.setInfo(arrNaoOrdenado);
        System.out.println("  Buscar 15 em arrNaoOrdenado: " + buscaLinear.buscar(15) + " (Esperado: 4)");
        System.out.println("  Comparacoes: " + buscaLinear.contarComparacoesBuscaLinear());
        System.out.println("  Buscar 99 em arrNaoOrdenado: " + buscaLinear.buscar(99) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaLinear.contarComparacoesBuscaLinear());
        buscaLinear.setInfo(arrVazio);
        System.out.println("  Buscar 5 em arrVazio: " + buscaLinear.buscar(5) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaLinear.contarComparacoesBuscaLinear());
        buscaLinear.setInfo(arrUnico);
        System.out.println("  Buscar 10 em arrUnico: " + buscaLinear.buscar(10) + " (Esperado: 0)");
        System.out.println("  Comparacoes: " + buscaLinear.contarComparacoesBuscaLinear());

        System.out.println("\n-> Testes de Ocorrências (buscarPrimeiraOcorrencia, buscarUltimaOcorrencia):");
        buscaLinear.setInfo(arrNaoOrdenado);
        System.out.println("  Primeira ocorrência de 40 em arrNaoOrdenado: " + buscaLinear.buscarPrimeiraOcorrencia(40) + " (Esperado: 3)");
        System.out.println("  Última ocorrência de 40 em arrNaoOrdenado: " + buscaLinear.buscarUltimaOcorrencia(40) + " (Esperado: 13)");
        System.out.println("  Primeira ocorrência de 99 em arrNaoOrdenado: " + buscaLinear.buscarPrimeiraOcorrencia(99) + " (Esperado: -1)");
        System.out.println("  Última ocorrência de 99 em arrNaoOrdenado: " + buscaLinear.buscarUltimaOcorrencia(99) + " (Esperado: -1)");
        buscaLinear.setInfo(arrTodosIguais);
        System.out.println("  Primeira ocorrência de 7 em arrTodosIguais: " + buscaLinear.buscarPrimeiraOcorrencia(7) + " (Esperado: 0)");
        System.out.println("  Última ocorrência de 7 em arrTodosIguais: " + buscaLinear.buscarUltimaOcorrencia(7) + " (Esperado: 4)");
    }

    private static void testBuscaLinearVetorOrdenado(Integer[] arrOrdenado, Integer[] arrVazio, Integer[] arrUnico, Integer[] arrTodosIguais) {
        BuscaLinearVetorOrdenado<Integer> buscaLinearOrdenado = new BuscaLinearVetorOrdenado<>();

        System.out.println("-> Testes Básicos de Busca (buscar):");
        buscaLinearOrdenado.setInfo(arrOrdenado);
        System.out.println("  Buscar 15 em arrOrdenado: " + buscaLinearOrdenado.buscar(15) + " (Esperado: 4)");
        System.out.println("  Comparacoes: " + buscaLinearOrdenado.contarComparacoesBuscaLinear());
        System.out.println("  Buscar 99 em arrOrdenado: " + buscaLinearOrdenado.buscar(99) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaLinearOrdenado.contarComparacoesBuscaLinear());
        buscaLinearOrdenado.setInfo(arrVazio);
        System.out.println("  Buscar 5 em arrVazio: " + buscaLinearOrdenado.buscar(5) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaLinearOrdenado.contarComparacoesBuscaLinear());

        System.out.println("\n-> Testes de Ocorrências (buscarPrimeiraOcorrencia, buscarUltimaOcorrencia):");
        buscaLinearOrdenado.setInfo(arrOrdenado);
        System.out.println("  Primeira ocorrência de 30: " + buscaLinearOrdenado.buscarPrimeiraOcorrencia(30) + " (Esperado: 7)");
        System.out.println("  Última ocorrência de 30: " + buscaLinearOrdenado.buscarUltimaOcorrencia(30) + " (Esperado: 8)");
        System.out.println("  Primeira ocorrência de 40: " + buscaLinearOrdenado.buscarPrimeiraOcorrencia(40) + " (Esperado: 10)");
        System.out.println("  Última ocorrência de 40: " + buscaLinearOrdenado.buscarUltimaOcorrencia(40) + " (Esperado: 12)");
        System.out.println("  Primeira ocorrência de 99: " + buscaLinearOrdenado.buscarPrimeiraOcorrencia(99) + " (Esperado: -1)");
        System.out.println("  Última ocorrência de 99: " + buscaLinearOrdenado.buscarUltimaOcorrencia(99) + " (Esperado: -1)");

        System.out.println("\n-> Testes de Intervalo (existeEntre, buscarTodosIntervalo (boolean), contarElementosNoIntervalo):");
        buscaLinearOrdenado.setInfo(arrOrdenado);
        System.out.println("  Existe entre 10 e 20: " + buscaLinearOrdenado.existeEntre(10, 20) + " (Esperado: true)");
        System.out.println("  Existe entre 2 e 4: " + buscaLinearOrdenado.existeEntre(2, 4) + " (Esperado: false)");
        System.out.println("  Buscar todos intervalo (boolean) 20-35: " + buscaLinearOrdenado.buscarTodosIntervalo(20, 35) + " (Esperado: true)");
        System.out.println("  Contar elementos no intervalo 20-35: " + buscaLinearOrdenado.contarElementosNoIntervalo(20, 35) + " (Esperado: 5)");
        System.out.println("  Contar elementos no intervalo 40-40: " + buscaLinearOrdenado.contarElementosNoIntervalo(40, 40) + " (Esperado: 3)");
        System.out.println("  Contar elementos no intervalo 2-4: " + buscaLinearOrdenado.contarElementosNoIntervalo(2, 4) + " (Esperado: 0)");
    }

    private static void testBuscaBinaria(Integer[] arrOrdenado, Integer[] arrVazio, Integer[] arrUnico, Integer[] arrTodosIguais) {
        BuscaBinaria<Integer> buscaBinaria = new BuscaBinaria<>();

        System.out.println("-> Testes Básicos de Busca (buscar):");
        buscaBinaria.setInfo(arrOrdenado);
        System.out.println("  Buscar 12 em arrOrdenado: " + buscaBinaria.buscar(12) + " (Esperado: 3)");
        System.out.println("  Comparacoes: " + buscaBinaria.contarComparacoesBuscaBinaria());
        System.out.println("  Buscar 30 em arrOrdenado: " + buscaBinaria.buscar(30) + " (Esperado: 7 ou 8)");
        System.out.println("  Comparacoes: " + buscaBinaria.contarComparacoesBuscaBinaria());
        System.out.println("  Buscar 99 em arrOrdenado: " + buscaBinaria.buscar(99) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaBinaria.contarComparacoesBuscaBinaria());

        System.out.println("\n-> Testes de Ocorrências (buscarPrimeiraOcorrencia, buscarUltimaOcorrencia):");
        buscaBinaria.setInfo(arrOrdenado);
        System.out.println("  Primeira ocorrência de 30: " + buscaBinaria.buscarPrimeiraOcorrencia(30) + " (Esperado: 7)");
        System.out.println("  Última ocorrência de 30: " + buscaBinaria.buscarUltimaOcorrencia(30) + " (Esperado: 8)");
        System.out.println("  Primeira ocorrência de 40: " + buscaBinaria.buscarPrimeiraOcorrencia(40) + " (Esperado: 10)");
        System.out.println("  Última ocorrência de 40: " + buscaBinaria.buscarUltimaOcorrencia(40) + " (Esperado: 12)");

        System.out.println("\n-> Testes de Intervalo (existeEntre, buscarTodosIntervalo (boolean), contarElementosNoIntervalo):");
        buscaBinaria.setInfo(arrOrdenado);
        System.out.println("  Existe entre 10 e 20: " + buscaBinaria.existeEntre(10, 20) + " (Esperado: true)");
        System.out.println("  Existe entre 2 e 4: " + buscaBinaria.existeEntre(2, 4) + " (Esperado: false)");
        System.out.println("  Buscar todos intervalo (boolean) 20-35: " + buscaBinaria.buscarTodosIntervalo(20, 35) + " (Esperado: true)");
        System.out.println("  Contar elementos no intervalo 20-35: " + buscaBinaria.contarElementosNoIntervalo(20, 35) + " (Esperado: 5)");
        System.out.println("  Contar elementos no intervalo 40-40: " + buscaBinaria.contarElementosNoIntervalo(40, 40) + " (Esperado: 3)");

        System.out.println("\n-> Testes de Busca Binária ou Inserção (buscaBinariaOuInserir):");
        buscaBinaria.setInfo(arrOrdenado);
        System.out.println("  Busca ou insere 12: " + buscaBinaria.buscaBinariaOuInserir(12) + " (Esperado: 3)");
        System.out.println("  Busca ou insere 13: " + buscaBinaria.buscaBinariaOuInserir(13) + " (Esperado: 4)");
        System.out.println("  Busca ou insere 0: " + buscaBinaria.buscaBinariaOuInserir(0) + " (Esperado: 0)");
        System.out.println("  Busca ou insere 100: " + buscaBinaria.buscaBinariaOuInserir(100) + " (Esperado: " + arrOrdenado.length + ")");
    }

    private static void testBuscaBinariaRecursiva(Integer[] arrOrdenado, Integer[] arrVazio, Integer[] arrUnico, Integer[] arrTodosIguais) {
        BuscaBinariaRecursiva<Integer> buscaBinariaRecursiva = new BuscaBinariaRecursiva<>();

        System.out.println("-> Testes Básicos de Busca (buscar):");
        buscaBinariaRecursiva.setInfo(arrOrdenado);
        System.out.println("  Buscar 12 em arrOrdenado: " + buscaBinariaRecursiva.buscar(12) + " (Esperado: 3)");
        System.out.println("  Comparacoes: " + buscaBinariaRecursiva.contarComparacoesBuscaBinaria());
        System.out.println("  Buscar 30 em arrOrdenado: " + buscaBinariaRecursiva.buscar(30) + " (Esperado: 7 ou 8)");
        System.out.println("  Comparacoes: " + buscaBinariaRecursiva.contarComparacoesBuscaBinaria());
        System.out.println("  Buscar 99 em arrOrdenado: " + buscaBinariaRecursiva.buscar(99) + " (Esperado: -1)");
        System.out.println("  Comparacoes: " + buscaBinariaRecursiva.contarComparacoesBuscaBinaria());

        System.out.println("\n-> Testes de Ocorrências (buscarPrimeiraOcorrencia, buscarUltimaOcorrencia):");
        buscaBinariaRecursiva.setInfo(arrOrdenado);
        System.out.println("  Primeira ocorrência de 30: " + buscaBinariaRecursiva.buscarPrimeiraOcorrencia(30) + " (Esperado: 7)");
        System.out.println("  Última ocorrência de 30: " + buscaBinariaRecursiva.buscarUltimaOcorrencia(30) + " (Esperado: 8)");
        System.out.println("  Primeira ocorrência de 40: " + buscaBinariaRecursiva.buscarPrimeiraOcorrencia(40) + " (Esperado: 10)");
        System.out.println("  Última ocorrência de 40: " + buscaBinariaRecursiva.buscarUltimaOcorrencia(40) + " (Esperado: 12)");

        System.out.println("\n-> Testes de Intervalo (existeEntre, buscarTodosIntervalo (boolean), contarElementosNoIntervalo):");
        buscaBinariaRecursiva.setInfo(arrOrdenado);
        System.out.println("  Existe entre 10 e 20: " + buscaBinariaRecursiva.existeEntre(10, 20) + " (Esperado: true)");
        System.out.println("  Existe entre 2 e 4: " + buscaBinariaRecursiva.existeEntre(2, 4) + " (Esperado: false)");
        System.out.println("  Buscar todos intervalo (boolean) 20-35: " + buscaBinariaRecursiva.buscarTodosIntervalo(20, 35) + " (Esperado: true)");
        System.out.println("  Contar elementos no intervalo 20-35: " + buscaBinariaRecursiva.contarElementosNoIntervalo(20, 35) + " (Esperado: 5)");
        System.out.println("  Contar elementos no intervalo 40-40: " + buscaBinariaRecursiva.contarElementosNoIntervalo(40, 40) + " (Esperado: 3)");

        System.out.println("\n-> Testes de Busca Binária ou Inserção (buscaBinariaOuInserir):");
        buscaBinariaRecursiva.setInfo(arrOrdenado);
        System.out.println("  Busca ou insere 12: " + buscaBinariaRecursiva.buscaBinariaOuInserir(12) + " (Esperado: 3)");
        System.out.println("  Busca ou insere 13: " + buscaBinariaRecursiva.buscaBinariaOuInserir(13) + " (Esperado: 4)");
        System.out.println("  Busca ou insere 0: " + buscaBinariaRecursiva.buscaBinariaOuInserir(0) + " (Esperado: 0)");
        System.out.println("  Busca ou insere 100: " + buscaBinariaRecursiva.buscaBinariaOuInserir(100) + " (Esperado: " + arrOrdenado.length + ")");
    }
}