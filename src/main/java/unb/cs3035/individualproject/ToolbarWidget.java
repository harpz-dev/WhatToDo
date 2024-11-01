package unb.cs3035.individualproject;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolbarWidget extends ToolBar {
    public Button addButton, darkModeButton, switchViewButton, archiveButton;
    public Image addImg, nightImg, brightImg, editImg, listImg, historyImg;

    public ToolbarWidget() {

        //loading images
        addImg = new Image(getClass().getClassLoader().getResourceAsStream("more.png"));
        nightImg = new Image(getClass().getClassLoader().getResourceAsStream("night-mode.png"));
        listImg = new Image(getClass().getClassLoader().getResourceAsStream("list.png"));
        editImg= new Image(getClass().getClassLoader().getResourceAsStream("edit.png"));
        brightImg = new Image(getClass().getClassLoader().getResourceAsStream("brightness.png"));
        historyImg= new Image(getClass().getClassLoader().getResourceAsStream("history.png"));

        //Creating Buttons for cut/paste
        addButton = new Button("", new ImageView(addImg));
        darkModeButton = new Button("", new ImageView(nightImg));
        darkModeButton.setAlignment(Pos.CENTER_RIGHT);
        switchViewButton = new Button("", new ImageView(listImg));
        switchViewButton.setAlignment(Pos.CENTER_RIGHT);
        archiveButton= new Button("", new ImageView(historyImg));

        //Adding tooltips for cut/paste
        final Tooltip addTip = new Tooltip();
        addTip.setText("Add");
        addButton.setTooltip(addTip);

        final Tooltip darkModeTip = new Tooltip();
        darkModeTip.setText("Dark/Light Mode");
        darkModeButton.setTooltip(darkModeTip);

        final Tooltip viewModeTip = new Tooltip();
        viewModeTip.setText("Switch between kanban and list view");
        switchViewButton.setTooltip(viewModeTip);

        //Add ToggleButtons to the toolbar

        //Populating toolbar
        this.getItems().addAll(addButton, switchViewButton, archiveButton, new Separator(), new Separator(), darkModeButton);

    }

    public void switchView(int i){
        switch (i){
            case 0: switchViewButton.setGraphic(new ImageView(editImg)); break;
            case 1: switchViewButton.setGraphic(new ImageView(listImg));break;
        }
    }

    public void switchDarkMode(int i){
        switch(i){
            case 0: darkModeButton.setGraphic(new ImageView(nightImg)); break;
            case 1: { darkModeButton.setGraphic(new ImageView(brightImg));
                    addButton.setStyle("-fx-base:white");
                    darkModeButton.setStyle("-fx-base:white");
                    switchViewButton.setStyle("-fx-base:white");
                    archiveButton.setStyle("-fx-base:white");
                    break;}
        }
    }

}