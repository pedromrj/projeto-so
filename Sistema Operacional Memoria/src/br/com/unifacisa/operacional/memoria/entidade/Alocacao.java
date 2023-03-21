package br.com.unifacisa.operacional.memoria.entidade;

public class Alocacao {

    private String id;

    private Integer posicao;

    private Integer tamanho;

    public Alocacao(String id, Integer tamanho) {
        this.id = id;
        this.tamanho = tamanho;
    }

    public String getId() {
        return id;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }


    @Override
    public String toString() {
        return "Alocacao{" +
                "id='" + id + '\'' +
                ", posicao=" + posicao +
                ", tamanho=" + tamanho +
                '}';
    }
}
