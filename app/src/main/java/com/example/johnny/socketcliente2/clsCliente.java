package com.example.johnny.socketcliente2;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Johnny on 21/07/2016.
 */
public class clsCliente extends AsyncTask<Void, String, Boolean> {


    private Activity activity;
    private int puerto;


    Socket cliente;//Socket cliente
    String host = "";//Host al cual se comunicara
    String mensaje = "";//Mensaje que se enviara al server
    private boolean status = true;//Estado de la conexion
    DataOutputStream flujoSalida;


    TextView txtMensaje;

    public clsCliente(Activity activity, int puerto, String host, TextView txtMensaje) {
        this.activity = activity;
        this.puerto = puerto;
        this.host= host;
        this.txtMensaje = txtMensaje;
        this.mensaje = txtMensaje.getText().toString();
    }




    /*Antes de iniciar el proceso, reiniciamos la barra de progreso*/
    @Override
    protected void onPreExecute() {

    }


    /*
    * El método doInBackground() se ejecuta en un hilo secundario (por tanto no podremos interactuar
    * con la interfaz), pero sin embargo todos los demás se ejecutan en el hilo principal, lo que
    * quiere decir que dentro de ellos podremos hacer referencia directa a nuestros controles de
    * usuario para actualizar la interfaz. Ademas si se llama al metodo publishProgress() este
    * automáticamente ejecuta el onProgressUpdate() que actualizara la interfaz si es necesario
    * */
    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            cliente = new Socket(host, puerto);//se crea el socket de tipo cliente
            flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(mensaje);
            publishProgress("Enviado con exito");
        } catch (IOException ex) {
            publishProgress("Error al enviar el mensaje");
        }

        /*Se le retorna true a la funcion onPostExecute*/
        return true;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(activity, values[0],
                Toast.LENGTH_SHORT).show();
        txtMensaje.setText("");
    }


    @Override
    protected void onPostExecute(Boolean result) {
        /*Tan pronto termine el proceso, se muestra un toast en la activity indincando que termino
        * el proceso*/
        if(result)
            Toast.makeText(activity, "Fin de la conexion",
                    Toast.LENGTH_SHORT).show();
    }


}
