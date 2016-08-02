package locations;

import control.Player;

/**
 * Represents a doorway for the board. Is used to get the players into rooms.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Doorway extends Square {

	private Doorway other;
	
	/**
	 * Create a Doorway
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public Doorway(int x, int y, Player player, Room room) {
		super(x, y, player, room);
	}

	/**
	 * @return the other doorway that links to this.
	 */
	public Doorway getOther() {
		return other;
	}

	/**
	 * @param the other doorway that connects to this one.
	 */
	public void setOther(Doorway other) {
		this.other = other;
	}

}
