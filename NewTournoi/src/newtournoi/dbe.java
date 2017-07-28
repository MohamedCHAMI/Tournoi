package newtournoi;

import com.healthmarketscience.jackcess.query.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MCCrime
 */
public class dbe {
        
   public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://C:\\DonneeEquipeJoueur.accdb", "","");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
   
   
    public ArrayList<DataEquipe> BindTable(){
        
   ArrayList<DataEquipe> list = new ArrayList<DataEquipe>();
   Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   try {
   st = con.createStatement();
   rs = st.executeQuery("SELECT * FROM `Equipe`");
   
   DataEquipe d;
   while(rs.next()){


   }
   
   } catch (SQLException ex) {
   }
   return list;
   }
}
