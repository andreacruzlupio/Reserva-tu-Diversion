package com.example.andrea.reserva_tu_diversion;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.andrea.reserva_tu_diversion.Adaptador.AdaptadorEventos;
import com.example.andrea.reserva_tu_diversion.Adaptador.Tematicaa;

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class Eventos extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>  {
    ArrayList<Tematicaa> ListDatos;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    TextView nombre, Descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        ListDatos = new ArrayList<Tematicaa>();
        recyclerView= findViewById(R.id.reseven);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        nombre=(TextView) findViewById(R.id.NomEvent);
        Descripcion=(TextView) findViewById(R.id.DesEvent);
        requestQueue = Volley.newRequestQueue(this);


        try {
            String url="https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/sppiner.php?Consulta=select%20*%20from%20Tematicaa";
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            Toast.makeText(this, "error " + e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void btnreserva (View view)
    {
        Intent intent = new Intent(this, Reserva.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error "+error.toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        try {
            for(int x = 0; x < jsonArray.length(); x++){
                jsonObject = jsonArray.getJSONObject(x);
                String Cod_Tematica = jsonObject.optString("Cod_Tematica");
                String Descripcion = jsonObject.optString("Descripcion");
                ListDatos.add(new Tematicaa(Cod_Tematica, Descripcion));
                Toast.makeText(this, Cod_Tematica, Toast.LENGTH_LONG).show();
            }
            AdaptadorEventos adaptadorEventos = new AdaptadorEventos(ListDatos, this);
            recyclerView.setAdapter(adaptadorEventos);
        } catch (Exception e) {
            Toast.makeText(this, "Erro_v2" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
