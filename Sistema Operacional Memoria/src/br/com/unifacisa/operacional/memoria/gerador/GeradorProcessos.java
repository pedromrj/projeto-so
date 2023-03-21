package br.com.unifacisa.operacional.memoria.gerador;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.Random;

public class GeradorProcessos {

    public static final int ALCANCE_MIN = 10;
    public static final int ALCANCE_MAX = 50;

    public static Alocacao gerar() {
        return new Alocacao(Memoria.ALOCACAO_PROCESSO,new Random().nextInt((ALCANCE_MAX - ALCANCE_MIN) + 1) + ALCANCE_MIN);
    }

}
