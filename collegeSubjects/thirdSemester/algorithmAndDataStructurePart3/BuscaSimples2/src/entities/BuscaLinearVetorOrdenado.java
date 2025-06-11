package entities;

public class BuscaLinearVetorOrdenado<T extends Comparable<T>> extends BuscaAbstract {
	@SuppressWarnings("unchecked")
	public int buscar(T valor) {
		comparacoes = 0; // Reset comparisons for each search
		int n = getInfo().length - 1;
		for(int i = 0; i <= n; i++) {
			comparacoes++; // Comparison for getInfo()[i].equals(valor)
			if(getInfo()[i].equals(valor)) { // Use .equals() for object comparison
				return i;
			} else {
				comparacoes++; // Comparison for valor.compareTo((T) getInfo()[i]) < 0
				if(valor.compareTo((T) getInfo()[i]) < 0) {
					break; // Element not found as it's sorted
				}
			}
		}
		return -1;
	}

	/**
	 * Sem ArrayList, este método retorna o índice da primeira ocorrência.
	 */
	@SuppressWarnings("unchecked")
	public int buscarPrimeiraOcorrencia(T valor) {
		for(int i = 0; i < getInfo().length; i++) {
			if (((T) getInfo()[i]).compareTo(valor) == 0) {
				return i;
			} else if (((T) getInfo()[i]).compareTo(valor) > 0) {
				break; // Since it's sorted, no more occurrences will be found
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public int buscarUltimaOcorrencia(T valor) {
		int ultimaOcorrencia = -1;
		for(int i = 0; i < getInfo().length; i++) {
			if (((T) getInfo()[i]).compareTo(valor) == 0) {
				ultimaOcorrencia = i;
			} else if (((T) getInfo()[i]).compareTo(valor) > 0) {
				break; // Since it's sorted, no more occurrences will be found
			}
		}
		return ultimaOcorrencia;
	}

	@SuppressWarnings("unchecked")
	public boolean existeEntre(T valorInicial, T valorFinal) {
		for (Object o : getInfo()) {
			T current = (T) o;
			if (current.compareTo(valorInicial) >= 0 && current.compareTo(valorFinal) <= 0) {
				return true;
			} else if (current.compareTo(valorFinal) > 0) {
				break; // Optimization for sorted array
			}
		}
		return false;
	}

	/**
	 * Sem ArrayList, este método apenas verifica se há *algum* elemento no intervalo.
	 * Não retorna todos os elementos.
	 */
	@SuppressWarnings("unchecked")
	public boolean buscarTodosIntervalo(T valorInicial, T valorFinal) {
		return existeEntre(valorInicial, valorFinal);
	}

	// Método auxiliar para contar o número de elementos no intervalo.
	@SuppressWarnings("unchecked")
	public int contarElementosNoIntervalo(T valorInicial, T valorFinal) {
	    int count = 0;
	    for (int i = 0; i < getInfo().length; i++) {
	        T current = (T) getInfo()[i];
	        if (current.compareTo(valorInicial) >= 0 && current.compareTo(valorFinal) <= 0) {
	            count++;
	        } else if (current.compareTo(valorFinal) > 0) {
	            break; // O array está ordenado, então podemos parar
	        }
	    }
	    return count;
	}

	public int contarComparacoesBuscaLinear() {
		return comparacoes;
	}
}