package locations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Board;

/**
 * Is a square that a player can start on.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class StarterSquare extends Location {
	
	private final String imageString = "/assets/starter.png";
	private BufferedImage image = null;

	/**
	 * Create a StarterSquare
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public StarterSquare() {
		super();
	}
	
	@Override
	public void addLocation(Location l){
		if (l != null && l instanceof Square){
			actualAddLocation(l);
		}
	}

	@Override
	public void print() {
		System.out.print("@");
	}

	@Override
	public void draw(Graphics g, int x, int y, Board board, int coX, int coY) {
		try{
			if(image == null){
				this.image = ImageIO.read(getClass().getResource(imageString));
			}
			g.drawImage(image,x,y,null);
		}catch(IOException e){
			System.err.println("IOException while drawing starterSquare");
		}
	}

}
