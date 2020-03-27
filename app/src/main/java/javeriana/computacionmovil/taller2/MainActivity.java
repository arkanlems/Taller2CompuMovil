package javeriana.computacionmovil.taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ImageButton botonContactos = findViewById(R.id.BotonContactos);
        botonContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity();
            }
          });

        ImageButton botonCamara = findViewById(R.id.BotonCamara);
        botonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Camara.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton botonMapa = findViewById(R.id.botonMapa);
        botonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivityMap();
            }
        });

    }

    public void callNextActivity()
    {
        Intent ss = new Intent(MainActivity.this, Contactos.class);
        startActivity(ss);
        finish();
    }
    public void callNextActivityMap()
    {
        Intent ss = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(ss);
        finish();
    }
    }


