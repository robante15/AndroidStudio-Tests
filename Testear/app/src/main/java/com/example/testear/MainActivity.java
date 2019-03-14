package com.example.testear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txt_num1, txt_num2;
    private TextView lbl_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_num1=(EditText)findViewById(R.id.txt_num1);
        txt_num2=(EditText)findViewById(R.id.txt_num2);
        lbl_resultado=(TextView)findViewById(R.id.lbl_resultado);

    }

    public void calcuSuma(View view){
        double valor1 = Double.parseDouble(txt_num1.getText().toString());
        double valor2 = Double.parseDouble(txt_num2.getText().toString());
        double resulSum = valor1 + valor2;
        lbl_resultado.setText(String.valueOf(resulSum));

    }

}
