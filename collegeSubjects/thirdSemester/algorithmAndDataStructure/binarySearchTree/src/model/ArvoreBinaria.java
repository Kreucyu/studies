package model;

public class ArvoreBinaria<T> extends ArvoreBinariaAbstract<T> {

	private NoArvoreBinaria<T> raiz;
	public void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}
	
	@Override
	public NoArvoreBinaria<T> buscar(T info) {
		// TODO Auto-generated method stub
		return buscar(raiz, info);
	}
	
	private NoArvoreBinaria<T> buscar(NoArvoreBinaria<T> no, T info) {
		return no;
		
	}
	
}
