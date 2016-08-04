package control;

import java.util.Random;

public class Dice {
	
	private int number;
	private int sides;
	
	/**
	 * @param number of dice.
	 * @param sides, number of sides for each dice. Must have at least 3.
	 */
	public Dice(int number, int sides){
		this.number = number;
		this.sides = sides;
		assert sides >= 3 : "Must have more than 3 sides for dice!";
	}

	/**
	 * Gives a random integer based on number (of dice) and sides.
	 * @return an integer from (1 * number) to (sides * number)
	 */	
	public int roll(){
		int min = 1 * number;
		int max = sides * number;
		assert min < max;
		int result =  (int) (min + Math.random()*(max - min + 1));
		//for the one small chance that math.random gives a perfect 1.0.
		if(result == 13) return 12;
		return result;
	}
}
