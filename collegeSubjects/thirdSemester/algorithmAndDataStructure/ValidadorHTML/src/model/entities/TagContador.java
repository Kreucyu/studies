package model.entities;

public class TagContador implements Comparable<TagContador> {
	public String nome;
	public int quantidade;

	public TagContador(String nome) {
		this.nome = nome;
		this.quantidade = 1;
	}

	@Override
	public int compareTo(TagContador other) {
		return Integer.compare(other.quantidade, this.quantidade);
	}
}
