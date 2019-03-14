package com.robante15.checkboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText n1, n2;
    private CheckBox chk1, chk2, chk3, chk4;
    private TextView r1, r2, r3, r4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1 = (EditText) findViewById(R.id.txt_valor1);
        n2 = (EditText) findViewById(R.id.txt_valor2);
        chk1 = (CheckBox) findViewById(R.id.chk_sumar);
        chk2 = (CheckBox) findViewById(R.id.chk_restar);
        chk3 = (CheckBox) findViewById(R.id.chk_multiplicar);
        chk4 = (CheckBox) findViewById(R.id.chk_dividir);
        r1 = (TextView) findViewById(R.id.lbl_RSuma);
        r2 = (TextView) findViewById(R.id.lbl_RResta);
        r3 = (TextView) findViewById(R.id.lbl_RMult);
        r4 = (TextView) findViewById(R.id.lbl_RDiv);
    }

    public void calcular(View view) {
        String valor1 = n1.getText().toString();
        String valor2 = n2.getText().toString();
        String res1 = "", res2 = "", res3 = "", res4 = "";
        if (TextUtils.isEmpty(valor1)) {
            n1.setError("Error no puede quedar vacio");
            return;
        }
        if (TextUtils.isEmpty(valor2)) {
            n2.setError("Error no puede quedar vacio");
            return;
        }

        if (valor1 != "" && valor2 != "") {
            Double num1 = Double.parseDouble(valor1);
            Double num2 = Double.parseDouble(valor2);

            if (chk1.isChecked() == true) {
                double suma = num1 + num2;
                res1 = "Suma: " + suma;
            }

            if (chk2.isChecked() == true) {
                double resta = num1 - num2;
                res2 = "Resta: " + resta;
            }

            if (chk3.isChecked() == true) {
                double multi = num1 * num2;
                res3 = "Multiplicación: " + multi;
            }

            if (chk4.isChecked() == true) {
                double divis = num1 / num2;
                res4 = "División: " + divis;
            }
        }

        if (chk1.isChecked() == false && chk2.isChecked() == false && chk3.isChecked() == false && chk4.isChecked() == false) {
            res1 = "No selecciono ninguna opción";
            Toast.makeText(this, res1, Toast.LENGTH_SHORT).show();
            //limpiar();
        }

        r1.setText(res1);
        r2.setText(res2);
        r3.setText(res3);
        r4.setText(res4);

    }

    public void limpiar() {
        r1.setText("Suma: ");
        r2.setText("Resta: ");
        r3.setText("Multiplicación: ");
        r4.setText("División: ");
        n1.setText("");
        n2.setText("");
    }

    public void cerrar(View view) {
        finish();
        moveTaskToBack(true);
    }

}
