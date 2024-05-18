package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

    public class LernkartenAdapter extends RecyclerView.Adapter<LernkartenAdapter.LernkartenViewHolder> {

        private List<Lernkarte> lernkartenList;

        public LernkartenAdapter(List<Lernkarte> lernkartenList) {
            this.lernkartenList = lernkartenList;
        }

        @NonNull
        @Override
        public LernkartenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lernkarte, parent, false);
            return new LernkartenViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LernkartenViewHolder holder, int position) {
            Lernkarte lernkarte = lernkartenList.get(position);
            holder.textViewFrage.setText(lernkarte.getFrage());
            holder.textViewAntwort.setText(lernkarte.getAntwort());
        }

        @Override
        public int getItemCount() {
            if (lernkartenList != null) {
                return lernkartenList.size();
            } else {
                return 0; // or another appropriate value, depending on your logic
            }
        }

        public static class LernkartenViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewFrage;
            public TextView textViewAntwort;

            public LernkartenViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewFrage = itemView.findViewById(R.id.text_view_frage);
                textViewAntwort = itemView.findViewById(R.id.text_view_antwort);
            }
        }
    }

