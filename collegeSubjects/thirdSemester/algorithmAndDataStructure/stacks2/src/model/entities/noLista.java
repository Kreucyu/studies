package model.entities;

public class noLista<T> {
	private T info;
	private noLista<T> proximo;

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	public noLista<T> getProximo() {
		return proximo;
	}

	public void setProximo(noLista<T> proximo) {
		this.proximo = proximo;
	}
	
}
