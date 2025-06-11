package entities;

//MapaDispersao.java
public class MapaDispersao<T> {
 private NoMapa<T>[] info; // 
 private int tamanho; //
 private int quantidadeElementos;

 // Constante para indicar um slot vazio que já teve um elemento
 // Usado para "lazy deletion" em endereçamento aberto
 private final NoMapa<T> LAZY_DELETED = new NoMapa<>(-1, null);

 public MapaDispersao(int tamanho) { // O construtor MapaDispersao (int) deve criar um mapa com vetor encapsulado igual ao fornecido como argumento.
     this.tamanho = tamanho;
     // A supressão de warnings é necessária porque não se pode criar arrays genéricos diretamente.
     @SuppressWarnings("unchecked")
     NoMapa<T>[] temp = (NoMapa<T>[]) new NoMapa[tamanho];
     this.info = temp;
     this.quantidadeElementos = 0;
 }

 // O método calcularHash (int) deve obter o hash para uma determinada chave de busca.
 // Utilizar a função de resto de divisão para compactar o valor.
 private int calcularHash(int chave) {
     return chave % tamanho;
 }

 // O método inserir (int, T) deve armazenar o objeto fornecido como argumento no mapa de dispersão, considerando a chave informada.
 public void inserir(int chave, T dado) {
     if (quantidadeElementos == tamanho) {
         System.out.println("Mapa cheio. Não é possível inserir.");
         return;
     }

     int hash = calcularHash(chave);
     int indiceInicial = hash;
     int i = 0;

     // Procura por um slot vazio ou um slot marcado como "lazy deleted"
     do {
         int indiceAtual = (indiceInicial + i) % tamanho;
         if (info[indiceAtual] == null || info[indiceAtual] == LAZY_DELETED) {
             info[indiceAtual] = new NoMapa<>(chave, dado);
             quantidadeElementos++;
             return;
         }
         // Se a chave já existe, atualiza o valor
         if (info[indiceAtual].getChave() == chave) {
             info[indiceAtual].setValor(dado);
             return;
         }
         i++;
     } while (i < tamanho); // Continua procurando até percorrer todo o array
 }

 // O método remover (int) deve remover do mapa de dispersão o objeto que possui a mesma chave de busca do objeto fornecido como argumento.
 public void remover(int chave) {
     int hash = calcularHash(chave);
     int indiceInicial = hash;
     int i = 0;

     do {
         int indiceAtual = (indiceInicial + i) % tamanho;
         if (info[indiceAtual] == null) {
             // Chegou a um slot vazio, significa que a chave não está presente
             return;
         }
         if (info[indiceAtual].getChave() == chave && info[indiceAtual] != LAZY_DELETED) {
             // Encontrou o elemento, marca como "lazy deleted"
             info[indiceAtual] = LAZY_DELETED;
             quantidadeElementos--;
             return;
         }
         i++;
     } while (i < tamanho);
 }

 // O método buscar (int) deve procurar no mapa de dispersão um objeto que possua chave de busca igual à fornecido como argumento.
 // Como resultado do seu processamento, o método deve retornar o objeto localizado.
 public T buscar(int chave) {
     int hash = calcularHash(chave);
     int indiceInicial = hash;
     int i = 0;

     do {
         int indiceAtual = (indiceInicial + i) % tamanho;
         if (info[indiceAtual] == null) {
             // Chegou a um slot vazio, significa que a chave não está presente
             return null;
         }
         if (info[indiceAtual].getChave() == chave && info[indiceAtual] != LAZY_DELETED) {
             // Encontrou o elemento
             return info[indiceAtual].getValor();
         }
         i++;
     } while (i < tamanho);
     return null; // Chave não encontrada após percorrer todo o array
 }

 // O método calcular Fator Carga () deve calcular e retornar o fator de carga do mapa de dispersão.
 public double calcularFatorCarga() {
     return (double) quantidadeElementos / tamanho;
 }

 // Para fins de depuração e visualização
 public void imprimirMapa() {
     System.out.println("--- Mapa de Dispersão (Endereçamento Aberto) ---");
     for (int i = 0; i < tamanho; i++) {
         System.out.print("[" + i + "] ");
         if (info[i] == null) {
             System.out.println("null");
         } else if (info[i] == LAZY_DELETED) {
             System.out.println("LAZY_DELETED");
         } else {
             System.out.println("Chave: " + info[i].getChave() + ", Valor: " + info[i].getValor());
         }
     }
     System.out.println("----------------------------------------------");
 }
}
