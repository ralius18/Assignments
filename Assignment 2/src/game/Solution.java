package game;

import locations.Room;

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

	public boolean checkGuess(Character character, Weapon weapon, Room room) {
		return (this.character.equals(character) &&
				this.weapon.equals(weapon) &&
				this.room.equals(room));
	}
	
	public void print(){
		if (isSolved){
			System.out.println("Character: "+character.cardName()+"\nWeapon: "+
								weapon.cardName()+"\nRoom: "+room.cardName());
		}
		else {
			System.out.println("Solution not yet solved");
		}
	}
}
