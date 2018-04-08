package com.test.luca.inn;

import android.content.Context;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Luca on 02/03/2018.
 */

public class Display {

    public static volatile Context context;

    public static volatile WindowManager windowManager;

    public static void makeToast(String testo, int toastLength){
        Toast.makeText(context,testo,toastLength).show();
    }

    public static int PixelToDp(int px){
        return Math.round(context.getResources().getDisplayMetrics().density*px);
    }

}
