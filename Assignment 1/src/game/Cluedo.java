package game;

import control.*;
import locations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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
				board.printBoard();
				
				try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					//Begin processing a single player
					p.move(board);
					Location currentLocation = board.getLocationAtPoint(board.getPosition(p.getCharacter()));
					if (currentLocation instanceof Room || currentLocation instanceof Middle){
						if (currentLocation instanceof Middle){
							System.out.println("Would you like to attempt to solve the murder? If you are wrong, you cannot continure (y/n)");
						}
						else {
							System.out.println("Would you like to make a guess in the "+((Room)currentLocation).cardName()+"? (y/n)");
						}
						boolean valid = false;
						boolean makingGuess = false;
						while (!valid){
							String input = reader.readLine();
							if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
								valid = true;
								makingGuess = true;
							}
							else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")){
								valid = true;
								makingGuess = false;
							}
							else {
								System.out.println("Enter either 'y' or 'n'");
							}
						}
						if (makingGuess){
							System.out.println("Your current hand:\n"+p.handToString());
							System.out.println("\nWeapons:\n");
							for (int j = 0; j < weapons.length; j++){
								System.out.println("["+j+"]"+weapons[j].cardName()+" ");
							}
							System.out.println();
							
							valid = false;
							Weapon chosenWeapon = null;
							while (!valid){
								System.out.println("Choose weapon: (Enter number)");
								valid = true;
								try{
									int index = Integer.parseInt(reader.readLine());
									chosenWeapon = weapons[index];
								} catch (Exception e){
									System.out.println("Invalid input");
									valid = false;
								}
							}
							
							System.out.println("\nCharacters:\n");
							for (int j = 0; j < characters.length; j++){
								System.out.println("["+j+"]:"+characters[j].cardName()+ " ");
							}
							System.out.println();
							valid = false;
							Character chosenCharacter = null;
							while (!valid){
								System.out.println("Choose character: (Enter number)");
								valid = true;
								try{
									int index = Integer.parseInt(reader.readLine());
									chosenCharacter = characters[index];
								} catch (Exception e){
									System.out.println("Invalid input");
									valid = false;
								}
							}
							
							if (currentLocation instanceof Room){
								board.setPosition(chosenCharacter, board.getPosition(p.getCharacter()));
							}
							
							Room chosenRoom = null;
							if (currentLocation instanceof Room){
								chosenRoom = (Room) currentLocation;
							}
							else if (currentLocation instanceof Middle){
								System.out.println("Rooms:\n");
								for (int j = 0; j < rooms.length; j++){
									System.out.print("["+j+"]:"+rooms[j].cardName()+ " ");
									if (j < rooms.length-1){
										System.out.print("| ");
									}
								}
								System.out.println();
								valid = false;
								while (!valid){
									System.out.println("Choose room: (Enter number)");
									valid = true;
									try {
										int index = Integer.parseInt(reader.readLine());
										chosenRoom = rooms[index];
									} catch (Exception e){
										System.out.println("Invalid input");
										valid = false;
									}
								}
							}
							
							if (currentLocation instanceof Middle){
								if (solution.checkGuess(chosenCharacter, chosenWeapon, chosenRoom)){
									System.out.println(p.getCharacter().cardName()+" has solved it!\n"
											+ "The solution was: ");
									solution.print();
									running = false;
									return;
								}
								else {
									ArrayList<Card> cardsLeftOver = p.getHand();
									players[i] = null;
									while (! cardsLeftOver.isEmpty()){
										for (int j = 0; j < players.length && !cardsLeftOver.isEmpty(); j++){
											if (players[j] != null){
												players[j].giveCard(cardsLeftOver.remove(0));
											}
										}
									}
									System.out.println(p.getCharacter().cardName()
											+" made an incorrect guess. They are out of the game");
								}
							}
							else{
								Player[] otherPlayers = new Player[players.length-1];
								int index = 0;
								for (int j = 0; j < i; j++){
									otherPlayers[index++] = players[j];
								}
								for (int j = i+1; j < players.length; j++){
									otherPlayers[index++] = players[j];
								}
								for (Player otherPlayer : otherPlayers){
									Card card = otherPlayer.getCard(chosenCharacter, chosenWeapon, chosenRoom);
									if (card != null){
										System.out.println(otherPlayer.getCharacter().cardName()
												+" has shown you their card: ["+card.cardName()+"]");
										break;
									}
									else {
										System.out.println(otherPlayer.getCharacter().cardName()
												+" does not have any of the cards that you guessed");
									}
								}
							}
						}
					} //End of player turn
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			ArrayList<Player> newPlayers = new ArrayList<Player>();
			for (int i = 0; i < players.length; i++){
				if (players[i] != null){
					newPlayers.add(players[i]);
				}
			}
			players = new Player[newPlayers.size()];
			for (int i = 0; i < players.length; i++){
				players[i] = newPlayers.get(i);
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
