package entities;

public class OrdenacaoBolha<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    // Adicione este construtor
    public OrdenacaoBolha(Class<T> type) {
        super(type); // Chama o construtor da classe pai OrdenacaoAbstract
    }

	@Override
	public void ordenar() {
		if (getInfo() == null || getInfo().length <= 1) {
			return;
		}
		trocas = 0;
		comparacoes = 0;
		int n = getInfo().length;
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < n - 1 - i; j++) {
				comparacoes++;
				if(getInfo()[j].compareTo(getInfo()[j+1]) > 0) {
					trocar(j,j+1);
				}
			}
		}	
	}

	@Override
	public void ordenarSegmento(int inicio, int fim) {
		if (getInfo() == null || inicio < 0 || fim >= getInfo().length || inicio >= fim) {
			return;
		}
		trocas = 0;
		comparacoes = 0;
		for(int i = inicio; i <= fim; i++) {
			for(int j = inicio; j < fim - (i - inicio); j++) {
				comparacoes++;
				if(getInfo()[j].compareTo(getInfo()[j+1]) > 0) {
					trocar(j,j+1);
				}
			}
		}
	}
}