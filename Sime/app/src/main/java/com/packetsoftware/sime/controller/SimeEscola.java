package com.packetsoftware.sime.controller;

import java.io.Serializable;

public class SimeEscola implements Serializable {

    private String status;
    private String menssage;
    private EscolaUsuario escola_usuario;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

    public EscolaUsuario getEscola_usuario() {
        return escola_usuario;
    }

    public void setEscola_usuario(EscolaUsuario escola_usuario) {
        this.escola_usuario = escola_usuario;
    }
}
