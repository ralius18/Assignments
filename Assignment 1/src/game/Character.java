package game;

/**
 * Represents a character in the game (not a player)
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Character implements Card{
	
	private String name;
	public enum NAME{
		Scarlett,
		Mustard,
		White,
		Green,
		Peacock,
		Plum
	}
	
	public Character(String name){
		this.name = name;
	}
	
	public String cardName(){
		return name;
	}

}
