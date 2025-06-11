package application;

import entities.NoArvoreBinaria;
import entities.ArvoreBinaria;
import entities.ArvoreBinariaBusca;

public class TesteArvores {

    public static void main(String[] args) {
        System.out.println("--- Testes ArvoreBinariaBusca ---");
        testeArvoreBinariaBusca();

        System.out.println("\n--- Testes ArvoreBinaria (Generica) ---");
        testeArvoreBinariaGenerica();
    }

    public static void testeArvoreBinariaBusca() {
        ArvoreBinariaBusca<Integer> bst = new ArvoreBinariaBusca<>();

        System.out.println("BST está vazia? " + bst.estaVazia());

        // Inserção de elementos
        bst.inserir(50);
        bst.inserir(30);
        bst.inserir(70);
        bst.inserir(20);
        bst.inserir(40);
        bst.inserir(60);
        bst.inserir(80);
        bst.inserir(10);
        bst.inserir(35);
        bst.inserir(45);

        System.out.println("Árvore após inserções (Pré-ordem): " + bst.toString()); // Pré-ordem
        System.out.println("BST está vazia? " + bst.estaVazia());

        // Buscar
        System.out.println("Buscando 40: " + (bst.buscar(40) != null ? bst.buscar(40).getInfo() : "Não encontrado")); // Buscar
        System.out.println("Buscando 99: " + (bst.buscar(99) != null ? bst.buscar(99).getInfo() : "Não encontrado")); // Buscar
        System.out.println("Pertence 30? " + bst.pertence(30)); // Pertence
        System.out.println("Pertence 100? " + bst.pertence(100)); // Pertence
        System.out.println("Buscando recursivo 60: " + (bst.buscarRecursivo(60) != null ? bst.buscarRecursivo(60).getInfo() : "Não encontrado")); // Buscar recursivo

        // Encontra maior e menor
        System.out.println("Maior elemento: " + bst.encontraMaior()); // Encontra maior
        System.out.println("Menor elemento: " + bst.encontraMenor()); // Encontra menor

        // Altura e Contar Nós
        System.out.println("Altura da árvore: " + bst.altura()); // Altura
        System.out.println("Número total de nós: " + bst.contarNos()); // Contar nós

        // Verificar se é válida (BST)
        System.out.println("A BST é válida? " + bst.verificarSeEhValida()); // Verificar se é válida

        // Contar nós em intervalo
        System.out.println("Nós entre 30 e 70: " + bst.contarNosEmIntervalo(30, 70)); // Contar nós em intervalo
        System.out.println("Nós entre 5 e 15: " + bst.contarNosEmIntervalo(5, 15)); // Contar nós em intervalo

        // Mostrar em ordem decrescente
        System.out.println("Elementos em ordem decrescente: " + bst.mostrarEmOrdemDecrescente()); // Mostrar em ordem decrescente

        // Mostrar em ordem crescente
        System.out.println("Elementos em ordem crescente: " + bst.mostrarEmOrdemCrescente()); // Mostrar em ordem crescente

        // Contar pares
        System.out.println("Número de nós com valores pares: " + bst.contarPares()); // Contar pares

        // Esta balanceada
        System.out.println("A árvore está balanceada? " + bst.estaBalanceada()); // Está balanceada

        // É degenerada
        System.out.println("A árvore é degenerada? " + bst.isDegenerada()); // É degenerada

        // Contar nós com um filho, dois filhos, folhas, internos
        System.out.println("Número de nós folha: " + bst.contarNosFolha()); // Contar nós folha
        System.out.println("Número de nós com um filho: " + bst.contarNosComUmFilho()); // Contar nós com um filho
        System.out.println("Número de nós com dois filhos: " + bst.contarNosComDoisFilhos()); // Contar nós com 2 filhos
        System.out.println("Número de nós internos: " + bst.contarNosInternos()); // Contar nós internos
        System.out.println("Número total de filhos (pela soma dos filhos): " + bst.contarTotalFilhos()); // Contar filhos

        // Listar em ordem (já coberto por mostrarEmOrdemCrescente e exibirEmOrdem)
        System.out.println("Listar em ordem (exibirEmOrdem): " + bst.exibirEmOrdem()); // Listar em ordem
        System.out.println("Pré-ordem: " + bst.exibirPreOrdem()); // Pré-ordem
        System.out.println("Pós-ordem: " + bst.exibirPosOrdem()); // Pós-ordem

        // Somar valores
        System.out.println("Soma dos valores: " + bst.somarValores()); // Somar valores

        // Inverter
        System.out.println("Árvore antes de inverter (Pré-ordem): " + bst.exibirPreOrdem());
        bst.inverter(); // Inverter
        System.out.println("Árvore depois de inverter (Pré-ordem): " + bst.exibirPreOrdem()); // Agora será em ordem decrescente logicamente

        // Testar encontra maior e menor depois de inverter (os valores se invertem)
        System.out.println("Maior elemento após inverter: " + bst.encontraMaior()); // Encontra maior
        System.out.println("Menor elemento após inverter: " + bst.encontraMenor()); // Encontra menor
        
        // Desfazer a inversão para testes posteriores de BST
        bst.inverter(); 
        System.out.println("Árvore após desfazer inversão (Pré-ordem): " + bst.exibirPreOrdem());

        // Testar é completa e é cheia
        System.out.println("A árvore é completa? " + bst.eCompleta()); // É completa
        System.out.println("A árvore é cheia? " + bst.eCheia()); // É cheia

        // Contar nós em nível
        System.out.println("Número de nós no nível 0: " + bst.contarNosNivel(0)); // Contar nós nível
        System.out.println("Número de nós no nível 1: " + bst.contarNosNivel(1)); // Contar nós nível
        System.out.println("Número de nós no nível 2: " + bst.contarNosNivel(2)); // Contar nós nível
        System.out.println("Número de nós no nível 3: " + bst.contarNosNivel(3)); // Contar nós nível

        // Contar folhas em nível
        System.out.println("Número de folhas no nível 3: " + bst.contarFolhasNivel(3)); // Contar folhas em nível

        // Menor maior que / Maior menor que
        System.out.println("Menor maior que 40: " + bst.menorMaiorQue(40)); // Menor maior que
        System.out.println("Maior menor que 40: " + bst.maiorMenorQue(40)); // Maior menor que
        System.out.println("Menor maior que 80: " + bst.menorMaiorQue(80)); // Menor maior que
        System.out.println("Maior menor que 10: " + bst.maiorMenorQue(10)); // Maior menor que


        // Testes de remoção
        System.out.println("\n--- Testes de Remoção ---");
        System.out.println("Árvore antes da remoção (Pré-ordem): " + bst.exibirPreOrdem());

        bst.retirar(20); // Remover nó folha
        System.out.println("Árvore após remover 20 (Pré-ordem): " + bst.exibirPreOrdem());
        System.out.println("Buscando 20: " + (bst.buscar(20) != null ? bst.buscar(20).getInfo() : "Não encontrado"));

        bst.retirar(70); // Remover nó com 2 filhos (substitui por sucessor)
        System.out.println("Árvore após remover 70 (Pré-ordem): " + bst.exibirPreOrdem());
        System.out.println("Buscando 70: " + (bst.buscar(70) != null ? bst.buscar(70).getInfo() : "Não encontrado"));

        bst.retirar(30); // Remover nó com 1 filho (direito)
        System.out.println("Árvore após remover 30 (Pré-ordem): " + bst.exibirPreOrdem());
        System.out.println("Buscando 30: " + (bst.buscar(30) != null ? bst.buscar(30).getInfo() : "Não encontrado"));
        
        bst.retirar(50); // Remover raiz
        System.out.println("Árvore após remover 50 (Pré-ordem): " + bst.exibirPreOrdem());
        System.out.println("Buscando 50: " + (bst.buscar(50) != null ? bst.buscar(50).getInfo() : "Não encontrado"));

        bst.retirar(100); // Remover elemento inexistente
        System.out.println("Árvore após tentar remover 100 (Pré-ordem): " + bst.exibirPreOrdem());
    }

    public static void testeArvoreBinariaGenerica() {
        ArvoreBinaria<String> arvore = new ArvoreBinaria<>();

        // Criação manual da árvore para demonstrar a ArvoreBinaria genérica
        //           "B"
        //          /   \
        //        "A"   "C"
        //              /   \
        //            "D"   "E"
        
        NoArvoreBinaria<String> noA = new NoArvoreBinaria<>("A"); //
        NoArvoreBinaria<String> noD = new NoArvoreBinaria<>("D"); //
        NoArvoreBinaria<String> noE = new NoArvoreBinaria<>("E"); //
        NoArvoreBinaria<String> noC = new NoArvoreBinaria<>("C", noD, noE); //
        NoArvoreBinaria<String> noB = new NoArvoreBinaria<>("B", noA, noC); //

        arvore.setRaiz(noB); //

        System.out.println("Árvore genérica (Pré-ordem): " + arvore.exibirPreOrdem()); // Pré-ordem
        System.out.println("Árvore genérica (Em Ordem): " + arvore.exibirEmOrdem()); // Em ordem
        System.out.println("Árvore genérica (Pós-ordem): " + arvore.exibirPosOrdem()); // Pós-ordem

        // Buscar
        System.out.println("Buscando 'C': " + (arvore.buscar("C") != null ? arvore.buscar("C").getInfo() : "Não encontrado")); // Buscar
        System.out.println("Buscando 'Z': " + (arvore.buscar("Z") != null ? arvore.buscar("Z").getInfo() : "Não encontrado")); // Buscar
        System.out.println("Buscando recursivo 'A': " + (arvore.buscarRecursivo("A") != null ? arvore.buscarRecursivo("A").getInfo() : "Não encontrado")); // Buscar recursivo

        // Contar Nós
        System.out.println("Número total de nós na árvore genérica: " + arvore.contarNos()); // Contar nós
        System.out.println("Altura da árvore genérica: " + arvore.altura()); // Altura
        System.out.println("Número de nós folha: " + arvore.contarNosFolha()); // Contar nós folha
        System.out.println("Número de nós internos: " + arvore.contarNosInternos()); // Contar nós internos
        System.out.println("Número de nós com um filho: " + arvore.contarNosComUmFilho()); // Contar nós com um filho
        System.out.println("Número de nós com dois filhos: " + arvore.contarNosComDoisFilhos()); // Contar nós com 2 filhos

        // Esta balanceada
        System.out.println("A árvore genérica está balanceada? " + arvore.estaBalanceada()); // Está balanceada

        // Inverter
        System.out.println("Árvore genérica antes de inverter (Pré-ordem): " + arvore.exibirPreOrdem());
        arvore.inverter(); // Inverter
        System.out.println("Árvore genérica depois de inverter (Pré-ordem): " + arvore.exibirPreOrdem());

        // Testar é completa e é cheia
        System.out.println("A árvore genérica é completa? " + arvore.eCompleta()); // É completa
        System.out.println("A árvore genérica é cheia? " + arvore.eCheia()); // É cheia

        // Criando uma árvore cheia para teste
        ArvoreBinaria<Integer> arvoreCheia = new ArvoreBinaria<>();
        arvoreCheia.setRaiz(new NoArvoreBinaria<>(1, new NoArvoreBinaria<>(2, new NoArvoreBinaria<>(4), new NoArvoreBinaria<>(5)), new NoArvoreBinaria<>(3, new NoArvoreBinaria<>(6), new NoArvoreBinaria<>(7)))); //
        System.out.println("Árvore de teste cheia (Pré-ordem): " + arvoreCheia.exibirPreOrdem());
        System.out.println("A árvore de teste cheia é cheia? " + arvoreCheia.eCheia()); // É cheia
        System.out.println("A árvore de teste cheia é completa? " + arvoreCheia.eCompleta()); // É completa

        // Contar nós em nível
        System.out.println("Número de nós no nível 0 da árvore genérica: " + arvore.contarNosNivel(0)); // Contar nós nível
        System.out.println("Número de nós no nível 1 da árvore genérica: " + arvore.contarNosNivel(1)); // Contar nós nível
        System.out.println("Número de nós no nível 2 da árvore genérica: " + arvore.contarNosNivel(2)); // Contar nós nível
        System.out.println("Número de nós no nível 3 da árvore genérica: " + arvore.contarNosNivel(3)); // Contar nós nível
        
        // Contar folhas em nível
        System.out.println("Número de folhas no nível 2 da árvore genérica: " + arvore.contarFolhasNivel(2)); // Contar folhas em nível

        // Testar se são iguais
        ArvoreBinaria<String> outraArvore = new ArvoreBinaria<>();
        NoArvoreBinaria<String> outraNoA = new NoArvoreBinaria<>("A"); //
        NoArvoreBinaria<String> outraNoD = new NoArvoreBinaria<>("D"); //
        NoArvoreBinaria<String> outraNoE = new NoArvoreBinaria<>("E"); //
        NoArvoreBinaria<String> outraNoC = new NoArvoreBinaria<>("C", outraNoD, outraNoE); //
        NoArvoreBinaria<String> outraNoB = new NoArvoreBinaria<>("B", outraNoA, outraNoC); //
        outraArvore.setRaiz(outraNoB); //
        
        System.out.println("As duas árvores genéricas são iguais? " + arvore.saoIguais(outraArvore)); // São iguais

        ArvoreBinaria<String> arvoreDiferente = new ArvoreBinaria<>();
        arvoreDiferente.setRaiz(new NoArvoreBinaria<>("X")); //
        System.out.println("A árvore genérica é igual a uma árvore com apenas 'X'? " + arvore.saoIguais(arvoreDiferente)); // São iguais
    }
}