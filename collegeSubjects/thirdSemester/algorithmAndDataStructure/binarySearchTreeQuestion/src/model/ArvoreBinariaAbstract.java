package model;

public abstract class ArvoreBinariaAbstract<T extends Comparable<T>> {
    private NoArvoreBinaria<T> raiz;

    protected void setRaiz(NoArvoreBinaria<T> raiz) {
        this.raiz = raiz;
    }

    public NoArvoreBinaria<T> getRaiz() {
        return raiz;
    }

    public boolean pertence(T info) {
        return buscar(info) != null;
    }

    public abstract NoArvoreBinaria<T> buscar(T info);

    public String toString() {
        return (raiz != null) ? raiz.toStringFormatado() : "<>";
    }
}
