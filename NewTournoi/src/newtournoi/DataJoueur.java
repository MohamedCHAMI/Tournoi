/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtournoi;

/**
 *
 * @author MCCrime
 */
public class DataJoueur {
    private int idj;
    private String namej;
    private String logoj;

    public DataJoueur(int idj, String namej, String logoj) {
        this.idj = idj;
        this.namej = namej;
        this.logoj = logoj;
    }

    public DataJoueur() {
    }

    public int getIdj() {
        return idj;
    }

    public void setIdj(int idj) {
        this.idj = idj;
    }

    public String getNamej() {
        return namej;
    }

    public void setNamej(String namej) {
        this.namej = namej;
    }

    public String getLogoj() {
        return logoj;
    }

    public void setLogoj(String logoj) {
        this.logoj = logoj;
    }
    
    
    
}
