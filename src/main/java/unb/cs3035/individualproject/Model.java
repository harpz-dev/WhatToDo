package unb.cs3035.individualproject;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {

    SimpleListProperty <Card> toDoList, inProgressList, archiveList;

    public Model(){
        ArrayList<Card> list1 = new ArrayList<Card>();
        ObservableList<Card> observableList1 = (ObservableList<Card>) FXCollections.observableArrayList(list1);
        toDoList= new SimpleListProperty<Card>(observableList1);

        ArrayList<Card> list2 = new ArrayList<Card>();
        ObservableList<Card> observableList2 = (ObservableList<Card>) FXCollections.observableArrayList(list2);
        inProgressList= new SimpleListProperty<Card>(observableList2);

        ArrayList<Card> list3 = new ArrayList<Card>();
        ObservableList<Card> observableList3 = (ObservableList<Card>) FXCollections.observableArrayList(list3);
        archiveList= new SimpleListProperty<Card>(observableList3);

    }

    public void addTodoCard(String s){
        Card c= new Card();
        c.setText(s);
        toDoList.add(c); //todo check if it updates correctly if you put the setText after adding it to the todo list

    }

    public void deleteTodoCard(String id){
       for (Card c: toDoList){
           if(c.getCardID().equals(id)){
               toDoList.remove(c);
               break;
           }
       }
    }


    public void addInProgressCard(String s){
        Card c= new Card();
        c.setText(s);
        inProgressList.add(c); //todo check if it updates correctly if you put the setText after adding it to the inprogress list

    }

    public void deleteInProgressCard(String id){
        for (Card c: inProgressList){
            if(c.getCardID().equals(id)){
                inProgressList.remove(c);
                break;
            }
        }
    }

    public void archiveCard(Card c){
        archiveList.add(c);
    }


    public Card getTodoCardWithId(String id){
        Card targetCard= null;
        for(Card c: Main.model.toDoList){
            if(c.getCardID().equals(id)){
                targetCard = c;
            }
        }

        return targetCard;
    }

    public Card getInProgressCardWithId(String id){
        Card targetCard= null;
        for(Card c: Main.model.inProgressList){
            if(c.getCardID().equals(id)){
                targetCard = c;
            }
        }

        return targetCard;
    }


}
