import java.util.ArrayList;
import java.awt.*;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deck{
	    protected JFrame cardJFrame;
	    protected JPanel panel;
	    protected JLabel cardJLabel[][] = new JLabel[81][3]; // the container for the Image (of a bug)
	    protected Card card[] = new Card[81];
	    protected ArrayList<Card> list;
	    
	    protected void Shuffle(){
	    	
	    }
	    protected void makeDeck(){
	    	list = new ArrayList();
	    	int count = 0;
	    	for(int i =0;i<3;i++){
	        	for(int j = 0; j<3; j++){
	        		for (int k = 0; k<3; k++){
	        			for(int m=0; m<3;m++){
	        				
	        				card[count] = new Card(cardJFrame,panel,count,0,i,j,k,m);
	        				list.add(card[count]);
	        				count++;
	        				//System.out.println("Card with: " + i + j + k + m + "count: " + count);
	        				
	        			}
	        		}	
	        	}
	        }
	    	
	    	for (int i =0;i<10;i++){
	    		Collections.shuffle(list);
	    	}
	    	//System.out.println(list);
	    }
	    
	    public Deck(JFrame passedInJFrame, JPanel panel1){
	        cardJFrame = passedInJFrame;
	        panel = panel1;
	        makeDeck();
	      /*for (int i =0; i <12;i++){  
	        card[i] = new Card(cardJFrame,i,0,(int)(i%3),1,1,1);
	      }*/
	        /*
	       fatColorName[0] = "fatBlue.png";
	        fatColorName[1] = "fatRed.png";
	        fatColorName[2] = "fatNormal.png";
	        mediumColorName[0] = "mediumBlue.png";
	        mediumColorName[1] = "mediumRed.png";
	        mediumColorName[2] = "mediumNormal.png";
	        skinnyColorName[0] = "skinnyBlue.png";
	        skinnyColorName[1] = "skinnyRed.png";
	        skinnyColorName[2] = "skinnyNormal.png";
	        ornamentName[0] = "ornamentBlue.png";
	        ornamentName[1] = "ornamentRed.png";
	        ornamentName[2] = "noOrnament.png";
	        topperName[0] = "star.png";
	        topperName[1] = "angel.png";
	        topperName[2] = "nutcracker.png";
	        
	         int count = 0;
	        for(int i =0;i<3;i++){
	        	for(int j = 0; j<3; j++){
	        		for (int k = 0; k<3; k++){
	        			for(int m=0; m<3;m++){
	        				switch(i){
	        				case 0:
	        					cardImage[count][0] = new ImageIcon(fatColorName[j]);
	        					break;
	        				case 1:
	        					cardImage[count][0] = new ImageIcon(skinnyColorName[j]);
	        					break;
	        				case 2:
	        					cardImage[count][0] = new ImageIcon(mediumColorName[j]);
	        					break;
	        				}
	        				cardImage[count][1] = new ImageIcon(ornamentName[k]);
	        				cardImage[count][2] = new ImageIcon(topperName[m]);
	        				
	        				count++;
	        				
	        						
	        				
	        			}
	        		}	
	        	}
	        }
	        
	        JLabel label = new JLabel();
	    	ImageIcon icon = new ImageIcon("fatRed.png");
	    	label.setIcon(icon);
	        
	        
	        for (int i = 0; i<81;i++){
		    	   for(int j = 0; j<3; j++){
		    		cardJLabel[i][j] = new JLabel();
		    		cardJLabel[i][j].setIcon(new ImageIcon("fatNormal.png"));
			        cardJLabel[i][j].setBounds (10, 10, 10, 10); // arbitrary, will change later
			        cardJFrame.getContentPane().add(cardJLabel[i][j]);
			        cardJLabel[i][j].setVisible(false);
			        cardJLabel[i][j].setVisible(true);
			        
		    	   }
		       }	
	        for (int i = 0; i<81; i++){
	        
	        	cardJLabel[i][0].setIcon(cardImage[i][0]);
				cardJLabel[i][1].setIcon(cardImage[i][1]);
				cardJLabel[i][2].setIcon(cardImage[i][2]);
				System.out.println(i + " " +cardJLabel[i][0]);
	        }
	        horizontalMovement = 0;
	        verticalMovement = 0;
	        xPosition = 50*passedInCardNumber; // arbitrary starting point
	        yPosition = 20; // arbitrary starting point    
	        */
	    }
	   
}
