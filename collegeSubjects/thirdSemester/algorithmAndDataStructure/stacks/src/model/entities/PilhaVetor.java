package model.entities;

import model.exception.PilhaCheiaException;
import model.exception.PilhaVaziaException;

public class PilhaVetor<T> implements Pilha<T> {
	private Object info[];
	private int limite;
	private int tamanho;

	@SuppressWarnings("unchecked")
	public PilhaVetor(int limite) {
		this.limite = limite;
		this.info = (T[]) new Object[limite];
		this.tamanho = 0;
	}

	@Override
	public void push(T info) {
		try {
		if (limite <= tamanho - 1) {
			throw new PilhaCheiaException("\nPilha cheia!");
		}
		this.info[++tamanho] = info;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("\nPilha cheia!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		try {
		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		return (T) this.info[tamanho--];
		} catch (PilhaVaziaException e) {
			System.out.println("\nPilha vazia!");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if (estaVazia()) {
			throw new PilhaVaziaException("\nPilha vazia!");
		}
		return (T) this.info[tamanho];
	}

	@Override
	public boolean estaVazia() {
		return (tamanho == 0);
	}

	@Override
	public void liberar() {
		while (!estaVazia()) {
			pop();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (!estaVazia()) {
			for (int i = tamanho; i > 0; i--) {
				sb.append(info[i]);
				if (i - 1 > 0) {
					sb.append(", ");
				}
			}
			return sb.toString();
		}
		sb.append("\nPilha vazia");
		return sb.toString();
	}
	
	public String toString1() {
		StringBuilder sb = new StringBuilder();
		if (!estaVazia()) {
			for (int i = tamanho; i >= 0; i--) {
				sb.append(info[i]);
				if (i > 0) {
					sb.append(", ");
				}
			}
			return sb.toString();
		}
		sb.append("\nPilha vazia");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public void concatenar(PilhaVetor<T> p) {
		if (limite <= tamanho - 1) {
			throw new PilhaCheiaException("\nPilha cheia!");
		}
		int conc = 0;
		for (int i = 0; i <= p.tamanho; i++) {
			if (p.info[i] != null) {
				conc++;
			}
		}
		if (limite - tamanho >= conc) {
			for (int i = 0; i <= p.tamanho; i++) {
				if (p.info[i] != null) {
					push((T) p.info[i]);
				}
			}

		} else {
			throw new PilhaCheiaException("\nNão há espaço para concatenar na pilha!");
		}
	}
}
