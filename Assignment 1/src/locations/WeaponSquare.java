package locations;

import control.Player;
import game.Weapon;

/**
 * Is a square that holds a weapon that can be picked up by the players.
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class WeaponSquare extends Square {
	
	//TODO: is the weapon picked up once and once only??
	private Weapon weapon;

	public WeaponSquare(int x, int y, Player player, Room room, Weapon weapon) {
		super(x, y, player, room);
		this.weapon = weapon;
	}
	
	/**
	 * Get the weapon that is stored on this square. Does not remove the weapon
	 * (much like the peek method for a stack)
	 * @return weapon on this square.
	 */
	public Weapon getWeapon(){
		return weapon;
	}

	/**
	 * Takes the weapon off of this square.
	 * @return weapon on this square.
	 */
	public Weapon takeWeapon(){
		assert weapon != null: "No weapon to take (need a different way to deal with this).";
		Weapon temp = weapon;
		this.weapon = null;
		return temp;
	}
}
