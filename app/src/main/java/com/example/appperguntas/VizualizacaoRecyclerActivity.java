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

import java.util.ArrayList;

public class VizualizacaoRecyclerActivity extends AppCompatActivity {


    RecyclerView rvVizualizaPerguntas;
    PerguntaAdapter perguntaAdapter;
    ControladorBanco crud = new ControladorBanco(VizualizacaoRecyclerActivity.this);
    ArrayList<Pergunta> lstPerguntas;
    ArrayList<Conteudo> lstConteudos;
    InformacoesApp informacoesApp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizacao_recycler);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvVizualizaPerguntas = findViewById(R.id.rvVizualizaPerguntas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        crud = new ControladorBanco(VizualizacaoRecyclerActivity.this);

        informacoesApp = (InformacoesApp) getApplicationContext();

        lstPerguntas = crud.CarregaPergunta();
        lstConteudos = crud.CarregaConteudo();

        if (crud.CarregaPergunta() != null) {
            perguntaAdapter = new PerguntaAdapter(lstConteudos, lstPerguntas, trataCliqueItem);
            rvVizualizaPerguntas.setLayoutManager(new LinearLayoutManager(VizualizacaoRecyclerActivity.this));
            rvVizualizaPerguntas.setItemAnimator(new DefaultItemAnimator());
            rvVizualizaPerguntas.setAdapter(perguntaAdapter);
        }

    }

    PerguntaAdapter.PerguntaOnClickListener trataCliqueItem = new PerguntaAdapter.PerguntaOnClickListener() {
        @Override
        public void onClickPergunta(View view, int position) {
            Pergunta perg = lstPerguntas.get(position);
            Conteudo cont = lstPerguntas.get(position).getConteudo();
            Intent it = new Intent(VizualizacaoRecyclerActivity.this, VisualizacaoDetalhadaActivity.class);
            it.putExtra("pergunta", perg);
            it.putExtra("conteudo", cont);
            startActivity(it);
            finish();
        }
    };


}
