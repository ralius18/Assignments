package game;

/**
 * Represents a room that a player can be in
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Room extends Card implements Location{
	
	private String name;
	
	public Room(String name){
		super();
		this.name = name;
	}

	public String toString(){
		return this.name;
	}

}
