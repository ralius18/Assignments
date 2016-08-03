package control;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.*;
import game.Character;
import locations.Square;

public class Player {

	private Character character;
	private int uid;
	private Square currentSquare;
	private ArrayList<Card> hand;
	
	public Player(Character character){
		this.character = character;
		this.hand = new ArrayList<Card>();
	}
	
	public void move(Board board){
		Point currentPos = board.getPosition(this.character);
		int roll = board.getDice().roll();
		System.out.println("You rolled " + roll);
		Point move = attemptMove(roll, currentPos, board);
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
						Square characterSquare = board.getSquareFromPoint(currentPos);
						Square inputSquare = board.getSquareFromPoint(point);
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

	public boolean isValid(int depth, int maxDepth, Square current, Square to, Board board) {
		if (depth > maxDepth){
			return false;
		}
		else if (current == to){
			return true;
		}
		
		for (Square square : current.getValidMoves()){
			if (isValid(depth+1, maxDepth, square, to, board)){
				return true;
			}
		}
		return false;
	}

	public void giveCard(Card card){
		hand.add(card);
	}
	
	public String name(){
		return character.cardName();
	}
	
}
