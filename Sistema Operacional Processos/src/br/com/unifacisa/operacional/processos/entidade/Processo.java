package br.com.unifacisa.operacional.processos.entidade;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Processo implements Comparable<Processo> {

    public static final int TEMPO_DE_CADA_INSTRUCAO = 1000;

    private long id;
    private long quantidadeInstrucoes;

    public Processo(long id,long quantidadeInstrucoes) {
        this.id = id;
        this.quantidadeInstrucoes = quantidadeInstrucoes;
    }

    // Thread Sleep usado para teste!
    public void finalizarInstrucao() {
        System.out.println("Instrução finalizada para o id ".concat(String.valueOf(id)).concat(" resta ".concat(String.valueOf(quantidadeInstrucoes)).concat(" instruções.")));
        quantidadeInstrucoes--;
//        try {
//            Thread.sleep(TEMPO_DE_CADA_INSTRUCAO);
//        } catch (InterruptedException e) {
//            System.out.println("não foi possivel realizar o sleep");
//        }
    }

    public long getTempo() {
        return TEMPO_DE_CADA_INSTRUCAO * quantidadeInstrucoes;
    }

    public boolean estaFinalizado() {
        return quantidadeInstrucoes == 0;
    }

    @Override
    public String toString() {
        return "Processo{" + "id, " +id +
                "tempo=" + getTempo() +
                '}';
    }

    @Override
    public int compareTo(Processo processo) {
        return 0;
    }
}
