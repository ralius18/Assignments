package locations;

import java.util.ArrayList;

import control.Player;

public class RoomSquare extends Square {
	
	//List of possible locations to move to
	private ArrayList<Square> validMoves;

	public RoomSquare(int x, int y, Player player, Room room) {
		super(x, y, player, room);
	}
	
	@Override
	public void addSquare(Square s){
		if (s instanceof Doorway || s instanceof RoomSquare 
				|| s instanceof Stairwell){
			if (!validMoves.contains(s))
				validMoves.add(s);
		}
	}

}
