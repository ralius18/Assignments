package locations;

import control.*;

/**
 * Is a square on the board. A square that players move around on, in between rooms.
 * Also container for other types of squares
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Square extends Location {
	
	private Player player;
	private boolean isEntrance;
	
	/**
	 * Creates square, which could be an entrance to a room
	 * @param isEntrance
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
	
	/**
	 * Sets player on the square
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	
	/**
	 * @return Whether the Square is an entrance
	 */
	public boolean isEntrance(){
		return isEntrance;
	}
	
	@Override
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
