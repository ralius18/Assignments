package weapons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Weapon;

public class Revolver extends Weapon {
	
	private final String imageString = "/assets/revolverCard.png";
	private BufferedImage image = null;

	@Override
	public String cardName() {
		return "Revolver";
	}

	@Override
	public void drawCard(Graphics g, int x, int y) {
		try {
			if (image == null){
				image = ImageIO.read(getClass().getResource(imageString));
				g.drawImage(image, x, y, null);
			}
		} catch(IOException e){
			System.err.println("IOException when drawing weapon card");
		}
	}

}
