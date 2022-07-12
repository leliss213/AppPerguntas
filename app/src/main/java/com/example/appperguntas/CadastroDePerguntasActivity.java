package com.example.appperguntas;

import android.icu.text.IDNA;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroDePerguntasActivity extends AppCompatActivity {
    EditText etCadastroDePerguntasEnunciado, etCadastroDePerguntasOpcaoA, etCadastroDePerguntasOpcaoB, etCadastroDePerguntasOpcaoC, etCadastroDePerguntasOpcaoD, etCadastroDePerguntasOpcaoE;
    Button bCadastroPerguntasSalvar, bCadastroPerguntasLimpar;
    RadioButton rbCadastroDePerguntasOpcaoA, rbCadastroDePerguntasOpcaoB, rbCadastroDePerguntasOpcaoC, rbCadastroDePerguntasOpcaoD, rbCadastroDePerguntasOpcaoE;
    RadioGroup rgCadastroDePerguntasGrupo;
    Spinner spCadastroPerguntasConteudo;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_perguntas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spCadastroPerguntasConteudo = (Spinner) findViewById(R.id.spCadastroPerguntasConteudo);
        etCadastroDePerguntasEnunciado = (EditText) findViewById(R.id.etCadastroDePerguntasEnunciado);
        etCadastroDePerguntasOpcaoA = (EditText) findViewById(R.id.etCadastroDePerguntasOpcaoA);
        etCadastroDePerguntasOpcaoB = (EditText) findViewById(R.id.etCadastroDePerguntasOpcaoB);
        etCadastroDePerguntasOpcaoC = (EditText) findViewById(R.id.etCadastroDePerguntasOpcaoC);
        etCadastroDePerguntasOpcaoD = (EditText) findViewById(R.id.etCadastroDePerguntasOpcaoD);
        etCadastroDePerguntasOpcaoE = (EditText) findViewById(R.id.etCadastroDePerguntasOpcaoE);
        bCadastroPerguntasSalvar = (Button) findViewById(R.id.bCadastroPerguntasSalvar);
        bCadastroPerguntasLimpar = (Button) findViewById(R.id.bCadastroPerguntasLimpar);
        rgCadastroDePerguntasGrupo = (RadioGroup) findViewById(R.id.rgCadastroDePerguntasGrupo);
        rbCadastroDePerguntasOpcaoA = (RadioButton) findViewById(R.id.rbCadastroDePerguntasOpcaoA);
        rbCadastroDePerguntasOpcaoB = (RadioButton) findViewById(R.id.rbCadastroDePerguntasOpcaoB);
        rbCadastroDePerguntasOpcaoC = (RadioButton) findViewById(R.id.rbCadastroDePerguntasOpcaoC);
        rbCadastroDePerguntasOpcaoD = (RadioButton) findViewById(R.id.rbCadastroDePerguntasOpcaoD);
        rbCadastroDePerguntasOpcaoE = (RadioButton) findViewById(R.id.rbCadastroDePerguntasOpcaoE);

        informacoesApp = (InformacoesApp) getApplicationContext();

        String[] conteudo = new String[informacoesApp.getLstConteudos().size() + 1];
        conteudo[0] = "Selecionar";
        for (int x = 0; x < informacoesApp.getLstConteudos().size(); x++) {
            conteudo[x + 1] = informacoesApp.getLstConteudos().get(x).getNomeConteudo();
        }
        spCadastroPerguntasConteudo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, conteudo));

        bCadastroPerguntasSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCadastroDePerguntasEnunciado.equals("")) {
                    if (!etCadastroDePerguntasOpcaoA.equals("")) {
                        if (!etCadastroDePerguntasOpcaoB.equals("")) {
                            if (!etCadastroDePerguntasOpcaoC.equals("")) {
                                if (!etCadastroDePerguntasOpcaoD.equals("")) {
                                    if (!etCadastroDePerguntasOpcaoE.equals("")) {
                                        if (rgCadastroDePerguntasGrupo.getCheckedRadioButtonId() != -1) {
                                            if (spCadastroPerguntasConteudo.getSelectedItemPosition() > 0) {
                                                char opcaoCorreta;
                                                if (rbCadastroDePerguntasOpcaoA.isChecked()) {
                                                    opcaoCorreta = 'A';
                                                } else if (rbCadastroDePerguntasOpcaoB.isChecked()) {
                                                    opcaoCorreta = 'B';
                                                } else if (rbCadastroDePerguntasOpcaoC.isChecked()) {
                                                    opcaoCorreta = 'C';
                                                } else if (rbCadastroDePerguntasOpcaoD.isChecked()) {
                                                    opcaoCorreta = 'D';
                                                } else {
                                                    opcaoCorreta = 'E';
                                                }
                                                //Criando o objeto da classe pergunta para armazenar na lista
                                                Pergunta perg = new Pergunta(etCadastroDePerguntasEnunciado.getText().toString(), etCadastroDePerguntasOpcaoA.getText().toString(), etCadastroDePerguntasOpcaoB.getText().toString(), etCadastroDePerguntasOpcaoC.getText().toString(), etCadastroDePerguntasOpcaoD.getText().toString(), etCadastroDePerguntasOpcaoE.getText().toString(), opcaoCorreta, informacoesApp.getLstConteudos().get(spCadastroPerguntasConteudo.getSelectedItemPosition() - 1));
                                                informacoesApp.getLstPerguntas().add(perg);
                                                Toast.makeText(CadastroDePerguntasActivity.this, "Pergunta Cadastrada com sucesso", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(CadastroDePerguntasActivity.this, "Selecione o conteúdo da questão", Toast.LENGTH_SHORT).show();
                                                spCadastroPerguntasConteudo.requestFocus();
                                            }
                                        } else {
                                            Toast.makeText(CadastroDePerguntasActivity.this, "Selecione a alternativa correta", Toast.LENGTH_SHORT).show();
                                            rgCadastroDePerguntasGrupo.requestFocus();
                                        }
                                    } else {
                                        Toast.makeText(CadastroDePerguntasActivity.this, "Preencha a alternativa E", Toast.LENGTH_SHORT).show();
                                        etCadastroDePerguntasOpcaoE.requestFocus();
                                    }
                                } else {
                                    Toast.makeText(CadastroDePerguntasActivity.this, "Preencha a alternativa D", Toast.LENGTH_SHORT).show();
                                    etCadastroDePerguntasOpcaoD.requestFocus();
                                }

                            } else {
                                Toast.makeText(CadastroDePerguntasActivity.this, "Preencha a alternativa C", Toast.LENGTH_SHORT).show();
                                etCadastroDePerguntasOpcaoC.requestFocus();
                            }
                        } else {
                            Toast.makeText(CadastroDePerguntasActivity.this, "Preencha a alternativa B", Toast.LENGTH_SHORT).show();
                            etCadastroDePerguntasOpcaoB.requestFocus();
                        }
                    } else {
                        Toast.makeText(CadastroDePerguntasActivity.this, "Preencha a alternativa A", Toast.LENGTH_SHORT).show();
                        etCadastroDePerguntasOpcaoA.requestFocus();
                    }

                } else {
                    Toast.makeText(CadastroDePerguntasActivity.this, "Insira o enunciado da questão", Toast.LENGTH_SHORT).show();
                    etCadastroDePerguntasEnunciado.requestFocus();
                }
            }
        });

        bCadastroPerguntasLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });
    }

    public void limpaCampos() {
        etCadastroDePerguntasEnunciado.setText("");
        etCadastroDePerguntasOpcaoA.setText("");
        etCadastroDePerguntasOpcaoB.setText("");
        etCadastroDePerguntasOpcaoC.setText("");
        etCadastroDePerguntasOpcaoD.setText("");
        etCadastroDePerguntasOpcaoE.setText("");
        rgCadastroDePerguntasGrupo.clearCheck();
    }

}
