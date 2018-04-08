package com.test.luca.inn;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Luca on 07/03/2018.
 */

public class LogInRoom extends AsyncTask<String,String,String> {

    private MainActivity mainActivity;

    public LogInRoom(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            //creo il socket con i suoi elementi
            Socket socket=new Socket(Data.IPSERVER,9999);
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //invio al server l'azione che voglio compiere
            printWriter.println("1");
            //invio al server il nome della stanza
            printWriter.println(strings[0]);
            //leggo l'ip dell'host
            Data.host=bufferedReader.readLine();
        }catch (Exception e){
            publishProgress("Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Display.makeToast(values[0], Toast.LENGTH_SHORT);
    }

    @Override
    protected void onPostExecute(String s) {
        if (Data.host.compareTo("null")!=0)
            mainActivity.startActivity(new Intent(mainActivity,RoomActivity.class));
        else
            publishProgress("Errore di connesione con la stanza");
    }
}
