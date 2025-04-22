package application;

import model.ArvoreBinaria;
import model.NoArvoreBinaria;

public class App {

	public static void main(String[] args) {
		ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
		System.out.println("estaVazia() = " + arvore.estaVazia());
		NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(5);
		arvore.setRaiz(no5);
		System.out.println("\nestaVazia() = " + arvore.estaVazia());
		ArvoreBinaria<Integer> arvore1 = new ArvoreBinaria<>();
		NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1);
		NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2);
		NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3);
		NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
		NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(6);
		arvore1.setRaiz(no1);
		no1.setEsquerda(no2);
		no1.setDireita(no3);
		no2.setDireita(no4);
		no3.setEsquerda(no5);
		no3.setDireita(no6);
		System.out.println("\ntoString() = " + arvore1.toString());
		System.out.println("\npertence(1) = " + arvore1.pertence(1));
		System.out.println("\npertence(3) = " + arvore1.pertence(3));
		System.out.println("\npertence(6) = " + arvore1.pertence(6));
		System.out.println("\npertence(10) = " + arvore1.pertence(10));
		System.out.println("\ncontarNos() = " + arvore1.contarNos());
		System.out.println("\ngetAltura() = " + arvore1.getAltura());
		System.out.println("\nDistancia entre nós (2, 6) = " + arvore1.calcDisNosQtde(2, 6));
		System.out.println("\nDistancia altura entre nós (2, 6) = " + arvore1.calcularDistanciaAltura(2, 6));

	}

}
