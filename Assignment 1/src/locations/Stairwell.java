package locations;

import java.util.ArrayList;

import control.Player;

/**
 * Is a stairwell that connects connects to another stairwell in another room.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Stairwell extends Location {
	
	private Room otherRoom;
	
	//List of possible locations to move to
	private ArrayList<Square> validMoves;
	
	//TODO: I assume with the other stairwell can not be put in through the constructor. 

	/**
	 * Create a Stairwell
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
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
		System.out.println("U");
	}
	
}
