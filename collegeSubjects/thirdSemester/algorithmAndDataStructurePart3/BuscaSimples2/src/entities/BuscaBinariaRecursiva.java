package entities;

public class BuscaBinariaRecursiva<T extends Comparable<T>> extends BuscaAbstract{
	    public int buscar(T valor) {
	        comparacoes = 0; // Reset comparisons for each search
	        int n = getInfo().length - 1;
	        return buscar(valor, 0, n, getInfo());
	    }

	    @SuppressWarnings("unchecked")
	    private int buscar(T valor, int inicio, int fim, Object[] info) {
	        comparacoes++; // Comparison for inicio > fim
	        if (inicio > fim) return -1;
	        int meio = (inicio + fim) / 2;
	        T meioValor = (T) info[meio];
	        comparacoes++; // Comparison for valor.compareTo(meioValor) < 0
	        if (valor.compareTo(meioValor) < 0) {
	            return buscar(valor, inicio, meio - 1, info);
	        } else {
	            comparacoes++; // Comparison for valor.compareTo(meioValor) > 0
	            if (valor.compareTo(meioValor) > 0) {
	                return buscar(valor, meio + 1, fim, info);
	            } else {
	                return meio;
	            }
	        }
	    }

		@SuppressWarnings("unchecked")
		public int buscarPrimeiraOcorrencia(T valor) {
			return buscarPrimeiraOcorrencia(valor, 0, getInfo().length - 1, -1);
		}

		@SuppressWarnings("unchecked")
		private int buscarPrimeiraOcorrencia(T valor, int inicio, int fim, int primeiraOcorrencia) {
			if (inicio > fim) {
				return primeiraOcorrencia;
			}
			int meio = (inicio + fim) / 2;
			T meioValor = (T) getInfo()[meio];
			if (valor.compareTo(meioValor) < 0) {
				return buscarPrimeiraOcorrencia(valor, inicio, meio - 1, primeiraOcorrencia);
			} else if (valor.compareTo(meioValor) > 0) {
				return buscarPrimeiraOcorrencia(valor, meio + 1, fim, primeiraOcorrencia);
			} else {
				primeiraOcorrencia = meio; // Found an occurrence, try to find an earlier one
				return buscarPrimeiraOcorrencia(valor, inicio, meio - 1, primeiraOcorrencia);
			}
		}

		@SuppressWarnings("unchecked")
		public int buscarUltimaOcorrencia(T valor) {
			return buscarUltimaOcorrencia(valor, 0, getInfo().length - 1, -1);
		}

		@SuppressWarnings("unchecked")
		private int buscarUltimaOcorrencia(T valor, int inicio, int fim, int ultimaOcorrencia) {
			if (inicio > fim) {
				return ultimaOcorrencia;
			}
			int meio = (inicio + fim) / 2;
			T meioValor = (T) getInfo()[meio];
			if (valor.compareTo(meioValor) < 0) {
				return buscarUltimaOcorrencia(valor, inicio, meio - 1, ultimaOcorrencia);
			} else if (valor.compareTo(meioValor) > 0) {
				return buscarUltimaOcorrencia(valor, meio + 1, fim, ultimaOcorrencia);
			} else {
				// Found an occurrence, try to find a later one
				return buscarUltimaOcorrencia(valor, meio + 1, fim, meio);
			}
		}

		@SuppressWarnings("unchecked")
		public boolean existeEntre(T valorInicial, T valorFinal) {
			return existeEntre(valorInicial, valorFinal, 0, getInfo().length - 1);
		}

		@SuppressWarnings("unchecked")
		private boolean existeEntre(T valorInicial, T valorFinal, int inicio, int fim) {
			if (inicio > fim) {
				return false;
			}
			int meio = (inicio + fim) / 2;
			T meioValor = (T) getInfo()[meio];

			if (meioValor.compareTo(valorInicial) >= 0 && meioValor.compareTo(valorFinal) <= 0) {
				return true;
			}

			if (meioValor.compareTo(valorInicial) < 0) {
				return existeEntre(valorInicial, valorFinal, meio + 1, fim);
			} else {
				return existeEntre(valorInicial, valorFinal, inicio, meio - 1);
			}
		}

		@SuppressWarnings("unchecked")
		public int buscaBinariaOuInserir(T valor) {
			return buscaBinariaOuInserir(valor, 0, getInfo().length - 1);
		}

		@SuppressWarnings("unchecked")
		private int buscaBinariaOuInserir(T valor, int inicio, int fim) {
			if (inicio > fim) {
				return inicio; // Insertion point
			}
			int meio = (inicio + fim) / 2;
			T meioValor = (T) getInfo()[meio];
			if (valor.compareTo(meioValor) < 0) {
				return buscaBinariaOuInserir(valor, inicio, meio - 1);
			} else if (valor.compareTo(meioValor) > 0) {
				return buscaBinariaOuInserir(valor, meio + 1, fim);
			} else {
				return meio; // Value found
			}
		}

		/**
		 * Sem ArrayList, este método não pode retornar um array dinâmico com os resultados.
		 * Ele pode apenas indicar se existe algum valor no intervalo.
		 * Para retornar todos os valores, seria necessário um array pré-alocado
		 * e um mecanismo para preenchê-lo, ou retornar uma nova estrutura de dados.
		 * Para este contexto, vamos retornar um boolean para simplificar.
		 */
		@SuppressWarnings("unchecked")
		public boolean buscarTodosIntervalo(T valorInicial, T valorFinal) {
			// A implementação é similar a existeEntre, pois sem ArrayList,
			// não podemos retornar um array de todos os elementos encontrados.
			// Retorna true se houver *pelo menos um* elemento no intervalo.
			return existeEntre(valorInicial, valorFinal);
		}

		// Método auxiliar para contar o número de elementos no intervalo (recursivo).
		@SuppressWarnings("unchecked")
		public int contarElementosNoIntervalo(T valorInicial, T valorFinal) {
			return contarElementosNoIntervalo(valorInicial, valorFinal, 0, getInfo().length - 1);
		}

		@SuppressWarnings("unchecked")
		private int contarElementosNoIntervalo(T valorInicial, T valorFinal, int inicio, int fim) {
			if (inicio > fim) {
				return 0;
			}
			int meio = (inicio + fim) / 2;
			T meioValor = (T) getInfo()[meio];
			int count = 0;

			if (meioValor.compareTo(valorInicial) >= 0 && meioValor.compareTo(valorFinal) <= 0) {
				count = 1;
				// Buscar à esquerda e à direita
				count += contarElementosNoIntervalo(valorInicial, valorFinal, inicio, meio - 1);
				count += contarElementosNoIntervalo(valorInicial, valorFinal, meio + 1, fim);
			} else if (meioValor.compareTo(valorInicial) < 0) {
				// Valor atual muito pequeno, buscar na metade direita
				count += contarElementosNoIntervalo(valorInicial, valorFinal, meio + 1, fim);
			} else {
				// Valor atual muito grande, buscar na metade esquerda
				count += contarElementosNoIntervalo(valorInicial, valorFinal, inicio, meio - 1);
			}
			return count;
		}


		public int contarComparacoesBuscaBinaria() {
			return comparacoes;
		}
	}