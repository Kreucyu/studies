package model.entities;

public class ListaEncadeada<T> {
	private NoLista<T> primeiro;

	public ListaEncadeada() {
		primeiro = null;
	}

	public NoLista<T> getPrimeiro() {
		return primeiro;
	}

	public void inserir(T valor) {
		NoLista<T> insert = new NoLista<>();
		insert.setInfo(valor);
		insert.setProximo(primeiro);
		primeiro = insert;
	}

	public boolean estaVazia() {
		return primeiro == null;
	}

	public NoLista<T> buscar(T valor) {
		NoLista<T> atual = primeiro;
		while (atual != null) {
			if (atual.getInfo().equals(valor) || atual.getInfo().toString().equals(valor.toString())) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	public void retirar(T valor) {
		NoLista<T> atual = primeiro;
		NoLista<T> anterior = null;
		while (atual != null && !atual.getInfo().equals(valor)) {
			anterior = atual;
			atual = atual.getProximo();
		}
		if (atual != null) {
			if (anterior == null) {
				primeiro = atual.getProximo();
			} else {
				anterior.setProximo(atual.getProximo());
			}
		}

	}

	public int obterComprimento() {
		int temp = 0;
		NoLista<T> atual = primeiro;
		while (atual != null) {
			atual = atual.getProximo();
			temp++;
		}
		return temp;
	}

	public NoLista<T> obterNo(int idx) {
		try {
			if (idx < 0 || idx >= obterComprimento()) {
				throw new IndexOutOfBoundsException();
			}
			NoLista<T> atual = primeiro;
			int temp = 0;
			while (temp < idx) {
				atual = atual.getProximo();
				temp++;
			}
			return atual;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\nPosição inválida!\n");
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		NoLista<T> atual = primeiro;
		if (atual == null) {
			sb.append("A pilha está vazia!");
			return sb.toString();
		}
		while (atual != null) {
			sb.append(atual.getInfo());
			if (atual.getProximo() != null) {
				sb.append(", ");
			}
			atual = atual.getProximo();
		}
		return sb.toString();
	}

	public void exit() {
		System.out.println("\nSaindo...");
		System.exit(0);
	}
}