package com.zhuojiaoshou.chattool;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by JiajieZhuo on 2017/3/10.
 */
public class Server {
    public static void main(String[] args) {
        try {
            NetMap<String, Neter> hm = new NetMap<>();

            ServerSocket ss = new ServerSocket(8000);

            while (true) {
                System.out.println("Listening at 8000 port...");
                Socket s = ss.accept();
                Neter net = new Neter(s);

                Service t = new Service();
                t.setNeter(net);
                t.setHashMap(hm);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Service extends Thread {
    private Neter net;
    private  NetMap<String, Neter> hm;

    public void setHashMap(NetMap<String, Neter> hm) {
        this.hm = hm;
    }

    public void setNeter(Neter net) {
        this.net = net;
    }

    @Override
    public void run() {
        try {
            String user_pass = net.receive();

            String user = user_pass.split("%%%")[0];
            String pass = user_pass.split("%%%")[0];

            SQLer sql = new SQLer();

            if (sql.vali(user, pass)) {
                net.send("OK");

                hm.send("add%" + user);

                for (String tu : hm.keySet()) {
                    net.send("add%" + tu);
                }

                hm.put(user, net);

                while (true) {
                    String message = net.receive();

                    if (message.equals("{exit}")) {
                        hm.remove(user);
                        hm.send("exit" + user);
                        return;
                    }

                    String to = message.split("%")[0];
                    String mess = message.split("%")[1];
                    hm.send(to, "mess" + mess);
                }
            } else {
                net.send("ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
