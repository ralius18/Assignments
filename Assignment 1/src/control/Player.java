package control;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.*;
import game.Character;
import locations.*;

/**
 * This represents a player, with a corresponding Character and a hand of cards.
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Player {

	private Character character;
	private ArrayList<Card> hand;
	
	/**
	 * Creates player and instantiates a hand
	 * @param character
	 */
	public Player(Character character){
		this.character = character;
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * Rolls the dice and attempts to move 
	 * @param board
	 */
	public void move(Board board){
		Point currentPos = board.getPosition(this.character);
		int roll = board.getDice().roll();
		System.out.println("You rolled " + roll);
		Point move = attemptMove(roll, currentPos, board);
		board.setPosition(this.character, move);
	}
	
	/**
	 * Attempts a move by asking the player for a coordinate, then checking if the player
	 * can move there from their current location
	 * @param roll
	 * @param currentPos
	 * @param board
	 * @return Point to move to
	 */
	public Point attemptMove(int roll, Point currentPos, Board board) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean valid = false;
		while (!valid){
			String input = "";
			System.out.println("Enter coordinate to move to: (Capital letter 1st, numbers second)");
			
			try {
				input = reader.readLine();
			} catch (IOException e){
				e.printStackTrace();
			}
			
			if (input.length() == 2 || input.length() == 3){
				try {
					Integer.parseInt(input.substring(1)); //second (and third) characters are integers
					valid = true;
				} catch (Exception f){
					System.out.println("Second and third characters must be digits");
				}
				try {
					Integer.parseInt(input.substring(0,1));
					valid = false;
					System.out.println("First character must be a capital letter");
				} catch (Exception f){
					valid = true;
				}
				
				if (valid){
					valid = false;
					//converting char to int
					try {
						int x = input.charAt(0) - 'A';
						if (x >= 0){
							int y = Integer.parseInt(input.substring(1,input.length()));
							Point inputPoint = new Point(x,y);
							Location characterSquare = board.getSquareFromPoint(currentPos);
							Location inputSquare = board.getSquareFromPoint(inputPoint);
							if (isValid(0, roll, characterSquare, inputSquare, board)){
								return new Point(x,y);
							}
							else {
								valid = false;
							}
						}
					} catch (Exception f){
						System.out.println("Letter must be capital");
						valid = false;
						continue;
					}
				}
			}
			else { //not right length
				System.out.println("Enter coordinate correctly");
			}
		}
		
		return null; //unreachable
	}

	/**
	 * Recursive method which traverses the validMoves and checks if it can get to the target location
	 * @param depth
	 * @param maxDepth
	 * @param current
	 * @param to
	 * @param board
	 * @return
	 */
	public boolean isValid(int depth, int maxDepth, Location current, Location to, Board board) {
		if (depth > maxDepth){
			return false;
		}
		else if (current == to){
			return true;
		}
		
		for (Location location: current.getValidMoves()){
			if (isValid(depth+1, maxDepth, location, to, board)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Give the player the given card
	 * @param card
	 */
	public void giveCard(Card card){
		hand.add(card);
	}
	
	/**
	 * @return Player's current hand of cards
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/**
	 * @return Player's character's name
	 */
	public String name(){
		return character.cardName();
	}

	/**
	 * @return Player's character
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * @return Player's hand as a string
	 */
	public String handToString(){
		String result = "";
		for (Card c : hand){
			result += "[" + c.cardName() + "]";
		}
		return result;
	}

	/**
	 * Gets a card in the player's hand which corresponds to any
	 * of the given parameters (but only one).
	 * Order is Character -> Weapon -> Room
	 * 
	 * NOTE: Could override equals() in card to make it look simpler,
	 * but does the same job.
	 * 
	 * @param character
	 * @param weapon
	 * @param room
	 * @return Card which equals one of the parameters
	 */
	public Card getCard(Character character, Weapon weapon, Room room) {
		for (Card c : hand){
			if (c.cardName().equals(character.cardName()))
				return c;
			else if (c.cardName().equals(weapon.cardName()))
				return c;
			else if (c.cardName().equals(room.cardName()))
				return c;
		}
		return null;
	}
	
}
