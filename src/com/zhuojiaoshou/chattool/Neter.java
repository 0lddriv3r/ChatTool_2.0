package com.zhuojiaoshou.chattool;

import java.io.*;
import java.net.Socket;

/**
 * Created by JiajieZhuo on 2017/3/10.
 */
public class Neter {
    private PrintWriter pw;
    private BufferedReader br;

    public Neter(Socket s) {
        try {
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = s.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            pw = new PrintWriter(osw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        try {
            pw.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        String message = "";
        try {
            message = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
