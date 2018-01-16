package com.pedropereira.projetoverao.modelo;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Pedro.Pereira on 28/12/2017.
 */

public class Pesagem implements Serializable{

    private Long chave;
    private Date dataHora;
    private Double latitude;
    private Double longitude;
    private String filial;
    private String momento;
    private Double peso;

    public Long getChave() {
        return chave;
    }

    public void setChave(Long chave) {
        this.chave = chave;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();

        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        String dataFormatada = format2.format(dataHora).toString();

        texto.append(peso);
        texto.append(" kg");
        texto.append(" - ");
        texto.append(dataFormatada);

        return texto.toString();
    }
}
