package com.example.andrea.reserva_tu_diversion;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
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

public class Reserva extends AppCompatActivity  implements Response.ErrorListener, Response.Listener<JSONObject>{
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText CodigoRes, NomCli, Correo,Fecha, Total, CodMesa, CodUsu;
    String cod_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

       // Button btnreserva = (Button) findViewById(R.id.btnreservaa);
        CodigoRes=(EditText) findViewById(R.id.Cod_Res);
        NomCli=(EditText)findViewById(R.id.Nom_Client);
        Correo=(EditText)findViewById(R.id.Correoo);
        Fecha=(EditText)findViewById(R.id.FechaRes);
        Total=(EditText)findViewById(R.id.ToRes);
        CodMesa=(EditText)findViewById(R.id.MesaRes);
        //CodUsu=(EditText)findViewById(R.id.UsuRes);
        Intent intent = getIntent();
        cod_usuario = intent.getStringExtra("Cod");
        requestQueue = Volley.newRequestQueue(this);
    }
    public void btnreservaA (View view)
    {
        try {
            String url="https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/Ingresar_Reserva.php?" +
                    "Cod_Reserva="+CodigoRes.getText().toString()+"&" +
                    "Nombre_Cliente="+NomCli.getText().toString()+"&" +
                    "Correo="+Correo.getText().toString()+"&" +
                    "Fecha="+Fecha.getText().toString()+"&" +
                    "Total="+Total.getText().toString()+"&" +
                    "Cod_Mesa="+CodMesa.getText().toString()+"&" +
                    "Cod_Usuario="+cod_usuario+"";

            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            Toast.makeText(this, "error " + e.toString(), Toast.LENGTH_LONG).show();
        }
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
            jsonObject = jsonArray.getJSONObject(0);
            String mensaje = jsonObject.optString("message");
            jsonObject = jsonArray.getJSONObject(0);
            String cod_usuarior=jsonObject.optString("Cod_Usuario");
            //Toast.makeText(this, "Ob " + mensaje, Toast.LENGTH_LONG).show();

            if (mensaje.equals("Ingresado")) {
                Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Menu_principal.class);
                startActivity(intent);
            } else if (mensaje.equals("Error")) {
                Toast.makeText(this, "Error_Datos", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error_Ingreso", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro_v2" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
