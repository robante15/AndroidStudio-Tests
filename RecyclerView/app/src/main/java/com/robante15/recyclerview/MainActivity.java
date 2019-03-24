package com.robante15.recyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    SearchView sv;
    Cursor cursor ;
    String nombre, telefono ;
    public  static final int RequestPermissionCode  = 1 ;
    public static final int REQUEST_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnableRuntimePermission();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*agregar  al recycler  la función addOnItemTouchListener, esta función no está en el adapter
        sino es una función propia del RecyclerView y la debes utilizar cuando estás definiendo tu RecyclerView
         */
        sv= (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        final MyAdapter adapter=new MyAdapter(this,getContactos());
        rv.setAdapter(adapter);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                try {
                    View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        int position = rv.getChildAdapterPosition(child);
                        String  info2= ((TextView) rv.findViewById(R.id.posTxt)).getText().toString();
                        String info = ((TextView) rv.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.posTxt)).getText().toString();
                        String num = info.replace(" ","");
                        onCall(num);

                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
            public void onTouchEvent(RecyclerView rv, MotionEvent motionEvent) {

            }
        });
        //SEARCH
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    //ADD PLAYERS TO ARRAYLIST
    private ArrayList<Contacto> getContactos()
    {
        ArrayList<Contacto> contactos =new ArrayList<>();
        Contacto p=new Contacto();
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        while (cursor.moveToNext()) {
            nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            telefono = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            p=new Contacto();
            p.setName(nombre);
            p.setPos(telefono);
            contactos.add(p);
        }
        cursor.close();
        return contactos;
    }

    //permisos para acceso a contactos del sistemas
    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,
                Manifest.permission.READ_CONTACTS))
        {
            Toast.makeText(MainActivity.this,"Acceso a CONTACTOS Permitido", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"Permiso Asignado, Accediendo a CONTACTOS.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"Permiso denegado, no puede acceder a CONTACTOS.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void onCall(String number){
        TelephonyManager tm=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm.getCallState()== TelephonyManager.CALL_STATE_IDLE) {
            call(number);
        }else{
            Toast.makeText(this,"Telefono en uso",Toast.LENGTH_LONG).show();
        }
    }

    public void call(String number){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CODE);

        } else{
            Toast.makeText(this,"Realizando llamada a: "+number,Toast.LENGTH_LONG).show();
            Intent intent= new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+number));
            startActivity(intent);
        }
    }
}
