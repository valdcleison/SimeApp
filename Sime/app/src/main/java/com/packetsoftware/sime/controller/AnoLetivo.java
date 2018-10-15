package com.packetsoftware.sime.controller;

import java.util.Date;

public class AnoLetivo {
    private String idanoletivo;
    private int anoletivo;
    private Date dtinicio;

    public String getIdanoletivo() {
        return idanoletivo;
    }

    public void setIdanoletivo(String idanoletivo) {
        this.idanoletivo = idanoletivo;
    }

    public int getAnoletivo() {
        return anoletivo;
    }

    public void setAnoletivo(int anoletivo) {
        this.anoletivo = anoletivo;
    }

    public Date getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(Date dtinicio) {
        this.dtinicio = dtinicio;
    }
}
