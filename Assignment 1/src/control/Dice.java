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
		assert sides >= 3 : "Mu8st have more than 3 sides for dice!";
	}

	/**
	 * Gives a random integer based on number (of dice) and sides.
	 * @return an integer from (1 * number) to (sides * number)
	 */
	public int roll(){
		int result = 0;
		if (sides >= 3){
			for (int i = 0; i < number; i++){
				Random random = new Random();
				int roll = random.nextInt();
				result += (roll % sides)+1;
			}
		}
		else {
			System.out.println("Number of sides must be at least 3");
		}
		return result;
	}
}
