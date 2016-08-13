package tests;

import static org.junit.Assert.*;

import org.junit.*;

import control.Dice;

public class DiceTests {

	@Test
	//Uses old roll method.
	public void Test1(){
		Dice dice = new Dice(6);
		for(int i = 1; i <= 100; i++){
			int result = dice.roll();
			//System.out.println("Roll: "+i+", Got: "+result);
			if(result < 2 || result > 12)
				fail("With 2 six sided dice, managed to roll: "+result+"\n"
						+ "Highest should be 12 and lowest should be 2.");
		}
	}
	
//	@Test
//	public void Test2(){
//		//Uses new dice method. roll2().
//		Dice dice = new Dice(2, 6);
//		rollDice(dice, 100, 2, 12);
//	}
//	
//	@Test
//	public void Test3(){
//		Dice dice = new Dice(3, 3);
//		rollDice(dice, 100, 3, 9);
//	}
	
	/**
	 * @param dice the dice you want to test.
	 * @param times the amount of rolls you want to do.
	 * @param min the smallest number that can be rolled with dice.
	 * @param max the highest number that can be rolled with dice.
	 */
	public void rollDice(Dice dice, int times, int min, int max){
		//Uses new dice method. roll2().
		boolean gotMin = false;
		boolean gotMax = false;
		for(int i = 1; i <= times; i++){
			int result = dice.roll();
			//System.out.println("Roll: "+i+", Got: "+result);
			if(result == min) gotMin = true;
			else if(result == max) gotMax = true;
			else if(result < min || result > max)
				fail("With "+min+", "+max+" sided dice, managed to roll: "+result+"\n"
						+ "Highest should be "+max+" and lowest should be "+min+".");
		}
		if(!gotMin) fail("After "+times+" rolls, never got min result of "+min);
		if(!gotMax) fail("After "+times+" rolls, never got max result of "+max);
	}
	
}
