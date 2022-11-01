package com.example.appperguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class ControladorBanco {
    private SQLiteDatabase db;
    private BancoDeDados banco;

    public ControladorBanco(Context context) {
        banco = new BancoDeDados(context);
    }

    public String CadastraConteudo(Conteudo conteudo) {
        ContentValues valores = new ContentValues();
        long resultado;

        int tipoConteudo = conteudo.getTipoConteudo();
        String nomeConteudo = conteudo.getNomeConteudo();

        db = banco.getWritableDatabase();

        valores.put(BancoDeDados.getNomeConteudo(), nomeConteudo);
        valores.put(BancoDeDados.getTipoConteudo(), tipoConteudo);

        resultado = db.insert(BancoDeDados.getTabelaConteudo(), null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }

    public String CadastraPergunta(Pergunta pergunta) {
        ContentValues valores = new ContentValues();
        long resultado;

        String enunciado = pergunta.getEnunciado();
        String opcaoA = pergunta.getOpcaoA();
        String opcaoB = pergunta.getOpcaoB();
        String opcaoC = pergunta.getOpcaoC();
        String opcaoD = pergunta.getOpcaoD();
        String opcaoE = pergunta.getOpcaoE();
        char opcaoCorreta = pergunta.getOpcaoCorreta();
        int conteudo = pergunta.getConteudo().getIdConteudo();

        db = banco.getWritableDatabase();

        valores.put(BancoDeDados.getEnunciado(), enunciado);
        valores.put(BancoDeDados.getOpcaoA(), opcaoA);
        valores.put(BancoDeDados.getOpcaoB(), opcaoB);
        valores.put(BancoDeDados.getOpcaoC(), opcaoC);
        valores.put(BancoDeDados.getOpcaoD(), opcaoD);
        valores.put(BancoDeDados.getOpcaoE(), opcaoE);
        valores.put(BancoDeDados.getOpcaoCorreta(), String.valueOf(opcaoCorreta));
        valores.put(BancoDeDados.getFKConteudo(), conteudo);
        resultado = db.insert(BancoDeDados.getTabelaPerguntas(), null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }

    public ArrayList<Conteudo> CarregaConteudo() {
        Cursor cursor;
        ArrayList<Conteudo> lstConteudos = new ArrayList<>();
        //Abrindo o banco de dados
        db = banco.getWritableDatabase();
        //Indicando para o banco a tabela a ser selecionada
        String consulta = BancoDeDados.getTabelaConteudo();
        //Indicando para o banco as colunas a serem selecionadas
        //String[] colunas = {BancoDeDados.getIdConteudo(), BancoDeDados.getNomeConteudo(), BancoDeDados.getTipoConteudo()};
        //cursor = db.rawQuery("Select * from " + consulta, null);
        cursor = db.query(consulta, null, null, null, null, null, null, null);
        //cursor = db.query(consulta, colunas, null,null,null,null,null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                //Pegando os elementos de determinado elemento do banco
                int idConteudo = cursor.getInt(cursor.getColumnIndex(BancoDeDados.getIdConteudo()));
                int tipoConteudo = cursor.getInt(cursor.getColumnIndex(BancoDeDados.getTipoConteudo()));
                String nomeConteudo = cursor.getString(cursor.getColumnIndex(BancoDeDados.getNomeConteudo()));

                //Criando objeto da classe conteúdo
                Conteudo conteudo = new Conteudo(idConteudo, tipoConteudo, nomeConteudo);
                //Adicionando pergunta na lista
                lstConteudos.add(conteudo);

            }
        }
        //Fechando o cursor
        cursor.close();
        //Retornando a lista
        return lstConteudos;
    }

    public ArrayList<Pergunta> CarregaPergunta() {
        Cursor cursor;
        ArrayList<Pergunta> lstPerguntas = new ArrayList<>();
        //Abrindo o banco de dados
        db = banco.getWritableDatabase();
        //Indicando em quais tabelas o banco deverá pesquisar
        String consulta = BancoDeDados.getTabelaPerguntas() + " INNER JOIN " + BancoDeDados.getTabelaConteudo()
                + " ON " + BancoDeDados.getTabelaPerguntas() + "." + BancoDeDados.getFKConteudo()
                + " = " + BancoDeDados.getTabelaConteudo() + "." + BancoDeDados.getIdConteudo();
        //Indicando quais colunas o banco deverá mostrar
        cursor = db.query(consulta, null, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                //Obtendo as informações da pergunta
                int idPergunta = cursor.getInt(cursor.getColumnIndex(BancoDeDados.getIdPergunta()));
                String enunciado = cursor.getString(cursor.getColumnIndex(BancoDeDados.getEnunciado()));
                String opcaoA = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoA()));
                String opcaoB = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoB()));
                String opcaoC = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoC()));
                String opcaoD = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoD()));
                String opcaoE = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoE()));
                char opcaoCorreta = cursor.getString(cursor.getColumnIndex(BancoDeDados.getOpcaoCorreta())).charAt(0);

                //Criando objeto da classe Conteudo
                int idConteudo = cursor.getInt(cursor.getColumnIndex(BancoDeDados.getIdConteudo()));
                String nomeConteudo = cursor.getString(cursor.getColumnIndex(BancoDeDados.getNomeConteudo()));
                int tipoConteudo = cursor.getInt(cursor.getColumnIndex(BancoDeDados.getTipoConteudo()));

                Conteudo conteudo = new Conteudo(idConteudo, tipoConteudo, nomeConteudo);

                //Criando objeto da classe pergunta
                Pergunta pergunta = new Pergunta(idPergunta, enunciado, opcaoA, opcaoB, opcaoC, opcaoD, opcaoE, opcaoCorreta, conteudo);

                //Adicionando pergunta na lista
                lstPerguntas.add(pergunta);
            }
        }
        //Fechando o cursor
        cursor.close();
        //Retornando a lista
        return lstPerguntas;
    }

    public String AlteraPergunta(Pergunta pergunta) {
        ContentValues valores;
        String where;
        long resultado;
        int id = pergunta.getIdPergunta();

        where = BancoDeDados.getIdPergunta() + "=" + id;

        db = banco.getReadableDatabase();

        valores = new ContentValues();

        String enunciado = pergunta.getEnunciado();
        String opcaoA = pergunta.getOpcaoA();
        String opcaoB = pergunta.getOpcaoB();
        String opcaoC = pergunta.getOpcaoC();
        String opcaoD = pergunta.getOpcaoD();
        String opcaoE = pergunta.getOpcaoE();
        char opcaoCorreta = pergunta.getOpcaoCorreta();
        long conteudo = pergunta.getConteudo().getIdConteudo();

        valores.put(BancoDeDados.getEnunciado(), enunciado);
        valores.put(BancoDeDados.getOpcaoA(), opcaoA);
        valores.put(BancoDeDados.getOpcaoB(), opcaoB);
        valores.put(BancoDeDados.getOpcaoC(), opcaoC);
        valores.put(BancoDeDados.getOpcaoD(), opcaoD);
        valores.put(BancoDeDados.getOpcaoE(), opcaoE);
        valores.put(BancoDeDados.getOpcaoCorreta(), String.valueOf(opcaoCorreta));
        valores.put(BancoDeDados.getFKConteudo(), conteudo);
        resultado = db.update(BancoDeDados.getTabelaPerguntas(), valores, where, null);
        db.close();

        if (resultado == -1) {
            return "Erro ";
        } else {
            return "Sucesso ";
        }
    }

    public String deletaPergunta(Pergunta pergunta) {
        long resultado;
        int id = pergunta.getIdPergunta();
        String where = BancoDeDados.getIdPergunta() + "=" + id;
        db = banco.getReadableDatabase();

        resultado = db.delete(BancoDeDados.getTabelaPerguntas(), where, null);
        db.close();
        if (resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }

    public void DeletaBanco() {
        db = banco.getReadableDatabase();
        db.delete(BancoDeDados.getTabelaPerguntas(), null, null);
        db.delete(BancoDeDados.getTabelaConteudo(), null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + BancoDeDados.getTabelaPerguntas() + "'");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + BancoDeDados.getTabelaConteudo() + "'");
        db.close();
    }
}
