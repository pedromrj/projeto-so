package br.com.unifacisa.operacional.memoria.entidade;

import br.com.unifacisa.operacional.memoria.enums.TipoAlocacao;

import java.util.*;

public class Memoria {

    public static final Integer TAMANHO = 1000;

    public static final String ALOCACAO_LIVRE = "L";

    public static final String ALOCACAO_PROCESSO = "P";

    private LinkedList<Alocacao> memoria = new LinkedList<Alocacao>();

    private TipoAlocacao tipoAlocacao;

    public void adicionar(Alocacao alocacao) {
        if(memoria.isEmpty()) {
            Alocacao livre = new Alocacao(ALOCACAO_LIVRE, TAMANHO);
            livre.setPosicao(0);
            memoria.add(livre);
        }

        tipoAlocacao.getAlgoritmo().inserir(memoria, alocacao);
        // Ajustando para não ter objeto de espaço de memoria junto EX: (P,0,10),(L,11,5),(L,17,3),(P,21,3)...
        atualizacao();
    }

    public void remover(Integer quantidade) {
        int tamanho = memoria.size();
        int count = 1;

        while(count <= quantidade) {
            Alocacao alocacao = memoria.get(new Random().nextInt(tamanho));
            alocacao.setId(ALOCACAO_LIVRE);
            count++;
        }
        atualizacao();
    }

    public Integer getAlocacao() {
        return tipoAlocacao.getAlgoritmo().sucesso();
    }

    public void setTipoAlocacao(TipoAlocacao tipoAlocacao) {
        this.tipoAlocacao = tipoAlocacao;
    }

    public Integer getDescartes() {
        return tipoAlocacao.getAlgoritmo().descartes();
    }


    private void atualizacao() {
        LinkedList<Alocacao> memoriaInicial = new LinkedList<>(memoria);
        LinkedList<Alocacao> memoriaTemp = new LinkedList<>();
        LinkedList<Integer> memoriaRemove = new LinkedList<Integer>();

        for (int i = 0; i < memoriaInicial.size(); i++) {
            Alocacao aloc = memoriaInicial.get(i);
            if (aloc.getId().equalsIgnoreCase(ALOCACAO_LIVRE) && !memoriaRemove.contains(i)) {
                for (int j = i + 1; j < memoriaInicial.size(); j++) {
                    Alocacao aloc1 = memoria.get(j);
                    if (aloc1.getId().equalsIgnoreCase(ALOCACAO_LIVRE)) {
                        aloc.setTamanho(aloc.getTamanho() + aloc1.getTamanho());
                        memoriaRemove.add(j);
                    } else {
                        break;
                    }
                }
            }

            if (!memoriaRemove.contains(i)) {
                memoriaTemp.add(aloc);
            }
        }
        memoria = new LinkedList<>(memoriaTemp);
    }

    public LinkedList<Alocacao> getMemoria() {
        return memoria;
    }
}
