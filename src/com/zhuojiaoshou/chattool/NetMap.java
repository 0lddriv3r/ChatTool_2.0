package com.zhuojiaoshou.chattool;


import java.util.HashMap;

/**
 * Created by JiajieZhuo on 2017/3/10.
 */
public class NetMap<K, V> extends HashMap<String, Neter> {
    public void send(String message) {
        try {
            for (Neter n : this.values()) {
                n.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String user, String message) {
        try {
            Neter n = this.get(user);
            n.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
