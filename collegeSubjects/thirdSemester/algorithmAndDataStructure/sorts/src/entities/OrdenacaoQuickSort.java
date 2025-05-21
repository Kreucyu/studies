package entities;

public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        quicksort(0, getInfo().length - 1);
    }

    private void quicksort(int inicio, int fim) {
        if (inicio < fim) {
            int pivo = particionar(inicio, fim);
            quicksort(inicio, pivo - 1);
            quicksort(pivo + 1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        T[] info = getInfo();
        T pivo = info[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (info[j].compareTo(pivo) <= 0) {
                i++;
                trocar(i, j);
            }
        }

        trocar(i + 1, fim);
        return i + 1;
    }
}

