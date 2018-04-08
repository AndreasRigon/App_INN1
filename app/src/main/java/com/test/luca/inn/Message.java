package com.test.luca.inn;

/**
 * Created by Luca on 08/03/2018.
 */

public class Message {

    private String text;
    private String ipClient;

    public Message(String text, String ipClient){
        this.text=text;
        this.ipClient=ipClient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }
}
