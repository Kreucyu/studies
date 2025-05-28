package model.entities;

/**
 * Representa uma tag HTML e a quantidade de vezes que ela aparece em um documento.
 * 
 * Utiliza Comparable para permitir ordenação decrescente por frequência.
 */

public class TagContador implements Comparable<TagContador> {
	public String nome; // Nome da tag HTML.
	public int quantidade; // Quantidade de ocorrências.

	// Cria a instância da classe.
	public TagContador(String nome) {
		this.nome = nome;
		this.quantidade = 1;
	}

	// Compara esse objeto com o outro, retornando um valor negativo, positivo ou zero.
	@Override
	public int compareTo(TagContador other) {
		return Integer.compare(other.quantidade, this.quantidade);
	}
}
