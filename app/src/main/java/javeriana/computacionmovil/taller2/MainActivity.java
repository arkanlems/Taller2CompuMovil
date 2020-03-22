package javeriana.computacionmovil.taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String MainActivity = "MainActivity";
    private static final int REQUEST= 112;
    Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(MainActivity, 0);
        HashMap<String, String> map = (HashMap<String, String>) settings.getAll();


        ImageButton botonContactos = findViewById(R.id.BotonContactos);
        botonContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                };
                if (!hasPermissions(mContext, PERMISSIONS)) {
                    Log.d("TAG", "@@@ IN IF hasPermissions");
                    ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
                } else {
                    Log.d("TAG", "@@@ IN ELSE hasPermissions");
                    callNextActivity();
                }

                Log.d("TAG", "@@@ IN ELSE  Build.VERSION.SDK_INT >= 23");
                callNextActivity();

            }
        });
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void callNextActivity()
    {
        Intent ss = new Intent(MainActivity.this, Contactos.class);
        ss.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ss.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ss.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(ss);
        finish();
    }
    }


