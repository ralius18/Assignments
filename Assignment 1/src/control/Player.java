package control;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.*;
import game.Character;
import locations.*;

public class Player {

	private Character character;
	private int uid;
	private Location currentLocation;
	private ArrayList<Card> hand;
	
	public Player(Character character){
		this.character = character;
		this.hand = new ArrayList<Card>();
	}
	
	public void move(Board board){
		Point currentPos = board.getPosition(this.character);
		int roll = board.getDice().roll2();
		System.out.println("You rolled " + roll);
		Point move = attemptMove(roll, currentPos, board);
		board.setPosition(this.character, move);
	}
	
	public Point attemptMove(int roll, Point currentPos, Board board) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean valid = false;
		while (!valid){
			String input = "";
			System.out.println("Enter coordinate to move to: ");
			
			try {
				input = reader.readLine();
			} catch (IOException e){
				e.printStackTrace();
			}
			
			if (input.length() == 2 || input.length() == 3){
				try {
					Integer.parseInt(input.substring(1));
					valid = true;
				} catch (Exception f){
					f.printStackTrace();
				}
				try {
					Integer.parseInt(input.substring(0,1));
					valid = false;
				} catch (Exception f){
					f.printStackTrace();
				}
				
				if (valid){
					valid = false;
					int x = input.charAt(0) - 'A';
					if (x >= 0){
						int y = Integer.parseInt(input.substring(1,input.length()));
						Point point = new Point(x,y);
						Location characterSquare = board.getSquareFromPoint(currentPos);
						Location inputSquare = board.getSquareFromPoint(point);
						if (isValid(0, roll, characterSquare, inputSquare, board)){
							return new Point(x,y);
						}
						else {
							valid = false;
						}
					}
				}
			}
			return null; //unreachable
		}
		
		return null;
	}

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

	public void giveCard(Card card){
		hand.add(card);
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public String name(){
		return character.cardName();
	}

	public Character getCharacter() {
		return character;
	}
	
	public String handToString(){
		String result = "";
		for (Card c : hand){
			result += "[" + c.cardName() + "]";
		}
		if (result.length() > 0){
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
}
