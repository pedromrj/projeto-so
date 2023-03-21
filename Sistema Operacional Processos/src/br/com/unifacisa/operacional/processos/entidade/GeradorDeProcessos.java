package br.com.unifacisa.operacional.processos.entidade;

import java.util.Random;
import java.util.UUID;

public class GeradorDeProcessos {

    public static final int ALCANCE_MIN = 10;
    public static final int ALCANCE_MAX = 50;

    public static Processo gerar() {
        int num = new Random().nextInt(10)+1;

        if (num >= 0 && num <= 3) {
            return new Processo(System.currentTimeMillis(), new Random().nextInt((ALCANCE_MAX - ALCANCE_MIN) + 1) + ALCANCE_MIN);
        }

        return null;
    }


}
