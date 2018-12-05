package com.example.andrea.reserva_tu_diversion;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;


public class Rregistrar_Evento extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText Codigo, Descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rregistrar__evento);
        Button btnenv = (Button) findViewById(R.id.btnGRE);
        Codigo=(EditText) findViewById(R.id.CodReEvento);
        Descripcion=(EditText)findViewById(R.id.DesReEvento);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void btnGEve (View view)
    {
        try {
            String url = "https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/Ingresar_Tematicaa.php?" +
                    "Cod_Tematica="+Codigo.getText().toString()+"&" +
                    "Descripcion="+Descripcion.getText().toString()+"";

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            Toast.makeText(this, "error " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            Toast.makeText(this, "Error " + error.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e){

        }
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.optJSONArray("Datos");
            JSONObject jsonObject = null;
            jsonObject = jsonArray.getJSONObject(0);
            String mensaje = jsonObject.optString("message");

            //Toast.makeText(this, "Ob " + mensaje, Toast.LENGTH_LONG).show();

            if (mensaje.equals("Ingresado")) {
                Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Menu_principal.class);
                startActivity(intent);
            }
            else if(mensaje.equals("Error")){
                Toast.makeText(this, "Error_Datos", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Error_Ingreso", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Erro_v2" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
