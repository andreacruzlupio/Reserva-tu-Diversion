package com.example.andrea.reserva_tu_diversion;

import android.app.ProgressDialog;
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
public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener  {
    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText contraseña, usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.activity_registrar_usuario2);
        Button btncrear = (Button) findViewById(R.id.btncrear);
        Button btnentrar = (Button) findViewById(R.id.btnentrar);
        usuario=(EditText) findViewById(R.id.textusuario);
        contraseña=(EditText) findViewById(R.id.textContra);
        requestQueue = Volley.newRequestQueue(this);

    }
    public void btnentrar (View view)
    {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Validando...");
        progreso.show();
        String url = "https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/Consultas.php?Consulta=select%20*%20from%20Usuario%20where%20" +
                "Usuario='"+usuario.getText().toString()+"'%20and%20"+
                "Contraseña='"+contraseña.getText().toString()+"'";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
        usuario.setText("");
        contraseña.setText("");
    }

    public void btncrear (View view)
    {
        Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);
        usuario.setText("");
        contraseña.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(this, "!Ups¡ \n" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("Datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            String cod_usuarior=jsonObject.optString("Cod_Usuario");
            Toast.makeText(this, "Cod " + cod_usuarior, Toast.LENGTH_LONG).show();
            if(!cod_usuarior.equals("0")){
                //el usuario existe
                Toast.makeText(this, "Bienvenido" + cod_usuarior, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Menu_principal.class);
                startActivity(intent);
            }else{
                //no existe
                Toast.makeText(this, "Error de Usuario/Contraseña :/", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "Algo salio mal :( \n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        progreso.hide();
    }
}



