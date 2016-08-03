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
public abstract class Square {

	//I set these to public final cause I assume we won't be changing them, plus easy access.
	public final int x;
	public final int y;
	public final Room room;
	
	//There will be different players on squares.
	private Player player;
	
	//List of possible locations to move to
	private ArrayList<Square> validMoves;
	
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
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public ArrayList<Square> getValidMoves(){
		return validMoves;
	}
	
	public void addSquare(Square s){
		if (s != null){
			if (s instanceof Doorway || s instanceof Square){
				if (!validMoves.contains(s))
					validMoves.add(s);
			}
		}
	}
	
}
