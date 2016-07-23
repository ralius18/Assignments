package game;

import control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Base class for text based Cluedo game
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Cluedo {
	
	private Board board;
	private ArrayList<Player> players;
	private ArrayList<Character> characters;
	private ArrayList<Room> rooms;
	private ArrayList<Weapon> weapons;
	private Solution solution;
	
	
	public Cluedo(int numPlayers){
		//Initialising characters
		characters = new ArrayList<Character>();
		characters.add(new Character("Scarlett"));
		characters.add(new Character("Mustard"));
		characters.add(new Character("White"));
		characters.add(new Character("Green"));
		characters.add(new Character("Peacock"));
		characters.add(new Character("Plum"));

		//Initialising rooms
		rooms = new ArrayList<Room>();
		rooms.add(new Room("Kitchen"));
		rooms.add(new Room("Ball Room"));
		rooms.add(new Room("Conservatory"));
		rooms.add(new Room("Billiard Room"));
		rooms.add(new Room("Library"));
		rooms.add(new Room("Study"));
		rooms.add(new Room("Hall"));
		rooms.add(new Room("Lounge"));
		rooms.add(new Room("Dining Room"));
		
		//Initialising weapons
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon("Candlestick"));
		weapons.add(new Weapon("Dagger"));
		weapons.add(new Weapon("Lead Pipe"));
		weapons.add(new Weapon("Revolver"));
		weapons.add(new Weapon("Rope"));
		weapons.add(new Weapon("Spanner"));
		
		//Create new board
		board = new Board(characters, rooms);

		//Initialising players
		players = new ArrayList<Player>(numPlayers);
		//TODO: Actually initialise the players
		
		dealCards();
	}
	
	public void dealCards(){
		
	}

	public static void main(String[] args){
		System.out.println("*** CLUEDO V1.0 ***");
		System.out.println("By Brad Stone & Jarvis Dunn, 2016");
		System.out.println("How many players? (3-6)");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String numString = "";
		int numPlayers = 0;
		boolean valid = false;
		while (!valid){
			try{
				valid = true;
				numString = reader.readLine();
				numPlayers = Integer.parseInt(numString);
				if (numPlayers < 3 || numPlayers > 6){
					valid = false;
					System.out.println("Must be between 3 and 6 players");
				}
			}
			catch (Exception e){
				valid = false;
				System.out.println("Must be between 3 and 6 players");
			}
		}
		
		new Cluedo(numPlayers);
	}
}
