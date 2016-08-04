package locations;

import java.util.ArrayList;

public abstract class Location {
	
	//Collection of Locations that can be moved to from here
	private ArrayList<Location> validMoves = new ArrayList<Location>();
	
	/**
	 * @return ArrayList of valid Locations to move to
	 */
	public ArrayList<Location> getValidMoves(){
		return validMoves;
	}
	
	/**
	 * Checks that the given location can be moved to from this location
	 * @param location
	 */
	public abstract void addLocation(Location l);
	
	/**
	 * Actually adds the location to vlaidMoves
	 * @param location
	 */
	public void actualAddLocation(Location l){
		if (!validMoves.contains(l))
			validMoves.add(l);
	}
	
	/**
	 * Prints the location to console
	 */
	public abstract void print();
}
