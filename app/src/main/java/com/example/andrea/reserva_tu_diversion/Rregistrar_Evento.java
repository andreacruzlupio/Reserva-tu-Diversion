package com.example.andrea.reserva_tu_diversion;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class Rregistrar_Evento extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    EditText Codigo, Descripcion;
    ImageView Foto;
    private static final int COD_SELECCIONADA = 10;
    private static final int COD_FOTO = 20;
    private static final String CARPETA_IMAGEN = "imagenes";
    private static final String CARPETA_PRINCIPAL = "misImagenesApp";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;
    private final int MIS_PERMISOS = 100;
    File fileImagen;
    Bitmap bitmap;
    private String path;

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
        Codigo.setText("");
        Descripcion.setText("");
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

    private void abriCamara() {
        File miFile=new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada=miFile.exists();
        if(isCreada){
            isCreada=miFile.mkdirs();
        }
        if(isCreada){
            Long consecutivo= System.currentTimeMillis()/1000;
            String nombre=consecutivo.toString()+".jpg";
            path=Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;
            fileImagen=new File(path);
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities=this.getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(this,authorities,fileImagen);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen));
            }
            startActivityForResult(intent,COD_FOTO);
        }
    }

    public void subirFoto(View view){
        final CharSequence[] opciones={"Tomar Foto","Elegir de Galeria","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Elige una Opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    abriCamara();
                }else{
                    if (opciones[i].equals("Elegir de Galeria")){
                        Intent intent=new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione"), COD_SELECCIONADA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }

    private Bitmap redimensionarImagen(Bitmap bitmap, float anchoNuevo, float altoNuevo) {
        int ancho=bitmap.getWidth();
        int alto=bitmap.getHeight();
        if(ancho>anchoNuevo || alto>altoNuevo){
            float escalaAncho=anchoNuevo/ancho;
            float escalaAlto= altoNuevo/alto;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaAncho,escalaAlto);

            return Bitmap.createBitmap(bitmap,0,0,ancho,alto,matrix,false);

        }else{
            return bitmap;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case COD_SELECCIONADA:
                Uri miPath=data.getData();
                Foto.setImageURI(miPath);
                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),miPath);
                    Foto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile(this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("Path",""+path);
                            }
                        });
                bitmap= BitmapFactory.decodeFile(path);
                Foto.setImageBitmap(bitmap);
                break;
        }
        bitmap=redimensionarImagen(bitmap,600,800);
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    private boolean solicitaPermisosVersionesSuperiores() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if((this.checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&this.checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED){
            return true;
        }
        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MIS_PERMISOS);
        }
        return false;
    }

    public void botonEliminar(View view){
        Intent intent = new Intent(this, EliminarEvento.class);
        startActivity(intent);
    }

}
