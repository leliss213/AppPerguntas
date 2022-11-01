package com.example.appperguntas;

import android.content.Intent;
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

import java.util.ArrayList;


public class VisualizacaoDetalhadaActivity extends AppCompatActivity {
    Spinner spAlterarConteudo;
    EditText etAlterarEnunciado, etAlterarOpcaoA, etAlterarOpcaoB, etAlterarOpcaoC, etAlterarOpcaoD, etAlterarOpcaoE;
    RadioGroup rgAlterarGrupo;
    RadioButton rbAlterarOpcaoA, rbAlterarOpcaoB, rbAlterarOpcaoC, rbAlterarOpcaoD, rbAlterarOpcaoE;
    Button bAlterarAlterar,bAlterarLimpar, bAlterarDeletar;
    ControladorBanco crud;
    Pergunta perg;
    Conteudo cont;
    ArrayList<Pergunta> lstPerguntas;
    ArrayList<Conteudo> lstConteudos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada);
        //Spinner
        spAlterarConteudo = (Spinner) findViewById(R.id.spAlterarConteudo);
        //EditText
        etAlterarEnunciado = (EditText) findViewById(R.id.etAlterarEnunciado);
        etAlterarOpcaoA = (EditText) findViewById(R.id.etAlterarOpcaoA);
        etAlterarOpcaoB = (EditText) findViewById(R.id.etAlterarOpcaoB);
        etAlterarOpcaoC = (EditText) findViewById(R.id.etAlterarOpcaoC);
        etAlterarOpcaoD = (EditText) findViewById(R.id.etAlterarOpcaoD);
        etAlterarOpcaoE = (EditText) findViewById(R.id.etAlterarOpcaoE);
        //RadioGroup
        rgAlterarGrupo = (RadioGroup) findViewById(R.id.rgAlterarGrupo);
        //RadioButton
        rbAlterarOpcaoA = (RadioButton) findViewById(R.id.rbAlterarOpcaoA);
        rbAlterarOpcaoB = (RadioButton) findViewById(R.id.rbAlterarOpcaoB);
        rbAlterarOpcaoC = (RadioButton) findViewById(R.id.rbAlterarOpcaoC);
        rbAlterarOpcaoD = (RadioButton) findViewById(R.id.rbAlterarOpcaoD);
        rbAlterarOpcaoE = (RadioButton) findViewById(R.id.rbAlterarOpcaoE);
        //Button
        bAlterarAlterar = (Button) findViewById(R.id.bAlterarAlterar);
        bAlterarLimpar = (Button) findViewById(R.id.bAlterarLimpar);
        bAlterarDeletar = (Button) findViewById(R.id.bAlterarDeletar);
        //ControladorBanco
        crud = new ControladorBanco(VisualizacaoDetalhadaActivity.this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstConteudos = crud.CarregaConteudo();

        String[] conteudo = new String[lstConteudos.size() + 1];
        conteudo[0] = "Selecionar";
        for (int x = 0; x < lstConteudos.size(); x++) {
            conteudo[x + 1] = lstConteudos.get(x).getNomeConteudo();
        }
        spAlterarConteudo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, conteudo));

        Intent it = getIntent();
        if (it != null && it.hasExtra("pergunta") && it.hasExtra("conteudo")){
          perg = (Pergunta) it.getSerializableExtra("pergunta");
          cont = (Conteudo) it.getSerializableExtra("conteudo");

            spAlterarConteudo.setSelection(cont.getIdConteudo());
            etAlterarEnunciado.setText(perg.getEnunciado());
            etAlterarOpcaoA.setText(perg.getOpcaoA());
            etAlterarOpcaoB.setText(perg.getOpcaoB());
            etAlterarOpcaoC.setText(perg.getOpcaoC());
            etAlterarOpcaoD.setText(perg.getOpcaoD());
            etAlterarOpcaoE.setText(perg.getOpcaoE());
            String opcaoCorreta = Character.toString(perg.getOpcaoCorreta());

            if (opcaoCorreta.equals("A")){
                rbAlterarOpcaoA.toggle();
            } else if (opcaoCorreta.equals("B")){
                rbAlterarOpcaoB.toggle();
            } else if (opcaoCorreta.equals("C")){
                rbAlterarOpcaoC.toggle();
            } else if (opcaoCorreta.equals("D")){
                rbAlterarOpcaoD.toggle();
            } else{
                rbAlterarOpcaoE.toggle();
            }
        }

        bAlterarAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if (!etAlterarEnunciado.equals("")) {
                            if (!etAlterarOpcaoA.equals("")) {
                                if (!etAlterarOpcaoB.equals("")) {
                                    if (!etAlterarOpcaoC.equals("")) {
                                        if (!etAlterarOpcaoD.equals("")) {
                                            if (!etAlterarOpcaoE.equals("")) {
                                                if (rgAlterarGrupo.getCheckedRadioButtonId() != -1) {
                                                    if (spAlterarConteudo.getSelectedItemPosition() > 0) {

                                                        String enunciado = etAlterarEnunciado.getText().toString();
                                                        String opcaoA = etAlterarOpcaoA.getText().toString();
                                                        String opcaoB = etAlterarOpcaoB.getText().toString();
                                                        String opcaoC = etAlterarOpcaoC.getText().toString();
                                                        String opcaoD = etAlterarOpcaoD.getText().toString();
                                                        String opcaoE = etAlterarOpcaoE.getText().toString();
                                                        char opcaoCorreta;
                                                        if (rbAlterarOpcaoA.isChecked()) {
                                                            opcaoCorreta = 'A';
                                                        } else if (rbAlterarOpcaoB.isChecked()) {
                                                            opcaoCorreta = 'B';
                                                        } else if (rbAlterarOpcaoC.isChecked()) {
                                                            opcaoCorreta = 'C';
                                                        } else if (rbAlterarOpcaoD.isChecked()) {
                                                            opcaoCorreta = 'D';
                                                        } else {
                                                            opcaoCorreta = 'E';
                                                        }
                                                        //Criando o objeto da classe pergunta para armazenar na lista
                                                            Pergunta pergunta = new Pergunta(perg.getIdPergunta(),enunciado,opcaoA,opcaoB,opcaoC,opcaoD,opcaoE, opcaoCorreta, crud.CarregaConteudo().get(spAlterarConteudo.getSelectedItemPosition()-1));
                                                            String resultado = crud.AlteraPergunta(pergunta);
                                                            Toast.makeText(VisualizacaoDetalhadaActivity.this, resultado, Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(VisualizacaoDetalhadaActivity.this, VizualizacaoRecyclerActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                    } else {
                                                        Toast.makeText(VisualizacaoDetalhadaActivity.this, "Selecione o conteúdo da questão", Toast.LENGTH_SHORT).show();
                                                        spAlterarConteudo.requestFocus();
                                                    }
                                                } else {
                                                    rgAlterarGrupo.requestFocus();
                                                    rbAlterarOpcaoE.setError("Selecione a alternativa correta");
                                                }
                                            } else {
                                                etAlterarOpcaoE.requestFocus();
                                                etAlterarOpcaoE.setError("Preencha o campo");
                                            }
                                        } else {
                                            Toast.makeText(VisualizacaoDetalhadaActivity.this, "Preencha a alternativa D", Toast.LENGTH_SHORT).show();
                                            etAlterarOpcaoD.requestFocus();
                                            etAlterarOpcaoD.setError("Preencha o campo");
                                        }

                                    } else {
                                        Toast.makeText(VisualizacaoDetalhadaActivity.this, "Preencha a alternativa C", Toast.LENGTH_SHORT).show();
                                        etAlterarOpcaoC.requestFocus();
                                        etAlterarOpcaoC.setError("Preencha o campo");
                                    }
                                } else {
                                    Toast.makeText(VisualizacaoDetalhadaActivity.this, "Preencha a alternativa B", Toast.LENGTH_SHORT).show();
                                    etAlterarOpcaoB.requestFocus();
                                    etAlterarOpcaoB.setError("Preencha o campo");
                                }
                            } else {
                                Toast.makeText(VisualizacaoDetalhadaActivity.this, "Preencha a alternativa A", Toast.LENGTH_SHORT).show();
                                etAlterarOpcaoA.requestFocus();
                                etAlterarOpcaoA.setError("Preencha o campo");
                            }

                        } else {
                            Toast.makeText(VisualizacaoDetalhadaActivity.this, "Insira o enunciado da questão", Toast.LENGTH_SHORT).show();
                            etAlterarEnunciado.requestFocus();
                            etAlterarEnunciado.setError("Preencha o campo");
                        }
            }
        });
        bAlterarLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        bAlterarDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String resultado = crud.deletaPergunta(perg);
                    Toast.makeText(VisualizacaoDetalhadaActivity.this, resultado, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VisualizacaoDetalhadaActivity.this, VizualizacaoRecyclerActivity.class);
                    startActivity(intent);
                    finish();
            }
        });
    }

    public void limpaCampos(){
        spAlterarConteudo.setSelection(0);
        etAlterarEnunciado.setText("");
        etAlterarOpcaoA.setText("");
        etAlterarOpcaoB.setText("");
        etAlterarOpcaoC.setText("");
        etAlterarOpcaoD.setText("");
        etAlterarOpcaoE.setText("");
        rgAlterarGrupo.clearCheck();
    }

}
