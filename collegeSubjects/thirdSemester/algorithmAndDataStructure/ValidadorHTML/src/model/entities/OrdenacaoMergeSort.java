package model.entities;

public class OrdenacaoMergeSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

	@Override
	public void ordenar() {
		mergesort(0, getInfo().length - 1);
	}

	private void mergesort(int inicio, int fim) {
		if (inicio < fim) {
			int meio = (inicio + fim) / 2;
			mergesort(inicio, meio);
			mergesort(meio + 1, fim);
			intercalar(inicio, meio, fim);
		}
	}

	private void intercalar(int inicio, int meio, int fim) {
		T[] info = getInfo();
		T[] aux = info.clone();

		int i = inicio;
		int j = meio + 1;
		int k = inicio;

		while (i <= meio && j <= fim) {
			if (aux[i].compareTo(aux[j]) <= 0) {
				info[k++] = aux[i++];
			} else {
				info[k++] = aux[j++];
			}
		}

		while (i <= meio) {
			info[k++] = aux[i++];
		}

		while (j <= fim) {
			info[k++] = aux[j++];
		}
	}
}
