package com.robante15.listacontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listaContactos;
    ArrayList<String> StoreContacts;
    ArrayAdapter<String> arrayAdapter;
    Cursor cursor;
    String name, phonenumber;
    public static final int RequestPermissionCode = 1;
    private View v;
    Button btn_contactosListar;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaContactos = (ListView) findViewById(R.id.list_contactos);
        btn_contactosListar = (Button) findViewById(R.id.btn_listarContactos);
        StoreContacts = new ArrayList<String>();
        EnableRuntimePermission();

        btn_contactosListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetContactsIntoArrayList();
                arrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        R.layout.contact_items_listview,
                        R.id.lbl_nombreContacto, StoreContacts
                );
                listaContactos.setAdapter(arrayAdapter);
            }
        });

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = parent.getItemAtPosition(position).toString();
                itemSeleccionado.split(":");
                String numero = itemSeleccionado.split(":")[1];
                numero = numero.trim();
                // muestra un mensaje
                Toast.makeText(getApplicationContext(), "El valor  seleccionado es: " +
                        numero, Toast.LENGTH_SHORT).show();
                onCall(numero);
            }
        });

    }

    public void GetContactsIntoArrayList() {
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            StoreContacts.add(name + " : " + phonenumber);
        }
        cursor.close();
    }

    public void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(MainActivity.this, "Acceso a CONTACTOS Permitido", Toast.LENGTH_LONG).show();

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permiso Asignado, Accediendo a CONTACTOS.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permiso denegado, no puede acceder a CONTACTOS.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void onCall(String numero) {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm.getCallState() == TelephonyManager.CALL_STATE_IDLE) {
            call(numero);
        } else {
            Toast.makeText(this, "Telefono en uso", Toast.LENGTH_LONG).show();
        }
    }

    public void call(String numero) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);

        } else {
            //String number= txtNumber.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numero));
            startActivity(intent);
            Toast.makeText(this, "Realizando llamada", Toast.LENGTH_LONG).show();
        }

    }

    public void limpiar(View view) {
        Toast.makeText(MainActivity.this, "CONTACTOS", Toast.LENGTH_LONG).show();
    }

    public void cerrar(View view) {
        finish();
        moveTaskToBack(true);
    }

}
