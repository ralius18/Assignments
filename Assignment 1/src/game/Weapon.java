package game;

public class Weapon implements Card{
	
	private String name;
	public enum TYPE{
		Candlestick,
		Dagger,
		Pipe,
		Revolver,
		Rope,
		Spanner
	}

	public Weapon(String name){
		this.name = name;
	}
	
	public String cardName(){
		return name;
	}
	
}
