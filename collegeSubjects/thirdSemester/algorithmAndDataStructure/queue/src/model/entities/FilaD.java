package model.entities;

public interface FilaD <T> {
	
	void inserir(T valor);
	void inserirInicio(T valor);
	boolean estaVazia();
	T peek();
	T retirar();
	T retirarFinal();
	void liberar();
	
}
