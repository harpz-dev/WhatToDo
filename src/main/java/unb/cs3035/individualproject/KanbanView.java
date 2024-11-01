package unb.cs3035.individualproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class KanbanView extends Pane {

    public HBox root; //Perhaps this shouldn't be the root
    public VBox todoBox, inProgressBox;
    public ScrollPane todoBoxScroll, inProgressBoxScroll;
    private int col; //number of columns (2 boxes-> to-do and inprogress)

    public static double maxCardHeight=0;
    public KanbanView(){

        col=2;

        this.setHeight(this.getBoundsInParent().getHeight());
        this.setWidth(this.getBoundsInParent().getWidth());

        todoBox= new VBox();
        todoBox.setSpacing(10);
        todoBoxScroll= new ScrollPane(todoBox);

        todoBox.setPadding(new Insets(10, 10, 10, 10));


        inProgressBox= new VBox();
        inProgressBox.setSpacing(10);
        inProgressBoxScroll= new ScrollPane(inProgressBox);

        inProgressBox.setPadding(new Insets(10, 10, 10, 10));


        todoBox.setStyle(Main.todoBgStyle.getValue()); //todo IMPORTANT, PARAMETERIZE THIS FOR DARK MODE
        inProgressBox.setStyle(Main.inProgressBgStyle.getValue());//todo IMPORTANT, PARAMETERIZE THIS FOR DARK MODE


        inProgressBox.setMinSize(100, 50); //Todo replace with card size (get rid of magic numbers)
        todoBox.setMinSize(100, 50); //Todo replace with card size (get rid of magic numbers)

        inProgressBoxScroll.setMinSize(100, 50); //Todo replace with card size (get rid of magic numbers)
        todoBoxScroll.setMinSize(100, 50); //Todo replace with card size (get rid of magic numbers)

        VBox todoLabelCont, inProgLabelCont;
        Label todoLabel= new Label("TO-DO");
        todoLabelCont= new VBox(todoLabel, todoBoxScroll);
        todoLabelCont.setAlignment(Pos.CENTER);

        Label inProgLabel= new Label("IN-PROGRESS");
        inProgLabelCont= new VBox(inProgLabel, inProgressBoxScroll);
        inProgLabelCont.setAlignment(Pos.CENTER);

        root= new HBox(todoLabelCont, inProgLabelCont);

        this.getChildren().add(root);

        Main.model.inProgressList.addListener(new ChangeListener<ObservableList<Card>>() {
            @Override //todo does this update when a card's text is changed?
            public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {
                draw();

            }
        });

        Main.model.toDoList.addListener(new ChangeListener<ObservableList<Card>>() {
            @Override //todo does this update when a card's text is changed?
            public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {
                draw();

            }
        });

        //Handle when dark mode is toggled
        Main.todoStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                draw();
            }
        });

        Main.inProgressStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                draw();
                System.out.println("This is called");
            }
        });

        Main.todoBgStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                draw();

            }
        });

        Main.inProgressBgStyle.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                draw();
                layoutChildren();
            }
        });


    }

    @Override
    public void layoutChildren(){
        todoBox.setMinSize(this.getWidth()/col, this.getHeight());
        inProgressBox.setMinSize(this.getWidth()/col, this.getHeight());
        todoBox.setMaxSize(this.getWidth()/col, this.getHeight());
        inProgressBox.setMaxSize(this.getWidth()/col, this.getHeight());

        todoBoxScroll.setMinSize(this.getWidth()/col, this.getHeight());
        inProgressBoxScroll.setMinSize(this.getWidth()/col, this.getHeight());

    }

    public void draw(){
        inProgressBox.getChildren().clear();
        inProgressBox.setStyle(Main.inProgressBgStyle.getValue());
        inProgressBox.requestLayout();



        todoBox.getChildren().clear();
        todoBox.setStyle(Main.todoBgStyle.getValue());
        todoBox.requestLayout();

        for(Card c: Main.model.inProgressList){
            CardWidget cw= new CardWidget(c, Main.inProgressStyle.getValue());
            inProgressBox.getChildren().add(cw);
            cw.toFront();//todo remove if problem

            maxCardHeight=0;

        }

        for(Card c: Main.model.toDoList){
            CardWidget cw= new CardWidget(c, Main.todoStyle.getValue());
            todoBox.getChildren().add(cw);
            cw.toFront();//todo remove if problem
        }

    }

}
