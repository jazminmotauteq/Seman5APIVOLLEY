package com.example.seman5apiusandovolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Btenviar (View view){
        EditText Usuario = findViewById(R.id.txtnombre);
        EditText Clave = findViewById(R.id.txtclave);
        String usuario;
        String  clave;
        usuario=Usuario.getText().toString();
        clave=Clave.getText().toString();
        //codigo para concetar a internet
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        datos.put("correo",usuario);
        datos.put("clave",clave);
        WebService ws= new WebService(" https://api.uealecpeterson.net/public/login"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("POST");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView respuesta =findViewById(R.id.txtaleta);
        JSONObject jsonrespuesta = new JSONObject(result);

        if (jsonrespuesta.has("error"))
        {
            respuesta.setText(jsonrespuesta.getString("error"));
        }
        else
        {

            Bundle b = new Bundle();
            b.putString("TOKEN", jsonrespuesta.getString("access_token"));
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            intent.putExtras(b);
            startActivity(intent);

        }


    }
}


