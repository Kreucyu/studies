package entities;

public class OrdenacaoBolhaOtimizada<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordenar() {
        T[] info = getInfo();
        boolean trocou;

        for (int i = 0; i < info.length - 1; i++) {
            trocou = false;
            for (int j = 0; j < info.length - 1 - i; j++) {
                if (info[j].compareTo(info[j + 1]) > 0) {
                    trocar(j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }
}

