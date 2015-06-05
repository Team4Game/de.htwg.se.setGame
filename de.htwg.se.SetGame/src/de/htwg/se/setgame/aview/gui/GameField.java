package de.htwg.se.setgame.aview.gui;

import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.se.setgame.controller.impl.logic.impl.GameProvider;
import de.htwg.se.setgame.controller.impl.logic.impl.PackProvider;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.ModelFactory;

/**
 * @author raina
 *
 */
public class GameField extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static int index = 0;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
	private static final int TEN = 10;
	private static final int ELEVEN = 11;
	private static final int TWENTY = 20;

	private static JButton b1 = new JButton();
	private static JButton b2 = new JButton();
	private static JButton b3 = new JButton();
	private static JButton b4 = new JButton();
	private static JButton b5 = new JButton();
	private static JButton b6 = new JButton();
	private static JButton b7 = new JButton();
	private static JButton b8 = new JButton();
	private static JButton b9 = new JButton();
	private static JButton b10 = new JButton();
	private static JButton b11 = new JButton();
	private static JButton b12 = new JButton();

	private static Boolean a1 = false;
	private static Boolean a2 = false;
	private static Boolean a3 = false;
	private static Boolean a4 = false;
	private static Boolean a5 = false;
	private static Boolean a6 = false;
	private static Boolean a7 = false;
	private static Boolean a8 = false;
	private static Boolean a9 = false;
	private static Boolean a10 = false;
	private static Boolean a11 = false;
	private static Boolean a12 = false;

	private static int number1 = ZERO;
	private static int number2 = ONE;
	private static int number3 = TWO;
	private static int number4 = THREE;
	private static int number5 = FOUR;
	private static int number6 = FIVE;
	private static int number7 = SIX;
	private static int number8 = SEVEN;
	private static int number9 = EIGHT;
	private static int number10 = NINE;
	private static int number11 = TEN;
	private static int number12 = ELEVEN;

	private static URL resource1;
	private static URL resource2;
	private static URL resource3;
	private static URL resource4;
	private static URL resource5;
	private static URL resource6;
	private static URL resource7;
	private static URL resource8;
	private static URL resource9;
	private static URL resource10;
	private static URL resource11;
	private static URL resource12;

	private static URL resource13;
	private static URL resource14;
	private static URL resource15;
	private static URL resource16;
	private static URL resource17;
	private static URL resource18;
	private static URL resource19;
	private static URL resource20;
	private static URL resource21;
	private static URL resource22;
	private static URL resource23;
	private static URL resource24;
	
	private static Icon icon1;
	private static Icon icon2;
	private static Icon icon3;
	private static Icon icon4;
	private static Icon icon5;
	private static Icon icon6;
	private static Icon icon7;
	private static Icon icon8;
	private static Icon icon9;
	private static Icon icon10;
	private static Icon icon11;
	private static Icon icon12;
	
	private static Icon icon13;
	private static Icon icon14;
	private static Icon icon15;
	private static Icon icon16;
	private static Icon icon17;
	private static Icon icon18;
	private static Icon icon19;
	private static Icon icon20;
	private static Icon icon21;
	private static Icon icon22;
	private static Icon icon23;
	private static Icon icon24;

	private static JPanel panel1 = new JPanel();

	private static ICard[] card = new ICard[FIVE];
	private static IPack iPack;
	private static Map<Integer, String> cardToPicture;
	private static List<ICard> saveList;
	private static List<String> urlListe;
	private static List<String> test;

	private static Integer cardkey = Integer.valueOf(ZERO);

	public GameField() {

		cardToPicture = new TreeMap<Integer, String>();
		saveList = new LinkedList<ICard>();
		urlListe = new LinkedList<String>();
		test = new LinkedList<String>();
        PackProvider packProvider = new PackProvider(new ModelFactory());
		iPack = packProvider.getPack();

		for (int i = 0; i < iPack.getPack().size(); i++) {
			test.add("/pack/" + i + ".gif");
		}
		for (int index1 = 0; index1 < iPack.getPack().size(); index1++) {
			cardToPicture.put(index1, test.get(index1));
		}

		saveUrl();

		cardkey = ZERO;

		resource1 = getClass().getResource(urlListe.get(number1));
		resource2 = getClass().getResource(urlListe.get(number2));
		resource3 = getClass().getResource(urlListe.get(number3));
		resource4 = getClass().getResource(urlListe.get(number4));
		resource5 = getClass().getResource(urlListe.get(number5));
		resource6 = getClass().getResource(urlListe.get(number6));
		resource7 = getClass().getResource(urlListe.get(number7));
		resource8 = getClass().getResource(urlListe.get(number8));
		resource9 = getClass().getResource(urlListe.get(number9));
		resource10 = getClass().getResource(urlListe.get(number10));
		resource11 = getClass().getResource(urlListe.get(number11));
		resource12 = getClass().getResource(urlListe.get(number12));

		icon1 = new ImageIcon(resource1);
		icon2 = new ImageIcon(resource2);
		icon3 = new ImageIcon(resource3);
		icon4 = new ImageIcon(resource4);
		icon5 = new ImageIcon(resource5);
		icon6 = new ImageIcon(resource6);
		icon7 = new ImageIcon(resource7);
		icon8 = new ImageIcon(resource8);
		icon9 = new ImageIcon(resource9);
		icon10 = new ImageIcon(resource10);
		icon11 = new ImageIcon(resource11);
		icon12 = new ImageIcon(resource12);

		b1.setIcon(icon1);
		b2.setIcon(icon2);
		b3.setIcon(icon3);
		b4.setIcon(icon4);
		b5.setIcon(icon5);
		b6.setIcon(icon6);
		b7.setIcon(icon7);
		b8.setIcon(icon8);
		b9.setIcon(icon9);
		b10.setIcon(icon10);
		b11.setIcon(icon11);
		b12.setIcon(icon12);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);

		panel1.setLayout(new GridLayout(THREE, FOUR, TEN, TEN));

		panel1.add(b1);
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b4);
		panel1.add(b5);
		panel1.add(b6);
		panel1.add(b7);
		panel1.add(b8);
		panel1.add(b9);
		panel1.add(b10);
		panel1.add(b11);
		panel1.add(b12);

		panel1.setBorder(BorderFactory.createEmptyBorder(TWENTY, TWENTY,
				TWENTY, TWENTY));
		this.add(panel1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		actionStart1(source);
		actionStart2(source);
		actionStart3(source);
		actionStart4(source);
		actionStart5(source);
		actionStart6(source);
		actionStart7(source);
		actionStart8(source);
		actionStart9(source);
		actionStart10(source);
		actionStart11(source);
		actionStart12(source);
		

		resetPanel();
		
	}
	
	public void resetPanel() {
		if(index == FOUR) {
			JOptionPane.showMessageDialog(null, "Only THREE cards "
					+ "addicted a SET :)", "Warning", JOptionPane.OK_OPTION);
			reset();
		}
	}
	
	public void actionStart1(Object source) {
		
		if (source.equals(b1) && a1.equals(false)) {
			index++;
			a1 = true;
			card[index] = saveList.get(number1);
		} else if (source.equals(b1) && a1.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart2(Object source) {
		
		if (source.equals(b2) && a2.equals(false)) {
			index++;
			a2 = true;
			card[index] = saveList.get(number2);
		} else if (source.equals(b2) && a2.equals(true)) {
			getPanel();
		}
		
	}


	public void actionStart3(Object source) {
		
		if (source.equals(b3) && a3.equals(false)) {
			index++;
			a3 = true;
			card[index] = saveList.get(number3);
		} else if (source.equals(b3) && a3.equals(true)) {
			getPanel();
		}
	
	}
	
	public void actionStart4(Object source) {
		
		if (source.equals(b4) && a4.equals(false)) {
			index++;
			a4 = true;
			card[index] = saveList.get(number4);
		} else if (source.equals(b4) && a4.equals(a4)) {
			getPanel();
		}
		
	}

	public void actionStart5(Object source) {
		if (source.equals(b5) && a5.equals(false)) {
			index++;
			a5 = true;
			card[index] = saveList.get(number5);
		} else if (source.equals(b5) && a5.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart6(Object source) {
		
		if (source.equals(b6) && a6.equals(false)) {
			index++;
			a6 = true;
			card[index] = saveList.get(number6);
		} else if (source.equals(b6) && a6.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart7(Object source) {
		
		if (source.equals(b7) && a7.equals(false)) {
			index++;
			a7 = true;
			card[index] = saveList.get(number7);
		} else if (source.equals(b7) && a7.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart8(Object source) {
		
		if (source.equals(b8) && a8.equals(false)) {
			index++;
			a8 = true;
			card[index] = saveList.get(number8);
		} else if (source.equals(b8) && a8.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart9(Object source) {
		
		if (source.equals(b9) && a9.equals(false)) {
			index++;
			a9 = true;
			card[index] = saveList.get(number9);
		} else if (source.equals(b9) && a9.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart10(Object source) {
		
		if (source.equals(b10) && a10.equals(false)) {
			index++;
			a10 = true;
			card[index] = saveList.get(number10);
		} else if (source.equals(b10) && a10.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart11(Object source) {
		
		if (source.equals(b11) && a11.equals(false)) {
			index++;
			a11 = true;
			card[index] = saveList.get(number11);
		} else if (source.equals(b11) && a11.equals(true)) {
			getPanel();
		}
		
	}
	
	public void actionStart12(Object source) {
		

		if (source.equals(b12) && a12.equals(false)) {
			index++;
			a12 = true;
			card[index] = saveList.get(number12);
		} else if (source.equals(b12) && a12.equals(true)) {
			getPanel();
		}
		
	}
	
	public void getPanel() {
		JOptionPane.showMessageDialog(null, "" + "Not a second time :P",
				"Warning", JOptionPane.OK_OPTION);
	}

	public static ICard getCardforSetOne() {
		reset();
		return card[ONE];
	}

	public static ICard getCardforSetTwo() {
		return card[TWO];
	}

	public static ICard getCardforSetThree() {
		return card[THREE];
	}

	private static void reset() {
		a1 = false;
		a2 = false;
		a3 = false;
		a4 = false;
		a5 = false;
		a6 = false;
		a7 = false;
		a8 = false;
		a9 = false;
		a10 = false;
		a11 = false;
		a12 = false;

		index = ZERO;
	}

	public static void updateLink() {

		urlListe.clear();
		saveList.clear();

		saveUrl();

		cardkey = ZERO;

		reset();
		updateGF();

	}

	private static void updateGF() {
		resource13 = ImageIcon.class.getResource(urlListe.get(number1));
		resource14 = ImageIcon.class.getResource(urlListe.get(number2));
		resource15 = ImageIcon.class.getResource(urlListe.get(number3));
		resource16 = ImageIcon.class.getResource(urlListe.get(number4));
		resource17 = ImageIcon.class.getResource(urlListe.get(number5));
		resource18 = ImageIcon.class.getResource(urlListe.get(number6));
		resource19 = ImageIcon.class.getResource(urlListe.get(number7));
		resource20 = ImageIcon.class.getResource(urlListe.get(number8));
		resource21 = ImageIcon.class.getResource(urlListe.get(number9));
		resource22 = ImageIcon.class.getResource(urlListe.get(number10));
		resource23 = ImageIcon.class.getResource(urlListe.get(number11));
		resource24 = ImageIcon.class.getResource(urlListe.get(number12));

		icon13 = new ImageIcon(resource13);
		icon14 = new ImageIcon(resource14);
		icon15 = new ImageIcon(resource15);
		icon16 = new ImageIcon(resource16);
		icon17 = new ImageIcon(resource17);
		icon18 = new ImageIcon(resource18);
		icon19 = new ImageIcon(resource19);
		icon20 = new ImageIcon(resource20);
		icon21 = new ImageIcon(resource21);
		icon22 = new ImageIcon(resource22);
		icon23 = new ImageIcon(resource23);
		icon24 = new ImageIcon(resource24);

		b1.setIcon(icon13);
		b2.setIcon(icon14);
		b3.setIcon(icon15);
		b4.setIcon(icon16);
		b5.setIcon(icon17);
		b6.setIcon(icon18);
		b7.setIcon(icon19);
		b8.setIcon(icon20);
		b9.setIcon(icon21);
		b10.setIcon(icon22);
		b11.setIcon(icon23);
		b12.setIcon(icon24);
	}
	
	public static int numberGiven() {
		return iPack.getPack().size();
	}
	
	public static void saveUrl() {
		for (int index1 = 0; index1 < numberGiven() ; index1++) {
			string(index1);
			cardkey++;
		}
	}
	
	public static void string(int index1) {
		String s = iPack.getPack().get(index1).toString();
		saveUrlFor(s);
	}
	 
	public static void saveUrlFor(String card) {
		for (ICard key : GUI.getController().getCardInFieldGame()) {
			if (card.equals(key.toString())) {
				urlListe.add(cardToPicture.get(cardkey));
				saveList.add(key);
            }
		}
	}

}
