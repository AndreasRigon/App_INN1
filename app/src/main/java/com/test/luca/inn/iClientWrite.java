package com.test.luca.inn;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Luca on 10/03/2018.
 */

public class iClientWrite extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            //invio il messaggio all'host
            iClient.printWriter.println(strings[0]);
            //creo la label che contiene il messaggio appena inviato dal client
            publishProgress("1", strings[0]);
        }catch (Exception e){
            publishProgress("0", "Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values[0].compareTo("0")==0)
            Display.makeToast(values[1], Toast.LENGTH_SHORT);
        else if (values[0].compareTo("1")==0)
            RoomActivity.addLabelMessage(values[1],Gravity.RIGHT, R.drawable.blue_box);
    }

}
