package br.com.unifacisa.operacional.memoria.abstracts;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.LinkedList;

public abstract class Algoritmo {

    public void inserir(LinkedList<Alocacao> memoria, Alocacao alocacao) {
    }

    public abstract int sucesso();

    public abstract int descartes();

    public  boolean inserir(LinkedList<Alocacao> memoria, Alocacao alocacao, Alocacao x) {

        Integer tamanhoLivre = x.getTamanho() - alocacao.getTamanho();
        Alocacao alocacaoLivre = new Alocacao(Memoria.ALOCACAO_LIVRE, tamanhoLivre);
        Integer posicaoLivre = x.getPosicao() + alocacao.getTamanho();
        alocacaoLivre.setPosicao(posicaoLivre);
        alocacao.setPosicao(x.getPosicao() );
        int index = memoria.indexOf(x);
        memoria.remove(x);

        if (alocacao.getTamanho() + alocacao.getPosicao() <= Memoria.TAMANHO) {
            alocacaoLivre.setTamanho(alocacaoLivre.getTamanho());
            memoria.add(index,alocacaoLivre);
            memoria.add(index,alocacao);
            return true;
        }
        return false;
    }

}
