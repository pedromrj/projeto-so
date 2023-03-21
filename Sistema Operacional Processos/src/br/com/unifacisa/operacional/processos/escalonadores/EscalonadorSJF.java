package br.com.unifacisa.operacional.processos.escalonadores;

import br.com.unifacisa.operacional.processos.entidade.Processo;

import java.util.Comparator;

public class EscalonadorSJF extends Escalonador {

    @Override
    public Processo getProcesso() {
        gerarProcesso();
        Processo processo = null;
        if (existeProcesso()) {
            processo = obterProcesso();

            if (processo.estaFinalizado()) {
                removerProcesso();
                ordenar();
                processo = existeProcesso() ? obterProcesso() : null;
            }
            System.out.println(processos);
            System.out.println("Possui ".concat(String.valueOf(getProcessos().size())).concat(" processos."));
        }
        return processo;
    }

    @Override
    public int getQuantun() {
        return 0;
    }

    public void ordenar() {
        getProcessos().sort(Comparator.comparingLong(Processo::getTempo));
    }


}
