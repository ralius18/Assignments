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
	private Dice dice;
	
	public Board (Character[] characters, Room[] rooms){
		this.characters = characters;
		this.rooms = rooms;
		this.squares = new Square[24][25];
		this.dice = new Dice(2,6);
		
		ArrayList<Point> startPoints = new ArrayList<Point>();
		String boardString =  "nnnnnnnnnSnnnnSnnnnnnnnn\n"+
							  "kkkkkUn___bbbb___ncccccc\n"+
							  "kkkkkk__bbbbbbbb__cccccc\n"+
							  "kkkkkk__bbbbbbbb__cccccc\n"+
							  "kkkkkk__bbbbbbbb__Dccccc\n"+
							  "kkkkkk_DDbbbbbbDD_DcccUn\n"+
							  "nkkkDk__bbbbbbbb_______S\n"+
							  "____D___bDbbbbDb_______n\n"+
							  "n________D____D___iiiiii\n"+
							  "ddddd____________DDiiiii\n"+
							  "dddddddd__mmmmm___iiiiii\n"+
							  "dddddddd__mmmmm___iiiiii\n"+
							  "dddddddDD_mmmmm___iiiiDi\n"+
							  "dddddddd__mmmmm_____D_Dn\n"+
							  "dddddddd__mmmmm___llDlln\n"+
							  "ddddddDd__mmmmm__lllllll\n"+
							  "n_____D___mmmmm_DDllllll\n"+
							  "S__________DD____lllllll\n"+
							  "n_____D__hhDDhh___llllln\n"+
							  "UoooooD__hhhhhh________S\n"+
							  "ooooooo__hhhhhDD_D_____n\n"+
							  "ooooooo__hhhhhh__DsssssU\n"+
							  "ooooooo__hhhhhh__sssssss\n"+
							  "ooooooo__hhhhhh__sssssss\n"+
							  "oooooonSnhhhhhhn_nssssss\n";
		
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
	
	public Point getPosition(Character character){
		return playerPositions.get(character);
	}
	
	public Dice getDice(){
		return dice;
	}
	
	public Square parseSquare(char symbol) {
		switch (symbol){
			case 'n' : return null;
		}
		return null;
	}
	
	public Square getSquareFromPoint(Point p){
		return squares[p.x][p.y];
	}

	public void print(){
		
	}
	
}
