package de.htwg.se.setgame.util.persistance.hibernate.pojo;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by David on 05.01.16.
 */
@Entity()
@Table(name = "cardsinfield")
public class CardsInField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Integer, Card> getCardsInField() {
        return cardsInField;
    }

    public void setCardsInField(Map<Integer, Card> cardsInField) {
        this.cardsInField = cardsInField;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Integer, Card> cardsInField = new TreeMap<Integer, Card>();
}
