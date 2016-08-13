package control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dice {
	private int sides;
	private int currentRoll = 1;
	
	/**
	 * @param number of dice.
	 * @param sides, number of sides for each dice. Must have at least 3.
	 */
	public Dice(int sides){
		this.sides = sides;
		assert sides >= 3 : "Must have more than 3 sides for dice!";
	}

	/**
	 * Gives a random integer based on number (of dice) and sides.
	 * @return an integer from (1 * number) to (sides * number)
	 */	
	public int roll(){
//		int min = 1 * number;
//		int max = sides * number;
//		assert min < max;
//		int result =  (int) (min + Math.random()*(max - min + 1));
//		//for the one small chance that math.random gives a perfect 1.0.
//		if(result == 13) return 12;
//		currentRoll = result;
//		return result;
		int roll = (int) Math.ceil(Math.random()*sides);
		if (roll > 6){
			currentRoll = 6;
		}
		else{
			currentRoll = roll;
		}
		return roll;
			
	}
	
	public void draw(Graphics g){
		try{
			BufferedImage image = ImageIO.read(getClass().getResource(
					"/assets/dice"+currentRoll+".png"));
			g.drawImage(image, 0, 0, null);
		} catch(IOException e){
			System.err.println("IOException when drawing dice");
		}
	}
}
