package unb.cs3035.individualproject;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AboutWindow extends Stage {

    public Image logoImg;
    public AboutWindow(){
        Text aboutText= new Text("WhatToDo: A todo application written in JavaFx\n\n\n\n Authored by: H.");
        logoImg = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"));
        ImageView logView= new ImageView(logoImg);
        FlowPane fp= new FlowPane(logView, aboutText);



        Scene sc= new Scene(fp, 290, 144);
        this.setScene(sc);
        this.setTitle("About this application");
        this.show();

    }
}
