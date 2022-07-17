package com.example.appperguntas;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VisualizacaoDetalhadaActivity extends AppCompatActivity {
    TextView tvVisualizaPergunta, tvVisualizaConteudo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada);
        tvVisualizaConteudo = findViewById(R.id.tvVisualizaConteudo);
        tvVisualizaPergunta = findViewById(R.id.tvVisualizaPergunta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent it = getIntent();
        if (it != null && it.hasExtra("pergunta")){
          Pergunta perg = (Pergunta) it.getSerializableExtra("pergunta");

            tvVisualizaPergunta.setText(perg.getEnunciado());
            //tvVisualizaConteudo.setText(perg.getConteudo());
        }
    }

}
