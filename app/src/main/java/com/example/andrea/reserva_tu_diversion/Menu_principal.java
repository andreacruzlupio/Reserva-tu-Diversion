package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        ImageButton btnofe = (ImageButton) findViewById(R.id.bof);


    }
    public void btnOf (View view)
    {
        Intent intent = new Intent(this, RegistrarOferta.class);
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
}
