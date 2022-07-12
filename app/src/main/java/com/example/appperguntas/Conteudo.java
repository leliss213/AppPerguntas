package com.example.appperguntas;

import java.io.Serializable;

public class Conteudo implements Serializable {
    private int idConteudo;
    private int tipoConteudo;
    private String nomeConteudo;

    public Conteudo(int tipoConteudo, String nomeConteudo) {
        this.tipoConteudo = tipoConteudo;
        this.nomeConteudo = nomeConteudo;
    }

    public int getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(int idConteudo) {
        this.idConteudo = idConteudo;
    }

    public int getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(int tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public String getNomeConteudo() {
        return nomeConteudo;
    }

    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }
}
