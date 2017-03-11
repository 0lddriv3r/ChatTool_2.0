package com.zhuojiaoshou.chattool;

import java.io.*;
import java.sql.*;

/**
 * Created by JiajieZhuo on 2017/3/10.
 */
public class SQLer {
    public boolean vali(String user, String pass) {
        boolean b = false;
        try {
            File f = new File("SQL.init");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String driver = br.readLine();
            String url = br.readLine();
            String u = br.readLine();
            String p = br.readLine();

            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, u, p);
            PreparedStatement ps = cn.prepareStatement("select * from userwhere = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            b = rs.next();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
}
