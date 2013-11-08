package de.htwg.se.SetGame.Modul;

import java.awt.*;
import java.util.LinkedList;
import java.util.TreeMap;
import java.text.Format;
import java.util.TreeSet;

import javax.swing.text.html.FormSubmitEvent;

public class Card {
	Cards pack[];
	private int numberOfCard = 0;

	/* One card in card */
	public class Cards {
		String color;
		String fomr;
		String panelFilling;
		int anz;

		String[] form = {"balk","ovally","wave"};
		String[] pannelFilling = {"halffill","fill","empty"};


		public Cards(String color, String form, String panelFilling, int anzahl) {

			if ((color.equals("red") || color.equals("green")
					|| color.equals("purple")) && (form.equals("balk")
					|| form.equals("ovally") || form.equals("wave"))
					&& (panelFilling.equals("halffill")
					|| panelFilling.equals("fill")
					|| panelFilling.equals("empty")) && (anzahl == 1 || anzahl == 2
					|| anzahl == 3)) {

				this.color = color;
				this.fomr = form;
				this.panelFilling = panelFilling;
				this.anz = anzahl;

			}
		}
	}
	

	public Card() {
		this.pack = creatCards();
	}

	private Cards[] creatCards() {
		Cards list[] = new Cards[81];
		/* Reds */
		/* 1 */
		Cards [] cards = new Cards[81];
		for(int i = 0 ; i < 81; i++){
			/*create all reds*/
			
			
		}
		
		Cards card1 = new Cards("red", "balk", "halffill", 1);
		Cards card2 = new Cards("red", "ovally", "halffill", 1);
		Cards card3 = new Cards("red", "wave", "halffill", 1);

		Cards card4 = new Cards("red", "balk", "fill", 1);
		Cards card5 = new Cards("red", "ovally", "fill", 1);
		Cards card6 = new Cards("red", "wave", "fill", 1);

		Cards card7 = new Cards("red", "balk", "empty", 1);
		Cards card8 = new Cards("red", "ovally", "empty", 1);
		Cards card9 = new Cards("red", "wave", "empty", 1);
		/* 2 */
		Cards card10 = new Cards("red", "balk", "halffill", 2);
		Cards card11 = new Cards("red", "ovally", "halffill", 2);
		Cards card12 = new Cards("red", "wave", "halffill", 2);

		Cards card13 = new Cards("red", "balk", "fill", 2);
		Cards card14 = new Cards("red", "ovally", "fill", 2);
		Cards card15 = new Cards("red", "wave", "fill", 2);

		Cards card16 = new Cards("red", "balk", "empty", 2);
		Cards card17 = new Cards("red", "ovally", "empty", 2);
		Cards card18 = new Cards("red", "wave", "empty", 2);
		/* 3 */
		Cards card19 = new Cards("red", "balk", "halffill", 3);
		Cards card20 = new Cards("red", "ovally", "halffill", 3);
		Cards card21 = new Cards("red", "wave", "halffill", 3);

		Cards card22 = new Cards("red", "balk", "fill", 3);
		Cards card23 = new Cards("red", "ovally", "fill", 3);
		Cards card24 = new Cards("red", "wave", "fill", 3);

		Cards card25 = new Cards("red", "balk", "empty", 3);
		Cards card26 = new Cards("red", "ovally", "empty", 3);
		Cards card27 = new Cards("red", "wave", "empty", 3);

		/* green */
		/* 1 */
		Cards card28 = new Cards("green", "balk", "halffill", 1);
		Cards card29 = new Cards("green", "ovally", "halffill", 1);
		Cards card30 = new Cards("green", "wave", "halffill", 1);

		Cards card31 = new Cards("green", "balk", "fill", 1);
		Cards card32 = new Cards("green", "ovally", "fill", 1);
		Cards card33 = new Cards("green", "wave", "fill", 1);

		Cards card34 = new Cards("green", "balk", "empty", 1);
		Cards card35 = new Cards("green", "ovally", "empty", 1);
		Cards card36 = new Cards("green", "wave", "empty", 1);
		/* 2 */
		Cards card37 = new Cards("green", "balk", "halffill", 2);
		Cards card38 = new Cards("green", "ovally", "halffill", 2);
		Cards card39 = new Cards("green", "wave", "halffill", 2);

		Cards card40 = new Cards("green", "balk", "fill", 2);
		Cards card41 = new Cards("green", "ovally", "fill", 2);
		Cards card42 = new Cards("green", "wave", "fill", 2);

		Cards card43 = new Cards("green", "balk", "empty", 2);
		Cards card44 = new Cards("green", "ovally", "empty", 2);
		Cards card45 = new Cards("green", "wave", "empty", 2);
		/* 3 */
		Cards card46 = new Cards("green", "balk", "halffill", 3);
		Cards card47 = new Cards("green", "ovally", "halffill", 3);
		Cards card48 = new Cards("green", "wave", "halffill", 3);

		Cards card49 = new Cards("green", "balk", "fill", 3);
		Cards card50 = new Cards("green", "ovally", "fill", 3);
		Cards card51 = new Cards("green", "wave", "fill", 3);

		Cards card52 = new Cards("green", "balk", "empty", 3);
		Cards card53 = new Cards("green", "ovally", "empty", 3);
		Cards card54 = new Cards("green", "wave", "empty", 3);

		/* purple */
		/* 1 */
		Cards card55 = new Cards("purple", "balk", "halffill", 1);
		Cards card56 = new Cards("purple", "ovally", "halffill", 1);
		Cards card57 = new Cards("purple", "wave", "halffill", 1);

		Cards card58 = new Cards("purple", "balk", "fill", 1);
		Cards card59 = new Cards("purple", "ovally", "fill", 1);
		Cards card60 = new Cards("purple", "wave", "fill", 1);

		Cards card61 = new Cards("purple", "balk", "empty", 1);
		Cards card62 = new Cards("purple", "ovally", "empty", 1);
		Cards card63 = new Cards("purple", "wave", "empty", 1);
		/* 2 */
		Cards card64 = new Cards("purple", "balk", "halffill", 2);
		Cards card65 = new Cards("purple", "ovally", "halffill", 2);
		Cards card66 = new Cards("purple", "wave", "halffill", 2);

		Cards card67 = new Cards("purple", "balk", "fill", 2);
		Cards card68 = new Cards("purple", "ovally", "fill", 2);
		Cards card69 = new Cards("purple", "wave", "fill", 2);

		Cards card70 = new Cards("purple", "balk", "empty", 2);
		Cards card71 = new Cards("purple", "ovally", "empty", 2);
		Cards card72 = new Cards("purple", "wave", "empty", 2);
		/* 3 */
		Cards card73 = new Cards("purple", "balk", "halffill", 3);
		Cards card74 = new Cards("purple", "ovally", "halffill", 3);
		Cards card75 = new Cards("purple", "wave", "halffill", 3);

		Cards card76 = new Cards("purple", "balk", "fill", 3);
		Cards card77 = new Cards("purple", "ovally", "fill", 3);
		Cards card78 = new Cards("purple", "wave", "fill", 3);

		Cards card79 = new Cards("purple", "balk", "empty", 3);
		Cards card80 = new Cards("purple", "ovally", "empty", 3);
		Cards card81 = new Cards("purple", "wave", "empty", 3);

		
		list[0] = card1;
		list[1] = card2;
		list[2] = card3;
		
		list[3] = card4;
		list[4] = card5;
		list[5] = card6;

		list[6] = card7;
		list[7] = card8;
		list[8] = card9;

		list[9] = card10;
		list[10] = card11;
		list[11] = card12;

		list[12] = card13;
		list[13] = card14;
		list[14] = card15;

		list[15] = card16;
		list[16] = card17;
		list[17] = card18;
		
		list[18] = card19;
		list[19] = card20;
		list[20] = card21;

		list[21] = card22;
		list[22] = card23;
		list[23] = card24;
		
		list[24] = card25;
		list[25] = card26;
		list[26] = card27;

		list[27] = card28;
		list[28] = card29;
		list[29] = card30;

		list[30] = card31;
		list[31] = card32;
		list[32] = card33;
		
		list[33] = card34;
		list[34] = card35;
		list[35] = card36;

		list[36] = card37;
		list[37] = card38;
		list[38] = card39;

		list[39] = card40;
		list[40] = card41;
		list[41] = card42;
		
		list[42] = card43;
		list[43] = card44;
		list[44] = card45;
		
		list[45] = card46;
		list[46] = card47;
		list[47] = card48;
		
		list[48] = card49;
		list[49] = card50;
		list[50] = card51;

		list[51] = card52;
		list[52] = card53;
		list[53] = card54;
		
		list[54] = card55;
		list[55] = card56;
		list[56] = card57;
		
		list[57] = card58;
		list[58] = card59;
		list[59] = card60;

		list[60] = card61;
		list[61] = card62;
		list[62] = card63;
		
		list[63] = card64;
		list[64] = card65;
		list[65] = card66;

		list[66] = card67;
		list[67] = card68;
		list[68] = card69;

		list[69] = card70;
		list[70] = card71;
		list[71] = card72;

		list[72] = card73;
		list[73] = card74;
		list[74] = card75;
		
		list[75] = card76;
		list[76] = card77;
		list[77] = card78;
		
		list[78] = card79;
		list[79] = card80;
		list[80] = card81;


		int size = 0;
		for (int i = 0; i < list.length; i++) {
			size = size + 1;
		}
		this.numberOfCard = size;
		for (int i = 0; i < list.length;i++) {
		}
		return list;

	}

	public int getNumerofCards() {
		return this.numberOfCard;

	}
	public Cards[] creatRed(){
			Cards[] c = new Cards[27];
			for(int i = 0 ; i < 27; i++){
				if(i < 3){
			
				}
					
				
			}
			return c;
		
	}
	



	
}
