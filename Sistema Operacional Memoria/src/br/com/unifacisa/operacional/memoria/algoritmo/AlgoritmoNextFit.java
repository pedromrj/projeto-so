package br.com.unifacisa.operacional.memoria.algoritmo;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.abstracts.Algoritmo;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.LinkedList;

public class AlgoritmoNextFit extends Algoritmo {

    private Alocacao index;

    private int contadorSucessos = 0;

    private int contadorDescartes = 0;

    @Override
    public void inserir(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        boolean adicionou = false;

        int tamanho = memoria.size();

        if ( index == null) {
            index = alocacao;
        }


        for (int i = getIndex(memoria,alocacao); i < tamanho; i++) {
            Alocacao x = memoria.get(i);
            if (x.getId().equalsIgnoreCase(Memoria.ALOCACAO_LIVRE)) {
                if (x.getTamanho() == alocacao.getTamanho()) {
                    x.setId(alocacao.getId());
                    adicionou = true;
                    index = x;
                    contadorSucessos++;
                    break;
                }
                if (alocacao.getTamanho() < x.getTamanho() && super.inserir(memoria, alocacao, x)) {
                    contadorSucessos++;
                    adicionou = true;
                    index = alocacao;
                    break;
                }
            }


        }

        if (!adicionou) {
            contadorDescartes++;
        }
    }

    private Integer getIndex(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        if (!memoria.contains(alocacao)) {
            return 0;
        } else {
            return memoria.indexOf(alocacao);
        }
    }


    @Override
    public int sucesso() {
        return contadorSucessos;
    }

    @Override
    public int descartes() {
        return contadorDescartes;
    }
}
