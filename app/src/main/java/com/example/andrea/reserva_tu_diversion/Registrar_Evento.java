package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registrar_Evento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__evento);
        Button btnenv = (Button) findViewById(R.id.btngEv);
    }
    public void btnOf (View view)
    {
        Intent intent = new Intent(this, Eventos.class);
        startActivity(intent);
    }

}
