package entities;

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
		return contarNos(raiz);
	}

	private int contarNos(NoArvoreBinaria<T> no) {
		if(no == null) {
			return 0;
		}
		return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
	}

}
