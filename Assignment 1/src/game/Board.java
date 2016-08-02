package game;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import locations.*;
import control.*;

/**
 * Class to control the board, storing all objects and their position
 * @author Brad Stone
 * @author Jarvis Dunn
 *
 */
public class Board {
	
	private Character[] characters;
	private Room[] rooms;
	private Square squares[][]; 
	//For storing locations of players
	private HashMap<Character,Point> playerPositions = new HashMap<Character,Point>();

	public Board (Character[] characters, Room[] rooms){
		this.characters = characters;
		this.rooms = rooms;
		this.squares = new Square[24][25];
		
		ArrayList<Point> startPoints = new ArrayList<Point>();
		String boardString = "";
		
		try {
		
			InputStream input = new ByteArrayInputStream(boardString.getBytes());
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			//Reading string into Square objects
			for (int y = 0; y < squares[0].length; y++){
				String line = reader.readLine();
				for (int x = 0; x < squares.length; x++){
					squares[x][y] = parseSquare(line.charAt(x));
					if (squares[x][y] instanceof StarterSquare){
						startPoints.add(new Point(x,y));
					}
				}
			}
			reader.close();
		} catch (IOException e){
			
		}
		
		//Enter starting positions
		for (int i = 0; i < characters.length; i++){
			playerPositions.put(characters[i], startPoints.get(i));
		}
	}
	
	private Square parseSquare(char symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	public void print(){
		
	}
	
}
