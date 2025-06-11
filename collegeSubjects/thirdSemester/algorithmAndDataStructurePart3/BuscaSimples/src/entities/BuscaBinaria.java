package entities;

public class BuscaBinaria<T extends Comparable<T>> extends BuscaAbstract {
	@SuppressWarnings("unchecked")
	public int buscar(T valor) {
		int n = getInfo().length - 1;
		int inicio = 0;
		int fim = n;
		while(inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if(valor.compareTo((T) getInfo()[meio]) < 0) {
				fim = meio - 1;
			} else {
				if(valor.compareTo((T) getInfo()[meio]) > 0) {
					inicio = meio + 1;
				} else {
					return meio;
				}
			}
		}
		return -1;
	}
}
