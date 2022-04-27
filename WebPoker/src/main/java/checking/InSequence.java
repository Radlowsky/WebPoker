/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checking;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * class containing the algorithm for checking the order of cards
 *
 * @version 3.0
 * @author Jeremiasz Radlowski
 */
public class InSequence implements java.io.Serializable {

    /**
     * a method that checks if the cards are in sequence
     *
     * @param list passes the list that contains the player's cards
     * @return true or false depending on the result
     * @throws checking.ProblemException when list is empty
     */
    public boolean inSequenceForRoyalFlush(ArrayList<Card> list) throws ProblemException {
        if (list == null) {
            throw new ProblemException("list is a null");
        }

        Stream card0 = list.stream().filter(c -> c.getFigure().equals("0"));
        Stream card1 = list.stream().filter(c -> c.getFigure().equals("1"));
        Stream cardj = list.stream().filter(c -> c.getFigure().toLowerCase().equals("j"));
        Stream cardq = list.stream().filter(c -> c.getFigure().toLowerCase().equals("q"));
        Stream cardk = list.stream().filter(c -> c.getFigure().toLowerCase().equals("k"));

        if (card0.count() > 0 && card1.count() > 0 && cardj.count() > 0 && cardq.count() > 0 && cardk.count() > 0) {
            return true;
        }

        return false;
    }

    /**
     * a method that checks if the cards are in sequence
     *
     * @param list passes the list that contains the player's cards
     * @return true or false depending on the result
     * @throws checking.ProblemException throws problem exception
     */
    public boolean inSequence(ArrayList<Card> list) throws ProblemException {
        if (list == null) {
            throw new ProblemException("list is a null");
        }

        int numberInAscii = 48;

        for (int a = 0; a < 10; a++) {
            Stream card1 = list.stream().filter(c -> c.getFigure().equals("9"));
            Stream card2 = list.stream().filter(c -> c.getFigure().equals("0"));
            Stream card3 = list.stream().filter(c -> c.getFigure().toLowerCase().equals("j"));
            Stream card4 = list.stream().filter(c -> c.getFigure().toLowerCase().equals("q"));
            Stream card5 = list.stream().filter(c -> c.getFigure().toLowerCase().equals("k"));

            card1 = list.stream().filter(c -> c.getFigure().equals("1"));

            if (card1.count() > 0 && card2.count() > 0 && card3.count() > 0 && card4.count() > 0 && card5.count() > 0) {
                return true;
            }

            for (int i = 0; i < 5; i++) {
                if (((Card) list.get(i)).getFigure().equals(Character.toString((char) numberInAscii))) {
                    for (int j = 0; j < 5; j++) {
                        if (((Card) list.get(j)).getFigure().equals(Character.toString((char) numberInAscii + 1))) {
                            for (int k = 0; k < 5; k++) {
                                if (((Card) list.get(k)).getFigure().equals(Character.toString((char) numberInAscii + 2))) {
                                    for (int l = 0; l < 5; l++) {
                                        if (((Card) list.get(l)).getFigure().equals(Character.toString((char) numberInAscii + 3))) {
                                            for (int m = 0; m < 5; m++) {
                                                if (((Card) list.get(m)).getFigure().equals(Character.toString((char) numberInAscii + 4))) {

                                                    return true;

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            numberInAscii++;
        }

        return false;
    }
}
