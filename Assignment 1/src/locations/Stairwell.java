package locations;

/**
 * Is a stairwell that connects to another stairwell in another room.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Stairwell extends Location {
	
	private Room otherRoom;

	/**
	 * Creates stairwell that links to other corner of board
	 * @param otherRoom
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
		System.out.print("U");
	}
	
}
