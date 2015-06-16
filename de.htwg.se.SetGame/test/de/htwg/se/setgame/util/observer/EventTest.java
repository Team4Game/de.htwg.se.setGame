package de.htwg.se.setgame.util.observer;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 16.06.2015.
 */
public class EventTest {
    Event target;
    @Before
    public void setUp() {
    }
    @Test
    public void createEvent(){
        this.target = new Event() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}
