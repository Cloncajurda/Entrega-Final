package com.sweb.rpibot.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author swe
 */
public class USRSQLHandler extends SQLHandler {

        public void checkUsrInsert(org.telegram.telegrambots.meta.api.objects.User usr, String bot) {
        if (this.checkUsr(usr.getId().toString())) {
            this.insertUsr(usr, bot);
        }
    }

    private boolean checkUsr(String tId) {
        String response = null;
        super.connectDB(DB);
        String sql = "SELECT * FROM Usr";
        try {
            PreparedStatement pstmt = super.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                response = rs.getString("tId");
            }
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        super.disconnectDB();

        return response == null ? true : false;
    }

    public void insertUsr(User usr, String bot) {
        super.connectDB(DB);
        String sql = "INSERT INTO Usr (tID,first_name,last_name,username,bot) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pstmt = super.conn.prepareStatement(sql);
            pstmt.setString(1, usr.getId().toString());
            pstmt.setString(2, usr.getFirstName());
            pstmt.setString(3, usr.getLastName());
            pstmt.setString(4, usr.getUserName());
            pstmt.setString(5, bot);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            Logger.getLogger(ADPSQLHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        super.disconnectDB();
    }

}
