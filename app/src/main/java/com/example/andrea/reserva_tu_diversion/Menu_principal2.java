package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class Menu_principal2 extends Activity {
    String cod_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal2);
        Intent intent = getIntent();
        cod_usuario = intent.getStringExtra("Cod");
    }
    public void btnOfC (View view)
    {
        Intent intent = new Intent(this, Ofertaa.class);
        intent.putExtra("Cod", cod_usuario);
        startActivity(intent);
    }
    public void btnOffC (View view)
    {
        Intent intent = new Intent(this, Oferta.class);
        intent.putExtra("Cod", cod_usuario);
        startActivity(intent);
    }
    public void btnEvC (View view)
    {
        Intent intent = new Intent(this, Eventos.class);
        intent.putExtra("Cod", cod_usuario);
        startActivity(intent);
    }

}
