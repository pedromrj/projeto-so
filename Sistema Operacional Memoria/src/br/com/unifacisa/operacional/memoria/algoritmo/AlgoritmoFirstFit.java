package br.com.unifacisa.operacional.memoria.algoritmo;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.abstracts.Algoritmo;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.Iterator;
import java.util.LinkedList;

public class AlgoritmoFirstFit extends Algoritmo {

    private int contadorSucessos = 0;

    private int contadorDescartes = 0;

    @Override
    public void inserir(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        Iterator<Alocacao> iterator = new LinkedList<>(memoria).stream().iterator();
        boolean adicionou = false;

        while(iterator.hasNext()) {
            Alocacao x = iterator.next();
            if (x.getId().equalsIgnoreCase(Memoria.ALOCACAO_LIVRE)) {
                if (x.getTamanho() == alocacao.getTamanho()) {
                    x.setId(alocacao.getId());
                    adicionou = true;
                    contadorSucessos++;
                    break;
                }
                if (alocacao.getTamanho() < x.getTamanho() && super.inserir(memoria, alocacao, x)) {
                    contadorSucessos++;
                    adicionou = true;
                    break;
                }
            }
        };

        if (!adicionou) {
            contadorDescartes++;
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


