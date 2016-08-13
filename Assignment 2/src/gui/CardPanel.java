package gui;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import game.Card;

public class CardPanel extends JPanel {
	
	List<Card> cards;
	
	public void addCards(List<Card> cards){
		cards = cards;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (cards == null){
			return;
		}
		for (int i = 0; i < cards.size(); i++){
			cards.get(i).drawCard(g, i*75, 0); //75 = card width
		}
	}
}
