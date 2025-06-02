package entities;

public class BuscaBinaria<T extends Comparable<T>> extends BuscaAbstract {
	public int buscar(T valor) {
		Object[] info = getInfo();
		int n = info.length;
		return buscar(valor, 0, n-1, info);
	}
	
	@SuppressWarnings("unchecked")
	private int buscar(T valor, int inicio, int fim, Object[] info) {
		int meio = (inicio + fim) / 2;
		if(valor.compareTo((T) info[meio]) < 0){
			buscar(valor, inicio, meio-1, info);
		} else if (valor.compareTo((T) info[meio]) > 0) {
			buscar(valor, meio+1, fim, info);
		} else return meio;
		return -1;
	}
}
