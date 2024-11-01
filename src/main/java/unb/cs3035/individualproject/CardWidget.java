package unb.cs3035.individualproject;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardWidget extends Pane {

    public final String id;
    public Text cardText;

    public CardWidget(Card c, String style){
        cardText= new Text(0, 10, ""); //todo magic number 10 comes from padding applied to parent vboxes (both of them)

        id= c.getCardID();
        cardText.textProperty().bind(c.cardTextProperty());

        this.getChildren().add(cardText);
        this.setStyle(style);

        this.widthProperty().addListener((obs, oldWidth, newWidth) -> adjustTextWrapping(newWidth.doubleValue()));



    }

    public String getCardId() {
        return id;
    }

    private void adjustTextWrapping(double width) {
        // Adjust wrapping width considering any padding or margins
        cardText.setWrappingWidth(width);
    }


    @Override
    public void layoutChildren(){

        /* DEPRECATED CODE (USEFUL FOR AN IDEA OF HOW THIS WORKS BUT NOTHING ELSE)
        double wrappingWidth = ((VBox) getParent()).getWidth();
        cardText.setWrappingWidth(wrappingWidth);
        */

        double textW = cardText.getLayoutBounds().getWidth();
        double textH= cardText.getLayoutBounds().getHeight();

        double oldW=0, oldH=0;
        cardText.getParent().requestLayout();// NEED THIS FOR THE CARD TO ADJUST ITS LENGTH DEPENDING
        //Max height of each card is set up based on the text bounds within card//todo check if it updates whenever a card is edited in the future after creation
        this.setMinHeight(cardText.getLayoutBounds().getHeight());



        //max width for the text before it starts wrapping (calculated using bounds from parent)
        cardText.setWrappingWidth(textW-oldW);
        oldW= textW;
        oldH= textH;

    }

}
