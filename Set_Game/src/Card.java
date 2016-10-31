import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon; // for ImageIcon
import javax.swing.JButton;


class Card {
	public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    public final static int NUMBER_OF_DIRECTIONS = 4;
    public final static int DECK_SIZE = 81;
    protected JFrame cardJFrame;
    protected JLabel label[] = new JLabel[3]; // the container for the Image (of a bug)
    protected Card card[] = new Card[81];
    JButton button = new JButton();
    
    protected String fatColorName[] = new String[3];
    protected String mediumColorName[] = new String[3];
    protected String skinnyColorName[] = new String[3];
    protected String ornamentName[] = new String[3];
    protected String topperName[] = new String[3];
    protected ImageIcon icon[] = new ImageIcon[3];
    protected boolean highlight = false;
    protected int Tree;
    protected int Ornament;
    protected int Topper;
    protected int Colors;
    
    
    protected int horizontalMovement;
    protected int verticalMovement;
    protected int directionChangeProbability;
    protected int cardDirection = UP;
    protected int xPosition=0;  // top left corner of Image
    protected int yPosition=0;  // top left corner of Image
	boolean movement = false;
	
    public Card(JFrame myJFrame, JPanel panel, int cardNumber, int directionChangeProbability, int tree, int orn, int top, int color){
    	
    	Colors = color;
    	Topper = top;
    	Tree = tree;
    	Ornament = orn;
    	
    	fatColorName[0] = "fatBlue.png";
        fatColorName[1] = "fatRed.png";
        fatColorName[2] = "fatNormal.png";
        mediumColorName[0] = "mediumBlue.png";
        mediumColorName[1] = "mediumRed.png";
        mediumColorName[2] = "mediumNormal.png";
        skinnyColorName[0] = "skinnyBlue.png";
        skinnyColorName[1] = "skinnyRed.png";
        skinnyColorName[2] = "skinnyNormal.png";
        ornamentName[0] = "purpleOrnaments.png";
        ornamentName[1] = "yellowOrnaments.png";
        ornamentName[2] = "noOrnament.png";
        topperName[0] = "star.png";
        topperName[1] = "angel.png";
        topperName[2] = "nutcracker.png";
        
        switch(tree){
		case 0:
			icon[2] = new ImageIcon(fatColorName[color]);
			//System.out.println("FAT");
			break;
		case 1:
			icon[2] = new ImageIcon(skinnyColorName[color]);
			//System.out.println("SKINNY");
			break;
		case 2:
			icon[2] = new ImageIcon(mediumColorName[color]);
			//System.out.println("MEDIUM");
			break;
		}
        
		icon[1] = new ImageIcon(ornamentName[orn]);
		icon[0] = new ImageIcon(topperName[top]);
		
       
        // movement is arbitrarily based on size of image
        horizontalMovement = icon[0].getIconWidth()/ 5; // arbitrary 1/5th of width
        verticalMovement = icon[0].getIconHeight()/ 5; // arbitrary 1/5th of height;  
        
        
        
     	   button = new JButton();
 	       for(int i = 0; i < 3; i++){
 		        label[i] = new JLabel();
 		        Image image = icon[i].getImage(); // transform it 
 		        Image newimg = image.getScaledInstance((int)(374/2), (int)(422/2),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
 		        icon[i] = new ImageIcon(newimg);  // transform it back
 		        label[i].setIcon(icon[i]);
 		        button.add(label[i]);
 		        button.setLayout(null);
 		        button.setBackground(Color.GRAY);
 		        label[i].setBounds(-10, 25, icon[0].getIconWidth(), icon[0].getIconHeight());
 		        
 		        button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						changeHighlight();
					}
 		        	  
 		        });
 		        
 		        
 	       }	
 	       panel.add(button);
 	       button.setVisible(true);
 	       panel.setVisible(true);
 	       myJFrame.getContentPane().repaint();
        
	    
     

     horizontalMovement = 0;
     verticalMovement = 0;
     xPosition = 20; // arbitrary starting point
     yPosition = 20; // arbitrary starting point  
     
    }
    
    public void move(){
    	if (movement == true){
	        // change direction?
	        if (Math.random()*100 < directionChangeProbability) // Math.random gives 0 to .9999
	            cardDirection = (int) Math.floor(Math.random()*NUMBER_OF_DIRECTIONS); 
	        
	        if (cardDirection == LEFT)
	            xPosition -= horizontalMovement;
	        else if (cardDirection == RIGHT)
	            xPosition += horizontalMovement;            
	        else if (cardDirection == UP)
	            yPosition -= verticalMovement;
	        else if (cardDirection == DOWN)
	            yPosition += verticalMovement;
	 
	        drawCard();          
	        
	        //if hit edge of window, then wrap around to the other side immediately
	        if (cardDirection == UP && atTopEdge()){
	            eraseCard();
	            yPosition = cardJFrame.getContentPane().getHeight()-icon[0].getIconHeight();
	            drawCard();
	        }
	        else if (cardDirection == DOWN && atBottomEdge()) {
	            eraseCard();
	            yPosition = 0;
	            drawCard();
	        }
	        else if (cardDirection == LEFT && atLeftEdge()){
	            eraseCard();
	            xPosition = cardJFrame.getContentPane().getWidth()-icon[0].getIconWidth();
	            drawCard();
	        }
	        else if (cardDirection == RIGHT && atRightEdge()){
	            eraseCard();
	            xPosition = 0;
	            drawCard();
	        }
    	}
    }
    public void changeHighlight(){
    	if (highlight == false){
    		highlight = true;
    		button.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
    	}else{
    		highlight = false;
    		button.setBorder(BorderFactory.createLineBorder(null));
    	}
    			
    }
    
    protected void drawCard(){   
    }
    protected String getTop(){
    	return topperName[Topper];
    }
    protected String getOrn(){
    	return topperName[Topper];
    }
    protected String getSize(){
    switch(Tree){
	case 0:
		return fatColorName[Colors];
		
	case 1:
		return skinnyColorName[Colors];
		
	case 2:
		return mediumColorName[Colors];
    default:
    	return "";
	}
    	
    }
    protected int getColors(){
    	return Colors;
    }
    protected void eraseCard(){
    	label[0].setVisible(false);
    }   
    
    protected boolean atRightEdge(){
        return (xPosition+label[0].getWidth()+horizontalMovement > cardJFrame.getContentPane().getWidth());
    }
    
    protected boolean atLeftEdge(){
        return (xPosition - horizontalMovement < 0); // horizontalMovement variable is alway positive
    }
    
    protected boolean atTopEdge(){
        return (yPosition - verticalMovement < 0); // vertical Movement variable is always positive 
    }
    
    protected boolean atBottomEdge(){
        return (yPosition+label[0].getHeight()+verticalMovement > cardJFrame.getContentPane().getHeight());
    }
    public boolean isHighlight(){
    	return highlight;
    }
    
    
    
    public boolean isCardHit(int xMousePosition, int yMousePosition){
        if ((xPosition <= xMousePosition 
            && xMousePosition <= xPosition + icon[0].getIconWidth())
            && (yPosition <= yMousePosition 
            && yMousePosition <= yPosition + icon[0].getIconHeight())){
                return true;
        }
        else {
            return false;
        }
    }
    
   public void gotHit(){
       
    }
    
    public void create(){   
        drawCard();
    }
    
    public void kill(){
        eraseCard();
    }
    public int getHeight(){
    	return icon[0].getIconHeight();
    }
    public int getWidth(){
    	return icon[0].getIconWidth();
    }
 
}