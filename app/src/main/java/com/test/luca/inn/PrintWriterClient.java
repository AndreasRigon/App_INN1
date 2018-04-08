package com.test.luca.inn;

import java.io.PrintWriter;

/**
 * Created by Luca on 10/03/2018.
 */

public class PrintWriterClient {

    private PrintWriter printWriter;
    private String ipClient;

    public PrintWriterClient(PrintWriter printWriter, String ipClient) {
        this.printWriter = printWriter;
        this.ipClient = ipClient;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }
}
