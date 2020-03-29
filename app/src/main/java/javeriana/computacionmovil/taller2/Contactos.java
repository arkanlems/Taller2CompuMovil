package javeriana.computacionmovil.taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Contactos extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    String[] mProjection;
    Cursor mCursor;
    ContactsAdapter mContactsAdapter;
    ListView mlista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        mlista = (ListView) findViewById(R.id.listView);

        requestPermissionContacts(Contactos.this,"contactos","para poder visulizar sus contactos " +
                "debe otrogar este permiso",0);
        mProjection = new String[]
                {
                ContactsContract.Profile._ID,
                ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
        };
        mContactsAdapter = new ContactsAdapter(this, null, 0);
        mlista.setAdapter(mContactsAdapter);



    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initView();
                } else {
                    Intent i = new Intent(Contactos.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
               
            }
                   // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
    public void requestPermissionContacts(Activity context, String permiso, String justificacion, int idCode){


                if (ContextCompat.checkSelfPermission(context,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(context,
                            Manifest.permission.READ_CONTACTS)) {
                                Toast.makeText(context,
                                justificacion + permiso ,
                                Toast.LENGTH_SHORT).show();
                    } else {

                        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                }

        }


    private void initView() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            mCursor=getContentResolver().query( ContactsContract.Contacts.CONTENT_URI,
                    mProjection, null, null, null);
            mContactsAdapter.changeCursor(mCursor);
        }
    }


}
