package entities;

public class OrdenacaoBolhaOtimizada<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    public OrdenacaoBolhaOtimizada(Class<T> type) { // Adicione este construtor
        super(type);
    }

	@Override
	public void ordenar() {
		int n = getInfo().length; // Corrigido de n-1 para n
		boolean trocou;
		
		for(int i = 0; i < n - 1; i++) { // Corrigido o loop externo
			trocou = false;
			for(int j = 0; j < n - 1 - i; j++) { // Corrigido o loop interno
				if(getInfo()[j].compareTo(getInfo()[j + 1]) > 0) {
					trocar(j, j+1);
					trocou = true;
				}
			}
			if(trocou == false) {
				return;
			}
		}
		
	}
    

	@Override
	public void ordenarSegmento(int inicio, int fim) {
		if (getInfo() == null || inicio < 0 || fim >= getInfo().length || inicio >= fim) {
			return;
		}
		trocas = 0; // Reinicia o contador de trocas para o segmento
		comparacoes = 0; // Reinicia o contador de comparações para o segmento
		boolean trocou;
		
		for(int i = inicio; i <= fim; i++) { // Loop externo no segmento
			trocou = false;
			// Loop interno no segmento, ajustando os limites
			for(int j = inicio; j < fim - (i - inicio); j++) {
				comparacoes++; // Conta a comparação
				if(getInfo()[j].compareTo(getInfo()[j + 1]) > 0) {
					trocar(j, j+1);
					trocou = true;
				}
			}
			if(trocou == false) {
				break;
			}
		}
	}
}