package com.pedropereira.projetoverao.modelo;

/**
 * Created by Pedro.Pereira on 28/12/2017.
 */

public class Pesagem {

    private Long chave;
    private String dataHora;
    private String momento;
    private String local;
    private Double peso;

    public Long getChave() {
        return chave;
    }

    public void setChave(Long chave) {
        this.chave = chave;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
