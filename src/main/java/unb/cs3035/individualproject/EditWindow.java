package unb.cs3035.individualproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditWindow extends Stage {

    public TextArea tf;
    public Button okButton, deleteButton;
    public ComboBox<String> comboBox;

    private Card c;
    public EditWindow(Card c){
        tf= new TextArea(c.cardTextProperty().getValue());
        tf.setMinSize(200, 300);
        tf.setWrapText(true);
        this.c= c;

        okButton= new Button("APPLY");
        okButton.setOnMouseClicked(mouseEvent -> {
            updateInfo();
            close();

        });


        deleteButton = new Button("DELETE");
        deleteButton.setOnMouseClicked(mouseEvent -> {
            boolean wasInList= false;
            if(Main.model.inProgressList.contains(c)||Main.model.toDoList.contains(c)){
                Main.model.archiveCard(c);
            }

            Main.model.inProgressList.remove(c);
            Main.model.toDoList.remove(c);
            close();
        });

        comboBox= new ComboBox<String>();
        comboBox.getItems().add("To-do");
        comboBox.getItems().add("In Progress");
        comboBox.getSelectionModel().select(0);



        BorderPane bp= new BorderPane();
        bp.setCenter(tf);


        HBox hb= new HBox(okButton, deleteButton, comboBox);
        bp.setBottom(hb);

        Scene sc= new Scene(bp, 720, 350);
        this.setScene(sc);
        this.show();
    }

    public void updateInfo(){
        c.setText(tf.getText());

        //Remove the card from both(if it is in either) and add it to whichever is selected
        Main.model.toDoList.remove(c);
        Main.model.inProgressList.remove(c);

        if(comboBox.getSelectionModel().isSelected(0)){
            Main.model.toDoList.add(c);
        }
        else if(comboBox.getSelectionModel().isSelected(1)){
            Main.model.inProgressList.add(c);
        }

    }

    public void editCard(Card cardIn){
        System.out.println("This changes");
        c= cardIn;
        tf.setText(c.cardTextProperty().getValue());
    }
}
