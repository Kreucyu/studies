package model;

public class ArvoreBinariaBusca<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

    public void inserir(T info) {
        setRaiz(inserir(getRaiz(), info));
    }

    private NoArvoreBinaria<T> inserir(NoArvoreBinaria<T> no, T info) {
        if (no == null) {
            return new NoArvoreBinaria<>(info);
        }
        if (info.compareTo(no.getInfo()) < 0) {
            no.setEsquerda(inserir(no.getEsquerda(), info));
        } else {
            no.setDireita(inserir(no.getDireita(), info));
        }
        return no;
    }

    @Override
    public NoArvoreBinaria<T> buscar(T info) {
        return buscar(getRaiz(), info);
    }

    private NoArvoreBinaria<T> buscar(NoArvoreBinaria<T> no, T info) {
        if (no == null || no.getInfo().equals(info)) {
            return no;
        }
        if (info.compareTo(no.getInfo()) < 0) {
            return buscar(no.getEsquerda(), info);
        } else {
            return buscar(no.getDireita(), info);
        }
    }

    public void retirar(T valor) {
        NoArvoreBinaria<T> p = getRaiz();
        NoArvoreBinaria<T> pai = null;
        boolean filhoEsquerda = false;

        while (p != null && !p.getInfo().equals(valor)) {
            pai = p;
            if (valor.compareTo(p.getInfo()) < 0) {
                filhoEsquerda = true;
                p = p.getEsquerda();
            } else {
                filhoEsquerda = false;
                p = p.getDireita();
            }
        }

        if (p == null) return; 


        if (p.getEsquerda() == null && p.getDireita() == null) {
            if (p == getRaiz()) {
                setRaiz(null);
            } else if (filhoEsquerda) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
        }
     
        else if (p.getDireita() == null) {
            if (p == getRaiz()) {
                setRaiz(p.getEsquerda());
            } else if (filhoEsquerda) {
                pai.setEsquerda(p.getEsquerda());
            } else {
                pai.setDireita(p.getEsquerda());
            }
        }
 
        else if (p.getEsquerda() == null) {
            if (p == getRaiz()) {
                setRaiz(p.getDireita());
            } else if (filhoEsquerda) {
                pai.setEsquerda(p.getDireita());
            } else {
                pai.setDireita(p.getDireita());
            }
        }
 
        else {
            NoArvoreBinaria<T> sucessor = extrairSucessor(p);
            if (p == getRaiz()) {
                setRaiz(sucessor);
            } else if (filhoEsquerda) {
                pai.setEsquerda(sucessor);
            } else {
                pai.setDireita(sucessor);
            }
            sucessor.setEsquerda(p.getEsquerda());
        }
    }

    private NoArvoreBinaria<T> extrairSucessor(NoArvoreBinaria<T> p) {
        NoArvoreBinaria<T> atual = p.getDireita();
        NoArvoreBinaria<T> paiSucessor = p;
        NoArvoreBinaria<T> sucessor = p;

        while (atual != null) {
            paiSucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsquerda();
        }

        if (sucessor != p.getDireita()) {
            paiSucessor.setEsquerda(sucessor.getDireita());
            sucessor.setDireita(p.getDireita());
        }

        return sucessor;
    }
}
