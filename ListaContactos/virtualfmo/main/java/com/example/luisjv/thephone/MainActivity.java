package com.example.luisjv.thephone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.telephony.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtNumber;
    private static final int REQUEST_CODE=100;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumber=(EditText)findViewById(R.id.txtNumber);
    }
    
    public void onDial(View view){
        TelephonyManager tm=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm.getCallState()== TelephonyManager.CALL_STATE_IDLE) {
            String number="tel:"+txtNumber.getText().toString();
            startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse(number)));
        }else{
            Toast.makeText(this,"Telefono en uso",Toast.LENGTH_LONG).show();

        }
    }
    
    public void onCall(View view){
        TelephonyManager tm=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (tm.getCallState()== TelephonyManager.CALL_STATE_IDLE) {
          call();
        }else{
            Toast.makeText(this,"Telefono en uso",Toast.LENGTH_LONG).show();
        }
    }
    
    public void call(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CODE);

        } else{
            String number=txtNumber.getText().toString();
            Intent  intent= new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+number));
            startActivity(intent);
            Toast.makeText(this,"Realizando llamada",Toast.LENGTH_LONG).show();
        }

    }
    
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode==REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                call();
            else
                Toast.makeText(this,"No tienes permiso para llamar !!!",Toast.LENGTH_LONG).show();
        }
    }
    cerote
    
    public void limpiar() {
        txtNumber.setText("");
        //result.setText("");

    }

    public void cerrar(View view) {
        finish();
        moveTaskToBack(true);
        //System.exit(0);
    }
}
