package com.example.andrea.reserva_tu_diversion.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andrea.reserva_tu_diversion.R;

import java.util.ArrayList;

public class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolderDatos>{
    ArrayList<Tematicaa> ListDatos;
    Context context;

    public AdaptadorEventos(ArrayList<Tematicaa> listDatos, Context context) {
        this.context = context;
        ListDatos = listDatos;
    }

    @NonNull
    @Override
    public AdaptadorEventos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_eventos, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return new ViewHolderDatos(view);
    }


    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
    holder.asicnarDatos(ListDatos.get(position));

    }

    @Override
    public int getItemCount()
    {
        return ListDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView Nombre, Descripcion;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.NomEvent);
            Descripcion = itemView.findViewById(R.id.DesEvent);
        }

        public void asicnarDatos(Tematicaa datos) {
            Nombre.setText(datos.getCod_Tematica());
            Descripcion.setText(datos.getDescripcion());
        }
    }
}
