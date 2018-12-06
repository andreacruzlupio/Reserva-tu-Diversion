package com.example.andrea.reserva_tu_diversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class EliminarOferta extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener  {

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText ICodigos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_oferta);
        Button btnAceptarEE = (Button) findViewById(R.id.BEliminar);
        ICodigos=findViewById(R.id.editText2);
        requestQueue = Volley.newRequestQueue(this);
    }

    public void bntAceptarEE (View view){
        if (!ICodigos.getText().toString().isEmpty()) {
            String url = "https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/Eliminar_todas.php?" +
                    "Tabla=Ofertas&"+
                    "Id=" + ICodigos.getText().toString() + "";
            url = url.replace(" ", "%20");
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);

        } else {
            Toast.makeText(this, "No puede dejar campos vacios",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "!Ups¡ \n" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            String message=jsonObject.optString("message");
            if(message.equals("Eliminado")){
                Toast.makeText(this, "EVENTO ELIMINADO", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
            }else{
                //no existe
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        ICodigos.setText("");
    }
}
