package com.test.luca.inn;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class RoomActivity extends AppCompatActivity {

    public static LinearLayout linearLayout;
    public static ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //imposto il layout nella linearLayout
        RoomActivity.linearLayout=(LinearLayout)findViewById(R.id.linearLayoutMessage);

        //imposto la variabile statica scrollView
        RoomActivity.scrollView=findViewById(R.id.scrollViewMessages);

        //imposto la WindowManager nella classe statica Display
        Display.windowManager=getWindowManager();

        //imposto il context
        Display.context=getApplicationContext();

        //controllo se l'utente è l'host
        if (Data.host.compareTo("0")==0)
            //se lo è avvio l'AsynckTask iHost
            new iHost().execute();
        //se non è l'host avvio il dispositivo in modalità client
        else{
            Display.makeToast("Sei un client", Toast.LENGTH_SHORT);
            new iClient().execute();
        }

        //creo l'evento del btnSendMessage
        findViewById(R.id.btnSendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((EditText)findViewById(R.id.txtMessage)).getText().toString().compareTo("")!=0) {
                    if (Data.host.compareTo("0") == 0) {
                        //se è l'host ad invire il messaggio, aggiungo il suo messaggio alla lista e stampo a schermo il nuovo messaggio
                        Data.lstMessages.add(new Message(((EditText) findViewById(R.id.txtMessage)).getText().toString(), "0"));
                        addLabelMessage(((EditText) findViewById(R.id.txtMessage)).getText().toString(), Gravity.RIGHT, R.drawable.blue_box);
                    } else
                        //se invece è il client ad inviare il messaggio avvio l'AsynckTask iClientWrite
                        new iClientWrite().execute(((EditText) findViewById(R.id.txtMessage)).getText().toString());
                    ((EditText) findViewById(R.id.txtMessage)).setText("");
                    RoomActivity.fullScrollMessage();
                }
            }
        });

        //utilizzo questo evento per vedere quando la tastiera virtuale "esce o scompare"
        final View mainLayout=this.findViewById(R.id.mainLayout);
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {

                //keyboard up
                if (mainLayout.getRootView().getHeight() - mainLayout.getHeight()>100) {
                    //nel momento in cui esce la tastiera faccio scorrere tutta la scrollView
                    RoomActivity.fullScrollMessage();
                }
                //keyboard down
                else {}
            }
        });

    }

    public static void fullScrollMessage(){
        RoomActivity.scrollView.post(new Runnable() {
            @Override
            public void run() {
                RoomActivity.scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public static void addLabelMessage(String messageText, int gravity, int idBox){
        //creo la textview da aggiungere al layout
        TextView lblMyMessage=new TextView(Display.context);
        //setto il testo della textView
        lblMyMessage.setText(messageText);
        //setto il colore del testo dei messaggi
        lblMyMessage.setTextColor(Color.WHITE);
        //setto il background del messaggio
        lblMyMessage.setBackgroundResource(idBox);
        //imposto la grandezza del testo(per adesso temporanea in futuro bisignerà ridimensionarla in base alle dimensioni dello schermo
        lblMyMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        //imposto il padding del messaggio
        lblMyMessage.setPadding(Display.PixelToDp(25),Display.PixelToDp(10),Display.PixelToDp(25),Display.PixelToDp(10));
        //creo il layoutParams che poi andro ad impostare per la lblMyMessage
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        //imposto il gravity della textView(destra o sinistra)
        layoutParams.gravity=gravity;
        //setto i margini delle label in base al gravity
        if (gravity==Gravity.RIGHT)
            layoutParams.setMargins(Display.PixelToDp(115),Display.PixelToDp(10),Display.PixelToDp(10),0);
        else
            layoutParams.setMargins(Display.PixelToDp(10),Display.PixelToDp(10),Display.PixelToDp(115),0);
        //imposto i parametri del layout per la textView
        lblMyMessage.setLayoutParams(layoutParams);
        //imposto la lunghezza massima della textView
        //lblMyMessage.setMaxWidth(Display.windowManager.getDefaultDisplay().getWidth()/2);
        //aggiungo la textView al layout della roomActivity
        RoomActivity.linearLayout.addView(lblMyMessage);
    }
}
