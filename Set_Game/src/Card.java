import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;
import javax.swing.ImageIcon; // for ImageIcon


class Card {
	public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;
    public final static int NUMBER_OF_DIRECTIONS = 4;
    public final static int DECK_SIZE = 81;
    protected JFrame cardJFrame;
    protected JLabel cardJLabel[][] = new JLabel[81][3]; // the container for the Image (of a bug)
    protected Card card[] = new Card[81];
    
    protected String fatColorName[] = new String[3];
    protected String mediumColorName[] = new String[3];
    protected String skinnyColorName[] = new String[3];
    protected String ornamentName[] = new String[3];
    protected String topperName[] = new String[3];
    protected ImageIcon cardImage[][] = new ImageIcon[81][4];
    
    protected int horizontalMovement;
    protected int verticalMovement;
    protected int directionChangeProbability;
    protected int cardDirection = UP;
    protected int xPosition=0;  // top left corner of Image
    protected int yPosition=0;  // top left corner of Image
	boolean movement = false;
	
    public Card(JFrame myJFrame, int directionChangeProbability, int cardNumber){
       
		cardImage[cardNumber][0] = new ImageIcon(mediumColorName[0]);	
		cardImage[cardNumber][1] = new ImageIcon(ornamentName[0]);
		cardImage[cardNumber][2] = new ImageIcon(topperName[0]);
        // movement is arbitrarily based on size of image
        horizontalMovement = cardImage[0][0].getIconWidth()/ 5; // arbitrary 1/5th of width
        verticalMovement = cardImage[0][0].getIconHeight()/ 5; // arbitrary 1/5th of height;  
        
        
	    	   for(int j = 0; j<3; j++){
	    		cardJLabel[cardNumber][j] = new JLabel();
	    		cardJLabel[cardNumber][j].setIcon(new ImageIcon("fatNormal.png"));
		        cardJLabel[cardNumber][j].setBounds (10, 10, 10, 10); // arbitrary, will change later
		        //cardJFrame.getContentPane().add(cardJLabel[cardNumber][j]);
		        cardJLabel[cardNumber][j].setVisible(false);
		        cardJLabel[cardNumber][j].setVisible(true);
		        
	    	   }
	    
    
     
     	cardJLabel[cardNumber][0].setIcon(cardImage[cardNumber][0]);
		cardJLabel[cardNumber][1].setIcon(cardImage[cardNumber][1]);
		cardJLabel[cardNumber][2].setIcon(cardImage[cardNumber][2]);
		System.out.println(cardNumber + " " +cardJLabel[cardNumber][0]);
     

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
	            yPosition = cardJFrame.getContentPane().getHeight()-cardImage[0][0].getIconHeight();
	            drawCard();
	        }
	        else if (cardDirection == DOWN && atBottomEdge()) {
	            eraseCard();
	            yPosition = 0;
	            drawCard();
	        }
	        else if (cardDirection == LEFT && atLeftEdge()){
	            eraseCard();
	            xPosition = cardJFrame.getContentPane().getWidth()-cardImage[0][0].getIconWidth();
	            drawCard();
	        }
	        else if (cardDirection == RIGHT && atRightEdge()){
	            eraseCard();
	            xPosition = 0;
	            drawCard();
	        }
    	}
    }
    protected void drawCard(){ 
    	cardJLabel[1][1].setIcon(cardImage[0][0]);
    	cardJLabel[1][1].setBounds(xPosition, yPosition, cardImage[0][0].getIconWidth(),cardImage[0][0].getIconHeight());  
        cardJLabel[1][1].setVisible(true);
    }

    protected void eraseCard(){
    	cardJLabel[1][1].setVisible(false);
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
        drawCard();
    }
    
    public void kill(){
        eraseCard();
    }
    
 
}