package com.example.andrea.reserva_tu_diversion.Adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.andrea.reserva_tu_diversion.R;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolderDatos>{
    @NonNull
    @Override
    public AdaptadorEventos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEventos.ViewHolderDatos viewHolderDatos, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        EditText Nombre, Fecha;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.TVEveNombre);
            Fecha = itemView.findViewById(R.id.TVEveFecha);
        }
    }
}
