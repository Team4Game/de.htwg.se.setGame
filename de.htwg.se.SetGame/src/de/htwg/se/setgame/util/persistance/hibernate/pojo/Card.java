package de.htwg.se.setgame.util.persistance.hibernate.pojo;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by David on 04.01.16.
 */
@Entity
@Table(name = "setgame_card")
public class Card extends de.htwg.se.setgame.model.impl.Card implements Serializable,Comparable {

    public void setCardID(Long cardID) {
        this.cardID = cardID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardID;

    public Long getCardID() {
        return cardID;
    }

    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String form;
    @Column(nullable = false)
    private String panelFilling;
    @Column(nullable = false)
    private int anz;




    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getForm() {
        return form;
    }

    @Override
    public String getPanelFilling() {
        return panelFilling;
    }

    @Override
    public int getNumberOfComponents() {
        return anz;
    }

    @Override
    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public void setPanelFilling(String panelFilling) {
        this.panelFilling = panelFilling;
    }

    @Override
    public void setNumberOfComponents(int numberOfComponents) {
        this.anz = numberOfComponents;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean compareTo(ICard card) {
        if (this.color.equals(card.getColor()) && this.anz == card.getNumberOfComponents()
                && this.form.equals(card.getForm())
                && this.panelFilling.equals(card.getPanelFilling())) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof ICard){
            return compareTo((ICard)o) ? 0 : 1;
        }
        return 1;
    }
}
