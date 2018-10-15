package com.packetsoftware.sime.controller;

public class Frequenciaaluno {
    private String idfrequenciaaluno;
    private String data;
    private String hrentrada;
    private Matricula matricula;
    private Frequencia frequencia;


    public String getIdfrequenciaaluno() {
        return idfrequenciaaluno;
    }

    public void setIdfrequenciaaluno(String idfrequenciaaluno) {
        this.idfrequenciaaluno = idfrequenciaaluno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHrentrada() {
        return hrentrada;
    }

    public void setHrentrada(String hrentrada) {
        this.hrentrada = hrentrada;
    }

        public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }
}
