package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu_principal extends AppCompatActivity {

    String cod_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        ImageButton btnofe = (ImageButton) findViewById(R.id.bof);
        Intent intent = getIntent();
        cod_usuario = intent.getStringExtra("Cod");
    }
    public void btnOf (View view)
{
    Intent intent = new Intent(this, RegistrarOferta.class);
    intent.putExtra("Cod", cod_usuario);
    startActivity(intent);
}
    public void btnOff (View view)
    {
        Intent intent = new Intent(this, Registrar_oferta_fufaz.class);
        startActivity(intent);
    }
    public void btnEv (View view)
    {
        Intent intent = new Intent(this, Rregistrar_Evento.class);
        startActivity(intent);
    }
    public void menu2 (View view)
    {
        Intent intent = new Intent(this, Menu_principal2.class);
        startActivity(intent);
    }
}
