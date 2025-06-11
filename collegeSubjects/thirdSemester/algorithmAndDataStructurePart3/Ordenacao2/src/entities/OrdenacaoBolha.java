package entities;

public class OrdenacaoBolha<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

	@Override
	public void ordenar() {
		int n = getInfo().length - 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n-i; j++) {
				if(getInfo()[j].compareTo(getInfo()[j+1]) > 0) {
					trocar(j,j+1);
				}
			}
		}	
	}
	
}
