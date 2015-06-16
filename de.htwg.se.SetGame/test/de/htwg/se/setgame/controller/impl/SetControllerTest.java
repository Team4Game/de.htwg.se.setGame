package de.htwg.se.setgame.controller.impl;


import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.persistence.hibernate.GameDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SetControllerTest {
    SetController target;
    LinkedList<ICard> aSetListe;

    @Before
    public void setUp() {
        this.target = new SetController(new ModelFactory(), new GameDao());
        this.aSetListe = new LinkedList<ICard>();
        aSetListe.addAll(this.target.getSetInField());
    }

    @Test
    public void testResetGame() {
        List<ICard> cardsForSEt = target.getASetInGame();
        target.isASetForController(cardsForSEt.get(0), cardsForSEt.get(1), cardsForSEt.get(2), 0);
        Assert.assertEquals(target.getCardinGame().size(), 78);
        target.newGame();
        Assert.assertEquals(target.getCardinGame().size(), 81);

    }

    @Test
    public void testIfIsSetInGame() {
        target.newGame();
        List<ICard> cardSet = target.getASetInGame();

        Assert.assertFalse(cardSet.size() != 3);
    }

    @Test
    public void testIfIsSetInGame_Fail() {
        target.newGame();
        for (int i = 0; i < 27; i++) {
            List<ICard> cardSet = target.getASetInGame();
            target.isASetForController(cardSet.get(0), cardSet.get(1), cardSet.get(2), 1);
            target.checkIfIsASeTInGame();
            if (target.getASetInGame().size() == 0 && target.getCardinGame().size() == 9) {
                target.newGame();
                i = 0;
            } else if (target.getASetInGame().size() == 0) {
                List<ICard> card = target.getCardinGame();
                List<ICard> cardSset = target.getASetInGame();
                if (cardSset.size() != 0) {
                    i = 0;
                } else {
                    break;
                }

                card.size();
            }
        }
        List<ICard> card = target.getCardinGame();
        List<ICard> cardSet = target.getASetInGame();

        card.size();
        Assert.assertEquals(cardSet.size(), 0);
    }

    @Test
    public void isInField_Fail() {
        //get Cards are not in game
        List<ICard> cardSet = target.getASetInGame();
        target.isASetForController(cardSet.get(0), cardSet.get(1), cardSet.get(2), 1);
        Assert.assertEquals( 78,target.getCardinGame().size());
        Assert.assertTrue(target.isInField(cardSet.get(0), cardSet.get(1), cardSet.get(2)) == false);
    }
    @Test
    public void isAset_Fail() {
        //get Cards are not in game
        List<ICard> cardSet = target.getASetInGame();
        target.isASetForController(cardSet.get(0), cardSet.get(1), cardSet.get(2), 1);
        Assert.assertEquals(target.getCardinGame().size(), 78);
        Assert.assertTrue(target.isASet(cardSet.get(0), cardSet.get(1), cardSet.get(2)) == false);
    }
    @Test
    public void allTheSetsInField_OK(){
        Assert.assertTrue(target.allTheSetsInField(new LinkedList<ICard>()) == true) ;
    }
    @Test
    public void allTheSetsInField_ok(){
        Assert.assertTrue(target.allTheSetsInField(target.getCardInFieldGame()) == true) ;
    }
    @Test
    public void setFieldSize_ok(){
        target.setFieldSize(20);
        Assert.assertEquals(target.getCardInFieldGame().size(), 20);
    }
    @Test
    public void testIsAsetForController() {
        target.isASetForController(aSetListe.get(0), aSetListe.get(1), aSetListe.get(2), target.getPlayerTwo());

    }
    @Test
    public void stillSetInGame_OK(){
        Assert.assertTrue(target.stillSetInGame() == true);
    }

    @Test
    public void stillSetInGame_Fail(){
        while(target.getASetInGame().size() != 0){
            List<ICard> cardSet = target.getASetInGame();
            target.isASetForController(cardSet.get(0), cardSet.get(1), cardSet.get(2), 1);
            target.checkIfIsASeTInGame();
        }
        Assert.assertTrue(target.stillSetInGame() == false);
    }
    @Test
         public void getplayerPoints_ok(){
        Assert.assertEquals(target.getPlayerOnePoints(),0);
        Assert.assertEquals(target.getPlayerTwoPoints(),0);
    }
    @Test
    public void getPlayer_ok(){
        Assert.assertEquals(target.getPlayerOne(),1);
        Assert.assertEquals(target.getPlayerTwo(),2);
    }
    @Test
    public void getField_ok(){
        IField f = target.getField();
        Assert.assertEquals(f.getCardsInField().size(),target.getCardInFieldGame().size());
    }
    @Test
    public void getCardAndIndexField_Ok(){
        Assert.assertEquals(target.getCardsAndTheIndexOfCardInField().size(), target.getCardInFieldGame().size());
    }
    @Test
    public void getPack_ok(){

        Assert.assertEquals(69, target.getPack().getPack().size());
    }
}
