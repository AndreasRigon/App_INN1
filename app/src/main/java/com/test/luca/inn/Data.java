package com.test.luca.inn;

import android.content.Context;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Luca on 01/03/2018.
 */

public class Data {

    public static final String IPSERVER="192.168.43.96";
    public static volatile String host;
    public static volatile ArrayList<Message> lstMessages=new ArrayList<>();

}