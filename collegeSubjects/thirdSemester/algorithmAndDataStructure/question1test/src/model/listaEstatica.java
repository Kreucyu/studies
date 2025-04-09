package model;

public class listaEstatica<T> {
	private Object[] info;
	private int tamanho;

	public listaEstatica() {
		info = new Object[10];
		tamanho = 0;
	}

	private void redimensionar() {
		Object novoVetor[] = new Object[info.length + 10];
		for (int i = 0; i < tamanho; i++) {
			novoVetor[i] = info[i];
		}
		info = novoVetor;
	}

	public void inserir(T valor) {
		if (tamanho == info.length) {
			redimensionar();
		}
		info[tamanho] = valor;
		tamanho++;
	}

	public void exibir() {
		for (int i = 0; i < tamanho; i++) {
			if (i == tamanho - 1) {
				System.out.println(info[i]);
			} else {
				System.out.print(info[i] + ", ");
			}
		}
	}

	public int buscar(T valor) {
		for(int i = 0; i < tamanho; i++) {
			if(info[i] == valor) {
				return i;
			}
		}
		return -1;
	}

	public void retirar(T valor) {
		int pos = buscar(valor);
		if(pos != -1) {
			for (int i = pos; i < tamanho - 1; i++) {
				info[i] = info[i + 1];
			}
			tamanho--;
		}	
	}

	public void liberar() {
		tamanho = 0;
		info = new Object[10];
	}

	@SuppressWarnings("unchecked")
	public T obterElemento(int posicao) {
		if(posicao > 0 || posicao < tamanho) {
			return (T) info[posicao];
		}
		return null;
	}

	public boolean estaVazia() {
		if(tamanho == 0) {
			return true;
		}
		return false;
	}
	
	public void exit() {
		System.out.println("Saindo...");
		System.exit(0);
	}

	public int getTamanho() {
		return tamanho;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < tamanho; i++) {
			sb.append(info[i]);
			if (i < tamanho - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public void inverter() {
    	for(int i = 0; i < tamanho / 2; i++) {
    		int j = tamanho - 1 - i;
    		Object temp = info[i];
    		info[i] = info[j];
    		info[j] = temp;
    	}
    }
	
	public void retirarElementos(int inicio, int fim) {
		if(obterElemento(inicio) == null || obterElemento(fim) == null || inicio > fim) {
			throw new IndexOutOfBoundsException();
		} else {
			int destino = inicio;
			for(int i = fim + 1; i != tamanho; i++) {
				info[destino] = info[i];
				info[i] = null;
				destino++;
			}
			tamanho -= ((fim + 1) - inicio);
		}	
	}
}
