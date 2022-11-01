package com.example.appperguntas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Crianda as variáveis para os elementos da tela
    Button bMainCadastarConteudo, bMainCadastrarPergunta, bMainVisualizarPergunta, bMainDeletaBanco;
    ControladorBanco crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Linkando os elementos da tela as variáveis
        bMainCadastarConteudo = (Button) findViewById(R.id.bMainCadastroConteudo);
        bMainCadastrarPergunta = (Button) findViewById(R.id.bMainCadastroPergunta);
        bMainVisualizarPergunta = (Button) findViewById(R.id.bMainVisualizacaoPergunta);
        bMainDeletaBanco = (Button) findViewById(R.id.bMainDeletaBanco);
        crud = new ControladorBanco(MainActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bMainCadastarConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroDeConteudosActivity.class);
                startActivity(it);
            }
        });

        bMainCadastrarPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroDePerguntasActivity.class);
                startActivity(it);

            }
        });

        bMainVisualizarPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, VizualizacaoRecyclerActivity.class);
                startActivity(it);

            }
        });
        bMainDeletaBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.DeletaBanco();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
