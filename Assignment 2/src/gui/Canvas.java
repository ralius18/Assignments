package gui;

import javax.swing.JPanel;

import game.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JPanel {

	private Board board;
	private GUI gui;
	
	public Canvas(int width, int height, Board board, GUI parent){
		this.gui = parent;
		this.board = board;
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.WHITE);
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e){
				canvasMouseMoved(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e){
				//No implementation
			}
		});
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				canvasMouseClicked(e);
			}
		});
	}

	private void canvasMouseClicked(MouseEvent e) {
		board.boardMouseClicked(e.getX(), e.getY());
		gui.repaint();
	}

	private void canvasMouseMoved(MouseEvent e) {
		board.mouseHover(e.getX(), e.getY());
		gui.repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, getWidth(), getHeight()-2);
		board.draw(g, getWidth(), getHeight());
	}
}
