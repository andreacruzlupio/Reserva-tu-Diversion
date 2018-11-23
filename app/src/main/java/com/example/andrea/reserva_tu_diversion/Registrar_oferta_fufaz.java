package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registrar_oferta_fufaz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_oferta_fufaz);
        Button btnpag = (Button) findViewById(R.id.guardarbtn);
    }
    public void btnOFF (View view)
    {
        Intent intent = new Intent(this, Oferta.class);
        startActivity(intent);
    }
}
