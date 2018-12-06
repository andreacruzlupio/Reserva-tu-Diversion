package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ofertaa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertaa);
        Button btnenv = (Button) findViewById(R.id.btnenOfer);
    }
    public void btnOf (View view)
    {
        Intent intent = new Intent(this, Reserva.class);
        startActivity(intent);
    }
}
