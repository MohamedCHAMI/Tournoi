/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtournoi;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MCCrime
 */
public class Joueur {

    public SimpleIntegerProperty Id = new SimpleIntegerProperty();
    public ObjectProperty Photo = new SimpleObjectProperty();
    public SimpleStringProperty Name = new SimpleStringProperty();

    public Integer getUserId() {
      return Id.get();
   }
    public Object getUserPhoto() {
      return Photo.get();
   }
    public String getUserType() {
      return Name.get();
   }
}
