package com.packetsoftware.sime.controller;

public class EscolaUsuario {

    private String idescola_usuario;
    private Escola escola;
    private Usuario usuario;
    private int inadmin;



    public String getIdescola_usuario() {
        return idescola_usuario;
    }

    public void setIdescola_usuario(String idescola_usuario) {
        this.idescola_usuario = idescola_usuario;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getInadmin() {
        return inadmin;
    }

    public void setInadmin(int inadmin) {
        this.inadmin = inadmin;
    }
}
