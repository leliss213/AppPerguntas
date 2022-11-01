package com.example.appperguntas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoDeDados extends SQLiteOpenHelper {

    private final Context context;

    private static final String nomeBanco = "BancoApp.db";
    private static final int versaoBanco = 2;
    private static final String tabelaConteudo = "Tabela_Conteudo";
    private static final String idConteudo = "_idConteudo";
    private static final String nomeConteudo = "nomeconteudo";
    private static final String tipoConteudo = "tipo";

    private static final String tabelaPerguntas = "Tabela_Perguntas";
    private static final String idPergunta = "_idPergunta";
    private static final String enunciado = "enunciado";
    private static final String opcaoA = "opcao_A";
    private static final String opcaoB = "opcao_B";
    private static final String opcaoC = "opcao_C";
    private static final String opcaoD = "opcao_D";
    private static final String opcaoE = "opcao_E";
    private static final String opcaoCorreta = "correta";
    private static final String fkconteudo = "conteudo";

    public BancoDeDados(@Nullable Context context) {
        super(context, nomeBanco, null, versaoBanco);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tabelaConteudo +
                " (" + idConteudo + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                nomeConteudo + " TEXT, " +
                tipoConteudo + " TEXT" + " );");

        db.execSQL("CREATE TABLE " + tabelaPerguntas +
                " (" + idPergunta + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                enunciado + " TEXT, " +
                opcaoA + " TEXT, " +
                opcaoB + " TEXT, " +
                opcaoC + " TEXT, " +
                opcaoD + " TEXT, " +
                opcaoE + " TEXT, " +
                opcaoCorreta + " TEXT, " +
                fkconteudo + " INTEGER, " + " FOREIGN KEY (" + fkconteudo + ")  references " + tabelaConteudo + " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabelaConteudo);
        db.execSQL("DROP TABLE IF EXISTS " + tabelaPerguntas);
        onCreate(db);
    }

    public Context getContext() {
        return context;
    }

    public static String getNomeBanco() {
        return nomeBanco;
    }

    public static int getVersaoBanco() {
        return versaoBanco;
    }

    public static String getTabelaConteudo() {
        return tabelaConteudo;
    }

    public static String getIdConteudo() {
        return idConteudo;
    }

    public static String getNomeConteudo() {
        return nomeConteudo;
    }

    public static String getTipoConteudo() {
        return tipoConteudo;
    }

    public static String getTabelaPerguntas() {
        return tabelaPerguntas;
    }

    public static String getIdPergunta() {
        return idPergunta;
    }

    public static String getEnunciado() {
        return enunciado;
    }

    public static String getOpcaoA() {
        return opcaoA;
    }

    public static String getOpcaoB() {
        return opcaoB;
    }

    public static String getOpcaoC() {
        return opcaoC;
    }

    public static String getOpcaoD() {
        return opcaoD;
    }

    public static String getOpcaoE() {
        return opcaoE;
    }

    public static String getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public static String getFKConteudo() {
        return fkconteudo;
    }
}
