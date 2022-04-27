package com.mycompany.webpoker;

import checking.Card;
import checking.Hand;
import checking.ProblemException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @version 5.0
 * @author Jeremiasz Radlowski
 */
public class PokerListener implements ServletContextListener, ServletContextAttributeListener {

    /**
     * Fabryka Entity menagerów
     */
    EntityManagerFactory emf;

    /**
     * Entity Manager
     */
    EntityManager em;

    /**
     *
     * @param sce is a servlet content event
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        List<String> history = new ArrayList<String>();
        ArrayList<Card> list = new ArrayList<Card>();
        Card cardOne = new Card();
        context.setAttribute("history", history);
        context.setAttribute("cardOne", cardOne);
        context.setAttribute("list", list);
        String presistanceUnitName = "WebPokerDataBaseConnection";
        emf = Persistence.createEntityManagerFactory(presistanceUnitName);
        em = emf.createEntityManager();
        context.setAttribute("entityMenager", em);
        try {
            Card testCard = new Card("d", "1");
            Hand testHand = new Hand();
            em.getTransaction().begin();
            em.persist(testCard);
            em.persist(testHand);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.remove(testCard);
            em.remove(testHand);
            em.getTransaction().commit();
            if (em.createQuery("SELECT u FROM Card u", checking.Card.class).getResultList().size() == 0
                    || em.createQuery("SELECT u FROM Hand u", checking.Hand.class).getResultList().size() == 0) {
                Card c1 = new Card("d", "1");
                Card c2 = new Card("d", "k");
                Card c3 = new Card("d", "j");
                Card c4 = new Card("d", "q");
                Card c5 = new Card("d", "0");
                Hand hand = new Hand();
                hand.setName("Royal Poker");
                c1.addHand(hand, 1);
                c2.addHand(hand, 2);
                c3.addHand(hand, 3);
                c4.addHand(hand, 4);
                c5.addHand(hand, 5);
                em.getTransaction().begin();
                em.persist(c1);
                em.persist(c2);
                em.persist(c3);
                em.persist(c4);
                em.persist(c5);
                em.getTransaction().commit();

                //Straight Flush
                Card c1SF = new Card("d", "9");
                Card c2SF = new Card("d", "8");
                Card c3SF = new Card("d", "7");
                Card c4SF = new Card("d", "6");
                Card c5SF = new Card("d", "5");
                Hand handSF = new Hand();
                handSF.setName("Straight Flush");
                c1SF.addHand(handSF, 1);
                c2SF.addHand(handSF, 2);
                c3SF.addHand(handSF, 3);
                c4SF.addHand(handSF, 4);
                c5SF.addHand(handSF, 5);
                em.getTransaction().begin();
                em.persist(c1SF);
                em.persist(c2SF);
                em.persist(c3SF);
                em.persist(c4SF);
                em.persist(c5SF);
                em.getTransaction().commit();

                //Four of a kind
                Card c1FOK = new Card("c", "1");
                Card c2FOK = new Card("d", "1");
                Card c3FOK = new Card("h", "1");
                Card c4FOK = new Card("s", "1");
                Card c5FOK = new Card("d", "5");
                Hand handFOK = new Hand();
                handFOK.setName("Four of a kind");
                c1FOK.addHand(handFOK, 1);
                c2FOK.addHand(handFOK, 2);
                c3FOK.addHand(handFOK, 3);
                c4FOK.addHand(handFOK, 4);
                c5FOK.addHand(handFOK, 5);
                em.getTransaction().begin();
                em.persist(c1FOK);
                em.persist(c2FOK);
                em.persist(c3FOK);
                em.persist(c4FOK);
                em.persist(c5FOK);
                em.getTransaction().commit();

                //Flush
                Card c1Flush = new Card("d", "1");
                Card c2Flush = new Card("d", "3");
                Card c3Flush = new Card("d", "7");
                Card c4Flush = new Card("s", "6");
                Card c5Flush = new Card("d", "5");
                Hand handFlush = new Hand();
                handFlush.setName("Flush");
                c1Flush.addHand(handFlush, 1);
                c2Flush.addHand(handFlush, 2);
                c3Flush.addHand(handFlush, 3);
                c4Flush.addHand(handFlush, 4);
                c5Flush.addHand(handFlush, 5);
                em.getTransaction().begin();
                em.persist(c1Flush);
                em.persist(c2Flush);
                em.persist(c3Flush);
                em.persist(c4Flush);
                em.persist(c5Flush);
                em.getTransaction().commit();

                //Straight
                Card c1Straight = new Card("d", "9");
                Card c2Straight = new Card("h", "8");
                Card c3Straight = new Card("c", "7");
                Card c4Straight = new Card("d", "6");
                Card c5Straight = new Card("s", "5");
                Hand handStraight = new Hand();
                handStraight.setName("Straight");
                c1Straight.addHand(handStraight, 1);
                c2Straight.addHand(handStraight, 2);
                c3Straight.addHand(handStraight, 3);
                c4Straight.addHand(handStraight, 4);
                c5Straight.addHand(handStraight, 5);
                em.getTransaction().begin();
                em.persist(c1Straight);
                em.persist(c2Straight);
                em.persist(c3Straight);
                em.persist(c4Straight);
                em.persist(c5Straight);
                em.getTransaction().commit();

                //Three of a kind
                Card c1TOK = new Card("c", "1");
                Card c2TOK = new Card("d", "1");
                Card c3TOK = new Card("h", "1");
                Card c4TOK = new Card("s", "3");
                Card c5TOK = new Card("d", "5");
                Hand handTOK = new Hand();
                handTOK.setName("Three of a kind");
                c1TOK.addHand(handTOK, 1);
                c2TOK.addHand(handTOK, 2);
                c3TOK.addHand(handTOK, 3);
                c4TOK.addHand(handTOK, 4);
                c5TOK.addHand(handTOK, 5);
                em.getTransaction().begin();
                em.persist(c1TOK);
                em.persist(c2TOK);
                em.persist(c3TOK);
                em.persist(c4TOK);
                em.persist(c5TOK);
                em.getTransaction().commit();

                //Two Pair
                Card c1TP = new Card("c", "1");
                Card c2TP = new Card("d", "1");
                Card c3TP = new Card("h", "2");
                Card c4TP = new Card("s", "2");
                Card c5TP = new Card("d", "5");
                Hand handTP = new Hand();
                handTP.setName("Two Pair");
                c1TP.addHand(handTP, 1);
                c2TP.addHand(handTP, 2);
                c3TP.addHand(handTP, 3);
                c4TP.addHand(handTP, 4);
                c5TP.addHand(handTP, 5);
                em.getTransaction().begin();
                em.persist(c1TP);
                em.persist(c2TP);
                em.persist(c3TP);
                em.persist(c4TP);
                em.persist(c5TP);
                em.getTransaction().commit();

                //Pair 
                Card c1Pair = new Card("c", "1");
                Card c2Pair = new Card("d", "1");
                Card c3Pair = new Card("h", "2");
                Card c4Pair = new Card("s", "3");
                Card c5Pair = new Card("d", "5");
                Hand handPair = new Hand();
                handPair.setName("Pair");
                c1Pair.addHand(handPair, 1);
                c2Pair.addHand(handPair, 2);
                c3Pair.addHand(handPair, 3);
                c4Pair.addHand(handPair, 4);
                c5Pair.addHand(handPair, 5);
                em.getTransaction().begin();
                em.persist(c1Pair);
                em.persist(c2Pair);
                em.persist(c3Pair);
                em.persist(c4Pair);
                em.persist(c5Pair);
                em.getTransaction().commit();

                //High Card
                Card c1HC = new Card("c", "1");
                Card c2HC = new Card("d", "3");
                Card c3HC = new Card("h", "4");
                Card c4HC = new Card("s", "7");
                Card c5HC = new Card("d", "5");
                Hand handHC = new Hand();
                handHC.setName("High Card");
                c1HC.addHand(handHC, 1);
                c2HC.addHand(handHC, 2);
                c3HC.addHand(handHC, 3);
                c4HC.addHand(handHC, 4);
                c5HC.addHand(handHC, 5);
                em.getTransaction().begin();
                em.persist(c1HC);
                em.persist(c2HC);
                em.persist(c3HC);
                em.persist(c4HC);
                em.persist(c5HC);
                em.getTransaction().commit();

            }
        } catch (ProblemException ex) {
            Logger.getLogger(PokerListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param sce is a servlet content event
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
