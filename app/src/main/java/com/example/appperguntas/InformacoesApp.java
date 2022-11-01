package com.example.appperguntas;

import android.app.Application;

import java.util.ArrayList;

public class InformacoesApp extends Application {
    private ArrayList<Conteudo> lstConteudos;
    private ArrayList<Pergunta> lstPerguntas;

    public ArrayList<Conteudo> getLstConteudos() {
        return lstConteudos;
    }

    public void setLstConteudos(ArrayList<Conteudo> lstConteudos) {
        this.lstConteudos = lstConteudos;
    }

    public ArrayList<Pergunta> getLstPerguntas() {
        return lstPerguntas;
    }

    public void setLstPerguntas(ArrayList<Pergunta> lstPerguntas) {
        this.lstPerguntas = lstPerguntas;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lstConteudos = new ArrayList<>();
        lstPerguntas = new ArrayList<>();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
