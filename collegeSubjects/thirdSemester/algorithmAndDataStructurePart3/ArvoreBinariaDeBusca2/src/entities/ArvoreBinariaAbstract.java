package entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class ArvoreBinariaAbstract<T> {
	private NoArvoreBinaria<T> raiz;

	public ArvoreBinariaAbstract() {
		setRaiz(null);
	}

	protected void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}
	
	public NoArvoreBinaria<T> getRaiz() {
		return this.raiz;
	}

	public boolean estaVazia() {
		return this.raiz == null;
	}

	public boolean pertence(T info) {
		return buscar(info) != null;
	}
	
	public abstract NoArvoreBinaria<T> buscar(T info);

	@Override
	public String toString() {
		return arvorePre(raiz);
	}

	// pre ordem (root, left, right)
	private String arvorePre(NoArvoreBinaria<T> no) {
		if (no == null) {
			return "<>";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(no.getInfo());
		sb.append(arvorePre(no.getEsquerda()));
		sb.append(arvorePre(no.getDireita()));
		sb.append(">");
		return sb.toString();
	}

	// contar nos
	public int contarNos() {
		return contarNos(raiz);
	}

	protected int contarNos(NoArvoreBinaria<T> no) {
		if(no == null) {
			return 0;
		}
		return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
	}

	// altura
	public int altura() {
		return altura(raiz);
	}

	protected int altura(NoArvoreBinaria<T> no) {
		if (no == null) {
			return -1; // Altura de uma árvore vazia é -1
		}
		int alturaEsquerda = altura(no.getEsquerda());
		int alturaDireita = altura(no.getDireita());
		return 1 + Math.max(alturaEsquerda, alturaDireita);
	}

	// in order traversal (left, root, right)
	public String exibirEmOrdem() {
		return exibirEmOrdem(raiz);
	}

	protected String exibirEmOrdem(NoArvoreBinaria<T> no) {
		if (no == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(exibirEmOrdem(no.getEsquerda()));
		sb.append(no.getInfo()).append(" ");
		sb.append(exibirEmOrdem(no.getDireita()));
		return sb.toString();
	}

	// post order traversal (left, right, root)
	public String exibirPosOrdem() {
		return exibirPosOrdem(raiz);
	}

	protected String exibirPosOrdem(NoArvoreBinaria<T> no) {
		if (no == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(exibirPosOrdem(no.getEsquerda()));
		sb.append(exibirPosOrdem(no.getDireita()));
		sb.append(no.getInfo()).append(" ");
		return sb.toString();
	}

	// pre order traversal (root, left, right) - same as toString but explicit name
	public String exibirPreOrdem() {
		return arvorePre(raiz);
	}

	// sao iguais (2 arvores)
	public boolean saoIguais(ArvoreBinariaAbstract<T> outraArvore) {
		return saoIguais(this.raiz, outraArvore.getRaiz());
	}

	private boolean saoIguais(NoArvoreBinaria<T> no1, NoArvoreBinaria<T> no2) {
		if (no1 == null && no2 == null) {
			return true;
		}
		if (no1 == null || no2 == null) {
			return false;
		}
		return no1.getInfo().equals(no2.getInfo()) &&
			   saoIguais(no1.getEsquerda(), no2.getEsquerda()) &&
			   saoIguais(no1.getDireita(), no2.getDireita());
	}

	// contar nos folha
	public int contarNosFolha() {
		return contarNosFolha(raiz);
	}

	private int contarNosFolha(NoArvoreBinaria<T> no) {
		if (no == null) {
			return 0;
		}
		if (no.getEsquerda() == null && no.getDireita() == null) {
			return 1;
		}
		return contarNosFolha(no.getEsquerda()) + contarNosFolha(no.getDireita());
	}

	// contar nos internos
	public int contarNosInternos() {
		return contarNosInternos(raiz);
	}

	private int contarNosInternos(NoArvoreBinaria<T> no) {
		if (no == null || (no.getEsquerda() == null && no.getDireita() == null)) {
			return 0;
		}
		return 1 + contarNosInternos(no.getEsquerda()) + contarNosInternos(no.getDireita());
	}

	// contar nos com 1 filho
	public int contarNosComUmFilho() {
		return contarNosComUmFilho(raiz);
	}

	private int contarNosComUmFilho(NoArvoreBinaria<T> no) {
		if (no == null) {
			return 0;
		}
		int count = 0;
		if ((no.getEsquerda() != null && no.getDireita() == null) ||
			(no.getEsquerda() == null && no.getDireita() != null)) {
			count = 1;
		}
		return count + contarNosComUmFilho(no.getEsquerda()) + contarNosComUmFilho(no.getDireita());
	}

	// contar nos com 2 filhos
	public int contarNosComDoisFilhos() {
		return contarNosComDoisFilhos(raiz);
	}

	private int contarNosComDoisFilhos(NoArvoreBinaria<T> no) {
		if (no == null) {
			return 0;
		}
		int count = 0;
		if (no.getEsquerda() != null && no.getDireita() != null) {
			count = 1;
		}
		return count + contarNosComDoisFilhos(no.getEsquerda()) + contarNosComDoisFilhos(no.getDireita());
	}

	// inverter
	public void inverter() {
		inverter(raiz);
	}

	private void inverter(NoArvoreBinaria<T> no) {
		if (no == null) {
			return;
		}
		NoArvoreBinaria<T> temp = no.getEsquerda();
		no.setEsquerda(no.getDireita());
		no.setDireita(temp);
		inverter(no.getEsquerda());
		inverter(no.getDireita());
	}

	// é completa
	public boolean eCompleta() {
		if (raiz == null) {
			return true;
		}

		Queue<NoArvoreBinaria<T>> queue = new LinkedList<>();
		queue.offer(raiz);
		boolean reachedNull = false;

		while (!queue.isEmpty()) {
			NoArvoreBinaria<T> current = queue.poll();

			if (current == null) {
				reachedNull = true;
			} else {
				if (reachedNull) {
					return false;
				}
				queue.offer(current.getEsquerda());
				queue.offer(current.getDireita());
			}
		}
		return true;
	}

	// é cheia
	public boolean eCheia() {
		return eCheia(raiz);
	}

	private boolean eCheia(NoArvoreBinaria<T> no) {
		if (no == null) {
			return true;
		}

		if (no.getEsquerda() == null && no.getDireita() == null) {
			return true;
		}

		if (no.getEsquerda() != null && no.getDireita() != null) {
			return eCheia(no.getEsquerda()) && eCheia(no.getDireita());
		}
		return false;
	}
	
	// contar nos em nivel
	public int contarNosNivel(int nivel) {
		return contarNosNivel(raiz, nivel, 0);
	}

	private int contarNosNivel(NoArvoreBinaria<T> no, int nivelDesejado, int nivelAtual) {
		if (no == null) {
			return 0;
		}
		if (nivelDesejado == nivelAtual) {
			return 1;
		}
		return contarNosNivel(no.getEsquerda(), nivelDesejado, nivelAtual + 1) +
			   contarNosNivel(no.getDireita(), nivelDesejado, nivelAtual + 1);
	}

	// contar folhas em nivel
	public int contarFolhasNivel(int nivel) {
		return contarFolhasNivel(raiz, nivel, 0);
	}

	private int contarFolhasNivel(NoArvoreBinaria<T> no, int nivelDesejado, int nivelAtual) {
		if (no == null) {
			return 0;
		}
		if (no.getEsquerda() == null && no.getDireita() == null) { // É uma folha
			if (nivelDesejado == nivelAtual) {
				return 1;
			} else {
				return 0;
			}
		}
		return contarFolhasNivel(no.getEsquerda(), nivelDesejado, nivelAtual + 1) +
			   contarFolhasNivel(no.getDireita(), nivelDesejado, nivelAtual + 1);
	}
	
	// buscar recursivo (generalizado para ArvoreBinariaAbstract)
	public NoArvoreBinaria<T> buscarRecursivo(T info) {
		return buscarRecursivo(raiz, info);
	}
	
	protected NoArvoreBinaria<T> buscarRecursivo(NoArvoreBinaria<T> no, T info) {
		if (no == null) {
			return null;
		}
		
		if (no.getInfo().equals(info)) {
			return no;
		}
		NoArvoreBinaria<T> esquerda = buscarRecursivo(no.getEsquerda(), info);
		if(esquerda != null) {
			return esquerda;
		}
		return buscarRecursivo(no.getDireita(), info);
	}

	// esta balanceada
	public boolean estaBalanceada() {
		return estaBalanceada(raiz);
	}

	protected boolean estaBalanceada(NoArvoreBinaria<T> no) {
		if (no == null) {
			return true;
		}
		int alturaEsquerda = altura(no.getEsquerda());
		int alturaDireita = altura(no.getDireita());

		if (Math.abs(alturaEsquerda - alturaDireita) <= 1 &&
			estaBalanceada(no.getEsquerda()) &&
			estaBalanceada(no.getDireita())) {
			return true;
		}
		return false;
	}

	// contar filhos (this is already covered by contarNosComUmFilho, contarNosComDoisFilhos, contarNosFolha)
	// If you want a method that returns the number of children for a specific node, you'd need to modify the NoArvoreBinaria class to expose that or pass the node directly.
	// Assuming it means counting total children in the tree:
	public int contarTotalFilhos() {
		// This is equivalent to total nodes minus number of leaves (for internal nodes)
		// Or simply total nodes - 1 if root has children.
		// A more direct interpretation would be sum of children of all nodes.
		// The sum of (count of 1-child nodes) + 2 * (count of 2-children nodes)
		return contarNosComUmFilho() + (2 * contarNosComDoisFilhos());
	}
}