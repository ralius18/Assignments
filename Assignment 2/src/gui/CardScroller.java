package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import game.Card;

public class CardScroller extends JScrollPane {
	
	private List<Card> processing = null;
	private CardPanel cardPanel = null;
	
	public CardScroller(JComponent component){
		super(component);
		cardPanel = (CardPanel) component;
		this.setPreferredSize(new Dimension(375, 150));
		setSize(375, 150);
	}
	
	public void addHand(List<Card> hand){
		processing = hand;
		int width = hand.size()*75;
		cardPanel.setPreferredSize(new Dimension(width, 125));
		cardPanel.setSize(width, 125);
		cardPanel.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		cardPanel.addCards(processing);
		cardPanel.repaint();
	}
}
