package locations;

import control.Player;

/**
 * Is a stairwell that connects connects to another stairwell in another room.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Stairwell extends Square {
	
	private Stairwell other;
	
	//TODO: I assume with the other stairwell can not be put in through the constructor. 

	/**
	 * Create a Stairwell
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public Stairwell(int x, int y, Player player, Room room) {
		super(x, y, player, room);
	}

	/**
	 * @return The other Stairwell that links to this one. 
	 */
	public Stairwell getOther() {
		return other;
	}

	/**
	 * @param Set the other Stairwell that links to this one. 
	 */
	public void setOther(Stairwell other) {
		this.other = other;
	}
	
}
