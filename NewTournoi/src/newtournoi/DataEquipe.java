package newtournoi;


/**
 *
 * @author MCCrime
 */
public class DataEquipe {

    private int id;
    private String name;
    private String logon;

    public DataEquipe( String name, String logon) {
        
        this.name = name;
        this.logon = logon;
    }

    public DataEquipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogon() {
        return logon;
    }

    public void setLogon(String logon) {
        this.logon = logon;
    }
    
    

}
