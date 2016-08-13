package locations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Board;

/**
 * Is a stairwell that connects to another stairwell in another room.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Stairwell extends Location {
	
	private Room otherRoom;
	private final String imageString = "/assets/stairwell.png";
	private BufferedImage image = null;

	/**
	 * Creates stairwell that links to other corner of board
	 * @param otherRoom
	 */
	public Stairwell(Room otherRoom) {
		super();
		this.otherRoom = otherRoom;
		addLocation(otherRoom);
	}

	/**
	 * @return The other Stairwell that links to this one. 
	 */
	public Room getOther() {
		return otherRoom;
	}
	
	@Override
	public void addLocation(Location l) {
		if (l instanceof Room){
			actualAddLocation(l);
		}
	}

	@Override
	public void print() {
		System.out.print("U");
	}

	@Override
	public void draw(Graphics g, int x, int y, Board board, int coX, int coY) {
		try{
			if(image == null){
				this.image = ImageIO.read(getClass().getResource(imageString));
			}
			g.drawImage(image,x,y,null);
		}catch(IOException e){
			System.err.println("IOException while drawing stairwell");
		}
	}
	
}
