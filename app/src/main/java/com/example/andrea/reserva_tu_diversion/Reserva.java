package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        Button btnreserva = (Button) findViewById(R.id.btnreservaa);
    }
    public void btnreservaA (View view)
    {
        Intent intent = new Intent(this, Resumen.class);
        startActivity(intent);
    }
}
