package locations;

import control.*;
import game.*;

/**
 * Is a square on the board.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Square {

	//I set these to public final cause I assume we won't be changing them, plus easy access.
	public final int x;
	public final int y;
	public final Room room;
	
	//There will be different players on squares.
	private Player player;
	
	//What if we have 2 people on the same square?? TODO
	//private Set<Player> multiplePlayers = new HashSet<>();
	
	/**
	 * Create a Square
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public Square(int x, int y, Player player, Room room){
		this.x = x;
		this.y = y;
		this.player = player;
		this.room = room;
	}
	
	/**
	 * @return the player on this square (Can be null! Which means no player is on this square).
	 */
	public Player getPlayer(){
		return player;
	}
	
}
