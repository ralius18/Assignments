package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import control.Dice;

public class DicePanel extends JPanel {

	private Dice dice;
	
	public DicePanel(Dice dice){
		this.dice = dice;
		setPreferredSize(new Dimension(50, 50));
		setSize(50,50);
	}
	
	@Override
	public void paint(Graphics g){
		dice.draw(g);
	}
}
