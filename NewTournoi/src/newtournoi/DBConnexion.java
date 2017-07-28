package newtournoi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Image image = new Image("C://Users//Windows 10//Pictures//teams//1.jpg");
        imgT.setImage(image);





for (int i = 0; i < ls.size(); i++) {
            if (eq1.equals(ls.get(i).getName())) {
                logoeq1 = ls.get(i).getLogon();
                Image image = new Image(logoeq1);
                imageV1.setImage(image);
            }}

        DBConnexion db = new DBConnexion("", ""); // user pass of Database
        String sql = "SELECT * FROM Equipe";

        try {
            db.connect();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {

                DataEquipe de = new DataEquipe(rs.getInt(0), rs.getString(1), rs.getString(2));
                ls.add(de);
            }
            rs.close();
            db.closeConnection();

        } catch (SQLException sqli) {
        } catch (Exception e) {
        }
        */

public class DBConnexion{
final String DB_PATH = "jdbc:ucanaccess://C:\\Tournoi\\DonneeEquipeJoueur.accdb ; memory=false";
String username = null ;
String password = null ;
Connection conn = null ;
Statement st = null ;
public DBConnexion(String name, String pass){
username = name ;
password = pass ;
}
public Connection getConnection(){
return conn;}

public void con(String sqlq3){
    try {
             st.execute(sqlq3);
        } catch (SQLException ex) {
            
        }
}

public void connect()throws SQLException ,Exception{
    
conn = DriverManager.getConnection(DB_PATH,username,password);
st = conn.createStatement();
}

public void closeConnection() throws SQLException , Exception{
st.close();
conn.close();
}

public Statement getStatement(){
return st;}

         public ArrayList<Equipe> BindTable() throws Exception{
        
   ArrayList<Equipe> list = new ArrayList<Equipe>();

DBConnexion db = new DBConnexion("", ""); // user pass of Database
        String sql = "SELECT * FROM Equipe";

        try {
            db.connect();
            ResultSet rs = db.getStatement().executeQuery(sql);
            System.out.println("Connected List Equipes");
   Equipe e;
   while(rs.next()){
   

   
 
   }
   
   } catch (SQLException ex) {
   
   }
   return list;
         } 


}