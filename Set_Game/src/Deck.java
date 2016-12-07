import java.util.ArrayList;
import java.awt.*;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


///////////////////////////////////////
///////////////////////////////////////
///////// Set Game Deck Class /////////
///////////////////////////////////////
///////////////////////////////////////



///////////////////////////////////////
/////////// Class Variables  //////////
///////////////////////////////////////

public class Deck{
	    protected JFrame cardJFrame;
	    protected JLayeredPane panel;
	    protected JLabel cardJLabel[][] = new JLabel[81][3]; // the container for the Image (of a bug)
	    protected Card card[] = new Card[81];
	    protected ArrayList<Card> list;
	    protected int num_decks;
	    

///////////////////////////////////////
///////// makeDeck Method  /////////
///////////////////////////////////////
	    
	    
	    // Method that makes a deck of 81 different cards based on the parameters given in the card class
	    protected void makeDeck(){
	    	list = new ArrayList();
	    	int count = 0;
	    	for(int c = 0; c < num_decks; c++){
		    	for(int i =0;i<3;i++){
		        	for(int j = 0; j<3; j++){
		        		for (int k = 0; k<3; k++){
		        			for(int m=0; m<3;m++){
		        				
		        				card[count] = new Card(cardJFrame,panel,count,0,i,j,k,m);
		        				list.add(card[count]);
		        				count++;
		        				
		        			}
		        		}	
		        	}
		        }
	    	}
	    	
	    	for (int i =0;i<10;i++){
	    		Collections.shuffle(list);
	    	}
	    }
	    

///////////////////////////////////////
///////// Constructor Method  /////////
///////////////////////////////////////
	    
	    // Constructor that makes the deck.
	    public Deck(JFrame passedInJFrame, JLayeredPane panel1, int num_of_decks){
	        cardJFrame = passedInJFrame;
	        panel = panel1;
	        num_decks = num_of_decks;
	        makeDeck();
	      
	    }
	   
}
