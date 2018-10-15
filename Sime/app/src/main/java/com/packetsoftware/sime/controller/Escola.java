package com.packetsoftware.sime.controller;

public class Escola {
    private String idescola;
    private String nomeescola;
    private String cnpj;
    private String status;
    private Contato contato;
    private AnoLetivo anoLetivo;
    private Endereco endereco;

    public String getIdescola() {
        return idescola;
    }

    public void setIdescola(String idescola) {
        this.idescola = idescola;
    }

    public String getNomeescola() {
        return nomeescola;
    }

    public void setNomeescola(String nomeescola) {
        this.nomeescola = nomeescola;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public AnoLetivo getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(AnoLetivo anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
