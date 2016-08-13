package characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Character;

public class White extends Character {
	
	private final String imageString = "/assets/white.png";
	private BufferedImage image = null;
	private final String cardImageString = "/assets/whiteCard.png";
	private BufferedImage cardImage = null;

	@Override
	public char toChar() {
		return 'W';
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		try{
			if (image == null){
				image = ImageIO.read(getClass().getResourceAsStream(imageString));
			}
			g.drawImage(image, x, y, null);
			if (isHovering()){
				int width = 70;
				int height = 15;
				g.setFont(new Font("Serif", Font.PLAIN, 10));
				if (x > 100){
					g.setColor(Color.BLACK);
					g.fillRect(x-width, y+2, width, height);
					g.setColor(Color.WHITE);
					g.fillRect(x-width+1, y+3, width-2, height-2);
					g.setColor(Color.BLACK);
					g.drawString(getName(), x+width+4, y+height/2+4);
				}
				else{
					g.setColor(Color.BLACK);
					g.fillRect(x+20, y+2, width, height);
					g.setColor(Color.WHITE);
					g.fillRect(x+21, y+3, width-2, height-2);
					g.setColor(Color.BLACK);
					g.drawString(getName(), x+24, y+height/2+4);
				}
			}
		} catch(IOException e){
			System.out.println("IOException when drawing character");
		}
	}

	@Override
	public void drawCard(Graphics g, int x, int y) {
		try{
			if (cardImage == null){
				cardImage = ImageIO.read(getClass().getResource(cardImageString));
			}
			g.drawImage(cardImage, x, y, null);
		} catch(IOException e){
			System.out.println("IOException when drawing character card");
		}
	}

	@Override
	public String getName() {
		return "White";
	}

}
