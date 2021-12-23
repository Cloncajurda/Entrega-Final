package com.sweb.rpibot.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author swe
 */
public class ADPSQLHandler extends SQLHandler {

    private static final String DB = "ADP";

    private Connection conn = null;
    private static String table = null;
    private String date = null;
    private String url;

    public ADPSQLHandler() {
    }

    public Map<String, String> getWordAnswer(String txt) {
        super.connectDB(DB);
        String sql = "SELECT * FROM ReactWords";
        Map<String, String> wordMap = new HashMap<String, String>();
        try {
            PreparedStatement pstmt = super.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                wordMap.put(rs.getString("word"), rs.getString("answer"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        super.disconnectDB();
        if (wordMap.isEmpty()) {
            return null;
        } else {
            return wordMap;
        }
    }

    private boolean checkWord(String word) {
        String response = null;
        super.connectDB(DB);
        String sql = "SELECT word FROM ReactWords where word like ?";
        try {
            PreparedStatement pstmt = super.conn.prepareStatement(sql);
            pstmt.setString(1, word);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                response = rs.getString("word");
            }
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        super.disconnectDB();

        return response != null ? true : false;
    }

    public String insertWord(String word, String answer) {
        if (this.checkWord(word) == true) {
            return "word exists, remove first, add later";
        } else {

            super.connectDB(DB);
            String sql = "INSERT INTO ReactWords (word,answer) VALUES (?,?)";
            try {
                PreparedStatement pstmt = super.conn.prepareStatement(sql);
                pstmt.setString(1, word);
                pstmt.setString(2, answer);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
            }
            super.disconnectDB();
            return "added";
        }
    }

    public void removeWord(String word) {
        super.connectDB(DB);
        String sql = "DELETE FROM ReactWords WHERE word = ?";
        try {
            PreparedStatement pstmt = super.conn.prepareStatement(sql);
            pstmt.setString(1, word);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.disconnectDB();
    }

    /*    public void insertNewId(String date, String msgId) {
    
    super.connectDB(DB);
    int highestId = this.getHighestId();
    highestId++;
    String sql = "INSERT INTO thanos (id,date,msgId) VALUES (?,?,?)";
    
    try {
    PreparedStatement pstmt = super.conn.prepareStatement(sql);
    pstmt.setInt(1, highestId);
    pstmt.setString(2, date);
    pstmt.setString(3, msgId);
    pstmt.executeUpdate();
    pstmt.close();
    } catch (SQLException e) {
    Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
    super.disconnectDB();
    }
    }*/

    /*    private int getHighestId() {
    int index = 0;
    String sql = "SELECT MAX(id) AS id FROM thanos";
    try {
    PreparedStatement pstmt = super.conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
    index = rs.getInt("id");
    }
    rs.close();
    pstmt.close();
    
    } catch (SQLException e) {
    Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
    }
    
    return index;
    }*/

    /*    public String[] getMsgIds(String ctrlDate) {
    super.connectDB(DB);
    String sql = "SELECT * FROM thanos WHERE date > ?";
    
    try {
    PreparedStatement pstmt = super.conn.prepareStatement(sql);
    pstmt.setString(1, ctrlDate);
    ResultSet rs = pstmt.executeQuery();
    while (rs.next()) {
    //    index = rs.getInt("id");
    }
    rs.close();
    pstmt.close();
    } catch (SQLException e) {
    Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
    super.disconnectDB();
    }
    
    return null;
    }*/

}
