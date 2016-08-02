package game;

import control.*;
import locations.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Base class for text based Cluedo game
 * 
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Cluedo {
	
	private Board board;
	private Player players[];
	private Character characters[];
	private Room rooms[];
	private Weapon weapons[];
	private Dice dice;
	private Solution solution;
	
	
	public Cluedo(int numPlayers){
		//Initialising characters
		characters = new Character[6];
		characters[0] = new Character("Scarlett");
		characters[1] = new Character("Mustard");
		characters[2] = new Character("White");
		characters[3] = new Character("Green");
		characters[4] = new Character("Peacock");
		characters[5] = new Character("Plum");

		//Initialising rooms
		rooms = new Room[9];
		rooms[0] = new Room("Kitchen");
		rooms[1] = new Room("Ball Room");
		rooms[2] = new Room("Conservatory");
		rooms[3] = new Room("Billiard Room");
		rooms[4] = new Room("Library");
		rooms[5] = new Room("Study");
		rooms[6] = new Room("Hall");
		rooms[7] = new Room("Lounge");
		rooms[8] = new Room("Dining Room");
		
		//Initialising weapons
		weapons = new Weapon[6];
		weapons[0] = new Weapon("Candlestick");
		weapons[1] = new Weapon("Dagger");
		weapons[2] = new Weapon("Lead Pipe");
		weapons[3] = new Weapon("Revolver");
		weapons[4] = new Weapon("Rope");
		weapons[5] = new Weapon("Spanner");
		
		//Create new board
		board = new Board(characters, rooms);

		//Initialising players
		players = new Player[numPlayers];
		initialisePlayers();
		
		dealCards();
		
		loop();
	}
	
	public Player[] initialisePlayers(){
		for (int i = 0; i < players.length; i++){
			System.out.println("Player "+(i+1)+
						": Enter number of character");
			int characterNum = 0;
			Character[] availableCharacters = new Character[characters.length];
			for (Character c : characters){
				if (! c.isSelected()){
					System.out.print(characterNum+": "+c.cardName()+"\n");
					availableCharacters[characterNum] = c;
					characterNum++;
				}
			}
			System.out.println();
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String in = reader.readLine();
				int selection = 0;
				boolean valid = false;
				while (!valid){
					try{
						selection = Integer.parseInt(in);
						if (selection >= 0 && selection < characterNum)
							valid = true;
						else
							valid = false;
					} catch(Exception e){
						valid = false;
					}
					if (!valid){
						System.out.println("Number must be in list");
						in = reader.readLine();
					}
				}
				players[i] = new Player(availableCharacters[selection]);
				availableCharacters[selection].select();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return players;
	}
	
	public void dealCards(){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		int characterIndexForSolution = new Dice(1,characters.length).roll()-1;
		int roomIndexForSolution = new Dice(1,rooms.length).roll()-1;
		int weaponIndexForSolution = new Dice(1,weapons.length).roll()-1;
		
		for (int i = 0; i < characters.length; i++){
			if (i != characterIndexForSolution)
				cards.add(characters[i]);
		}
		for (int i = 0; i < rooms.length; i++){
			if (i != roomIndexForSolution)
				cards.add(rooms[i]);
		}
		for (int i = 0; i < weapons.length; i++){
			if (i != weaponIndexForSolution)
				cards.add(weapons[i]);
		}
		
		this.solution = new Solution(characters[characterIndexForSolution],
						rooms[roomIndexForSolution],
						weapons[weaponIndexForSolution]);
		
		Collections.shuffle(cards);
		
		for (int i = 0; i < cards.size(); i++){
			players[i % players.length].giveCard(cards.get(i));
		}
	}
	
	public void loop(){
		boolean running = true;
		while(running){
			for (int i = 0; i < players.length; i++){
				Player p = players[i];
				System.out.println(p.name() + ": Your turn");
				board.print();
				
				try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					//TODO: Continue loop
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
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
