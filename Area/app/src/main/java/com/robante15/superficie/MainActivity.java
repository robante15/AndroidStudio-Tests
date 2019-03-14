package com.robante15.superficie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText diametro;
    private TextView lbl_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diametro=(EditText)findViewById(R.id.txt_diametro);
        lbl_resultado=(TextView)findViewById(R.id.lbl_areaCirculo);

    }

    public void areaCirculo(View view){
        double diam = Double.parseDouble(diametro.getText().toString());
        double areaDiametro = Math.PI*Math.pow((diam/2), 2);
        lbl_resultado.setText(String.valueOf(areaDiametro));
    }
}
