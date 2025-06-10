package model;

public class NoArvoreBinaria<T> {
    private T info;
    private NoArvoreBinaria<T> esquerda;
    private NoArvoreBinaria<T> direita;

    public NoArvoreBinaria(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoArvoreBinaria<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvoreBinaria<T> esquerda) {
        this.esquerda = esquerda;
    }

    public NoArvoreBinaria<T> getDireita() {
        return direita;
    }

    public void setDireita(NoArvoreBinaria<T> direita) {
        this.direita = direita;
    }

    public String toString() {
        return this.getInfo().toString();
    }

    public String toStringFormatado() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(this.getInfo());
        sb.append(this.getEsquerda() != null ? this.getEsquerda().toStringFormatado() : "<>");
        sb.append(this.getDireita() != null ? this.getDireita().toStringFormatado() : "<>");
        sb.append(">");
        return sb.toString();
    }
}
