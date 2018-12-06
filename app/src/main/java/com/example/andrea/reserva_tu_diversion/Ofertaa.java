package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ofertaa extends AppCompatActivity {
    String cod_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertaa);
        Button btnenv = (Button) findViewById(R.id.btnenOfer);
        Intent intent = getIntent();
        cod_usuario = intent.getStringExtra("Cod");
    }
    public void btnOf (View view)
    {
        Intent intent = new Intent(this, Reserva.class);
        intent.putExtra("Cod", cod_usuario);
        startActivity(intent);
    }
}
