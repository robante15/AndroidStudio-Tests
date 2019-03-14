package com.robante15.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText v1;
    private TextView result;
    private RadioButton r1, r2;
    double relacion = 1.60934;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = (EditText)findViewById(R.id.txt_val1);
        result = (TextView)findViewById(R.id.lbl_resultado);
        r1 = (RadioButton)findViewById(R.id.rbtn_km2mil);
        r2 = (RadioButton)findViewById(R.id.rbtn_mil2km);
    }

    public void CalConversion(View view){
        Double val1 = Double.parseDouble(v1.getText().toString());
        Double conversion = 0.0;
        String unidad = "";
        String resultado;

        if(r1.isChecked() == true){
            conversion = val1*relacion;
            unidad = "Km";
        }else{
            conversion = val1/relacion;
            unidad = "Millas";
        }

        resultado = String.valueOf(conversion);
        result.setText(resultado + unidad);

    }

    public void Limpiar(View view){
        v1.setText("");
        result.setText("Resultado: ");
    }

    public void Cerrar(View view){
        finish();
        moveTaskToBack(true);
    }
}
