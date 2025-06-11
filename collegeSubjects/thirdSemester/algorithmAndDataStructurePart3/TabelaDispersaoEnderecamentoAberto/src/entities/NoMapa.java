package entities;
import java.util.Objects;

public class NoMapa<T> {
    private int chave;
    private T valor;

    public NoMapa(int chave, T valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    // O método equals() para NoMapa deve ser baseado apenas na chave, conforme o documento original.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoMapa<?> noMapa = (NoMapa<?>) o;
        return chave == noMapa.chave; // A identidade por valor é utilizando apenas o atributo chave.
    }

    @Override
    public int hashCode() {
        return Objects.hash(chave);
    }

    @Override
    public String toString() {
        if (valor == null) { // Para o caso de LAZY_DELETED
            return "NoMapa{chave=" + chave + ", valor=null}";
        }
        return "NoMapa{chave=" + chave + ", valor=" + valor.toString() + "}";
    }
}