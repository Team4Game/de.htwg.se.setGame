package de.htwg.se.SetGame.Modul;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {
	Field f;
	@Before
	public void setUp(){
		this.f = new Field();
		
		
		
	}
	
	
	@Test
	public void test() {
		
	}
	@Test
	public void testFieldradom(){
		int array[] = f.rand();
		int numberofArray = 0;
		for(int i = 0; i < array.length;i++){
			for(int t = 0 ; t < array.length;t++){
				if(t == array[i]){
					System.out.println(i+"  number: "+t + " in index: "+i);
					numberofArray++;
					
				}
				
			}
				
		}
		if(numberofArray == array.length){
			System.out.println("true");
		}else{
			System.out.println("false!! \n numberofarray = "+numberofArray+"\narray length:  "+ array.length );
		}
			
		
	}

}
