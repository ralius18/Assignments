package control;

import game.*;
import game.Character;
import gui.GUI;
import locations.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	
	private Dice dice;
	private GUI graphics;
	
	public boolean running;
	
	private String selectedCharacter = "";
	private String selectedWeapon = "";
	private String selectedRoom = "";
	
	/**
	 * Creates a Cluedo object with the number of players provided
	 * @param numPlayers
	 */
	public Cluedo(int numPlayers, GUI g){
		this.graphics = g;
		
		characters = g.getCharacters();
		rooms = g.getRooms();
		weapons = g.getWeapons();

		players = new Player[numPlayers];
		dice = g.getDice();
		board = g.getBoard();
		
		dealCards();
	}
	
	public JComponent[] createComponents(boolean canChooseRoom){
		selectedCharacter = "";
		selectedRoom = "";
		selectedWeapon = "";
		
		ActionListener characterAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (((JRadioButton) e.getSource()).isSelected()){
					setSelection("c", ((JRadioButton) e.getSource()).getText());
				}
			}
		};
		
		ActionListener roomAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (((JRadioButton) e.getSource()).isSelected()){
					setSelection("r", ((JRadioButton) e.getSource()).getText());
				}
			}
		};
		
		ActionListener weaponAction = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (((JRadioButton) e.getSource()).isSelected()){
					setSelection("w", ((JRadioButton) e.getSource()).getText());
				}
			}
		};
		
		int columns = 3;
		if (canChooseRoom){
			columns = 4;
		}
		
		List<JComponent> components = new ArrayList<JComponent>();
		ButtonGroup characterGroup = new ButtonGroup();
		ButtonGroup roomGroup = new ButtonGroup();
		ButtonGroup weaponGroup = new ButtonGroup();
		JPanel panel = new JPanel(new BorderLayout());
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(200,300));
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(200,300));
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(200,300));
		
		for (Character c : characters){
			JRadioButton button = new JRadioButton(c.cardName());
			button.addActionListener(characterAction);
			characterGroup.add(button);
			west.add(button);
		}
		panel.add(west, BorderLayout.WEST);
		
		for (Weapon w : weapons){
			JRadioButton button = new JRadioButton(w.cardName());
			button.addActionListener(weaponAction);
			weaponGroup.add(button);
			center.add(button);
		}
		panel.add(center, BorderLayout.CENTER);

		if (canChooseRoom){
			for (Room r : rooms){
				JRadioButton button = new JRadioButton(r.cardName());
				button.addActionListener(roomAction);
				roomGroup.add(button);
				east.add(button);
			}
			panel.add(east, BorderLayout.EAST);
		}
		
		components.add(panel);
		return components.toArray(new JComponent[0]);
		
	}
	
	public void setSelection(String type, String cardName){
		switch (type){
			case "c": selectedCharacter = cardName;
			case "r": selectedRoom = cardName;
			case "w": selectedWeapon = cardName;
		}
	}
	
	/**
	 * Creates players by prompting for selection
	 * @return Array of players of the game
	 */
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
	
	/**
	 * Chooses cards for the solution, and deals the remaining
	 * cards to all players
	 */
	public void dealCards(){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		int characterIndexForSolution = new Dice(characters.length).roll()-1;
		int roomIndexForSolution = new Dice(rooms.length).roll()-1;
		int weaponIndexForSolution = new Dice(weapons.length).roll()-1;
		
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
	
	/**
	 * Main loop for game of Cluedo, loops over players turns
	 * until solution is solved
	 */
	public void loop(){
		boolean running = true;
		while(running){
			for (int i = 0; i < players.length; i++){
				Player p = players[i];
				graphics.getHand().addHand(p.getHand());
				graphics.repaint();
				JOptionPane.showMessageDialog(null, p.getCharacter().getName()
						+ ", your turn.");
				
				try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					//Begin processing a single player
					p.move(board, dice); //attempt to move the player
					graphics.repaint();
					Location currentLocation = board.getLocationAtPoint(board.getPosition(p.getCharacter()));
					
					if (currentLocation instanceof Room || currentLocation instanceof Middle){
						String message = "";
						//If player is in the Middle, ask to solve the murder
						if (currentLocation instanceof Middle){
							message = "Would you like to attempt to solve the murder? If you are wrong, you cannot continure (y/n)";
						}
						//If player is in a Room, ask to make a guess
						else {
							message = "Would you like to make a guess in the "+((Room)currentLocation).cardName()+"? (y/n)";
						}
						int input = 0;
						input = JOptionPane.showConfirmDialog(null, message, "Cluedo", JOptionPane.YES_NO_OPTION);
						
						boolean valid = false; //used for checking if input is valid
						boolean makingGuess = false; //used for checking if player is making a guess
						boolean listRooms = currentLocation instanceof Middle;
						
						if (makingGuess){
							
							Character chosenCharacter = null;
							Room chosenRoom = null;
							Weapon chosenWeapon = null;
							
							//loop until input is valid
							while (!valid){
								//ask to choose weapon
								JOptionPane.showMessageDialog(null,createComponents(listRooms), 
										"Choose", JOptionPane.PLAIN_MESSAGE);
								valid = true;
								chosenWeapon = null;
								if (selectedWeapon.equals("")){
									valid = false;
								}
								else {
									for (Weapon w : weapons){
										if (w.cardName().equals(selectedWeapon)){
											chosenWeapon = w;
										}
									}
								}
								
								chosenCharacter = null;
								if (selectedCharacter.equals("")){
									valid = false;
								}
								else {
									for (Character c : characters){
										if (c.cardName().equals(selectedCharacter)){
											chosenCharacter = c;
										}
									}
								}
								
								if (currentLocation instanceof Room && valid){
									board.setPosition(chosenCharacter, board.getPosition(p.getCharacter()));
								}
								
								chosenRoom = null;
								if (currentLocation instanceof Room){
									chosenRoom = (Room) currentLocation;
								}
								else if (currentLocation instanceof Middle){
									if (selectedRoom.equals("")){
										valid = false;
									}
									else{
										for (Room r : rooms){
											if (r.cardName().equals(selectedRoom)){
												chosenRoom = r;
											}
										}
									}
								}
							}
							
							//if player is attempting to solve
							if (currentLocation instanceof Middle){
								//if guess is correct
								if (solution.checkGuess(chosenCharacter, chosenWeapon, chosenRoom)){
									JOptionPane.showMessageDialog(null, p.getCharacter().getName()+
											" solved the murder!\nThe solution was: "+solution.toString());
									running = false;
									return;
								}
								else {
									//guess was incorrect, player out of game
									//deal player's cards to other players
									ArrayList<Card> cardsLeftOver = p.getHand();
									players[i] = null;
									while (! cardsLeftOver.isEmpty()){
										for (int j = 0; j < players.length && !cardsLeftOver.isEmpty(); j++){
											if (players[j] != null){
												players[j].giveCard(cardsLeftOver.remove(0));
											}
										}
									}
									JOptionPane.showMessageDialog(null, p.getCharacter().cardName()
											+" made an incorrect guess. They are out of the game");
								}
							}
							else{ //other players attempt to prove guess (in room) wrong
								Player[] otherPlayers = new Player[players.length-1];
								int index = 0;
								for (int j = 0; j < i; j++){
									otherPlayers[index++] = players[j];
								}
								for (int j = i+1; j < players.length; j++){
									otherPlayers[index++] = players[j];
								}
								message = "";
								for (Player otherPlayer : otherPlayers){
									//find one of the chosen cards in other player's hands
									Card card = otherPlayer.getCard(chosenCharacter, chosenWeapon, chosenRoom);
									if (card != null){//a player has one of the cards, so guess is wrong
										JOptionPane.showMessageDialog(null, otherPlayer.getCharacter().cardName()
												+" has shown you their card: ["+card.cardName()+"]");
										break;
									}
									else { //player does not have any of the cards, so guess could be correct
										JOptionPane.showMessageDialog(null, otherPlayer.getCharacter().cardName()
												+" does not have any of the cards that you guessed");
									}
								}
							}
						}
					} //End of player turn
				} catch (Exception e){
					e.printStackTrace(); //should not happen
				}
			}
			
			//taking players out of game (incorrect attempt to solve
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

	/**
	 * Entry point for game of Cluedo, prompts number of players
	 * @param args
	 */
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
		
		//new Cluedo(numPlayers);
	}
}
