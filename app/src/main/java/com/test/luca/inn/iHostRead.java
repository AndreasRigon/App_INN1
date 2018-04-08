package com.test.luca.inn;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Luca on 10/03/2018.
 */

public class iHostRead extends AsyncTask<String,String,String> {

    private BufferedReader bufferedReader;
    private String ipClient;

    public iHostRead(Socket socket){
        try{
            //creo il bufferedReader che mi andrà a leggere i messaggi dei client
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //salvo l'ip del client
            ipClient=socket.getInetAddress().getHostAddress();
        }catch (Exception e){}
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            publishProgress("0","Lettura Messaggi avviata");
            while (true){
                //leggo il messaggio
                String tmpMessaggio=bufferedReader.readLine();
                //controllo che il messaggio ricevuto non sia vuoto
                if (tmpMessaggio.compareTo("")!=0) {
                    //leggo il messaggio e lo aggiungo alla lista
                    Data.lstMessages.add(new Message(tmpMessaggio, ipClient));
                    //creo la label che contiene il messaggio
                    publishProgress("1", Data.lstMessages.get(Data.lstMessages.size() - 1).getText());
                }
            }
        }catch (Exception e){
            publishProgress("0","Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        //se la stringa in posizione 0 equivale a 0 creo un Toast
        if (values[0].compareTo("0")==0)
            Display.makeToast(values[1], Toast.LENGTH_SHORT);
        //se la stringa in posizione 0 è uguale a 1 creo la label con il messaggio
        else if (values[0].compareTo("1")==0) {
            RoomActivity.addLabelMessage(values[1], Gravity.LEFT, R.drawable.gray_box);
            RoomActivity.fullScrollMessage();
        }
    }

}
