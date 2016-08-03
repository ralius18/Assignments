package tests;

import static org.junit.Assert.*;

import org.junit.*;

import control.Dice;

public class DiceTests {

	@Test
	public void Test1(){
		Dice dice = new Dice(2, 6);
		for(int i = 0; i < 100; i++){
			int result = dice.roll();
			//System.out.println("Roll: "+i+", Got: "+result);
			if(result < 2 || result > 12)
				fail("With 2 six sided dice, managed to roll: "+result+"\n"
						+ "Highest should be 12 and lowest should be 2.");
		}
	}
	
}
