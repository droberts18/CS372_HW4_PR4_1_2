import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class YahtzeeRoll {

	/**
	 * Initializes the window and its contents
	 */
	public static void initialize(){
		// The basic window frame
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Dimensions for each of the dice slots
		Dimension diceDimensions = new Dimension(140, 140);
		
		// Where dice images appear
		JLabel diceSlot1 = new JLabel();
		diceSlot1.setPreferredSize(diceDimensions);
		frame.getContentPane().add(diceSlot1);
		
		JLabel diceSlot2 = new JLabel();
		diceSlot2.setPreferredSize(diceDimensions);
		frame.getContentPane().add(diceSlot2);
		
		JLabel diceSlot3 = new JLabel();
		diceSlot3.setPreferredSize(diceDimensions);
		frame.getContentPane().add(diceSlot3);
		
		JLabel diceSlot4 = new JLabel();
		diceSlot4.setPreferredSize(diceDimensions);
		frame.getContentPane().add(diceSlot4);
		
		JLabel diceSlot5 = new JLabel();
		diceSlot5.setPreferredSize(diceDimensions);
		frame.getContentPane().add(diceSlot5);
		
		JButton start = new JButton();
		start.setText("ROLL");
		frame.getContentPane().add(start);
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Creates 6 new rolls, each with one image of the side of a dice
				Roll[] rolls = new Roll[6];
				JLabel[] diceSlots = {diceSlot1, diceSlot2, diceSlot3, diceSlot4, diceSlot5};
				
				// Assigns a dice slot to a role
				for(int i = 0; i < 5; i++){
					rolls[i] = new Roll(diceSlots[i]);
				}
				
				// Refreshes UI
				frame.revalidate();
				
				// Starts threads
				for(int i = 0; i < 6; i++){
					Thread t = new Thread(rolls[i]);
					t.start();
					
					try{
						Thread.sleep(100);
					}
					catch(InterruptedException ex) {;}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		initialize();
	}

}
