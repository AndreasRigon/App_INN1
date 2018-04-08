package com.test.luca.inn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imposto il context
        Display.context=getApplicationContext();

        //imposto la WindowManager nella classe statica Display
        Display.windowManager=getWindowManager();

        //creo l'evento click per il btnNuovaStanza
        findViewById(R.id.btnNuovaStanza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //disabilito il btnNuovaStanza per evitare che l'utente lo prema più volte
                findViewById(R.id.btnNuovaStanza).setEnabled(false);
                //creo ed avvio l'AsynkTask per la creazione della nuova stanza al suo termine creo la nuova activity
                new NewRoom(MainActivity.this).execute();
            }
        });

        //tmp creo l'evento per il btn che mi fa accedere ad una stanza
        findViewById(R.id.btnAccediStanza).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LogInRoom(MainActivity.this).execute("Stanza1");
            }
        });

    }
}
