package locations;

import java.util.ArrayList;

public abstract class Location {
	
	private ArrayList<Location> validMoves = new ArrayList<Location>();
	
	public ArrayList<Location> getValidMoves(){
		return validMoves;
	}
	
	public abstract void addLocation(Location l);
	
	public void actualAddLocation(Location l){
		if (!validMoves.contains(l))
			validMoves.add(l);
	}
	
	public abstract String toString();
}
