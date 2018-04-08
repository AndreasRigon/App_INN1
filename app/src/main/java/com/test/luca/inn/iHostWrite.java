package com.test.luca.inn;

import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Luca on 10/03/2018.
 */

public class iHostWrite extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        try{
            while (true){
                if (Data.lstMessages.size()>0) {
                    //invio il messaggio a tutti i client (tranne a quello che me lo ha inviato)
                    for (int i = 0; i < iHost.printWritersClients.size(); i++)
                        if (Data.lstMessages.get(0).getIpClient().compareTo(iHost.printWritersClients.get(i).getIpClient())!=0)
                            iHost.printWritersClients.get(i).getPrintWriter().println(Data.lstMessages.get(0).getText());
                    //elimino il messaggio dalla lista
                    Data.lstMessages.remove(0);
                }
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
