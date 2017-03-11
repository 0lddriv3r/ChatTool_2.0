package com.zhuojiaoshou.chattool;

import java.io.*;

/**
 * Created by JiajieZhuo on 2017/3/10.
 */
public class Recorder {
    private File f;

    public Recorder() {
        f = new File("ChatHistory.chat");
    }

    public Recorder(String url) {
        f = new File(url);
    }

    public void write(String message) {
        try {
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        String mess = "";

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                mess += br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mess;
    }
}
