package unb.cs3035.individualproject;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class KanbanController {

    public KanbanController(){

        Main.kanbanView.todoBox.getChildren().addListener(
                new ListChangeListener<Node>() {
                    @Override
                    public void onChanged(Change<? extends Node> change) {
                        setCardHandlers();
                        setCardDropHandlers();
                    }
                }
        );
        Main.kanbanView.inProgressBox.getChildren().addListener(
                new ListChangeListener<Node>() {
                    @Override
                    public void onChanged(Change<? extends Node> change) {
                        setCardHandlers();
                        setCardDropHandlers();
                    }
                }
        );


        setCardHandlers();
        setDragAndDropHandlers();
    }


    public void setCardHandlers() {

        VBox todoBox = Main.kanbanView.todoBox;
        VBox inProgressBox = Main.kanbanView.inProgressBox;

        for (Node node : todoBox.getChildren()) {

            CardWidget card = (CardWidget) node;

            card.setOnDragDetected(event -> {
                Dragboard dragboard = card.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putString(card.getCardId());
                dragboard.setContent(content);

                double offsetX = event.getX();
                double offsetY = event.getY();

                // Set a drag view to make the dragged card semi-transparent
                card.setOpacity(0.7);

                // Update the position of the dragged card as the mouse moves
                card.setOnDragDone(e -> {
                    card.setOpacity(1.0);

                });
                card.setOnDragDone(e -> {
                    card.setOpacity(1.0);
                });


                card.setOnMouseDragged(e -> {
                    card.setLayoutX(e.getSceneX() - offsetX);
                    card.setLayoutY(e.getSceneY() - offsetY);
                });

                event.consume();
            });

            card.setOnMouseClicked(mouseEvent -> {
                EditWindow ew= Main.controller.openEditWindow(Main.model.getTodoCardWithId(card.getCardId()));
                ew.setTitle("Edit Card");
            }); //Remember to set for nodes belonging to both boxes


        }

        for (Node node : inProgressBox.getChildren()) {

            CardWidget card = (CardWidget) node;

            card.setOnDragDetected(event -> {
                Dragboard dragboard = card.startDragAndDrop(TransferMode.ANY);

                ClipboardContent content = new ClipboardContent();
                content.putString(card.getCardId());
                dragboard.setContent(content);

                double offsetX = event.getX();
                double offsetY = event.getY();

                // Set a drag view to make the dragged card semi-transparent
                card.setOpacity(0.7);

                // Update the position of the dragged card as the mouse moves
                card.setOnDragDone(e -> {
                    card.setOpacity(1.0);
                });
                card.setOnDragDone(e -> {
                    card.setOpacity(1.0);
                });


                card.setOnMouseDragged(e -> {
                    card.setLayoutX(e.getSceneX() - offsetX);
                    card.setLayoutY(e.getSceneY() - offsetY);
                });

                event.consume();
            });

            card.setOnMouseClicked(mouseEvent -> {
                EditWindow ew= Main.controller.openEditWindow(Main.model.getInProgressCardWithId(card.getCardId()));
                ew.setTitle("Edit Card");
            }); //Remember to set for nodes belonging to both boxes


        }
    }


    private void setDragAndDropHandlers(){
        VBox todoBox = Main.kanbanView.todoBox;
        VBox inProgressBox = Main.kanbanView.inProgressBox;
        inProgressBox.setOnDragOver(event -> {

            if (event.getGestureSource() != inProgressBox && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        inProgressBox.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                String cardId = dragboard.getString();
                Card targetCard= Main.model.getTodoCardWithId(cardId);
                if(targetCard==null){
                    event.consume();
                    return;
                }

                Main.model.inProgressList.add(targetCard);
                Main.model.deleteTodoCard(cardId);


            }

            event.setDropCompleted(success);
            event.consume();

            setCardHandlers();
        });

        todoBox.setOnDragOver(event -> {

            if (event.getGestureSource() != todoBox && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });

        todoBox.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                String cardId = dragboard.getString();
                Card targetCard= Main.model.getInProgressCardWithId(cardId);
                if(targetCard==null){
                    event.consume();
                    return;
                }

                Main.model.toDoList.add(targetCard);
                Main.model.deleteInProgressCard(cardId);


            }

            event.setDropCompleted(success);
            event.consume();

            setCardHandlers();
        });

    }

    public void setCardDropHandlers(){
        VBox inProgressBox= Main.kanbanView.inProgressBox;
        VBox todoBox= Main.kanbanView.todoBox;

        //setting card handlers for inProgressBox
        for(Node n: Main.kanbanView.inProgressBox.getChildren()) {
            CardWidget cw = (CardWidget) n;

            cw.setOnDragDropped(event -> {
                Dragboard dragboard = event.getDragboard();
                boolean success = false;

                CardWidget draggedON= (CardWidget)event.getSource();
                int index= Main.model.inProgressList.indexOf(Main.model.getInProgressCardWithId(draggedON.getCardId()));

                if (dragboard.hasString()) {
                    String cardId = dragboard.getString();
                    Card targetCard= Main.model.getTodoCardWithId(cardId);
                    if(targetCard==null){
                        targetCard= Main.model.getInProgressCardWithId(cardId);
                        if(targetCard==null){
                            event.consume();
                            return;
                        }
                    }

                    Main.model.deleteInProgressCard(cardId);//remove from both (so that you don't have to worry abt where the dragged card is from)
                    Main.model.deleteTodoCard(cardId);
                    Main.model.inProgressList.add(index, targetCard);


                }

                event.setDropCompleted(success);
                event.consume();


            });
        }

        //Setting handlers for todoBox children
        for(Node n: Main.kanbanView.todoBox.getChildren()) {
            CardWidget cw = (CardWidget) n;

            cw.setOnDragDropped(event -> {
                Dragboard dragboard = event.getDragboard();
                boolean success = false;

                CardWidget draggedON= (CardWidget)event.getSource();
                int index= Main.model.toDoList.indexOf(Main.model.getTodoCardWithId(draggedON.getCardId()));//find id of card that any card was dragged on

                //find the card that is currently being dragged
                if (dragboard.hasString()) {
                    String cardId = dragboard.getString();
                    Card targetCard= Main.model.getTodoCardWithId(cardId);
                    if(targetCard==null){
                        targetCard= Main.model.getInProgressCardWithId(cardId);
                        if(targetCard==null){
                            event.consume();
                            System.out.println("Null issue"); //if nothing found (unusual circumstance, just discard and return)
                            return;
                        }
                    }

                    Main.model.deleteInProgressCard(cardId);//remove from both (so that you don't have to worry abt where the dragged card is from)
                    Main.model.deleteTodoCard(cardId);
                    Main.model.toDoList.add(index, targetCard);

                }

                event.setDropCompleted(success);
                event.consume();


            });
        }

    }



}
