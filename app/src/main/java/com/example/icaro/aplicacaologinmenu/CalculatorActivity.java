package com.example.icaro.aplicacaologinmenu;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, Runnable{

    private EditText edtValor1;
    private EditText edtValor2;
    private Button btnCalcular;
    private TextView lbl_valor3;
    private Handler handler = new Handler();
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_activity);

        edtValor1 = (EditText) findViewById(R.id.edtValor1);
        edtValor2 = (EditText) findViewById(R.id.edtValor2);
        lbl_valor3 = (TextView) findViewById(R.id.lbl_valor3);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Processando...");
        dialog.setTitle("Exemplo WebService");
        dialog.show();

        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        int valor1 = Integer.parseInt(edtValor1.getText().toString());
        int valor2 = Integer.parseInt(edtValor2.getText().toString());

        try{
            CalculatorWS ws = new CalculatorWS();
            final int resultado = ws.add(valor1, valor2);

            handler.post(new Runnable() {
                @Override
                public void run() {

                    lbl_valor3.setText("Resultado: " + resultado);

                }
            });
        }catch (Exception ex)
        {
            Log.e("CalculatorActivity", "Erro", ex);
        }
        finally
        {
            dialog.dismiss();
        }

    }
}
