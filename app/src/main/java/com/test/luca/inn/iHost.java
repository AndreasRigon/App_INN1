package com.test.luca.inn;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Luca on 09/03/2018.
 */

public class iHost extends AsyncTask<String,String,String> {

    public static ArrayList<PrintWriterClient> printWritersClients=new ArrayList<>();

    @Override
    protected String doInBackground(String... strings) {
        try{
            publishProgress("iHost avviato");
            //creo il serversocket per ricevere i client e leggere i loro messaggi
            ServerSocket serverSocketReader=new ServerSocket(10000);
            ServerSocket serverSocketWriter=new ServerSocket(10001);
            //avvio iHostWrite
            new iHostWrite().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            boolean end= false;
            while (!end){
                //resto in attesa dei client
                new iHostRead(serverSocketReader.accept()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                //una volta ricevuta la richiesta da parte di un client per la lettura aspetto la richiesta per la scrittura
                Socket tmpSocket=serverSocketWriter.accept();
                iHost.printWritersClients.add(new PrintWriterClient(new PrintWriter(new BufferedWriter(new OutputStreamWriter(tmpSocket.getOutputStream())),true),tmpSocket.getInetAddress().getHostAddress()));
            }
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
