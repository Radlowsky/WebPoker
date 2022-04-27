/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checking;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * class of hands
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@Entity
public class Hand implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Card card1;
    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Card card2;
    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Card card3;
    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Card card4;
    @ManyToOne( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Card card5;

    /**
     *
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name is a name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return first card
     */
    public Card getCard1() {
        return card1;
    }

    /**
     *
     * @param card1 is a first card from hand
     */
    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    /**
     *
     * @return second card
     */
    public Card getCard2() {
        return card2;
    }

    /**
     *
     * @param card2 is a second card from hand
     */
    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    /**
     * 
     * @return third card
     */
    public Card getCard3() {
        return card3;
    }

    /**
     *
     * @param card3 is a third card from hand
     */
    public void setCard3(Card card3) {
        this.card3 = card3;
    }

    /**
     *
     * @return fourth card
     */
    public Card getCard4() {
        return card4;
    }

    /**
     *
     * @param card4 is a fourth card from hand
     */
    public void setCard4(Card card4) {
        this.card4 = card4;
    }

    /**
     *
     * @return is a fifth card from hand
     */
    public Card getCard5() {
        return card5;
    }

    /**
     *
     * @param card5 fifth card
     */
    public void setCard5(Card card5) {
        this.card5 = card5;
    }

    /**
     *
     * @return is a fifth card from hand
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id is a card id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hand)) {
            return false;
        }
        Hand other = (Hand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "checking.Hand[ id=" + id + " ]";
    }

}
