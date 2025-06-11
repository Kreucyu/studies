package application;

import entities.ArvoreBinaria;
import entities.ArvoreBinariaBusca;
import entities.NoArvoreBinaria;

public class App {
	public static void main(String[] args) {
		testeBinaria();
		ArvoreBinariaBusca<Integer> searchT = new ArvoreBinariaBusca<>();
		int dados[] = { 50, 30, 70, 40, 25, 75, 65, 35, 60 };
		for(int n : dados) {
			searchT.inserir(n);
		}
		System.out.println("Árvore Binária de Busca: " + searchT);
		System.out.println("Busca (70): " + searchT.buscar(70));
		System.out.println("Busca (69): " + searchT.buscar(69));
		
	      System.out.println("Caso 1: Inserção e estrutura da árvore");
	        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();
	        int[] dados1 = {50, 30, 70, 40, 25, 75, 65, 35, 60};
	        for (int v : dados1) {
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
	
	public static void testeBinaria() {
		ArvoreBinaria<Integer> binaryT = new ArvoreBinaria<>();
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1);
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3);
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(5);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(6);
		binaryT.setRaiz(no1);
		no1.setEsquerda(no2);
		no1.setDireita(no3);
		no2.setDireita(no4);
		no3.setEsquerda(no5);
		no3.setDireita(no6);
		System.out.println(binaryT.buscar(4));
	}
}
