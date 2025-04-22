package model.entities;

import model.exception.FilaCheiaException;
import model.exception.FilaVaziaException;

public class FilaVetor<T> implements Fila<T> {
	private Object[] info;
	private int limite;
	private int tamanho;
	private int inicio;

	@SuppressWarnings("unchecked")
	public FilaVetor(int limite) {
		this.limite = limite;
		this.info = (T[]) new Object[limite];
		this.tamanho = 0;
	}

	@Override
	public void inserir(T valor) {
		if (tamanho == limite) {
			System.out.println("\nFila cheia! redimensionando...");
			redimensionar();
		}
		int pos = (inicio + tamanho) % limite;
		info[pos] = valor;
		tamanho++;
	}

	@Override
	public boolean estaVazia() {
		return tamanho == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() {
		if (estaVazia()) {
			throw new FilaVaziaException();
		}
		return (T) info[inicio];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T retirar() {
		int pos = inicio;
		if (estaVazia()) {
			throw new FilaVaziaException();
		}
		if (tamanho >= 1 && inicio + 1 == limite) {
			inicio = 0;
		} else {
			inicio++;
		}
		tamanho--;
		return (T) info[pos];
	}

	@Override
	public void liberar() {
		while (!estaVazia()) {
			retirar();
		}
	}

	public FilaVetor<T> criarFilaConcatenada(FilaVetor<T> f2) {
		FilaVetor<T> f3 = new FilaVetor<>(getLimite() + f2.getLimite());
		int fim = ((inicio + tamanho) % limite) - 1;
		int temp = inicio;
		while (inicio <= fim) {
			f3.inserir(peek());
			inicio++;
		}
		int fim1 = ((f2.inicio + f2.tamanho) % f2.limite) - 1;
		int temp1 = f2.inicio;
		while (f2.inicio <= fim1) {
			f3.inserir(f2.peek());
			f2.inicio++;
		}
		inicio = temp;
		f2.inicio = temp1;
		return f3;

	}

	public int getLimite() {
		return limite;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (inicio > limite) {
			inicio = 0;
		}
		int fim;
		int var = (inicio + tamanho) % limite - 1;
		if (var == -1) {
			fim = limite - 1;
		} else {
			fim = var;
		}
		int temp = inicio;
		if (estaVazia()) {
			sb.append("Fila vazia!");
		} else {
		while (!(temp == fim + 1))  { //valor de fim +1 acabou ficando que nem inicio, tostring com erro.
			sb.append(info[temp]);
			if (!(temp == fim)) {
				sb.append(", ");
			}
			if (temp + 1 == limite && fim + 1 != limite) {
				temp = 0;
			} else {
				temp++;
			}
		} 
	}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public void encurtar() {
		if (tamanho == 0) {
			throw new FilaVaziaException();
		}
		FilaVetor<Object> f2 = new FilaVetor<>(tamanho);
		int temp = inicio;
		while (f2.tamanho != tamanho) {
			if(temp == limite) {
				temp = 0;
			}
			f2.inserir(info[temp]);
			temp++;
		}
		System.out.println("\nTamanho da fila antes de encurtar: " + limite);
		this.info = (T[]) new Object[tamanho];
		this.limite = this.tamanho;
		System.out.println("\nTamanho da fila depois de encurtar: " + limite);
		info = f2.info;
	}
	
	@SuppressWarnings("unchecked")
	private void redimensionar() {
		FilaVetor<Object> f2 = new FilaVetor<>(tamanho);
		int temp = inicio;
		while (f2.tamanho != tamanho) {
			if(temp == limite) {
				temp = 0;
			}
			f2.inserir(info[temp]);
			temp++;
		}
		this.info = (T[]) new Object[limite * 2];
		this.limite *= 2;
		for(int i = 0; f2.tamanho != 0; i++) {
			info[i] = f2.retirar();
		}
	}

}
