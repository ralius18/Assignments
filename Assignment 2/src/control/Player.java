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
	public void move(Board board, Dice dice){
		Point currentPos = board.getPosition(this.character);
		int roll = dice.roll();
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
		boolean valid = false;
		while (!valid){
			valid = false; //TODO: not sure
			Location currentLocation = board.getLocationAtPoint(currentPos);
			Object[] clickedLocation = board.getClickedLocation();
			while (clickedLocation == null){
				try{
					Thread.sleep(100);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				clickedLocation = board.getClickedLocation();
			}
			Location inputLocation = (Location) clickedLocation[0];
			if (isValid(0, roll, inputLocation, currentLocation, board)){
				return new Point(((int[])clickedLocation[1])[0], ((int[])clickedLocation[1])[1]);
			}
			else{
				valid = false;
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
