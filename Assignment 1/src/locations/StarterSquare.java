package locations;

import control.Player;

/**
 * Is a square that a player can start on.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class StarterSquare extends Square {

	/**
	 * Create a StarterSquare
	 * @param x position on board.
	 * @param y position on board.
	 * @param player on this square (null if no player on square).
	 * @param room that this square is in.
	 */
	public StarterSquare(int x, int y, Player player, Room room) {
		super(x, y, player, room);
	}

}
