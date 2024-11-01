package unb.cs3035.individualproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ArchiveWindow extends Stage {
    public ListView<String> archiveList;
    public ArchiveWindow(){
        archiveList= new ListView<>();
        draw();
        VBox vb= new VBox(archiveList);
        Scene sc= new Scene(vb);
        this.setScene(sc);
        this.show();

        Main.model.archiveList.addListener(new ChangeListener<ObservableList<Card>>() {
            @Override //todo does this update when a card's text is changed?
            public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {
                draw();

            }
        });
    }

    public void draw(){
        archiveList.getItems().clear();

        List<String> list= new ArrayList<>();
        for(Card c: Main.model.archiveList){
            list.add(c.cardTextProperty().getValue());
        }

        archiveList.getItems().addAll(list);
    }


}
