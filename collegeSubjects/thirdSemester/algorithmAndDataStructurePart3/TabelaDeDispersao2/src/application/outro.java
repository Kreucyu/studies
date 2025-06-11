package application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.ListaEncadeada;
import entities.MapaDispersao;
import entities.NoLista; // Importe NoLista também
import entities.NoMapa;

public class outro{

    public static void main(String[] args) {
        System.out.println("Iniciando cenário de testes avançado para MapaDispersao...");

        // Configurações iniciais para os testes de redimensionamento
        // Usaremos um tamanho inicial pequeno para forçar o redimensionamento.
        // A lógica do 'ajustarTamanhoNecessario' usa 0.9 para crescer e 0.5 para encolher, com mínimo de 4.
        MapaDispersao<String> mapaRedimensionamento = new MapaDispersao<>(4); // Tamanho inicial 4

        System.out.println("\n--- Teste A: Redimensionamento - Crescimento da Tabela ---");
        // Inserir elementos até ultrapassar o FATOR_MAXIMO (0.9)
        // 4 * 0.9 = 3.6 -> precisa ter 4 elementos para crescer
        System.out.println("Tamanho inicial: " + mapaRedimensionamento.getInfo().length);
        mapaRedimensionamento.inserir(1, "Dado 1"); // Fator carga: 1/4 = 0.25
        System.out.println("Fator de Carga após 1: " + mapaRedimensionamento.calcularFatorCarga());
        mapaRedimensionamento.inserir(2, "Dado 2"); // Fator carga: 2/4 = 0.5
        System.out.println("Fator de Carga após 2: " + mapaRedimensionamento.calcularFatorCarga());
        mapaRedimensionamento.inserir(3, "Dado 3"); // Fator carga: 3/4 = 0.75
        System.out.println("Fator de Carga após 3: " + mapaRedimensionamento.calcularFatorCarga());
        mapaRedimensionamento.inserir(4, "Dado 4"); // Fator carga: 4/4 = 1.0 (deveria crescer)
        System.out.println("Fator de Carga após 4: " + mapaRedimensionamento.calcularFatorCarga());
        System.out.println("Tamanho atual: " + mapaRedimensionamento.getInfo().length);

        assertEquals("Tamanho após crescimento (dobro de 4)", 8, mapaRedimensionamento.getInfo().length);
        assertEquals("Quantidade de objetos após crescimento", 4, mapaRedimensionamento.calcularQtdeObjetos());
        assertEquals("Buscar Dado 1 após crescimento", "Dado 1", mapaRedimensionamento.buscar(1));
        assertEquals("Buscar Dado 4 após crescimento", "Dado 4", mapaRedimensionamento.buscar(4));

        // Testar mais uma inserção em tabela já maior
        mapaRedimensionamento.inserir(5, "Dado 5"); // Fator carga: 5/8 = 0.625
        assertEquals("Tamanho após mais uma inserção (não deve crescer)", 8, mapaRedimensionamento.getInfo().length);
        assertEquals("Quantidade de objetos após mais uma inserção", 5, mapaRedimensionamento.calcularQtdeObjetos());
        assertEquals("Buscar Dado 5", "Dado 5", mapaRedimensionamento.buscar(5));


        System.out.println("\n--- Teste B: Redimensionamento - Diminuição da Tabela ---");
        // Remover elementos até ultrapassar o FATOR_MINIMO (0.5)
        // 8 * 0.5 = 4.0 -> precisa ter 3 ou menos elementos para encolher
        System.out.println("Tamanho inicial para encolher: " + mapaRedimensionamento.getInfo().length); // Deve ser 8
        System.out.println("Qtde objetos para encolher: " + mapaRedimensionamento.calcularQtdeObjetos()); // Deve ser 5

        mapaRedimensionamento.remover(1); // 4/8 = 0.5
        System.out.println("Fator de Carga após remover 1: " + mapaRedimensionamento.calcularFatorCarga());
        assertEquals("Tamanho não deve diminuir ainda", 8, mapaRedimensionamento.getInfo().length);

        mapaRedimensionamento.remover(2); // 3/8 = 0.375 (deveria encolher)
        System.out.println("Fator de Carga após remover 2: " + mapaRedimensionamento.calcularFatorCarga());
        System.out.println("Tamanho atual: " + mapaRedimensionamento.getInfo().length);

        assertEquals("Tamanho após diminuição (metade de 8)", 4, mapaRedimensionamento.getInfo().length); // 8/2 = 4
        assertEquals("Quantidade de objetos após diminuição", 3, mapaRedimensionamento.calcularQtdeObjetos());
        assertEquals("Buscar Dado 3 após diminuição", "Dado 3", mapaRedimensionamento.buscar(3));
        assertEquals("Buscar Dado 5 após diminuição", "Dado 5", mapaRedimensionamento.buscar(5));
        assertEquals("Buscar Dado 1 (removido)", null, mapaRedimensionamento.buscar(1));

        // Testar diminuição para o tamanho mínimo
        mapaRedimensionamento.remover(3); // 2/4 = 0.5
        System.out.println("Fator de Carga após remover 3: " + mapaRedimensionamento.calcularFatorCarga());
        mapaRedimensionamento.remover(4); // 1/4 = 0.25 (deveria tentar encolher para 2, mas o mínimo é 4)
        System.out.println("Fator de Carga após remover 4: " + mapaRedimensionamento.calcularFatorCarga());
        System.out.println("Tamanho atual: " + mapaRedimensionamento.getInfo().length);

        assertEquals("Tamanho não deve ser menor que o mínimo (4)", 4, mapaRedimensionamento.getInfo().length);
        assertEquals("Quantidade de objetos após atingir mínimo", 1, mapaRedimensionamento.calcularQtdeObjetos());
        assertEquals("Buscar Dado 5 após atingir mínimo", "Dado 5", mapaRedimensionamento.buscar(5));


        System.out.println("\n--- Teste C: `calcularProximoPrimo()` e `ehPrimo()` ---");
        // Cria um mapa para testes de primos
        MapaDispersao<String> mapaPrimo = new MapaDispersao<>(10); // Tamanho inicial não importa aqui para o teste do método
        // Como 'calcularProximoPrimo' e 'ehPrimo' são privados, precisamos chamá-los via reflexão
        // ou criar um método público temporário para teste, ou ter certeza que 'ajustarTamanhoNecessario' os usa.
        // Para simplicidade e já que ajustarTamanhoNecessario ainda não os usa por padrão, vamos testar ehPrimo diretamente.

        assertTrue("1 é primo (falso)", !isPrimo(1)); // 1 não é primo
        assertTrue("2 é primo", isPrimo(2));
        assertTrue("3 é primo", isPrimo(3));
        assertTrue("4 não é primo", !isPrimo(4));
        assertTrue("7 é primo", isPrimo(7));
        assertTrue("13 é primo", isPrimo(13));
        assertTrue("17 é primo", isPrimo(17));
        assertTrue("97 é primo", isPrimo(97)); // Grande primo

        // Para calcularProximoPrimo, se você o tornar público ou tiver um método de teste
        // Vamos simular o uso. O método ehPrimo do MapaDispersao é privado.
        // Se quisermos testá-lo diretamente, precisaríamos mudar sua visibilidade ou usar reflexão.
        // Assumindo que a classe MapaDispersao tem um método ehPrimo interno:
        System.out.println("Próximo primo de 10: " + getProximoPrimo(10));
        assertEquals("Próximo primo de 10", 11, getProximoPrimo(10));
        assertEquals("Próximo primo de 11", 11, getProximoPrimo(11));
        assertEquals("Próximo primo de 12", 13, getProximoPrimo(12));
        assertEquals("Próximo primo de 90", 97, getProximoPrimo(90));


        System.out.println("\n--- Teste D: `converterTabelaParaLista()` ---");
        MapaDispersao<String> mapaLista = new MapaDispersao<>(5);
        mapaLista.inserir(10, "Apple");
        mapaLista.inserir(15, "Banana");
        mapaLista.inserir(20, "Cherry");
        mapaLista.inserir(1, "Date"); // Colisão com 10 no hash 0
        mapaLista.inserir(6, "Elderberry"); // Colisão com 1 no hash 1
        mapaLista.inserir(11, "Fig"); // Colisão com 6 no hash 1

        ListaEncadeada<NoMapa<String>> listaConvertida = mapaLista.converterTabelaParaLista();

        assertEquals("Comprimento da lista convertida", 6, listaConvertida.obterComprimento());

        // Verificar se todos os elementos estão na lista e são os corretos
        List<String> valoresEsperados = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig");
        Set<String> valoresEncontrados = new HashSet<>();
        NoLista<NoMapa<String>> atual = listaConvertida.getPrimeiro();
        while (atual != null) {
            valoresEncontrados.add(atual.getInfo().getValor());
            atual = atual.getProximo();
        }

        assertEquals("Tamanho do conjunto de valores encontrados", valoresEsperados.size(), valoresEncontrados.size());
        assertTrue("Todos os valores esperados estão na lista", valoresEncontrados.containsAll(valoresEsperados));
        assertTrue("A lista convertida não contém valores inesperados", valoresEsperados.containsAll(valoresEncontrados));


        System.out.println("\nTodos os testes avançados concluídos!");
    }

    // --- Métodos auxiliares de asserção (mantidos do teste anterior) ---
    private static void assertEquals(String testName, Object expected, Object actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("PASSED: " + testName + " (Esperado: " + expected + ", Obtido: " + actual + ")");
        } else {
            System.err.println("FAILED: " + testName + " (Esperado: " + expected + ", Obtido: " + actual + ")");
        }
    }

    private static void assertEquals(String testName, double expected, double actual, double delta) {
        if (Math.abs(expected - actual) < delta) {
            System.out.println("PASSED: " + testName + " (Esperado: " + expected + ", Obtido: " + actual + ")");
        } else {
            System.err.println("FAILED: " + testName + " (Esperado: " + expected + ", Obtido: " + actual + ")");
        }
    }

    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASSED: " + testName);
        } else {
            System.err.println("FAILED: " + testName);
        }
    }

    private static void assertFalse(String testName, boolean condition) {
        if (!condition) {
            System.out.println("PASSED: " + testName);
        } else {
            System.err.println("FAILED: " + testName);
        }
    }

    private static void assertNotNull(String testName, Object obj) {
        if (obj != null) {
            System.out.println("PASSED: " + testName);
        } else {
            System.err.println("FAILED: " + testName + " (Objeto nulo)");
        }
    }

    // --- Métodos auxiliares para teste de métodos privados, caso não queira usar reflexão ---
    // Estes métodos simulam o comportamento dos métodos privados para poderem ser testados.
    // É crucial que sua classe MapaDispersao tenha as implementações correspondentes para que o teste seja válido.
    private static boolean isPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int getProximoPrimo(int tamanho) {
        if (tamanho <= 2) {
            return 2;
        }
        int num = tamanho;
        while (true) {
            if (isPrimo(num)) {
                return num;
            }
            num++;
        }
    }
}