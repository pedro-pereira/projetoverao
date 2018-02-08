package com.pedropereira.projetoverao.modelo;

/**
 * Created by Pedro.Pereira on 01/02/2018.
 */

public class Exercicio {

    private Long id;

    private Double quantidade;

    private String quantidadeUnidade;

    private String descricao;

    private Double intensidade;

    private String intensidadeUnidade;

    private String observacao;

    private Pesagem pesagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getQuantidadeUnidade() {
        return quantidadeUnidade;
    }

    public void setQuantidadeUnidade(String quantidadeUnidade) {
        this.quantidadeUnidade = quantidadeUnidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Double intensidade) {
        this.intensidade = intensidade;
    }

    public String getIntensidadeUnidade() {
        return intensidadeUnidade;
    }

    public void setIntensidadeUnidade(String intensidadeUnidade) {
        this.intensidadeUnidade = intensidadeUnidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pesagem getPesagem() {
        return pesagem;
    }

    public void setPesagem(Pesagem pesagem) {
        this.pesagem = pesagem;
    }
}
