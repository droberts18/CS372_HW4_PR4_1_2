
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Roll implements Runnable {
	private JLabel _label;
	
	/**
	 * Assigns a label to a roll for each image of a dice side to show up
	 * @param label		The label for a dice side image to be put on
	 */
	public Roll(JLabel label){
		_label = label;
	}
	
	/**
	 * Stops a dice from rolling
	 * @return		If the dice is done being rolled
	 */
	private boolean stop(){
		// Gets a new random integer from 1-50 and stores it in stopNum
		Random rand = new Random();
		int stopNum = rand.nextInt(50) + 1;
		
		// Stops the dice from rolling any more if the random number is equal to 50
		if(stopNum == 50){
			return true;
		}
		
		return false;
	}
	
	/**
	 *	Each thread runs this, gets an image of a random side of a dice and displays it to the user
	 */
	public void run(){
		DiceImg diceImg = new DiceImg();
		ArrayList<ImageIcon> rollImages = diceImg.getImages();
		
		do{
			Random rand = new Random();
			int randRollNum = rand.nextInt(6);
			
			_label.setIcon(rollImages.get(randRollNum));
			
			try{
				Thread.sleep(100);
			}
			catch (InterruptedException ex) {;}
		} while (stop() == false);
	}
}
