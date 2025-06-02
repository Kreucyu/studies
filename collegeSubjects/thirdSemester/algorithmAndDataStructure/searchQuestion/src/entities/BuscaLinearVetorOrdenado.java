package entities;

public class BuscaLinearVetorOrdenado<T extends Comparable<T>> extends BuscaAbstract {
	public int buscar(T valor) {
		Object[] info = getInfo();
		int n = info.length;
		for(int i = 0; i < n - 1; i++) {
			if(info[i] == valor) {
				return i;
			} else if (!valor.equals(info[i])) {
				break;
			}
		}
		return -1;
	}
}
