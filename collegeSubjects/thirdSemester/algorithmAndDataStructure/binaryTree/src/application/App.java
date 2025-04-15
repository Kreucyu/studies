package application;

import model.ArvoreBinaria;
import model.NoArvoreBinaria;

public class App {
	public static <T> void main(String args[]) {
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(5);
		System.out.println("Caso 1:");
		System.out.println("\nEstá vazia? estado: " + arvore.estaVazia());
		System.out.println("\nCaso 2:");
		arvore.setRaiz(no1);
		System.out.println("\nEstá vazia? estado: " + arvore.estaVazia());
		System.out.println("\nCaso 3:");
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(1);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(2);
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(3);
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(4);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(5);
		NoArvoreBinaria<Integer> no7 = new NoArvoreBinaria<>(6);
		no2.setEsquerda(no3);
		no2.setDireita(no4);
		no3.setDireita(no5);
		no4.setEsquerda(no6);
		no4.setDireita(no7);
		arvore.setRaiz(no2);
		System.out.println("\nÁrvore: " + arvore.toString());
		System.out.println("\nCaso 4:");
		if(arvore.pertence(1) == true) {
			System.out.println("\nValor encontrado (1)");
		} else {
			System.out.println("\nValor não encontrado (1)");
		}
		System.out.println("\nCaso 5:");
		if(arvore.pertence(3) == true) {
			System.out.println("\nValor encontrado (3)");
		} else {
			System.out.println("\nValor não encontrado (3)");
		}
		System.out.println("\nCaso 6:");
		if(arvore.pertence(6) == true) {
			System.out.println("\nValor encontrado (6)");
		} else {
			System.out.println("\nValor não encontrado (6)");
		}
		System.out.println("\nCaso 7:");
		if(arvore.pertence(10) == true) {
			System.out.println("\nValor encontrado (10)");
		} else {
			System.out.println("\nValor não encontrado (10)");
		}
		System.out.println("\nCaso 8:");
		System.out.println("\nQuantidade de nós: " + arvore.contarNos());
		
	}
}
