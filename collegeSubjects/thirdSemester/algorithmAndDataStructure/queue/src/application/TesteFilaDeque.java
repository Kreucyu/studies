package application;

import model.entities.FilaDeque;

public class TesteFilaDeque {

	public static <T> void main(String[] args) {
		//teste fila deque
		FilaDeque<Integer> f1 = new FilaDeque<>(10);
		f1.inserir(1);
		f1.inserir(2);
		f1.inserir(3);
		f1.inserirInicio(4);
		System.out.println(f1.toString());
		System.out.println("\n" + f1.retirarFinal());
		System.out.println("\n" + f1.toString());
		System.out.println("\n" + f1.retirar());
		System.out.println("\n" + f1.toString());
		System.out.println("\n" + f1.retirarFinal());
		System.out.println("\n" + f1.toString());
		System.out.println("\n" + f1.retirarFinal());
		System.out.println("\n" + f1.toString());
	}

}
