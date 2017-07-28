package newtournoi;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;

public class Equipe {
    private final SimpleIntegerProperty N;
    private final SimpleStringProperty NomEquipe;
    private final SimpleIntegerProperty mje;
    private final SimpleIntegerProperty mge;
    private final SimpleIntegerProperty mne;
    private final SimpleIntegerProperty mpe;
    private final SimpleIntegerProperty bpe;
    private final SimpleIntegerProperty bce;
    private final SimpleIntegerProperty dfb;
    private final SimpleIntegerProperty point;



    public Equipe(int N, String NomEquipe, int mje, int mge, int mne, int mpe, int bpe, int bce,int dfb ,int point) {
        this.N = new SimpleIntegerProperty(N) ;
        this.NomEquipe = new SimpleStringProperty(NomEquipe);
        this.mje = new SimpleIntegerProperty(mje) ;
        this.mge = new SimpleIntegerProperty(mge) ;
        this.mne = new SimpleIntegerProperty(mne) ;
        this.mpe = new SimpleIntegerProperty(mpe) ;
        this.bpe = new SimpleIntegerProperty(bpe) ;
        this.bce = new SimpleIntegerProperty(bce) ;
        this.dfb = new SimpleIntegerProperty(dfb) ;
        this.point = new SimpleIntegerProperty(point) ;
    }

    public int getN() {
        return N.get();
    }

    public String getNomEquipe() {
        return NomEquipe.get();
    }

    public int getMje() {
        return mje.get();
    }

    public int getMge() {
        return mge.get();
    }

    public int getMne() {
        return mne.get();
    }

    public int getMpe() {
        return mpe.get();
    }

    public int getBpe() {
        return bpe.get();
    }

    public int getBce() {
        return bce.get();
    }

    public int getDfb() {
        return dfb.get();
    }
    

    public int getPoint() {
        return point.get();
    }
   




}