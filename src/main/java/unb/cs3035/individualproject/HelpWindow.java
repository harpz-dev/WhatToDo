package unb.cs3035.individualproject;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpWindow extends Stage {
    public HelpWindow(){

        Text helpText= new Text("Quick Help Tips\n\n1. Click the + icon on the toolbar to add a card. While adding you may choose which category to add it to (ie. to-do or in-progress).\n"
                +"2. In edit (Kanban) view, you can drag cards around to your liking in accordance with priority etc. You can click on any card to edit its properties.\n"
                +"3. If you need to delete a card, you can do so by clicking on it and hitting the delete button in the pop up window.\n4. Deleted items are moved into an archive which you can view anytime by clicking the archive button on the toolbar");

        FlowPane fp= new FlowPane(helpText);
        Scene sc= new Scene(fp, 800, 248);
        this.setScene(sc);
        this.show();

    }
}
