package com.example.appperguntas;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroDeConteudosActivity extends AppCompatActivity {

    Button bCadastroDeConteudosSalvar, bCadastroDeConteudosLimpar;
    EditText etCadastroDeConteudosNome;
    Spinner spCadastroConteudosTipo;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_conteudos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bCadastroDeConteudosSalvar = (Button) findViewById(R.id.bCadastroConteudoSalvar);
        bCadastroDeConteudosLimpar = (Button) findViewById(R.id.bCadastroConteudoLimpar);
        etCadastroDeConteudosNome = (EditText) findViewById(R.id.etCadastroDeConteudosNome);
        spCadastroConteudosTipo = (Spinner) findViewById(R.id.spCadastroConteudosTipo);

        informacoesApp = (InformacoesApp) getApplicationContext();
        String[] tipoConteudo = new String[4];
        tipoConteudo[0] = "Selecionar";
        tipoConteudo[1] = "Orgânica";
        tipoConteudo[2] = "Inorgânica";

        spCadastroConteudosTipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipoConteudo));

        bCadastroDeConteudosSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCadastroDeConteudosNome.equals("")) {
                    if (spCadastroConteudosTipo.getSelectedItemPosition() > 0) {
                        Conteudo cont = new Conteudo(spCadastroConteudosTipo.getSelectedItemPosition(), etCadastroDeConteudosNome.getText().toString());
                        informacoesApp.getLstConteudos().add(cont);
                        Toast.makeText(CadastroDeConteudosActivity.this, "Conteúdo cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroDeConteudosActivity.this, "Selecione o tipo do conteúdo", Toast.LENGTH_SHORT).show();
                        spCadastroConteudosTipo.requestFocus();
                    }
                } else {
                    Toast.makeText(CadastroDeConteudosActivity.this, "Insira o nome do conteúdo", Toast.LENGTH_SHORT).show();
                    etCadastroDeConteudosNome.requestFocus();
                }
            }
        });
        bCadastroDeConteudosLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void limpaCampos() {
        etCadastroDeConteudosNome.setText("");
        spCadastroConteudosTipo.setSelection(0);
    }
}
