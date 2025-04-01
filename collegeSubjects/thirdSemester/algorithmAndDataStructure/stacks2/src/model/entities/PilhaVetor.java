package model.entities;

import model.exception.PilhaVaziaException;

public class PilhaVetor<T> implements Pilha<T> {
	private listaEncadeada<T> lista;

	public PilhaVetor(int limite) {
		lista = new listaEncadeada<>();
	}

	@Override
	public void push(T info) {
		lista.inserir(info);
	}

	@Override
	public T pop() {
		try {
		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		T valor = peek();
		if(valor != null) {
			lista.retirar(valor);
			return valor;
		} else {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		} catch (PilhaVaziaException e) {
			System.out.println("\nPilha vazia!");
			return null;
		}
	}

	@Override
	public T peek() {
		try {
		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		return (T) lista.getPrimeiro().getInfo();
		} catch (PilhaVaziaException e) {
			System.out.println("\nPilha vazia!");
			return null;
		}
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
