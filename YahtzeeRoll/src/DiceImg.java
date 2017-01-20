import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class DiceImg {
	private String[] imgNames = {"one", "two", "three", "four", "five", "six"};
	private ArrayList<ImageIcon> diceImages = new ArrayList<ImageIcon>();
	
	/**
	 * Creates an array of dice side images
	 */
	public DiceImg(){
		retrieveImages();
	}
	
	/**
	 * Retrieves the images from resources and stores them in an ArrayList
	 */
	private void retrieveImages(){
		for(int i = 0; i < 6; i++){
			try{
				Image img = Toolkit.getDefaultToolkit().getImage("resources\\" +
						imgNames[i] + ".png");
				img = img.getScaledInstance(100,
						100, Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(img);
				diceImages.add(imgIcon);
			}
			catch (Exception ex){;}
		}
	}
	
	/**
	 * Gets the images of each side of a dice
	 * @return	The ArrayList of dice images
	 */
	public ArrayList<ImageIcon> getImages(){
		return diceImages;
	}
}
