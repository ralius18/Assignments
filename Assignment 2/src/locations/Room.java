package locations;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import game.*;

/**
 * Represents a room that a player can be in
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public abstract class Room extends Location implements Card{
	
//	private String name;
//	private Square squares[][];
	private HashMap<String, BufferedImage> roomSquares = new HashMap<String, BufferedImage>();
	
	public Room(){
		super();
		String pre = "/assets/";
		String post = "room.png";
		try{
			roomSquares.put("", ImageIO.read(getClass().getResource(
					pre+""+post)));
			roomSquares.put("N", ImageIO.read(getClass().getResource(
					pre+"N"+post)));
			roomSquares.put("E", ImageIO.read(getClass().getResource(
					pre+"E"+post)));
			roomSquares.put("S", ImageIO.read(getClass().getResource(
					pre+"S"+post)));
			roomSquares.put("W", ImageIO.read(getClass().getResource(
					pre+"W"+post)));
			roomSquares.put("NE", ImageIO.read(getClass().getResource(
					pre+"NE"+post)));
			roomSquares.put("SE", ImageIO.read(getClass().getResource(
					pre+"SE"+post)));
			roomSquares.put("NW", ImageIO.read(getClass().getResource(
					pre+"NW"+post)));
			roomSquares.put("SW", ImageIO.read(getClass().getResource(
					pre+"SW"+post)));
		} catch(IOException e){
			System.err.println("IOException when drawing room");
		}
	}

//	public String cardName() {
//		return name;
//	}
//	
//	public Square[][] getSquares(){
//		return squares;
//	}
//	
	@Override
	public void addLocation(Location l){
		if (l != null){
			if (l instanceof Stairwell){
				actualAddLocation(l);
			}
			else if (l instanceof Square){
				if (((Square) l).isEntrance())
					actualAddLocation(l);
			}
		}
	}

	@Override
	public void print() {
		System.out.print("#");;
	}
	
	@Override
	public void draw(Graphics g, int x, int y, Board board, int coX, int coY){
		String type = "";

		Location north = null;
		Location south = null;
		Location east = null;
		Location west = null;
		if (coX < board.getWidth() - 1) {
			east = board.getLocationAtPoint(new Point(coX + 1, coY));
		}
		if (coX > 0) {
			west = board.getLocationAtPoint(new Point(coX - 1, coY));
		}
		if (coY < board.getHeight() - 1) {
			south = board.getLocationAtPoint(new Point(coX, coY + 1));
		}
		if (coY > 0) {
			north = board.getLocationAtPoint(new Point(coX, coY - 1));
		}
		if (north == null
				|| (north instanceof Square && !((Square) north).isEntrance())) {
			type += "N";
		}
		if (south == null
				|| (south instanceof Square && !((Square) south).isEntrance())) {
			type += "S";
		}
		if (east == null
				|| (east instanceof Square && !((Square) east).isEntrance())) {
			type += "E";
		}
		if (west == null
				|| (west instanceof Square && !((Square) west).isEntrance())) {
			type += "W";
		}

		g.drawImage(roomSquares.get(type), x, y, null);
	}
}
