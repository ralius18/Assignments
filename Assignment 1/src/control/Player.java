package control;

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
	
	public void giveCard(Card card){
		hand.add(card);
	}
	
	public String name(){
		return character.cardName();
	}
	
}
