package model;

public abstract class ArvoreBinariaAbstract<T> {
	private NoArvoreBinaria<T> raiz;
	private int qtdeNos;

	public ArvoreBinariaAbstract() {
		qtdeNos = 0;
		setRaiz(null);
	}

	protected void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}
	

	public NoArvoreBinaria<T> getRaiz() {
		return raiz;
	}

	public boolean estaVazia() {
		return this.raiz == null;
	}

	public boolean pertence(T info) {
		if(buscar(info) != null) return true;
		return false;
	}

	@Override
	public String toString() {

		return arvorePre(raiz);
	}

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

	public int contarNos() {
		if(contarNos(raiz) != 0) {
			qtdeNos++;
		} else {
			qtdeNos = 0;
		}
		return qtdeNos;
	}

	private int contarNos(NoArvoreBinaria<T> no) {
		if(no == null) {
			return 0;
		}
		if(contarNos(no.getEsquerda()) == 1) {
			qtdeNos++;
		}
		if(contarNos(no.getDireita()) == 1) {
			qtdeNos++;
		}
		return 1;
	}
	
	public abstract NoArvoreBinaria<T> buscar(T info);

}
