package game;

public class Solution {

	private Character character;
	private Room room;
	private Weapon weapon;
	private boolean isSolved = false;
	
	public Solution(Character character, Room room, Weapon weapon){
		this.character = character;
		this.room = room;
		this.weapon = weapon;
	}
}
