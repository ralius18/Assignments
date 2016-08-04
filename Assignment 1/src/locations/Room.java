package locations;

import game.*;

/**
 * Represents a room that a player can be in
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Room extends Location implements Card{
	
	private String name;
	private Square squares[][];
	public enum TYPE{
		Kitchen,
		BallRoom,
		Conservatory,
		BilliardRoom,
		Library,
		Study,
		Hall,
		Lounge,
		DiningRoom
	}
	
	public Room(String name){
		this.name = name;
	}

	public String cardName() {
		return name;
	}
	
	public Square[][] getSquares(){
		return squares;
	}
	
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
}
