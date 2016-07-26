package control;

import java.util.Random;

public class Dice {
	
	private int number;
	private int sides;
	
	public Dice(int number, int sides){
		this.number = number;
		this.sides = sides;
	}

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
