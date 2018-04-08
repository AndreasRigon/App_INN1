package com.test.luca.inn;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Luca on 10/03/2018.
 */

public class iClientRead extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        try{
            publishProgress("Read Avviato");
            //creo il socket per connettermi all'host
            Socket socket=new Socket(Data.host,10001);
            //creo il buffered reader per leggere i messaggi che mi invia
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                //leggo il messaggio
                String tmpMessaggio=bufferedReader.readLine();
                //controllo che il messaggio non sia vuoto
                if (tmpMessaggio.compareTo("")!=0) {
                    //dopo aver letto il messaggio creo la label contenete il messaggio
                    publishProgress("1", tmpMessaggio);
                }
            }
        }catch (Exception e){
            publishProgress("0", "Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].compareTo("0")==0)
            Display.makeToast(values[1], Toast.LENGTH_SHORT);
        else if (values[0].compareTo("1")==0) {
            RoomActivity.addLabelMessage(values[1], Gravity.LEFT, R.drawable.gray_box);
            RoomActivity.fullScrollMessage();
        }
    }

}
