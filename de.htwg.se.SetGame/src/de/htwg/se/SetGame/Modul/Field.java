package de.htwg.se.SetGame.Modul;

import java.util.LinkedList;

import org.hamcrest.CoreMatchers;

import de.htwg.se.SetGame.Modul.Card.Cards;

public class Field {
	
	private int fieldsize;
	private int counter = 0;
	private boolean free = true;
	private final int max = 81;
	private Card card = new Card();
	private Cards[] c = card.pack;
	
	
	public Field(){
		init();
	}
	
	public Cards[] init() {
		int fieldsize = 12;
		Cards[] field =  new Cards[fieldsize];
		

		this.fieldsize = fieldsize;
		return fillField(field);
	}
	
	public int[] rand(){
		int[] array = new int[81];
		boolean b =  true;
		for(int i = 0 ; i < 81; i++){
			int j = (int) (Math.random()*81+1);
			for(int t = 0; t < 81; t++){
				if(j == array[t] && i > 0){
					j = (int) (Math.random()*81+1);
				}
					
			}
				array[i] = j;



			
			
		}
		for(int t = 0; t < 81; t++){
			
		}
		return array;
			
	}
	private Cards[] fillField(Cards[] f) {
		
		
		int[] array = rand();
		
		for(int i = 0; i < fieldsize; i++) {
			
			f[i] = card.pack[array[i]];	
		
			
		}
		
		return f ;
		
	}
	
	private boolean filledField(Cards[] F) {
		if(F == null) {
			return true;
		}
		if( F.length == fieldsize+1 ){
			return false;
		} else {
			return true;
		}
	}

}
