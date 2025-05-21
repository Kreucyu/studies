package model.entities;

import model.exceptions.PilhaVaziaException;

public class PilhaLista<T> implements Pilha<T> {
	private ListaEncadeada<T> lista;

	public PilhaLista() {
		lista = new ListaEncadeada<>();
	}

	@Override
	public void push(T info) {
		lista.inserir(info);
	}

	@Override
	public T pop() {
		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		T valor = peek();
		if (valor != null) {
			lista.retirar(valor);
			return valor;
		} else {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
	}

	@Override
	public T peek() {

		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		return lista.getPrimeiro().getInfo();
	}

	@Override
	public boolean estaVazia() {
		return lista.estaVazia();
	}

	@Override
	public void liberar() {
		while (!estaVazia()) {
			pop();
		}
	}

	@Override
	public String toString() {
		return lista.toString();
	}

}
