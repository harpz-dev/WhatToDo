package unb.cs3035.individualproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class View extends BorderPane{

    public MenuBar menuBar;
    public ToolbarWidget tb;
    public enum ViewState{Kanban, List};
    public enum DarkMode{ON, OFF};
    public DarkMode dm;
    public ViewState state;

    public MenuItem addMItem, kanbanMItem, helpMItem, aboutMItem;
    public View(){

        menuBar = new MenuBar();

        Menu menuFile= new Menu("File");
        Menu menuView = new Menu("View");
        Menu menuHelp = new Menu("Help");

        menuBar.getMenus().addAll(menuFile, menuView, menuHelp);

        addMItem= new MenuItem("Add note");
        kanbanMItem= new MenuItem("Kanban View (edit)");
        helpMItem= new MenuItem("Help");
        aboutMItem= new MenuItem("About");

        menuFile.getItems().add(addMItem);
        menuView.getItems().add(kanbanMItem);
        menuHelp.getItems().add(helpMItem);
        menuHelp.getItems().add(aboutMItem);


        tb= new ToolbarWidget();
        state= ViewState.Kanban;
        Main.todoBgStyle = new SimpleStringProperty();
        Main.inProgressBgStyle = new SimpleStringProperty();
        Main.todoBgStyle.set("-fx-border-color: black; -fx-background-color: moccasin;");
        Main.inProgressBgStyle.set("-fx-border-color: black; -fx-background-color: cyan;");

        dm= DarkMode.OFF;

        VBox vb= new VBox(menuBar, tb);

        this.setTop(vb);
        this.setCenter(Main.kanbanView);
    }

    public void switchView(){
        if(state==ViewState.Kanban){
            state= ViewState.List;
            this.setCenter(Main.listView);
            tb.switchView(0);
        }

        else if(state==ViewState.List){
            state= ViewState.Kanban;
            this.setCenter(Main.kanbanView);
            tb.switchView(1);
        }
    }

    public void switchDarkMode(){
        if(dm==DarkMode.OFF){
            this.getScene().getRoot().setStyle("-fx-base:black");

            //Manage the kanban panes dark mode layout (Parent-> child approach)
            Main.todoBgStyle.set("-fx-border-color: black; -fx-background-color: black;");
            Main.inProgressBgStyle.set("-fx-border-color: black; -fx-background-color: black;");

            Main.todoStyle.set("-fx-border-color: black; -fx-background-color: floralwhite;");
            Main.inProgressStyle.set("-fx-border-color: black; -fx-background-color: floralwhite;");

           // Main.listView.todoLabelStyle.set("-fx-background-color: floralwhite;");
           // Main.listView.inProgressLabelStyle.set("-fx-background-color: floralwhite;");

            //Call the toolbar dark mode helper

            tb.switchDarkMode(1);
            dm= DarkMode.ON;

        }

        else{
            this.getScene().getRoot().setStyle("-fx-base:white");

            Main.todoBgStyle.set("-fx-border-color: black; -fx-background-color: moccasin;");
            Main.inProgressBgStyle.set("-fx-border-color: black; -fx-background-color: cyan;");

            Main.todoStyle.set("-fx-border-color: black; -fx-background-color: palevioletred;");
            Main.inProgressStyle.set("-fx-border-color: black; -fx-background-color: palegreen;");
            tb.switchDarkMode(0);
            dm= DarkMode.OFF;
        }
    }

}
