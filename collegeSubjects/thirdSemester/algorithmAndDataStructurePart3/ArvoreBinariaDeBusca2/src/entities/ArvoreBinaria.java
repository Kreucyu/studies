package entities;

public class ArvoreBinaria<T> extends ArvoreBinariaAbstract<T> {
	
	public void setRaiz(NoArvoreBinaria<T> raiz) {
		super.setRaiz(raiz);
	}

	@Override
	public NoArvoreBinaria<T> buscar(T info) {
		return buscarRecursivo(getRaiz(), info); // Uses the recursive search from abstract class
	}
}