import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Card {
	 public final static int UP = 0;
	    public final static int DOWN = 1;
	    public final static int LEFT = 2;
	    public final static int RIGHT = 3;
	    public final static int NUMBER_OF_DIRECTIONS = 4;
	    public final static int DECK_SIZE = 81;
	    protected JFrame cardJFrame;
	    protected JLabel cardJLabel[][] = new JLabel[DECK_SIZE][3]; // the container for the Image (of a bug)
	    
	    
	    protected String fatColorName[] = new String[3];
	    protected String mediumColorName[] = new String[3];
	    protected String skinnyColorName[] = new String[3];
	    protected String ornamentName[] = new String[3];
	    protected String topperName[] = new String[3];
	    protected ImageIcon cardImage[][] = new ImageIcon[81][4];
	    
	    protected int horizontalMovement;
	    protected int verticalMovement;
	    protected int directionChangeProbability;
	    protected int xPosition=0;  // top left corner of Image
	    protected int yPosition=0;  // top left corner of Image
	    protected int hit = 0;
	    
	    protected void Shuffle(){
	    	
	    }
	    protected void drawCard(){ 
	    	JLabel label = new JLabel();
	    	ImageIcon icon = new ImageIcon("fatRed.png");
	    	label.setIcon(icon);
	    }

	    protected void eraseCard(){
	    	
	    }   
	    
	    protected boolean atRightEdge(){
	        return (xPosition+cardJLabel[0][0].getWidth()+horizontalMovement > cardJFrame.getContentPane().getWidth());
	    }
	    
	    protected boolean atLeftEdge(){
	        return (xPosition - horizontalMovement < 0); // horizontalMovement variable is alway positive
	    }
	    
	    protected boolean atTopEdge(){
	        return (yPosition - verticalMovement < 0); // vertical Movement variable is always positive 
	    }
	    
	    protected boolean atBottomEdge(){
	        return (yPosition+cardJLabel[0][0].getHeight()+verticalMovement > cardJFrame.getContentPane().getHeight());
	    }
	    
	    //---------------------------------
	    //  Public Methods
	    //---------------------------------
	    //constructor:
	    public Card(JFrame passedInJFrame, int passedInDirectionChangeProbability, int passedInCardNumber){
	        cardJFrame = passedInJFrame;
	        directionChangeProbability = passedInDirectionChangeProbability;

	        
	       
	       
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
	    }
	   
	    
	    public boolean isCardHit(int xMousePosition, int yMousePosition){
	        if ((xPosition <= xMousePosition 
	            && xMousePosition <= xPosition + cardImage[0][0].getIconWidth())
	            && (yPosition <= yMousePosition 
	            && yMousePosition <= yPosition + cardImage[0][0].getIconHeight())){
	                return true;
	        }
	        else {
	            return false;
	        }
	    }
	    
	   public void gotHit(){
	       
	    }
	    
	    public void create(){   
	        hit = 0;
	        drawCard();
	    }
	    
	    public void kill(){
	        eraseCard();
	    }
	    
	    public void move(){
	    	
	    }
}
