package unb.cs3035.individualproject;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class Controller {

    public EditWindow editWindow;
    public ArchiveWindow archiveWindow;
    public HelpWindow helpWindow;
    public AboutWindow aboutWindow;

    public Controller() {

        Main.model.addTodoCard("Pre-populated test card- this will demonstrate the card wrapping and text being visible in the card!!");
        Main.model.addTodoCard("Test Card1");
        Main.model.addTodoCard("Test Card2");
        Main.model.addTodoCard("Test Card3");
        Main.model.addTodoCard("Test Card4");

        setToolBarHandlers();
        setMenuHandlers();

    }

    private void setToolBarHandlers(){
        Main.view.tb.addButton.setOnMouseClicked(mouseEvent -> {
            Card c= new Card();
            openEditWindow(c);

            editWindow.setTitle("New Card");

        });

        Main.view.tb.switchViewButton.setOnMouseClicked(mouseEvent -> {
            Main.view.switchView();
        });

        Main.view.tb.darkModeButton.setOnMouseClicked(mouseEvent -> {
            Main.view.switchDarkMode();
        });

        Main.view.tb.archiveButton.setOnMouseClicked(mouseEvent -> {
            ArchiveWindow aw= openArchiveWindow();
            aw.setTitle("Task History");
        });
    }

    private void setMenuHandlers(){
        Main.view.addMItem.setOnAction(event -> {
            Card c= new Card();
            openEditWindow(c);

            editWindow.setTitle("New Card");
        });

        Main.view.kanbanMItem.setOnAction(event -> {
            Main.view.switchView();
        });

        Main.view.helpMItem.setOnAction(event -> {
            //open help window
            HelpWindow hp= openHelpWindow();
        });

        Main.view.aboutMItem.setOnAction(event -> {
            AboutWindow ab= openAboutWindow();
        });
    }

    //opens (and returns a reference to) the editWindow
    public EditWindow openEditWindow(Card c){
        if(editWindow==null || !editWindow.isShowing()){
            editWindow= new EditWindow(c);
        }
        else{
            editWindow.editCard(c);
        }

        editWindow.requestFocus();

        editWindow.setOnCloseRequest(windowEvent -> {
            editWindow=null;
        });

        return editWindow;
    }


    public ArchiveWindow openArchiveWindow(){
        if(archiveWindow==null || !archiveWindow.isShowing()){
            archiveWindow= new ArchiveWindow();
        }
        archiveWindow.requestFocus();

        archiveWindow.setOnCloseRequest(windowEvent -> {
            archiveWindow=null;
        });

        return archiveWindow;
    }

    public HelpWindow openHelpWindow(){
        if(helpWindow==null||!helpWindow.isShowing()){
            helpWindow= new HelpWindow();
        }
        helpWindow.requestFocus();
        helpWindow.setOnCloseRequest(event->{
            helpWindow=null;
        });

        return helpWindow;
    }

    public AboutWindow openAboutWindow(){
        if(aboutWindow==null||!aboutWindow.isShowing()){
            aboutWindow= new AboutWindow();
        }
        aboutWindow.requestFocus();
        aboutWindow.setOnCloseRequest(event ->{
            aboutWindow=null;
        });

        return aboutWindow;
    }


}
