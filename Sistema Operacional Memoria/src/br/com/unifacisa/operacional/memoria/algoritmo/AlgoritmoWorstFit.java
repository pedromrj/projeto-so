package br.com.unifacisa.operacional.memoria.algoritmo;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.abstracts.Algoritmo;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.Comparator;
import java.util.LinkedList;

public class AlgoritmoWorstFit extends Algoritmo {

    private int contadorSucessos = 0;

    private int contadorDescartes = 0;

    @Override
    public void inserir(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        Alocacao aux = obterAlocacaoComEspaçoLivre(memoria, alocacao); // buscando o melhor espaço livre pra essa alocaçao.

        boolean adicionou = false;

        if (aux == null) {
            for (Alocacao x : memoria) {
                if (x.getTamanho() == alocacao.getTamanho()) {
                    inserirEspaçoIgual(memoria, x);// Inserir no espaço que que é igual.
                    adicionou = true;
                    break;
                }
            }

        } else {
            if (super.inserir(memoria, alocacao, aux)) {
                contadorSucessos++;
                adicionou = true;
            }
        }

        if (!adicionou) {
            contadorDescartes++;
        }
    }

    private Alocacao obterAlocacaoComEspaçoLivre(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        return memoria.stream().filter(x -> {
            return x.getId().equalsIgnoreCase(Memoria.ALOCACAO_LIVRE) && x.getTamanho() > alocacao.getTamanho();
        }).max(Comparator.comparing(Alocacao::getTamanho)).orElse(null);
    }

    public void inserirEspaçoIgual(LinkedList<Alocacao> memoria, Alocacao aux) {
        Alocacao aux1 = memoria.get(memoria.indexOf(aux));
        aux1.setId(Memoria.ALOCACAO_PROCESSO);
        contadorSucessos++;
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
