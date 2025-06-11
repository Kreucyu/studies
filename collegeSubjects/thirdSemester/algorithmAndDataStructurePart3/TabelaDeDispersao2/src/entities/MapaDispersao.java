package entities;

import java.util.ArrayList;
import java.util.List;

public class MapaDispersao<T> {
    private ListaEncadeada<NoMapa<T>> info[];

    public ListaEncadeada<NoMapa<T>>[] getInfo() {
        return info;
    }

    public void setInfo(ListaEncadeada<NoMapa<T>> info[]) {
        this.info = info;
    }

    @SuppressWarnings("unchecked")
    public MapaDispersao(int tamanho) {
        info = new ListaEncadeada[tamanho];
    }

    private int calcularHash(int chave) {
        int tamanho = getInfo().length;
        return chave % tamanho;
    }

    public void inserir(int chave, T dado) {
        int idx = calcularHash(chave);
        if (info[idx] == null) {
            info[idx] = new ListaEncadeada<>();
        }
        NoMapa<T> no = new NoMapa<T>();
        no.setChave(chave);
        no.setValor(dado);
        info[idx].inserir(no);
    }

    public void remover(int chave) {
        int idx = calcularHash(chave);
        // Ensure the list exists before trying to remove
        if (info[idx] != null) {
            NoMapa<T> no = new NoMapa<T>();
            no.setChave(chave);
            info[idx].retirar(no);
        }
    }

    public T buscar(int chave) {
        int idx = calcularHash(chave);
        if (info[idx] != null) {
            NoMapa<T> no = new NoMapa<T>();
            no.setChave(chave);
            // Assuming buscar in ListaEncadeada returns NoLista<NoMapa<T>> if found, null otherwise
            NoLista<NoMapa<T>> result = info[idx].buscar(no);
            if (result != null) { // Check result itself, not 'no'
                return result.getInfo().getValor();
            }
        }
        return null;
    }

    public double calcularFatorCarga() {
        double tamanho = getInfo().length;
        double qtde = 0;
        for (ListaEncadeada<NoMapa<T>> n : info) {
            if (n != null) {
                qtde += n.obterComprimento();
            }
        }
        return qtde / tamanho;
    }

    public int calcularQtdeObjetos() {
        int obj = 0;
        for (ListaEncadeada<NoMapa<T>> n : info) {
            if (n != null) {
                obj += n.obterComprimento();
            }
        }
        return obj;
    }

    /**
     * Lista todas as chaves presentes no mapa.
     * @return Uma lista de inteiros contendo todas as chaves.
     */
    public List<Integer> listarChaves() {
        List<Integer> chaves = new ArrayList<>();
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null) {
                NoLista<NoMapa<T>> atual = lista.getPrimeiro(); // Assuming getPrimeiro() exists in ListaEncadeada
                while (atual != null) {
                    chaves.add(atual.getInfo().getChave());
                    atual = atual.getProximo(); // Assuming getProximo() exists in NoLista
                }
            }
        }
        return chaves;
    }

    /**
     * Conta o número total de colisões no mapa.
     * Uma colisão ocorre quando mais de um elemento é mapeado para o mesmo índice da tabela hash.
     * @return O número total de colisões.
     */
    public int contarColisoes() {
        int colisoes = 0;
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null && lista.obterComprimento() > 1) {
                colisoes += (lista.obterComprimento() - 1);
            }
        }
        return colisoes;
    }

    /**
     * Verifica se uma chave específica está presente no mapa.
     * @param chave A chave a ser verificada.
     * @return true se a chave estiver presente, false caso contrário.
     */
    public boolean contemChave(int chave) {
        return buscar(chave) != null;
    }

    /**
     * Limpa o mapa, removendo todos os elementos.
     */
    @SuppressWarnings("unchecked")
    public void limpar() {
        info = new ListaEncadeada[info.length]; // Re-initializes the array with nulls
    }

    /**
     * Busca um valor associado a uma chave, com tratamento explícito de colisões
     * (já implícito na busca original que usa lista encadeada).
     * Este método é funcionalmente idêntico ao método `buscar` existente,
     * mas é mantido para clareza em relação ao requisito.
     * @param chave A chave a ser buscada.
     * @return O valor associado à chave, ou null se a chave não for encontrada.
     */
    public T buscaComTratamentoColisoes(int chave) {
        return buscar(chave); // The existing 'buscar' method already handles collisions via linked lists.
    }

    /**
     * Reorganiza a tabela hash, tipicamente redimensionando-a e redistribuindo os elementos.
     * Neste exemplo, simplesmente chama o método redimensionar com o dobro do tamanho.
     */
    public void reorganizarTabela() {
        redimensionar(info.length * 2);
    }

    /**
     * Retorna o comprimento da maior lista encadeada (bucket) no mapa.
     * @return O tamanho da maior lista.
     */
    public int tamanhoMaiorLista() {
        int maiorTamanho = 0;
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null) {
                int comprimentoAtual = lista.obterComprimento();
                if (comprimentoAtual > maiorTamanho) {
                    maiorTamanho = comprimentoAtual;
                }
            }
        }
        return maiorTamanho;
    }

    /**
     * Busca a posição (índice do array) onde uma chave seria armazenada.
     * Não verifica se a chave realmente existe, apenas calcula o hash.
     * @param chave A chave para calcular a posição.
     * @return O índice do array correspondente à chave.
     */
    public int buscarPosicao(int chave) {
        return calcularHash(chave);
    }

    /**
     * Calcula a taxa de ocupação (percentual de buckets não vazios) do mapa.
     * @return A taxa de ocupação como um valor double entre 0.0 e 1.0.
     */
    public double taxaDeOcupacao() {
        int bucketsOcupados = 0;
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null && lista.obterComprimento() > 0) {
                bucketsOcupados++;
            }
        }
        return (double) bucketsOcupados / info.length;
    }

    /**
     * Verifica se o mapa contém chaves duplicadas.
     * No entanto, a implementação atual de `inserir` não permite chaves duplicadas no mesmo bucket
     * (assumindo que `ListaEncadeada.inserir` não adiciona duplicatas ou que `NoMapa.equals`
     * com base na chave impede isso). Se 'inserir' sempre adiciona,
     * mesmo que a chave já exista, esta verificação seria mais relevante.
     * Com a implementação atual, o método `inserir` simplesmente adiciona um novo `NoMapa`
     * à lista encadeada. Se já existir um `NoMapa` com a mesma chave, ele será duplicado.
     * Para evitar duplicatas e ter uma lógica mais "mapa", o `inserir` deveria primeiro
     * buscar se a chave já existe e, se sim, atualizar o valor ou ignorar a inserção.
     * Assumindo que `ListaEncadeada.inserir` pode levar a duplicatas de `NoMapa` baseadas apenas na chave.
     * @return true se houver chaves duplicadas, false caso contrário.
     */
    public boolean possuiDuplicatas() {
        List<Integer> chavesEncontradas = new ArrayList<>();
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null) {
                NoLista<NoMapa<T>> atual = lista.getPrimeiro();
                while (atual != null) {
                    int chaveAtual = atual.getInfo().getChave();
                    if (chavesEncontradas.contains(chaveAtual)) {
                        return true;
                    }
                    chavesEncontradas.add(chaveAtual);
                    atual = atual.getProximo();
                }
            }
        }
        return false;
    }


    /**
     * Redimensiona a tabela hash para um novo tamanho.
     * Todos os elementos existentes são re-hashing e redistribuídos na nova tabela.
     * @param novoTamanho O novo tamanho desejado para a tabela.
     */
    @SuppressWarnings("unchecked")
    public void redimensionar(int novoTamanho) {
        if (novoTamanho <= 0) {
            throw new IllegalArgumentException("O novo tamanho deve ser maior que zero.");
        }

        // Armazenar os elementos existentes
        List<NoMapa<T>> todosElementos = new ArrayList<>();
        for (ListaEncadeada<NoMapa<T>> lista : info) {
            if (lista != null) {
                NoLista<NoMapa<T>> atual = lista.getPrimeiro();
                while (atual != null) {
                    todosElementos.add(atual.getInfo());
                    atual = atual.getProximo();
                }
            }
        }

        // Criar o novo array de listas
        info = new ListaEncadeada[novoTamanho];

        // Reinserir todos os elementos na nova tabela
        for (NoMapa<T> no : todosElementos) {
            inserir(no.getChave(), no.getValor());
        }
    }

    /**
     * Substitui o valor associado a uma chave existente.
     * Se a chave não existir, o valor não é substituído e a operação falha.
     * @param chave A chave do elemento a ser atualizado.
     * @param novoValor O novo valor a ser associado à chave.
     * @return true se o valor foi substituído com sucesso, false caso contrário.
     */
    public boolean substituirValor(int chave, T novoValor) {
        int idx = calcularHash(chave);
        if (info[idx] != null) {
            NoMapa<T> noProcurado = new NoMapa<>();
            noProcurado.setChave(chave);
            NoLista<NoMapa<T>> resultadoBusca = info[idx].buscar(noProcurado);
            if (resultadoBusca != null) {
                resultadoBusca.getInfo().setValor(novoValor);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica a distribuição dos elementos na tabela hash, imprimindo o comprimento de cada lista.
     * Ajuda a visualizar como os elementos estão dispersos.
     */
    public void verificarDistribuicao() {
        System.out.println("Verificação da Distribuição do Mapa Dispersão:");
        for (int i = 0; i < info.length; i++) {
            int comprimento = (info[i] != null) ? info[i].obterComprimento() : 0;
            System.out.println("Índice " + i + ": " + comprimento + " elementos");
        }
    }

    /**
     * Insere um novo par chave-valor, mas rejeita a inserção se o bucket já estiver ocupado
     * (ou seja, se já houver qualquer elemento no bucket).
     * Este é um tipo de estratégia de sondagem linear ou endereçamento aberto simplificado,
     * onde não há tratamento de colisões além da rejeição.
     * **Nota**: Esta implementação contradiz a natureza de uma tabela hash com listas encadeadas
     * para tratamento de colisões, onde múltiplos elementos no mesmo bucket são esperados.
     * Se o objetivo é que cada bucket tenha no máximo um elemento, a estrutura deveria ser diferente.
     * Mantida para atender ao requisito, mas com a ressalva.
     * @param chave A chave a ser inserida.
     * @param dado O dado a ser inserido.
     * @return true se a inserção foi bem-sucedida (bucket estava vazio), false caso contrário.
     */
    public boolean inserirComRejeicao(int chave, T dado) {
        int idx = calcularHash(chave);
        if (info[idx] == null || info[idx].obterComprimento() == 0) {
            info[idx] = new ListaEncadeada<>();
            NoMapa<T> no = new NoMapa<>();
            no.setChave(chave);
            no.setValor(dado);
            info[idx].inserir(no);
            return true;
        } else {
            // Bucket já ocupado, rejeita a inserção
            return false;
        }
    }

    /**
     * Obtém o último elemento (NoMapa) de uma lista encadeada em um determinado índice (bucket).
     * @param idx O índice do bucket.
     * @return O último NoMapa na lista do índice especificado, ou null se a lista estiver vazia ou não existir.
     */
    public NoMapa<T> obterUltimoDaLista(int idx) {
        if (idx < 0 || idx >= info.length || info[idx] == null || info[idx].obterComprimento() == 0) {
            return null;
        }
        // Assuming ListaEncadeada has a method to get the last node, or we can traverse
        NoLista<NoMapa<T>> atual = info[idx].getPrimeiro(); // Assuming getPrimeiro() exists
        NoMapa<T> ultimo = null;
        while (atual != null) {
            ultimo = atual.getInfo();
            atual = atual.getProximo(); // Assuming getProximo() exists
        }
        return ultimo;
    }
}