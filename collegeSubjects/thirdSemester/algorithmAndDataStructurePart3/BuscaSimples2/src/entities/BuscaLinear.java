package entities;

public class BuscaLinear<T> extends BuscaAbstract {
	public int buscar(T valor) {
		comparacoes = 0; // Reset comparisons for each search
		for(int i = 0; i < getInfo().length; i++) { // Changed condition to i < getInfo().length
			comparacoes++; // Comparison for getInfo()[i].equals(valor)
			if(getInfo()[i].equals(valor)) { // Use .equals() for object comparison
				return i;
			}
		}
		return -1;
	}

	/**
	 * Sem ArrayList, não podemos retornar um array dinâmico de todos os índices.
	 * Este método pode ser adaptado para retornar a *primeira* ocorrência.
	 * Se a intenção é retornar todas as ocorrências, você precisaria
	 * de um array de tamanho fixo pré-alocado e um contador,
	 * ou modificar o método para preencher um array passado como parâmetro.
	 * Para manter a simplicidade e a conformidade, ele retornará o primeiro índice.
	 */
	public int buscarPrimeiraOcorrencia(T valor) {
		for(int i = 0; i < getInfo().length; i++) {
			if(getInfo()[i].equals(valor)) {
				return i;
			}
		}
		return -1;
	}

	public int buscarUltimaOcorrencia(T valor) {
		int ultimaOcorrencia = -1;
		for(int i = 0; i < getInfo().length; i++) {
			if(getInfo()[i].equals(valor)) {
				ultimaOcorrencia = i;
			}
		}
		return ultimaOcorrencia;
	}

	public int contarComparacoesBuscaLinear() {
		return comparacoes;
	}
}