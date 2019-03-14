package com.user.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.math.BigDecimal;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static StringBuilder triTexto(int n) {
        StringBuilder result = new StringBuilder();
        int centenas = n / 100;
        int decenas  = (n % 100) / 10;
        int unidades = (n % 10);

        switch (centenas) {
            case 0: break;
            case 1:
                if (decenas == 0 && unidades == 0) {
                    result.append("Cien ");
                    return result;
                }
                else result.append("Ciento ");
                break;
            case 2: result.append("Doscientos "); break;
            case 3: result.append("Trescientos "); break;
            case 4: result.append("Cuatrocientos "); break;
            case 5: result.append("Quinientos "); break;
            case 6: result.append("Seiscientos "); break;
            case 7: result.append("Setecientos "); break;
            case 8: result.append("Ochocientos "); break;
            case 9: result.append("Novecientos "); break;
        }

        switch (decenas) {
            case 0: break;
            case 1:
                if (unidades == 0) { result.append("Diez "); return result; }
                else if (unidades == 1) { result.append("Once "); return result; }
                else if (unidades == 2) { result.append("Doce "); return result; }
                else if (unidades == 3) { result.append("Trece "); return result; }
                else if (unidades == 4) { result.append("Catorce "); return result; }
                else if (unidades == 5) { result.append("Quince "); return result; }
                else result.append("Dieci");
                break;
            case 2:
                if (unidades == 0) { result.append("Veinte "); return result; }
                else result.append("Veinti");
                break;
            case 3: result.append("Treinta "); break;
            case 4: result.append("Cuarenta "); break;
            case 5: result.append("Cincuenta "); break;
            case 6: result.append("Sesenta "); break;
            case 7: result.append("Setenta "); break;
            case 8: result.append("Ochenta "); break;
            case 9: result.append("Noventa "); break;
        }

        if (decenas > 2 && unidades > 0)
            result.append("y ");

        switch (unidades) {
            case 0: break;
            case 1: result.append("Un "); break;
            case 2: result.append("Dos "); break;
            case 3: result.append("Tres "); break;
            case 4: result.append("Cuatro "); break;
            case 5: result.append("Cinco "); break;
            case 6: result.append("Seis "); break;
            case 7: result.append("Siete "); break;
            case 8: result.append("Ocho "); break;
            case 9: result.append("Nueve "); break;
        }

        return result;
    }

}
