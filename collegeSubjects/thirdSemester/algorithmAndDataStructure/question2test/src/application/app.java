package application;

import model.listaEncadeada;
import model.listaEstatica;

public class app {

	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) {
		listaEncadeada<T> lista = new listaEncadeada<>();
		listaEstatica<T> lista2 = new listaEstatica<>();
		lista.inserir((T) Integer.valueOf(40));
		lista.inserir((T) Integer.valueOf(30));
		lista.inserir((T) Integer.valueOf(20));
		lista.inserir((T) Integer.valueOf(10));
		lista2.inserir((T) Integer.valueOf(50));
		lista2.inserir((T) Integer.valueOf(60));
		lista2.inserir((T) Integer.valueOf(70));
		lista.anexar(lista2);
		System.out.println(lista.toString());
	}
}

