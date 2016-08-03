package locations;

public class Middle extends Location {

	@Override
	public void addLocation(Location l) {
		if (l != null){
			if (l instanceof Square && ((Square)l).isEntrance()){
				actualAddLocation(l);
			}
		}

	}

	@Override
	public void print() {
		System.out.print("M");;
	}

}
