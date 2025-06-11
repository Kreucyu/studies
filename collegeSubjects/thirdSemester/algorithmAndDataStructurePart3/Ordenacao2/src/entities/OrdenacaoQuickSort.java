package entities;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

	public OrdenacaoQuickSort(Class<T> type) {
		super(type); // Adicione esta linha
	}

	@Override
	public void ordenar() {
		if (getInfo() == null || getInfo().length <= 1) {
			return;
		}
		trocas = 0;
		comparacoes = 0;
		quickSort(0, getInfo().length - 1);
	}

	@Override
	public void ordenarSegmento(int inicio, int fim) {
		if (getInfo() == null || inicio < 0 || fim >= getInfo().length || inicio >= fim) {
			return;
		}
		trocas = 0;
		comparacoes = 0;
		quickSort(inicio, fim);
	}
	
	private void quickSort(int inicio, int fim) {
		if(inicio < fim) {
			int idxPivo = particionar(inicio, fim);
			quickSort(inicio, idxPivo-1);
			quickSort(idxPivo+1, fim);
		}
	}
	
	private int particionar(int inicio, int fim) {
		int a = inicio;
		int b = fim + 1;
		T pivo = getInfo()[inicio];
		while(true) {
			do {
				a = a + 1;
				comparacoes++;
			} while(a <= fim && getInfo()[a].compareTo(pivo) < 0);
			do {
				b = b - 1;
				comparacoes++;
			} while(b >= inicio && getInfo()[b].compareTo(pivo) > 0);
			comparacoes++;
			if(a >= b) {
				break;
			}
			trocar(a,b);
		}
		trocar(b, inicio);
		return b;
	}
}