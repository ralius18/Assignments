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
	private boolean isSelected = false;
	
	public Character(String name){
		this.name = name;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void select(){
		isSelected = true;
	}
	
	public String cardName(){
		return name;
	}

}
