package gui;
import game.*;
import game.Character;
import control.*;
import locations.*;
import rooms.*;
import weapons.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import characters.*;

public class GUI extends JFrame {
	
	private JPanel top, right, bottom, left, center;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitFileMenuItem;
	
	private JMenu gameMenu;
	private JMenu subMenuSelectPlayers;
	private JMenuItem threePlayers;
	private JMenuItem fourPlayers;
	private JMenuItem fivePlayers;
	private JMenuItem sixPlayers;
	private JPopupMenu popUpMenu;
	
	private JRadioButton greenRadioButton;
	private JRadioButton scarlettRadioButton;
	private JRadioButton peacockRadioButton;
	private JRadioButton plumRadioButton;
	private JRadioButton mustardRadioButton;
	private JRadioButton whiteRadioButton;
	
	private Canvas canvas;
	private DicePanel dicePanel;
	private CardPanel cardPanel;
	private CardScroller handView;
	
	private Cluedo game;
	private Board board;
	private int amountOfPlayers = 0;
	private Player[] players;
	private Character[] characters = new Character[6];
	private Room[] rooms;
	private Weapon[] weapons;
	
	private boolean loadedGame;
	private boolean newGame;

	private Dice dice;
	
	public GUI(){
		super("Cluedo");
		
		//size, title and close operation
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		//Create menu bar
		menuBar = new JMenuBar();
		
		//File menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitFileMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitFileMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
		menuBar.add(fileMenu);
		
		//Game menu
		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		subMenuSelectPlayers = new JMenu("New Game");
		subMenuSelectPlayers.setMnemonic(KeyEvent.VK_N);
		threePlayers = new JMenuItem("3 Players");
		subMenuSelectPlayers.add(threePlayers);
		fourPlayers = new JMenuItem("4 Players");
		subMenuSelectPlayers.add(fourPlayers);
		fivePlayers = new JMenuItem("5 Players");
		subMenuSelectPlayers.add(fivePlayers);
		sixPlayers = new JMenuItem("6 Players");
		subMenuSelectPlayers.add(sixPlayers);
		gameMenu.add(subMenuSelectPlayers);
		fileMenu.add(exitFileMenuItem);
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);
		
		threePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPlayers(3);
			}
		});

		fourPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPlayers(4);
			}
		});

		fivePlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPlayers(5);
			}
		});

		sixPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPlayers(6);
			}
		});
		
		//Initialising characters
		characters = new Character[6];
		characters[0] = new Green();
		characters[1] = new Scarlett();
		characters[2] = new Peacock();
		characters[3] = new Plum();
		characters[4] = new Mustard();
		characters[5] = new White();

		//Initialising rooms
		rooms = new Room[9];
		rooms[0] = new Kitchen();
		rooms[1] = new Ballroom();
		rooms[2] = new Conservatory();
		rooms[3] = new BilliardRoom();
		rooms[4] = new Library();
		rooms[5] = new Study();
		rooms[6] = new Hall();
		rooms[7] = new Lounge();
		rooms[8] = new DiningRoom();
		
		//Initialising weapons
		weapons = new Weapon[6];
		weapons[0] = new Candlestick();
		weapons[1] = new Knife();
		weapons[2] = new LeadPipe();
		weapons[3] = new Revolver();
		weapons[4] = new Rope();
		weapons[5] = new Spanner();
		
		board = new Board(characters, rooms);
		canvas = new Canvas(500, 500, board, this);
		top = new JPanel();
		right = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		center = new JPanel();
		
		dice = new Dice(6);
		dicePanel = new DicePanel(dice);
		
		canvas = new Canvas(500,500, board, this);
		cardPanel = new CardPanel();
		handView = new CardScroller(cardPanel);
		
		bottom.add(dicePanel);
		bottom.add(handView);
		center.add(canvas);
		add(top, BorderLayout.NORTH);
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		
		setVisible(true);
		run();
	}
	
	public void run(){
		while (true){
			if (newGame){
				loadedGame = true;
				newGame = false;
			}
			try{
				Thread.sleep(500);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			if (loadedGame){
				game.loop();
				loadedGame = false;
			}
		}
	}
	
	public void selectPlayers(int i){
		amountOfPlayers = 0;
		popUpMenu = new JPopupMenu("Select Players");
		players = new Player[i];
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		
		greenRadioButton = new JRadioButton("Green");
		scarlettRadioButton = new JRadioButton("Scarlett");
		peacockRadioButton = new JRadioButton("Peacock");
		plumRadioButton = new JRadioButton("Plum");
		mustardRadioButton = new JRadioButton("Mustard");
		whiteRadioButton = new JRadioButton("White");
		
		radioButtonGroup.add(greenRadioButton);
		radioButtonGroup.add(scarlettRadioButton);
		radioButtonGroup.add(peacockRadioButton);
		radioButtonGroup.add(plumRadioButton);
		radioButtonGroup.add(mustardRadioButton);
		radioButtonGroup.add(whiteRadioButton);
		
		greenRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("G", false);
			}
		});

		scarlettRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("S", false);
			}
		});

		peacockRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("P", false);
			}
		});

		plumRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("L", false);
			}
		});

		mustardRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("M", false);
			}
		});

		whiteRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonEvent("W", false);
			}
		});
		
		popUpMenu.add(greenRadioButton);
		popUpMenu.add(scarlettRadioButton);
		popUpMenu.add(peacockRadioButton);
		popUpMenu.add(plumRadioButton);
		popUpMenu.add(mustardRadioButton);
		popUpMenu.add(whiteRadioButton);
		
		popUpMenu.addSeparator();
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				radioButtonEvent("", true);
			}
		});
		
		popUpMenu.add(okButton);
		popUpMenu.setLocation(getWidth()/2,getHeight()/2);
		add(popUpMenu);
		popUpMenu.setVisible(true);
	}

	public void radioButtonEvent(String string, boolean ok) {
		if (!ok){
			if (players.length != amountOfPlayers){
				switch (string){
					case "G": 
						players[amountOfPlayers] = new Player(characters[0]);
						amountOfPlayers++;
						characters[0].select();
						greenRadioButton.setEnabled(false);
						return;
					case "S": 
						players[amountOfPlayers] = new Player(characters[1]);
						amountOfPlayers++;
						characters[1].select();
						scarlettRadioButton.setEnabled(false);
						return;
					case "P": 
						players[amountOfPlayers] = new Player(characters[2]);
						amountOfPlayers++;
						characters[2].select();
						peacockRadioButton.setEnabled(false);
						return;
					case "L": 
						players[amountOfPlayers] = new Player(characters[3]);
						amountOfPlayers++;
						characters[3].select();
						plumRadioButton.setEnabled(false);
						return;
					case "M": 
						players[amountOfPlayers] = new Player(characters[4]);
						amountOfPlayers++;
						characters[4].select();
						mustardRadioButton.setEnabled(false);
						return;
					case "W": 
						players[amountOfPlayers] = new Player(characters[5]);
						amountOfPlayers++;
						characters[5].select();
						whiteRadioButton.setEnabled(false);
						return;
				}
			}
		}
		
		else{
			if (players.length == amountOfPlayers){
				popUpMenu.setVisible(false);
				if (game != null){
					game.running = false;
				}
				game = new Cluedo(amountOfPlayers, this);
				newGame = true;
				return;
			}
		}
	}
	
	public Character[] getCharacters(){
		return characters;
	}
	
	public Player[] getPlayers(){
		return players;
	}
	
	public Board getBoard() {
		return board;
	}

	public Weapon[] getWeapons() {
		return weapons;
	}

	public Room[] getRooms() {
		return rooms;
	}
	
	public Dice getDice(){
		return dice;
	}
	
	public CardScroller getHand(){
		return handView;
	}
	
	public void setBoard(Board board){
		canvas = new Canvas(500, 500, board, this);
	}

	public static void main(String[] args){
		new GUI();
	}
}
