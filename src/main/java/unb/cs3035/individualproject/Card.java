package unb.cs3035.individualproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card {

    private SimpleStringProperty text;

    private final String id; //unique identifier to link model to graphical representation

    public Card(){
        text= new SimpleStringProperty();

        text.set("New Note");
        id= Integer.toString(this.hashCode());
    }

    public StringProperty cardTextProperty(){
        return text;
    }

    public void setText(String s){
        text.set(s);
    }

    public String getCardID(){
        return id;
    }

}
