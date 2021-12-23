/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sweb.rpibot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swe
 */
public class SQLHandler {

    Connection conn = null;
    String url;

    String DB;

    public SQLHandler() {
    }

    public void connectDB(String db) {
        if (this.getSystem()) {
            this.url = "jdbc:sqlite:/home/pi/Dev/DB/";
        } else {
            this.url = "jdbc:sqlite:/home/swe/Code/sqlite/";
        }
        switch (db) {
            case "ADP":
                url = this.url + "ADP.db";
                break;
            case "CM":
                url = this.url + "Carucha.db";
                break;
            case "USR":
                url = this.url + "USR.db";
                break;
            case "CSH":
                url = this.url + "USR.db";
                break;
            default:
                System.out.println("--No DB connection");
                break;
        }

        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(url);
    }

    public void disconnectDB() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private boolean getSystem() {
        /* Properties props = System.getProperties();
        props.list(System.out);*/
        String usrHome = System.getProperty("user.dir");
        if (usrHome.contains("pi")) {
            return true;
        } else {
            return false;
        }
    }

    String getDB() {
        return DB;
    }

    void setDB(String DB) {
        this.DB = DB;
    }

}
