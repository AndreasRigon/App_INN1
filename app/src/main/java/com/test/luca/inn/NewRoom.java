package com.test.luca.inn;

import android.content.Context;
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
 * Created by Luca on 01/03/2018.
 */

public class NewRoom extends AsyncTask<String,String,String> {

    private MainActivity mainActivity;

    public NewRoom(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            //creo il socket con i suoi elementi per lo scambio di dati
            Socket socket=new Socket(Data.IPSERVER,9999);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            //dico al server che voglio creare una nuova Stanza
            printWriter.println("0");
            if (bufferedReader.readLine().compareTo("0")==0) {
                //dico all'utente che la stanza è stta creata tramite un Toast
                publishProgress("Stanza Creata");
                //imposto l'host a 0
                Data.host="0";
            }
        }catch(Exception e){
            //creo un toast in cui avverto l'utente che c'è stato un problema di rete
            publishProgress("Errore connesione");
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Display.makeToast(values[0],Toast.LENGTH_SHORT);
    }

    @Override
    protected void onPostExecute(String s) {
        mainActivity.startActivity(new Intent(mainActivity,RoomActivity.class));
    }

}
