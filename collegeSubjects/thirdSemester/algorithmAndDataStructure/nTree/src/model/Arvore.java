package model;

public class Arvore<T> {
	private NoArvore<T> raiz;

	public Arvore() {
		setRaiz(null);
	}

	public NoArvore<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvore<T> raiz) {
		this.raiz = raiz;
	}
	
	@Override
	public String toString() {
		if (raiz == null) {
			return "Árvore vazia";
		} else {
			return obterRepresentacaoTextual(raiz);
		}
	}
	
	private String obterRepresentacaoTextual(NoArvore<T> no) {
		StringBuilder sb = new StringBuilder();
		NoArvore<T> p;
		sb.append("<");
		sb.append(no.getInfo());
		p = no.getPrimeiro();
		while(p != null) {
			sb.append(obterRepresentacaoTextual(p));
			p = p.getProximo();
		}
		sb.append(">");
		return sb.toString();
	}

	public boolean pertence(T info) {
		if (raiz == null) {
			return false;
		} else {
			return pertence(raiz, info);
		}

	}

	private boolean pertence(NoArvore<T> no, T info) {
		if(no.getInfo() == info) {
			return true;
		} else {
			NoArvore<T> p;
			p = no.getPrimeiro();
			while(p != null) {
				if(pertence(p, info)) {
					return true;
				}
				p = p.getProximo();
			}
		}
		return false;
	}
	
	public int contarNos() {
		if(raiz == null) {
			return 0;
		} 
		return contarNos(raiz);
	}
	
	private int contarNos(NoArvore<T> no) {
		NoArvore<T> p;
		p = no.getPrimeiro();
		int total = 1;
		while(p != null) {
			total += contarNos(p);
			p = p.getProximo();
		}
		return total;
	}
	
	public int calcularDistanciaAltura(T info1, T info2) {
		return calDisAltura(raiz, info1, info2);
	}
	
    private int calDisAltura(NoArvore<T> no, T info1, T info2) {
        int alturaNo1 = calcularAltura(no, info1, 0);
        int alturaNo2 = calcularAltura(no, info2, 0);

        if (alturaNo1 == -1 || alturaNo2 == -1) {
            return -1;
        }

        return Math.abs(alturaNo1 - alturaNo2);
    }

    private int calcularAltura(NoArvore<T> no, T info, int alturaAtual) {
        if (no == null) return -1;
        if (no.getInfo().equals(info)) return alturaAtual;

        NoArvore<T> p = no.getPrimeiro();
        while (p != null) {
            int altura = calcularAltura(p, info, alturaAtual + 1);
            if (altura != -1) {
                return altura;
            }
            p = p.getProximo();
        }
        return -1;
    }
    
    public int calcDistanciaNos(T info1, T info2) {
        return calcDisNosQtde(raiz, info1, info2);
    }

    @SuppressWarnings("unchecked")
    private int calcDisNosQtde(NoArvore<T> no, T info1, T info2) {
        NoArvore<T>[] caminho1 = new NoArvore[contarNos()];
        NoArvore<T>[] caminho2 = new NoArvore[contarNos()];

        int c1 = encontrarCaminho(no, info1, caminho1, 0);
        int c2 = encontrarCaminho(no, info2, caminho2, 0);

        if (c1 == -1 || c2 == -1) {
            return -1;
        }
        
        int i = 0;
        while (i < c1 && i < c2 && caminho1[i].getInfo() == caminho2[i].getInfo()) {
            i++;
        }

        return (c1 - i) + (c2 - i);
    }

    private int encontrarCaminho(NoArvore<T> no, T info, NoArvore<T>[] caminho, int i) {
        if (no == null) return -1;
        
        caminho[i++] = no;
        
        if (no.getInfo().equals(info)) {
            return i;
        }

        NoArvore<T> p = no.getPrimeiro();
        while (p != null) {
            int resultado = encontrarCaminho(p, info, caminho, i);
            if (resultado != -1) {
                return resultado;
            }
            p = p.getProximo();
        }
        return -1;
    }
    
    public int getAltura() {
        return getAltura(raiz);
    }

    private int getAltura(NoArvore<T> no) {
        if (no == null) return 0;
        int alturaMaxima = 0;

        NoArvore<T> p = no.getPrimeiro();
        while (p != null) {
            alturaMaxima = Math.max(alturaMaxima, getAltura(p));
            p = p.getProximo();
        }

        return 1 + alturaMaxima;
    }


}
