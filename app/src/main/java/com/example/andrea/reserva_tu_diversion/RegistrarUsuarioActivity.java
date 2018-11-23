package com.example.andrea.reserva_tu_diversion;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
public class RegistrarUsuarioActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText Codigo, Nombre, Apellido, Edad, Fecha_N, Usuario, Contraseña, Tipo_Usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario2);
        Button bntRCliente = (Button) findViewById(R.id.btnregi);
        Codigo=(EditText) findViewById(R.id.textCodi);
        Nombre=(EditText)findViewById(R.id.textNom);
        Apellido=(EditText)findViewById(R.id.textApell);
        Edad=(EditText)findViewById(R.id.textedad);
        Fecha_N=(EditText)findViewById(R.id.textF);
        Usuario=(EditText)findViewById(R.id.textUsuario);
        Contraseña=(EditText)findViewById(R.id.textContraseña);
        Tipo_Usu=(EditText)findViewById(R.id.textTipo);
        requestQueue = Volley.newRequestQueue(this);
    }
    public void btnreg (View view){
        try {

            String url = "https://viridianamacias.000webhostapp.com/PHP/antros_Consultas/IngresarUsuario2.php?" +
                    "Cod_Usuario="+Codigo.getText().toString()+"&" +
                    "Nombre="+Nombre.getText().toString()+"&" +
                    "Apellido="+Apellido.getText().toString()+"&" +
                    "Edad="+Edad.getText().toString()+"&" +
                    "Fecha_Nacimiento="+Fecha_N.getText().toString()+"&" +
                    "Usuario="+Usuario.getText().toString()+"&" +
                    "Contrase%C3%B1a="+Contraseña.getText().toString()+"&" +
                    "Tipo_Usuario="+Tipo_Usu.getText().toString()+"";

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
