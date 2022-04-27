/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testInSequence;

import checking.Card;
import checking.InSequence;
import checking.ProblemException;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

/**
 * class of testing method in InSequenceForRoyalFlush class
 *
 * @version 3.0
 * @author Jeremiasz Radlowski
 */
public class TestInSequenceForRoyalFlush {

    /**
     * test of InSequenceForRoyalFlush method, of class Card for secured
     * reaction while using valid list
     *
     * @param a color of first card
     * @param g figure of first card
     * @param c color of second card
     * @param b figure of second card
     * @param d color of third card
     * @param f figure of third card
     * @param e color of fourth card
     * @param j figure of fourth card
     * @param h color of fifth card
     * @param i figure of fifth card
     */
    @ParameterizedTest
    @CsvSource({
        "d, j, d, q, d, k, h, 1, c, 0", "d, k, c, q, h, j, d, 1, d, 0"
    })
    public void testInSequenceForRoyalFlush_IfGoodInput(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
        try {
            ArrayList<Card> list = new ArrayList<>();

            Card c1 = new Card(a, b);
            Card c2 = new Card(c, d);
            Card c3 = new Card(e, f);
            Card c4 = new Card(g, h);
            Card c5 = new Card(i, j);

            list.addAll(Arrays.asList(c1, c2, c3, c4, c5));

            InSequence instance = new InSequence();
            boolean result = instance.inSequenceForRoyalFlush(list);
            assertTrue(result, "Input was incorrect");
        } catch (ProblemException ex) {
            fail("Problem exception");
        }
    }

    /**
     * test of InSequenceForRoyalFlush method, of class Card for secured
     * reaction while using invalid list
     *
     * @param a color of first card
     * @param g figure of first card
     * @param c color of second card
     * @param b figure of second card
     * @param d color of third card
     * @param f figure of third card
     * @param e color of fourth card
     * @param j figure of fourth card
     * @param h color of fifth card
     * @param i figure of fifth card
     */
    @ParameterizedTest
    @CsvSource({
        "d, j, d, 5, d, k, h, 1, c, 0", "d, k, c, q, h, j, d, 2, d, 0"
    })
    public void testInSequenceForRoyalFlush_IfBadInput(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
        try {
            ArrayList<Card> list = new ArrayList<>();

            Card c1 = new Card(a, b);
            Card c2 = new Card(c, d);
            Card c3 = new Card(e, f);
            Card c4 = new Card(g, h);
            Card c5 = new Card(i, j);

            list.addAll(Arrays.asList(c1, c2, c3, c4, c5));

            InSequence instance = new InSequence();
            boolean result = instance.inSequenceForRoyalFlush(list);
            assertFalse(result, "Input was correct");
        } catch (ProblemException ex) {
            fail("Problem exception");
        }
    }

    /**
     * test of InSequenceForRoyalFlush method, of class Card for secured
     * reaction while using empty list
     *
     * @param list empty list
     */
    @ParameterizedTest
    @NullSource
    public void testInSequenceForRoyalFlush_IfNullInput(ArrayList<Card> list) {
        try {
            InSequence instance = new InSequence();
            boolean result = instance.inSequenceForRoyalFlush(list);
            assertFalse(result, "Input was not null");
        } catch (ProblemException ex) {
        }
    }
}
