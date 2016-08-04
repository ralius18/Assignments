package locations;

import java.util.ArrayList;

import control.*;
import game.*;

/**
 * Is a square on the board. A square that players move around on, in between rooms.
 * Also container for other types of squares
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Square extends Location {
	
	//There will be different players on squares.
	private Player player;
	
	private boolean isEntrance;
	
	//What if we have 2 people on the same square?? TODO
	//private Set<Player> multiplePlayers = new HashSet<>();
	
	/**
	 * Create a Square
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public Square(boolean isEntrance){
		super();
		this.isEntrance = isEntrance;
	}
	
	/**
	 * @return the player on this square (Can be null! Which means no player is on this square).
	 */
	public Player getPlayer(){
		return player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public boolean isEntrance(){
		return isEntrance;
	}
	
	public void addLocation(Location l){
		if (l != null){
			if (l instanceof Room && isEntrance){
				actualAddLocation(l);
			}
			else if (! (l instanceof Room)){
				actualAddLocation(l);
			}
		}
	}
	
	@Override
	public void print(){
		if (isEntrance){
			System.out.print("E");
		}
		else{
			System.out.print("*");
		}
	}
	
}
