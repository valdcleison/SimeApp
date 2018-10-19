package com.packetsoftware.sime.controller;

public class Matricula {
    private String idmatricula;
    private Aluno aluno;
    private String numeromatricula;

    public String getIdmatricula() {
        return idmatricula;
    }

    public void setIdmatricula(String idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNumeromatricula() {
        return numeromatricula;
    }

    public void setNumeromatricula(String numeromatricula) {
        this.numeromatricula = numeromatricula;
    }
}
