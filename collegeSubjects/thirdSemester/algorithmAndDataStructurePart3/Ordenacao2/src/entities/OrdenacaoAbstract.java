package entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class OrdenacaoAbstract<T extends Comparable<T>> {
	private T[] info;
	protected long trocas;
	protected long comparacoes;
	private Class<T> type;

	public OrdenacaoAbstract(Class<T> type) {
		this.type = type;
	}

	public T[] getInfo() {
		return info;
	}

	public void setInfo(T[] info) {
		this.info = info;
		this.trocas = 0;
		this.comparacoes = 0;
	}
	
	@SuppressWarnings("unchecked")
	protected T[] createArray(int size) {
		return (T[]) java.lang.reflect.Array.newInstance(type, size);
	}
	
	public void trocar(int a, int b) {
		T temp = info[a];
		info[a] = info[b];
		info[b] = temp;
		trocas++;
	}
	
	public long getTrocas() {
		return trocas;
	}

	public long getComparacoes() {
		return comparacoes;
	}

	public void ordenarDecrescente() {
		ordenar(); // Primeiro ordena crescentemente
		T[] temp = createArray(info.length);
		for (int i = 0; i < info.length; i++) {
			temp[i] = info[info.length - 1 - i];
		}
		info = temp;
	}

	public abstract void ordenarSegmento(int inicio, int fim);

	public boolean estaOrdenado() {
		for (int i = 0; i < info.length - 1; i++) {
			comparacoes++;
			if (info[i].compareTo(info[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}

	public int contarElementosForaDeOrdem() {
		int count = 0;
		for (int i = 0; i < info.length - 1; i++) {
			comparacoes++;
			if (info[i].compareTo(info[i+1]) > 0) {
				count++;
			}
		}
		return count;
	}

	public Integer[] obterIndiceOrdenacao() {
		Integer[] indices = new Integer[info.length];
		for (int i = 0; i < info.length; i++) {
			indices[i] = i;
		}

		T[] tempInfo = createArray(info.length);
		System.arraycopy(info, 0, tempInfo, 0, info.length);

		// Bubble sort para os índices com base nos valores
		for (int i = 0; i < tempInfo.length - 1; i++) {
			for (int j = 0; j < tempInfo.length - 1 - i; j++) {
				comparacoes++;
				if (tempInfo[j].compareTo(tempInfo[j+1]) > 0) {
					T tempVal = tempInfo[j];
					tempInfo[j] = tempInfo[j+1];
					tempInfo[j+1] = tempVal;

					Integer tempIdx = indices[j];
					indices[j] = indices[j+1];
					indices[j+1] = tempIdx;
					trocas++;
				}
			}
		}
		return indices;
	}

	public T calcularMediana() {
		if (info == null || info.length == 0) {
			return null;
		}
		T[] temp = createArray(info.length);
		System.arraycopy(info, 0, temp, 0, info.length);

		// Usando Bubble Sort para ordenar a cópia (já que não podemos usar Arrays.sort com Collections.sort)
		for (int i = 0; i < temp.length - 1; i++) {
			for (int j = 0; j < temp.length - 1 - i; j++) {
				if (temp[j].compareTo(temp[j+1]) > 0) {
					T val = temp[j];
					temp[j] = temp[j+1];
					temp[j+1] = val;
				}
			}
		}

		if (temp.length % 2 == 1) {
			return temp[temp.length / 2];
		} else {
			return temp[temp.length / 2 - 1];
		}
	}

	public void ordenarPorParImpar() {
		if (type != Integer.class) {
			System.out.println("Este método só funciona para arrays de Integer.");
			return;
		}

		// Contar pares e ímpares para criar arrays do tamanho correto
		int countPares = 0;
		int countImpares = 0;
		for (T val : info) {
			Integer intVal = (Integer) val;
			if (intVal % 2 == 0) {
				countPares++;
			} else {
				countImpares++;
			}
		}

		Integer[] pares = new Integer[countPares];
		Integer[] impares = new Integer[countImpares];

		int idxPares = 0;
		int idxImpares = 0;
		for (T val : info) {
			Integer intVal = (Integer) val;
			if (intVal % 2 == 0) {
				pares[idxPares++] = intVal;
			} else {
				impares[idxImpares++] = intVal;
			}
		}

		// Ordenar os arrays de pares e ímpares usando Bubble Sort
		for (int i = 0; i < pares.length - 1; i++) {
			for (int j = 0; j < pares.length - 1 - i; j++) {
				if (pares[j] > pares[j+1]) {
					int temp = pares[j];
					pares[j] = pares[j+1];
					pares[j+1] = temp;
				}
			}
		}

		for (int i = 0; i < impares.length - 1; i++) {
			for (int j = 0; j < impares.length - 1 - i; j++) {
				if (impares[j] > impares[j+1]) {
					int temp = impares[j];
					impares[j] = impares[j+1];
					impares[j+1] = temp;
				}
			}
		}

		// Juntar os arrays de volta no 'info'
		int currentIdx = 0;
		for (Integer par : pares) {
			info[currentIdx++] = (T) par;
		}
		for (Integer impar : impares) {
			info[currentIdx++] = (T) impar;
		}
	}

	public void ordenarStringPorTamanho() {
		if (type != String.class) {
			System.out.println("Este método só funciona para arrays de String.");
			return;
		}
		// Usando Bubble Sort para ordenar Strings por tamanho
		for (int i = 0; i < info.length - 1; i++) {
			for (int j = 0; j < info.length - 1 - i; j++) {
				comparacoes++; // Comparação de tamanho
				if (((String)info[j]).length() > ((String)info[j+1]).length()) {
					trocar(j, j+1);
				}
			}
		}
	}

	public void ordenarPorUltimoDigito() {
		if (type != Integer.class) {
			System.out.println("Este método só funciona para arrays de Integer.");
			return;
		}
		// Usando Bubble Sort para ordenar por último dígito
		for (int i = 0; i < info.length - 1; i++) {
			for (int j = 0; j < info.length - 1 - i; j++) {
				comparacoes++; // Comparação de último dígito
				if (((Integer)info[j] % 10) > ((Integer)info[j+1] % 10)) {
					trocar(j, j+1);
				}
			}
		}
	}

	public void ordenarNegativos() {
		if (type != Integer.class) {
			System.out.println("Este método só funciona para arrays de Integer.");
			return;
		}
		
		int countNegativos = 0;
		int countNaoNegativos = 0;

		for (T val : info) {
			Integer intVal = (Integer) val;
			if (intVal < 0) {
				countNegativos++;
			} else {
				countNaoNegativos++;
			}
		}

		Integer[] negativos = new Integer[countNegativos];
		Integer[] naoNegativos = new Integer[countNaoNegativos];

		int idxNegativos = 0;
		int idxNaoNegativos = 0;
		for (T val : info) {
			Integer intVal = (Integer) val;
			if (intVal < 0) {
				negativos[idxNegativos++] = intVal;
			} else {
				naoNegativos[idxNaoNegativos++] = intVal;
			}
		}

		// Ordenar cada grupo (opcional, mas geralmente desejável) usando Bubble Sort
		for (int i = 0; i < negativos.length - 1; i++) {
			for (int j = 0; j < negativos.length - 1 - i; j++) {
				if (negativos[j] > negativos[j+1]) {
					int temp = negativos[j];
					negativos[j] = negativos[j+1];
					negativos[j+1] = temp;
				}
			}
		}

		for (int i = 0; i < naoNegativos.length - 1; i++) {
			for (int j = 0; j < naoNegativos.length - 1 - i; j++) {
				if (naoNegativos[j] > naoNegativos[j+1]) {
					int temp = naoNegativos[j];
					naoNegativos[j] = naoNegativos[j+1];
					naoNegativos[j+1] = temp;
				}
			}
		}

		int currentIdx = 0;
		for (Integer neg : negativos) {
			info[currentIdx++] = (T) neg;
		}
		for (Integer naoNeg : naoNegativos) {
			info[currentIdx++] = (T) naoNeg;
		}
	}

	public T[] intercalarVetoresOrdenados(T[] outroArray) {
		if (this.info == null || outroArray == null) {
			return null;
		}

		T[] resultado = createArray(this.info.length + outroArray.length);
		int i = 0, j = 0, k = 0;

		while (i < this.info.length && j < outroArray.length) {
			comparacoes++;
			if (this.info[i].compareTo(outroArray[j]) < 0) {
				resultado[k++] = this.info[i++];
			} else {
				resultado[k++] = outroArray[j++];
			}
		}

		while (i < this.info.length) {
			resultado[k++] = this.info[i++];
		}

		while (j < outroArray.length) {
			resultado[k++] = outroArray[j++];
		}
		return resultado;
	}

	public boolean saoPermutacoes(T[] outroArray) {
		if (this.info == null || outroArray == null || this.info.length != outroArray.length) {
			return false;
		}

		T[] temp1 = createArray(this.info.length);
		System.arraycopy(this.info, 0, temp1, 0, this.info.length);
		T[] temp2 = createArray(outroArray.length);
		System.arraycopy(outroArray, 0, temp2, 0, outroArray.length);

		// Ordenar as cópias usando Bubble Sort (ou outro algoritmo de sua escolha)
		for (int i = 0; i < temp1.length - 1; i++) {
			for (int j = 0; j < temp1.length - 1 - i; j++) {
				if (temp1[j].compareTo(temp1[j+1]) > 0) {
					T temp = temp1[j];
					temp1[j] = temp1[j+1];
					temp1[j+1] = temp;
				}
			}
		}

		for (int i = 0; i < temp2.length - 1; i++) {
			for (int j = 0; j < temp2.length - 1 - i; j++) {
				if (temp2[j].compareTo(temp2[j+1]) > 0) {
					T temp = temp2[j];
					temp2[j] = temp2[j+1];
					temp2[j+1] = temp;
				}
			}
		}

		for (int i = 0; i < temp1.length; i++) {
			comparacoes++;
			if (temp1[i].compareTo(temp2[i]) != 0) {
				return false;
			}
		}
		return true;
	}

	public void ordenarPorOcorrencia() {
		if (info == null || info.length == 0) {
			return;
		}

		// Para contar ocorrências sem HashMap, teríamos que fazer múltiplas passagens
		// ou criar um array auxiliar para pares (valor, contagem)
		// Vou usar HashMap para simplificar o contador de ocorrências,
		// mas a ordenação subsequente será feita sem ArrayList.
		// Se HashMap também for proibido, a lógica seria bem mais complexa.
		Map<T, Integer> frequencia = new HashMap<>();
		for (T item : info) {
			frequencia.put(item, frequencia.getOrDefault(item, 0) + 1);
		}

		// Bubble Sort customizado para ordenar por ocorrência e depois por valor
		for (int i = 0; i < info.length - 1; i++) {
			for (int j = 0; j < info.length - 1 - i; j++) {
				int cmpFrequencia = frequencia.get(info[j+1]).compareTo(frequencia.get(info[j])); // Decrescente por frequência
				
				if (cmpFrequencia > 0) { // info[j+1] tem mais ocorrências que info[j]
					trocar(j, j+1);
				} else if (cmpFrequencia == 0) { // Mesma frequência, ordena por valor crescente
					if (info[j].compareTo(info[j+1]) > 0) {
						trocar(j, j+1);
					}
				}
				comparacoes += 2; // Duas comparações lógicas no pior caso
			}
		}
	}

	public abstract void ordenar();
}