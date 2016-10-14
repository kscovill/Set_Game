import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Card {
	 public final static int UP = 0;
	    public final static int DOWN = 1;
	    public final static int LEFT = 2;
	    public final static int RIGHT = 3;
	    public final static int NUMBER_OF_DIRECTIONS = 4;
	    protected JFrame cardJFrame;
	    protected JLabel cardJLabel; // the container for the Image (of a bug)
	    
	    protected String cardImageName[] = new String[14];
	    
	    protected int horizontalMovement;
	    protected int verticalMovement;
	    protected int directionChangeProbability;
	    protected int cardDirection=UP;
	    protected int xPosition=0;  // top left corner of Image
	    protected int yPosition=0;  // top left corner of Image
	    protected int hit = 0;
	    
	    protected void drawCard(){ 
	    	/////////////////
	    	/////////////////
	    	// How are we going to handle this?
	    	////////////////
	    	////////////////
	    }

	    protected void eraseCard(){
	    	
	    }   
	    
	    protected boolean atRightEdge(){
	        return (xPosition+cardJLabel.getWidth()+horizontalMovement > cardJFrame.getContentPane().getWidth());
	    }
	    
	    protected boolean atLeftEdge(){
	        return (xPosition - horizontalMovement < 0); // horizontalMovement variable is alway positive
	    }
	    
	    protected boolean atTopEdge(){
	        return (yPosition - verticalMovement < 0); // vertical Movement variable is always positive 
	    }
	    
	    protected boolean atBottomEdge(){
	        return (yPosition+cardJLabel.getHeight()+verticalMovement > cardJFrame.getContentPane().getHeight());
	    }
	    
	    //---------------------------------
	    //  Public Methods
	    //---------------------------------
	    //constructor:
	    public Card(JFrame passedInJFrame, int passedInDirectionChangeProbability, int passedInCardNumber){
	        cardJFrame = passedInJFrame;
	        directionChangeProbability = passedInDirectionChangeProbability;

	        cardJLabel = new JLabel();
	        cardJLabel.setBounds (10, 10, 10, 10); // arbitrary, will change later
	        cardJFrame.getContentPane().add(cardJLabel);
	        cardJLabel.setVisible(false);
	        cardJLabel.setVisible(true);

	        horizontalMovement = 0;
	        verticalMovement = 0;
	        cardDirection = UP;
	        xPosition = 50*passedInCardNumber; // arbitrary starting point
	        yPosition = 20; // arbitrary starting point        
	    }
	   
	    public boolean isCardHit(int xMousePosition, int yMousePosition){
	        if ((xPosition <= xMousePosition 
	            && xMousePosition <= xPosition + cardImage[cardDirection].getIconWidth())
	            && (yPosition <= yMousePosition 
	            && yMousePosition <= yPosition + cardImage[cardDirection].getIconHeight())){
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
