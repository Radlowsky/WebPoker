/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checking;

import java.util.ArrayList;
import static java.util.Collections.swap;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * class of cards
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@Entity(name = "Card")
@Table(name = "Card")
public class Card implements java.io.Serializable {

    /**
     * object of class InSequence
     */
    @InformationField(info = "This is my object of checking if cards are in sequence")
    public InSequence sequence = new InSequence();

    /*color of card */
    private String color;

    /*figure of card */
    private String figure;

    /*id of card */
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    /**
     * object one to many
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Hand> listHand = new ArrayList<>();

    /**
     * contructor of card class
     */
    public Card() {
        super();
    }

    /**
     * constructor of card class
     * 
     * @param color is a color of card
     * @param figure is a figure of card
     * @throws checking.ProblemException throw exception when number of cards is
     * incorrect
     */
    public Card(String color, String figure) throws ProblemException {
        if (checkIfGoodInput(color, figure)) {
            this.color = color;
            this.figure = figure;
        } else {
            throw new ProblemException("Wrong input");

        }
    }

    /**
     * getter that returns color
     *
     * @return method returns color of card
     */
    public String getColor() {
        return color;
    }

    /**
     * getter that returns figure
     *
     * @return method returns figure of card
     */
    public String getFigure() {
        return figure;
    }

    /**
     *
     * @param hand is hand of cards
     * @param index is number of card
     */
    public void addHand(Hand hand, int index) {
        listHand.add(hand);
        switch (index) {
            default:
            case 1:
                hand.setCard1(this);
                break;
            case 2:
                hand.setCard2(this);
                break;
            case 3:
                hand.setCard3(this);
                break;
            case 4:
                hand.setCard4(this);
                break;
            case 5:
                hand.setCard5(this);
                break;
        }

    }

    /**
     *
     * @param hand is hand of cards
     * @param index is number of card
     */
    public void removeHand(Hand hand, int index) {
        listHand.remove(hand);
        switch (index) {
            default:
            case 1:
                hand.setCard1(null);
                break;
            case 2:
                hand.setCard2(null);
                break;
            case 3:
                hand.setCard3(null);
                break;
            case 4:
                hand.setCard4(null);
                break;
            case 5:
                hand.setCard5(null);
                break;
        }
    }

    private boolean checkIfGoodInput(String color, String figure) {
        if (color.toLowerCase().equals("d") || color.toLowerCase().equals("c") || color.toLowerCase().equals("h") || color.toLowerCase().equals("s")) {
            if (figure.toLowerCase().equals("q") || figure.toLowerCase().equals("j") || figure.toLowerCase().equals("k")) {
                return true;
            } else if (Integer.parseInt(figure) < 10 && Integer.parseInt(figure) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that check if cards have same color
     *
     * @param list is a list contains card
     * @return method that returns true or false depending on result
     * @throws checking.ProblemException throws problem exception
     */
    public boolean checkIfSameColor(ArrayList<Card> list) throws ProblemException {
        if (list == null) {
            throw new ProblemException("list is a null");
        }
        if (((Card) list.get(1)).color.equals(((Card) list.get(0)).color)
                && ((Card) list.get(2)).color.equals(((Card) list.get(0)).color)
                && ((Card) list.get(3)).color.equals(((Card) list.get(0)).color)
                && ((Card) list.get(4)).color.equals(((Card) list.get(0)).color)) {
            return true;
        }
        return false;
    }

    /**
     * method that check if cards are in some sequence
     *
     * @param list is a list contains card
     * @param isSameColor is boolean, true = cards have same color, false =
     * cards have different colors
     * @return string in which is a arranging cards
     * @throws checking.ProblemException throws problem exception
     */
    public String checkIfInSequence(ArrayList<Card> list, boolean isSameColor) throws ProblemException {
        if (list == null) {
            throw new ProblemException("list is a null");
        }
        if (isSameColor == true) {
            if (sequence.inSequence(list)) {
                if (sequence.inSequenceForRoyalFlush(list)) {
                    return "Royal Flush";
                }
                return "Straight Flush";
            }
            return "Flush";
        } else if (sequence.inSequence(list)) {
            return "Straight";
        } else {
            ArrayList<Card> tmpList = (ArrayList) list.clone();
            sortArrayList(tmpList);
            ArrayList<String> pairsList = compare(tmpList);
            switch (pairsList.size()) {
                case 4:
                    if (pairsList.get(1).equals((3)) && pairsList.get(3).equals(2)) {
                        return "Full House";
                    } else if (pairsList.get(1).equals((2)) && pairsList.get(3).equals(3)) {
                        return "Full House";
                    } else {
                        return "Two pairs";
                    }
                case 2:
                    switch (Integer.parseInt(pairsList.get(1))) {
                        case 4:
                            return "Four of kind";
                        case 3:
                            return "Three of kind";
                        case 2:
                            return "Pair";
                    }
                default:
                    return "High card";
            }
        }
    }

    /**
     * method that sort array list
     *
     * @param list is a list contains card
     */
    private void sortArrayList(ArrayList<Card> list) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 - i; j++) {
                if (list.get(j).figure.charAt(0) > list.get(j + 1).figure.charAt(0)) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    /**
     * method that compare cards in list and then return it as list
     *
     * @param list is a list contains card
     * @return list of cards
     */
    private ArrayList compare(ArrayList<Card> list) {
        ArrayList<String> array = new ArrayList();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 - i; j++) {
                if (list.get(i).figure.equals(list.get(j + 1).figure)) {

                    if (array.isEmpty()) {
                        array.add(list.get(i).figure);
                        array.add(Integer.toString(2));
                    } else {
                        boolean wasWritten = false;
                        for (int k = 0; k < list.size() / 2; k += 2) {
                            if (array.get(k).equals(list.get(i).figure)) {
                                int tmp = Integer.parseInt(array.get(k + 1));
                                tmp++;
                                array.set(k + 1, Integer.toString(tmp));
                                wasWritten = true;
                            }
                            if (wasWritten == false) {
                                array.add(list.get(i).figure);
                                array.add(Integer.toString(2));
                            }
                        }

                    }
                }
            }
        }
        return array;
    }
}
