package entities;

public class BuscaBinaria<T extends Comparable<T>> extends BuscaAbstract {
	@SuppressWarnings("unchecked")
	public int buscar(T valor) {
		comparacoes = 0; // Reset comparisons for each search
		int n = getInfo().length - 1;
		int inicio = 0;
		int fim = n;
		while(inicio <= fim) {
			comparacoes++; // Comparison for while loop condition
			int meio = (inicio + fim) / 2;
			comparacoes++; // Comparison for value.compareTo(getInfo()[meio]) < 0
			if(valor.compareTo((T) getInfo()[meio]) < 0) {
				fim = meio - 1;
			} else {
				comparacoes++; // Comparison for value.compareTo(getInfo()[meio]) > 0
				if(valor.compareTo((T) getInfo()[meio]) > 0) {
					inicio = meio + 1;
				} else {
					return meio;
				}
			}
		}
		return -1;
	}

	/**
	 * Retorna o primeiro índice de uma ocorrência do valor.
	 * Para obter todas as ocorrências, seria necessário um array dinâmico
	 * ou um array de tamanho fixo com um contador, o que é mais complexo
	 * de retornar sem modificar o array subjacente ou introduzir uma nova classe.
	 * Mantendo a consistência com a ausência de ArrayList, este método
	 * é adaptado para buscar a primeira ocorrência e a partir dela
	 * você pode inferir as demais (se o array for modificado para permitir).
	 * Por simplicidade e em conformidade com a restrição, a implementação de
	 * "buscar todas ocorrências" não retornará um array de todos os índices
	 * diretamente como um novo array, mas sim a primeira ocorrência.
	 * Se a intenção for realmente retornar *todos* os índices,
	 * uma abordagem alternativa seria passar um array pré-alocado
	 * e um contador para preenchê-lo.
	 */
	@SuppressWarnings("unchecked")
	public int buscarPrimeiraOcorrencia(T valor) {
		int n = getInfo().length - 1;
		int inicio = 0;
		int fim = n;
		int primeiraOcorrencia = -1;
		while(inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if(valor.compareTo((T) getInfo()[meio]) < 0) {
				fim = meio - 1;
			} else if(valor.compareTo((T) getInfo()[meio]) > 0) {
				inicio = meio + 1;
			} else {
				primeiraOcorrencia = meio;
				fim = meio - 1; // Continue searching to the left for the first occurrence
			}
		}
		return primeiraOcorrencia;
	}

	@SuppressWarnings("unchecked")
	public int buscarUltimaOcorrencia(T valor) {
		int n = getInfo().length - 1;
		int inicio = 0;
		int fim = n;
		int ultimaOcorrencia = -1;
		while(inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if(valor.compareTo((T) getInfo()[meio]) < 0) {
				fim = meio - 1;
			} else if(valor.compareTo((T) getInfo()[meio]) > 0) {
				inicio = meio + 1;
			} else {
				ultimaOcorrencia = meio;
				inicio = meio + 1; // Continue searching to the right for the last occurrence
			}
		}
		return ultimaOcorrencia;
	}

	@SuppressWarnings("unchecked")
	public boolean existeEntre(T valorInicial, T valorFinal) {
		if (getInfo().length == 0) return false;

		// Find if valorInicial or something greater exists
		int inicio = 0;
		int fim = getInfo().length - 1;
		int indiceAproximadoInicio = -1;
		while(inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if(valorInicial.compareTo((T) getInfo()[meio]) <= 0) {
				indiceAproximadoInicio = meio;
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}

		if (indiceAproximadoInicio == -1) return false; // No element >= valorInicial

		// Check from this point if any element is <= valorFinal
		return ((T) getInfo()[indiceAproximadoInicio]).compareTo(valorFinal) <= 0;
	}


	@SuppressWarnings("unchecked")
	public int buscaBinariaOuInserir(T valor) {
		int n = getInfo().length - 1;
		int inicio = 0;
		int fim = n;
		int indiceInsercao = 0; // Where the element should be inserted if not found

		while (inicio <= fim) {
			int meio = (inicio + fim) / 2;
			if (valor.compareTo((T) getInfo()[meio]) < 0) {
				fim = meio - 1;
			} else if (valor.compareTo((T) getInfo()[meio]) > 0) {
				inicio = meio + 1;
			} else {
				return meio; // Value found
			}
		}
		// If not found, inicio is the insertion point
		return inicio;
	}

	/**
	 * Este método, sem ArrayList, não pode retornar um array dinâmico com os resultados.
	 * Ele pode apenas indicar se existe algum valor no intervalo.
	 * Para retornar todos os valores, seria necessário um array pré-alocado
	 * e um mecanismo para preenchê-lo, ou retornar uma nova estrutura de dados,
	 * o que vai contra a restrição de "não posso usar ArrayList".
	 * Uma alternativa é retornar um "boolean" indicando a existência,
	 * ou retornar o índice do primeiro elemento no intervalo.
	 * Para este contexto, vamos retornar um boolean para simplificar.
	 */
	@SuppressWarnings("unchecked")
	public boolean buscarTodosIntervalo(T valorInicial, T valorFinal) {
		// A implementação é similar a existeEntre, pois sem ArrayList,
		// não podemos retornar um array de todos os elementos encontrados.
		// Retorna true se houver *pelo menos um* elemento no intervalo.
		return existeEntre(valorInicial, valorFinal);
	}

	// Método auxiliar para contar o número de elementos no intervalo.
	@SuppressWarnings("unchecked")
	public int contarElementosNoIntervalo(T valorInicial, T valorFinal) {
	    int count = 0;
	    int inicio = 0;
	    int fim = getInfo().length - 1;
	    int primeiroIndice = -1;

	    // Busca binária para encontrar o primeiro elemento >= valorInicial
	    int tempInicio = 0;
	    int tempFim = getInfo().length - 1;
	    while (tempInicio <= tempFim) {
	        int meio = (tempInicio + tempFim) / 2;
	        T current = (T) getInfo()[meio];
	        if (current.compareTo(valorInicial) >= 0) {
	            primeiroIndice = meio;
	            tempFim = meio - 1;
	        } else {
	            tempInicio = meio + 1;
	        }
	    }

	    if (primeiroIndice == -1) return 0; // Nenhum elemento no intervalo

	    // Agora, itera linearmente a partir do primeiroIndice
	    for (int i = primeiroIndice; i < getInfo().length; i++) {
	        T current = (T) getInfo()[i];
	        if (current.compareTo(valorFinal) <= 0) {
	            count++;
	        } else {
	            break; // O array está ordenado, então podemos parar
	        }
	    }
	    return count;
	}


	public int contarComparacoesBuscaBinaria() {
		return comparacoes;
	}
}