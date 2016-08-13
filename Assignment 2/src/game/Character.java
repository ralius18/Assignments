package game;

import java.awt.Graphics;

/**
 * Represents a character in the game (not a player)
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public abstract class Character implements Card{
	
	private String name;
	private boolean isSelected = false;
	private boolean isHovering = false;
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void select(){
		isSelected = true;
	}
	
	public String cardName(){
		return name;
	}
	
	public abstract String getName();
	
	public abstract char toChar();

	public void setHovering(boolean hovering) {
		this.isHovering = hovering;
	}
	
	public abstract void draw(Graphics g, int x, int y);
	
	public boolean isHovering(){
		return isHovering;
	}

}
