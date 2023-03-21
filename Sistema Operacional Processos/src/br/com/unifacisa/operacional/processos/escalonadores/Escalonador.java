package br.com.unifacisa.operacional.processos.escalonadores;

import br.com.unifacisa.operacional.processos.entidade.GeradorDeProcessos;
import br.com.unifacisa.operacional.processos.entidade.Processo;

import java.util.ArrayList;
import java.util.List;


public abstract class Escalonador {

    protected List<Processo> processos = new ArrayList<Processo>();

    public abstract Processo getProcesso();
    public abstract int getQuantun();

    // Processos para testa o tempo de cada escalonador.
    public Escalonador() {
//        processos.add(new Processo(1, 5));
//        processos.add(new Processo(2, 10));
//        processos.add(new Processo(3, 15));
//        processos.add(new Processo(4, 12));
//        processos.add(new Processo(5, 6));
//        processos.add(new Processo(6, 25));
//        processos.add(new Processo(7, 31));
    }

    protected void gerarProcesso() {
        System.out.println(processos);
        Processo processo = GeradorDeProcessos.gerar();
        if (processo != null) {
            processos.add(processo);
        }
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public boolean existeProcesso() {
        return !processos.isEmpty();
    }

    public Processo obterProcesso() {
        return processos.get(0);
    }

    public void removerProcesso() {
        processos.remove(0);
    }

    public void adicionarProcesso(Processo processo) {
        processos.add(processo);
    }

}
