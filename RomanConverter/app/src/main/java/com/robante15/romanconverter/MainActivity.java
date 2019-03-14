package com.robante15.romanconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numEntero;
    private TextView LeyendaResultado, ResultadoRomano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numEntero = (EditText) findViewById(R.id.txt_numero);
        LeyendaResultado = (TextView) findViewById(R.id.lbl_LegendResultado);
        ResultadoRomano = (TextView) findViewById(R.id.lbl_resultadoRomano);

    }

    public void limpiar(View view) {
        numEntero.setText("");
        LeyendaResultado.setText("");
        ResultadoRomano.setText("");

    }

    public void cerrar(View view) {
        finish();
        moveTaskToBack(true);
    }

    public void MostrarValor(View view) {

        String str_NumeroEntero = "";
        str_NumeroEntero = numEntero.getText().toString();

        if (TextUtils.isEmpty(str_NumeroEntero) == true) {
            numEntero.setError("Error el campo no puede estar vacio");
            return;
        }

        int NumeroBase10 = Integer.parseInt(str_NumeroEntero);

        if (NumeroBase10 > 3999 || NumeroBase10 < 1) {
            numEntero.setError("Tiene que ingresar un valor entre 1 y 3999");
            return;
        }

        String resultadoRomano = ConvertirDecimalRomano(NumeroBase10);
        LeyendaResultado.setText("El numero " + NumeroBase10 + " en romano es");
        ResultadoRomano.setText(resultadoRomano);
    }

    public static String ConvertirDecimalRomano(int numero) {
        int i, miles, centenas, decenas, unidades;
        String romano = "";
        //obtenemos cada cifra del número
        miles = numero / 1000;
        centenas = numero / 100 % 10;
        decenas = numero / 10 % 10;
        unidades = numero % 10;

        //millar
        for (i = 1; i <= miles; i++) {
            romano = romano + "M";
        }

        //centenas
        if (centenas == 9) {
            romano = romano + "CM";
        } else if (centenas >= 5) {
            romano = romano + "D";
            for (i = 6; i <= centenas; i++) {
                romano = romano + "C";
            }
        } else if (centenas == 4) {
            romano = romano + "CD";
        } else {
            for (i = 1; i <= centenas; i++) {
                romano = romano + "C";
            }
        }

        //decenas
        if (decenas == 9) {
            romano = romano + "XC";
        } else if (decenas >= 5) {
            romano = romano + "L";
            for (i = 6; i <= decenas; i++) {
                romano = romano + "X";
            }
        } else if (decenas == 4) {
            romano = romano + "XL";
        } else {
            for (i = 1; i <= decenas; i++) {
                romano = romano + "X";
            }
        }

        //unidades
        if (unidades == 9) {
            romano = romano + "IX";
        } else if (unidades >= 5) {
            romano = romano + "V";
            for (i = 6; i <= unidades; i++) {
                romano = romano + "I";
            }
        } else if (unidades == 4) {
            romano = romano + "IV";
        } else {
            for (i = 1; i <= unidades; i++) {
                romano = romano + "I";
            }
        }
        return romano;
    }

}
