package unb.cs3035.individualproject;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static SimpleStringProperty inProgressBgStyle = new SimpleStringProperty("-fx-border-color: black; -fx-background-color: ivory ;");
    public static SimpleStringProperty todoBgStyle = new SimpleStringProperty("-fx-border-color: black; -fx-background-color: cyan;");
    public static SimpleStringProperty inProgressStyle = new SimpleStringProperty("-fx-border-color: black; -fx-background-color: palegreen;");
    public static SimpleStringProperty todoStyle = new SimpleStringProperty("-fx-border-color: black; -fx-background-color: palevioletred;");
    public static Model model= new Model();
    public static KanbanView kanbanView= new KanbanView();
    public static TaskListView listView= new TaskListView();
    public static View view= new View();
    public static KanbanController kanbanController= new KanbanController();
    public static Controller controller= new Controller();

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root= new StackPane();

        Scene sc= new Scene(root, 1024, 768);

        launchSplashScreen(root);

        stage.setScene(sc);
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("pexels-ann-h.jpg")));
        stage.setTitle("WhatToDo");
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {

                if (controller.aboutWindow!=null && controller.aboutWindow.isShowing()) {
                    controller.aboutWindow.close();
                }
                if (controller.helpWindow!=null &&controller.helpWindow.isShowing()) {
                    controller.helpWindow.close();
                }
                if (controller.archiveWindow!=null &&controller.archiveWindow.isShowing()) {
                    controller.archiveWindow.close();
                }
                if (controller.editWindow!=null &&controller.editWindow.isShowing()) {
                    controller.editWindow.close();
                }

        });

    }

    private void launchSplashScreen(StackPane root){
        try {
            //Load splash screen view FXML
            StackPane pane = new StackPane();
            Image bgImg= new Image(getClass().getClassLoader().getResourceAsStream("pexels-ann-h.jpg"));
            ImageView bgImgView= new ImageView(bgImg);
            bgImgView.resize(1024, 768);
            pane.getChildren().add(bgImgView);


            //Add it to root container (Can be StackPane, AnchorPane etc)
            root.getChildren().setAll(pane);

            //Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            //Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            //After fade in, start fade out
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            //After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                root.getChildren().setAll(view);
            });
        } catch (Exception ex) {
            // Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
