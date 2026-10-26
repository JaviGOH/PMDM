package com.example.listajaviergallegos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class AdaptadorPlatos extends RecyclerView.Adapter<AdaptadorPlatos.ViewHolder> {
    // La lista de datos que va a pintar
    private final ArrayList<Plato> platos;
    public AdaptadorPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato, parent, false);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plato pelicula = platos.get(position);
        holder.tvReceta.setText(pelicula.getReceta());
        holder.tvTipo.setText(pelicula.getTipo());
        holder.ivImagen.setImageResource(pelicula.getImagen());
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    // El ViewHolder guarda las vistas de UNA fila
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvReceta;
        TextView tvTipo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvReceta = itemView.findViewById(R.id.tvReceta);
            tvTipo = itemView.findViewById(R.id.tvTipo);
        }
    }
}
