package unb.cs3035.individualproject;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TaskListView extends VBox {

    public ListView<String> toDoListView, inProgressListView;

    public SimpleStringProperty todoLabelStyle, inProgressLabelStyle;
    public TaskListView(){
        VBox todoSub= new VBox();
        Label todoLabel= new Label("TASKS TO DO");
        todoLabelStyle= new SimpleStringProperty(/*"-fx-border-color: black; -fx-background-color: moccasin;"*/);

        VBox inProgressSub= new VBox();
        Label inProgressLabel= new Label ("TASKS IN PROGRESS");
        inProgressLabelStyle= new SimpleStringProperty(/*"-fx-border-color: black; -fx-background-color: cyan;"*/);

        toDoListView= new ListView<>();

        inProgressListView = new ListView<>();
        Main.model.toDoList.addListener(new ChangeListener<ObservableList<Card>>() {
            @Override //todo does this update when a card's text is changed?
            public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {
                draw();

            }
        });
        Main.model.inProgressList.addListener(new ChangeListener<ObservableList<Card>>() {
            @Override //todo does this update when a card's text is changed?
            public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {
                draw();

            }
        });
        todoSub.getChildren().add(todoLabel);
        todoSub.getChildren().add(toDoListView);
        todoSub.setStyle(todoLabelStyle.getValue());
        todoSub.setPrefSize(600, 400);

        inProgressSub.getChildren().add(inProgressLabel);
        inProgressSub.getChildren().add(inProgressListView);
        inProgressSub.setStyle(inProgressLabelStyle.getValue());
        inProgressSub.setPrefSize(600, 400);

        this.getChildren().add(todoSub);
        this.getChildren().add(inProgressSub);


        todoLabelStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                todoLabel.setStyle(todoLabelStyle.getValue());
            }
        });

        inProgressLabelStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                inProgressLabel.setStyle(inProgressLabelStyle.getValue());
            }
        });
    }


    private void draw(){
        toDoListView.getItems().clear();
        inProgressListView.getItems().clear();

        List<String> list= new ArrayList<>();
        for(Card c: Main.model.toDoList){
            list.add(c.cardTextProperty().getValue());
        }

        toDoListView.getItems().addAll(list);

        list= new ArrayList<>();
        for(Card c: Main.model.inProgressList){
            list.add(c.cardTextProperty().getValue());
        }
        inProgressListView.getItems().addAll(list);
    }
}
