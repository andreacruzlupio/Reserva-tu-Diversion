package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Resumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        Button btnagregar = (Button) findViewById(R.id.btnagrofr);
        Button btnpag = (Button) findViewById(R.id.btnrealpago);
    }
    public void btnagrgarOf (View view)
    {
        Intent intent = new Intent(this, Ofertaa.class);
        startActivity(intent);
    }
    public void btnpago (View view)
    {
        //Intent intent = new Intent(this, PayPal.class);
        //startActivity(intent);
    }
}
