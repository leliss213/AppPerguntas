package com.example.appperguntas;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class PerguntaAdapter extends RecyclerView.Adapter<PerguntaAdapter.MyViewHolder> {
    private ArrayList<Conteudo>lstConteudos;
    private ArrayList<Pergunta>lstPerguntas;
    private PerguntaOnClickListener perguntaOnClickListener;


    public PerguntaAdapter(ArrayList<Conteudo> lstConteudos, ArrayList<Pergunta> lstPerguntas, PerguntaOnClickListener perguntaOnClickListener) {
        this.lstConteudos = lstConteudos;
        this.lstPerguntas = lstPerguntas;
        this.perguntaOnClickListener = perguntaOnClickListener;
    }

    @Override
    public PerguntaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PerguntaAdapter.MyViewHolder holder, final int position) {
        Pergunta perg = lstPerguntas.get(position);
        holder.tvPergunta.setText(perg.getEnunciado());
        Conteudo cont = lstConteudos.get(position);
        holder.tvConteudoPergunta.setText(cont.getNomeConteudo());
        /* CUIDADO: .setText() precisa sempre de String. Se for outro tipo de dado, deve ser feita a conversão com o String.valueOf() */

        // clique no item do cliente
        if (perguntaOnClickListener!= null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perguntaOnClickListener.onClickPergunta(holder.itemView, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return lstPerguntas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPergunta, tvConteudoPergunta;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPergunta = (TextView) itemView.findViewById(R.id.tvPergunta);
            tvConteudoPergunta = (TextView) itemView.findViewById(R.id.tvConteudoPergunta);


        }
    }
    public interface PerguntaOnClickListener {
        public void onClickPergunta(View view, int position);
    }

}