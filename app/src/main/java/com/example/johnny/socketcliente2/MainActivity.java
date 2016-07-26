package com.example.johnny.socketcliente2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtPuerto;
    TextView txtIp;
    TextView txtMensaje;


    clsCliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPuerto = (TextView) findViewById(R.id.txtPuerto);
        txtIp = (TextView) findViewById(R.id.txtIp);
        txtMensaje = (TextView) findViewById(R.id.txtMensaje);
    }


    public void enviarMensaje(View view){
        int puerto= Integer.parseInt(txtPuerto.getText().toString());
        String ip = txtIp.getText().toString();
        cliente = new clsCliente(this,puerto,ip,txtMensaje);
        cliente.execute();
    }
}
