package br.com.unifacisa.operacional.processos.escalonadores;

import br.com.unifacisa.operacional.processos.entidade.Processo;

public class EscalonadorRobinRound extends Escalonador {

    private final int quantum;

    public EscalonadorRobinRound(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public Processo getProcesso() {
        gerarProcesso();

        Processo processo = null;
        if (existeProcesso()) {
            processo = obterProcesso();
            removerProcesso();
            if(!processo.estaFinalizado()) {
                adicionarProcesso(processo);
            }
            processo = existeProcesso() ? obterProcesso(): null;
            System.out.println(processos);
            System.out.println("Possui ".concat(String.valueOf(getProcessos().size())).concat(" processos."));
        }

        return processo;
    }

    @Override
    public int getQuantun() {
        return this.quantum;
    }


}
