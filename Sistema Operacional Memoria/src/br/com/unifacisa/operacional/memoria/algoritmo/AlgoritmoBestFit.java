package br.com.unifacisa.operacional.memoria.algoritmo;

import br.com.unifacisa.operacional.memoria.entidade.Alocacao;
import br.com.unifacisa.operacional.memoria.abstracts.Algoritmo;
import br.com.unifacisa.operacional.memoria.entidade.Memoria;

import java.util.*;

public class AlgoritmoBestFit extends Algoritmo {

    protected int contadorSucessos = 0;

    protected int contadorDescartes = 0;

    @Override
    public void inserir(LinkedList<Alocacao> memoria, Alocacao alocacao) {
        Alocacao aux = obterEspacoIgual(memoria, alocacao); // Obtendo os espaço que são iguais ao tamanho que vai ser alocado.

        boolean adicionou = false;

        if (aux == null) { // Se existir realizar a inserção normalmente com o espaço que encontrar.
            Iterator<Alocacao> iterator = new LinkedList<>(memoria).stream().iterator();

            while(iterator.hasNext()) {
                Alocacao x = iterator.next();
                if (x.getId().equalsIgnoreCase(Memoria.ALOCACAO_LIVRE) && alocacao.getTamanho() < x.getTamanho() && super.inserir(memoria, alocacao, x)) {
                    contadorSucessos++;
                    adicionou = true;
                    break;
                }
            }

        } else {
            inserirEspaçoIgual(memoria, aux);// Inserir em espaço igual ao tamanho que vai ser alocado.
            adicionou = true;
        }

        if (!adicionou) {
            contadorDescartes++;
        }
    }

    public void inserirEspaçoIgual(LinkedList<Alocacao> memoria, Alocacao aux) {
        boolean adicionou;
        Alocacao aux1 = memoria.get(memoria.indexOf(aux));
        aux1.setId(Memoria.ALOCACAO_PROCESSO);
        contadorSucessos++;
    }

    private Alocacao obterEspacoIgual(List<Alocacao> memoria, Alocacao inserir) {
        return memoria.stream().filter(x -> {
            return x.getId().equalsIgnoreCase(Memoria.ALOCACAO_LIVRE) && Objects.equals(x.getTamanho(), inserir.getTamanho());
        }).findFirst().orElse(null);
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
