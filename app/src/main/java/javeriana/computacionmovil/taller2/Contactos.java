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
    String[] mProjection;
    Cursor mCursor;
    ContactsAdapter mContactsAdapter;
    ListView mlista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        mlista = (ListView) findViewById(R.id.list);
        mProjection = new String[]{ ContactsContract.Profile._ID, ContactsContract.Profile.DISPLAY_NAME_PRIMARY, };
        mContactsAdapter = new ContactsAdapter(this, null, 0);
        mlista.setAdapter(mContactsAdapter);
        initView();

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
