package com.example.appperguntas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

public class VizualizacaoRecyclerActivity extends AppCompatActivity {


    RecyclerView rvVizualizaPerguntas;
    PerguntaAdapter perguntaAdapter;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizacao_recycler);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvVizualizaPerguntas = findViewById(R.id.rvVizualizaPerguntas);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        informacoesApp = (InformacoesApp) getApplicationContext();

        if (informacoesApp.getLstPerguntas() != null) {
            perguntaAdapter = new PerguntaAdapter(informacoesApp.getLstConteudos(), informacoesApp.getLstPerguntas(), trataCliqueItem);
            rvVizualizaPerguntas.setLayoutManager(new LinearLayoutManager(VizualizacaoRecyclerActivity.this));
            rvVizualizaPerguntas.setItemAnimator(new DefaultItemAnimator());
            rvVizualizaPerguntas.setAdapter(perguntaAdapter);
        }

    }

    PerguntaAdapter.PerguntaOnClickListener trataCliqueItem = new PerguntaAdapter.PerguntaOnClickListener() {
        @Override
        public void onClickPergunta(View view, int position) {
            Pergunta perg = informacoesApp.getLstPerguntas().get(position);
            Conteudo cont = informacoesApp.getLstConteudos().get(position);
            Intent it = new Intent(VizualizacaoRecyclerActivity.this, VisualizacaoDetalhadaActivity.class);
            it.putExtra("pergunta", perg);
            startActivity(it);
            //Toast.makeText(informacoesApp, "Pergunta: " + perg.getEnunciado() + "Conteudo: " + cont.getNomeConteudo(), Toast.LENGTH_SHORT).show();
        }
    };





}
