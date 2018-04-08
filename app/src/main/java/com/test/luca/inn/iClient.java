package com.test.luca.inn;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Luca on 10/03/2018.
 */

public class iClient extends AsyncTask<String,String,String> {

    public volatile static Socket socketWriter;
    public volatile static PrintWriter printWriter;

    @Override
    protected String doInBackground(String... strings) {
        try {
            publishProgress("iClient Avviato");
            //creo il socket che si collegga con l'host
            iClient.socketWriter = new Socket(Data.host, 10000);
            //creo il printWriter che andrà ad inviare i messaggi all'host
            iClient.printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(iClient.socketWriter.getOutputStream())),true);
            //avvio il thread che mi andra a gestire le letture
            new iClientRead().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }catch (Exception e){
            publishProgress("Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Display.makeToast(values[0], Toast.LENGTH_SHORT);
    }

}
