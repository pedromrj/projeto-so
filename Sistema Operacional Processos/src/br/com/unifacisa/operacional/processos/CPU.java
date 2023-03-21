package br.com.unifacisa.operacional.processos;

import br.com.unifacisa.operacional.processos.entidade.Processo;
import br.com.unifacisa.operacional.processos.escalonadores.EscalonadorFCFS;
import br.com.unifacisa.operacional.processos.escalonadores.EscalonadorRobinRound;
import br.com.unifacisa.operacional.processos.escalonadores.Escalonador;
import br.com.unifacisa.operacional.processos.escalonadores.EscalonadorSJF;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CPU {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Integer CLOCK = 5;

    public static void main(String[] args) {

        long inicio = System.currentTimeMillis();
        int quantum = 0;
        System.out.println("Qual escalonador deseja: 1 - FCFS, 2 - SJF, 3 - RobinRound por padrão o escalonador é o FCFS");
        int tipoEscalonador = SCANNER.nextInt();
        Escalonador escalonador = getEscalonador(tipoEscalonador);
        Processo processo = escalonador.getProcesso();

        int contador = 0;

        while (true) {
            if (escalonador instanceof EscalonadorRobinRound) {
                if (quantum == escalonador.getQuantun() || ( processo != null && processo.estaFinalizado())) {
                    processo = escalonador.getProcesso();
                    quantum = 0;
                }
                quantum++;
            } else {
                if (contador == CLOCK) {
                    processo = escalonador.getProcesso();
                    contador = 0;
                }
                contador++;
            }

            if (processo != null && !processo.estaFinalizado()) {
                processo.finalizarInstrucao();
            }
// Usado para testa o tempo de cada escalonador!
//            if (escalonador.getProcessos().isEmpty()) {
//                break;
//            }
        }

//       System.out.println("Tempo em ms: " + (System.currentTimeMillis() - inicio));


    }

    private static Escalonador getEscalonador(int tipo) {
        Escalonador escalonador = null;
        switch (tipo) {
            case 2:
                escalonador = new EscalonadorSJF();
                break;
            case 3:
                System.out.println("Quantidade de Quantum deseja: ");
                int quantum = SCANNER.nextInt();
                escalonador = new EscalonadorRobinRound(quantum);
                break;
            default:
                escalonador = new EscalonadorFCFS();
        }
        return escalonador;
    }

    public static String dataFormatada(LocalDateTime data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formato);
    }

}

