package br.com.unifacisa.operacional.memoria;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;
import br.com.unifacisa.operacional.memoria.enums.TipoAlocacao;
import br.com.unifacisa.operacional.memoria.gerador.GeradorProcessos;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Integer ocupacao = 0;
        Integer processosDescartados = 0;
        int mediaTamanhoProcessos = 0;
        Integer alocacao = 0;
        Integer descarte = 0;
        int tamanhosProcessos = 0;
        int mediaFinalProcessos = 0;
        int mediaFinalOcupacao = 0;
        int mediaFinalDescarte = 0;

        Scanner teclado = new Scanner(System.in);

        System.out.println("Qual é o tipo de algoritmo que deseja: 1 - First_Fit, 2 - BestFit, 3 - NextFit, 4 - WorstFit por padrão será utilizado o FirstFit.");
        int tipoId = teclado.nextInt();

        TipoAlocacao tipo = TipoAlocacao.getAlgoritmo(tipoId);

        for (int i = 0; i < 100 ; i++) {
            Memoria memoria = new Memoria();
            memoria.setTipoAlocacao(tipo);
            Integer somaTamanhoProcessos = 0;
            for (int j = 0; j < 100; j++) {
                Alocacao aloc1 = GeradorProcessos.gerar();
                Alocacao aloc2 = GeradorProcessos.gerar();
                somaTamanhoProcessos += aloc1.getTamanho();
                somaTamanhoProcessos += aloc2.getTamanho();

                memoria.adicionar(aloc1);
                memoria.adicionar(aloc2);
                memoria.remover(new Random().nextInt(2) + 1);
            }
            mediaTamanhoProcessos = somaTamanhoProcessos/200;
            alocacao = memoria.getAlocacao();
            descarte = memoria.getDescartes();
            tamanhosProcessos += mediaTamanhoProcessos;
            ocupacao += alocacao;
            processosDescartados += descarte;
        }
        mediaFinalProcessos = tamanhosProcessos / 100;
        mediaFinalOcupacao = ocupacao / 100;
        mediaFinalDescarte = processosDescartados / 100;
        System.out.println("Com o " + tipo.name() + " Media Final de Processos: "+mediaFinalProcessos + " Media Final de ocupação: " + mediaFinalOcupacao+ " Media Final de Descarte: " + mediaFinalDescarte);
    }
}
