package com.packetsoftware.sime.controller;

public class Frequencia {
    private String idfrequencia;
    private String qtalunospresentes;
    private String qtalunosausentes;
    private String dtfrequencia;
    private String hrinicio;
    private String hrtermino;
    private Escola escola;

    public String getIdfrequencia() {
        return idfrequencia;
    }

    public void setIdfrequencia(String idfrequencia) {
        this.idfrequencia = idfrequencia;
    }

    public String getQtalunospresentes() {
        return qtalunospresentes;
    }

    public void setQtalunospresentes(String qtalunospresentes) {
        this.qtalunospresentes = qtalunospresentes;
    }

    public String getQtalunosausentes() {
        return qtalunosausentes;
    }

    public void setQtalunosausentes(String qtalunosausentes) {
        this.qtalunosausentes = qtalunosausentes;
    }

    public String getDtfrequencia() {
        return dtfrequencia;
    }

    public void setDtfrequencia(String dtfrequencia) {
        this.dtfrequencia = dtfrequencia;
    }

    public String getHrinicio() {
        return hrinicio;
    }

    public void setHrinicio(String hrinicio) {
        this.hrinicio = hrinicio;
    }

    public String getHrtermino() {
        return hrtermino;
    }

    public void setHrtermino(String hrtermino) {
        this.hrtermino = hrtermino;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
