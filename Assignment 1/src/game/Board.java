package game;

import java.util.ArrayList;

import locations.Room;

/**
 * Class to control the board, storing all objects and their position
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Board {
	
	private ArrayList<Character> characters;
	private ArrayList<Room> rooms;

	public Board (ArrayList<Character> characters, ArrayList<Room> rooms){
		this.characters = characters;
		this.rooms = rooms;
	}
	
}
