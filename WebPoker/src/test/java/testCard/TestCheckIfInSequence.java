/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testCard;

import checking.Card;
import checking.ProblemException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * class of testing method in Card class
 *
 * @version 3.0
 * @author Jeremiasz Radłowski
 */
public class TestCheckIfInSequence {

    /**
     * test of checkIfInSequence method, of class Card for secured reaction
     * while using empty list
     *
     * @param list empty list
     * @param isSameColor takes the value true or false depending on previous
     * result.
     */
    
    @ParameterizedTest
    @CsvSource(value = {
        ", true", ", false"
    })
    public void testCheckIfInSequence_IfNullInput(ArrayList<Card> list, boolean isSameColor) {
//        try {
//            ArrayList<Card> list1 = new ArrayList<>();
//            Card card = new Card();
//            card.checkIfInSequence(list1, isSameColor);
//            fail("Null was in input");
//
//        } catch (ProblemException ex) {
//
//        }
    }

}
