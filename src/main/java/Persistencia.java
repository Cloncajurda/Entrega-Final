
import static java.lang.Class.forName;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Persistencia {
    
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    
    public String servidor, basededatos, usuario, clave, ejecutar;
    
    public Connection conectarse (){
        
        try{
        class.forName("com.mysql.jdbc.Driver");
        }
        servidor = "127.0.0.1:3307/";
        basededatos ="Actividad Cac";
        usuario="root";
        clave="";
        
        cn=DriverManager.getConnection("jdbc:mysql://" + servidor + basededatos+ "?autoReconnect=true&useSSL=false", usuario, clave);
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex)
        }
        catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex)
        }
        return cn;
    }
    
    
    public ResultSet ConsultaSQL(String busqueda){
         try {            
            ps= Conectarse().PreparedStatement(busqueda);
            rs= ps.executeQuery();
            rsm=rs.getMetaData();        
        }                       
       catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
        
    }

    private Object Conectarse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
