import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

///////////////////////////////////////
///////////////////////////////////////
///////// Set Game Controller /////////
///////////////////////////////////////
///////////////////////////////////////
public class Controller extends TimerTask implements MouseListener, ActionListener{
	public Deck gameDeck;
    public JFrame gameJFrame;
    public Container gameContentPane;
    private int xMouseOffsetToContentPaneFromJFrame = 0;
    private int yMouseOffsetToContentPaneFromJFrame = 0;
    private java.util.Timer gameTimer = new java.util.Timer();
    private int highCount = 0;
    private int counter = 0;
    private ArrayList<Card> playable;
    private int numHighlighted = 0;
    
    protected String fatColorName[] = new String[3];
    protected String mediumColorName[] = new String[3];
    protected String skinnyColorName[] = new String[3];
    protected String ornamentName[] = new String[3];
    protected String topperName[] = new String[3];
    
	public Controller(String passedInWindowTitle, 
	        int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
		
		gameJFrame = new JFrame(passedInWindowTitle);
        gameJFrame.setSize(gameWindowWidth, gameWindowHeight);
        gameJFrame.setLocation(gameWindowX, gameWindowY);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JPanel gamePanel = new JPanel();
        
        panel.setBounds(0, 0, 380, gameWindowHeight);
        gamePanel.setBounds(380, 0, gameWindowWidth - 380, gameWindowHeight);
        panel.setBackground(Color.GRAY);
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setVisible(true);
        panel.setVisible(true);
        gamePanel.setLayout(null);
        panel.setLayout(null);
        
        gameJFrame.add(panel);
        gameJFrame.add(gamePanel);
        gameContentPane = gameJFrame.getContentPane();
        gameContentPane.setLayout(null); // not need layout, will use absolute system
        gameContentPane.setBackground(Color.white);
        
        // Event mouse position is given inside JFrame, where bug's image in JLabel is given inside ContentPane,
        //  so adjust for the border and frame
        int borderWidth = (gameWindowWidth - gameContentPane.getWidth())/2;  // 2 since border on either side
        xMouseOffsetToContentPaneFromJFrame = borderWidth;
        yMouseOffsetToContentPaneFromJFrame = gameWindowHeight - gameContentPane.getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
        
        playable = new ArrayList();
        
        
        gameJFrame.setVisible(true);
        gameJFrame.getContentPane().repaint();
        gameDeck = new Deck(gameJFrame,gamePanel);
        
        
        for (int j =0; j < 12; j++){
        	playable.add(gameDeck.list.get(0));
        	gameDeck.list.remove(0);
        }
        drawCards(playable);
        gameTimer.schedule(this, 0, 1000);
	}
	public static void main( String args[]){
        Controller myController = new Controller("Advanced Set", 50,50, 1200, 1200);// window title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
        
	}
	public void drawCards(ArrayList<Card> play){
		for ( int i =0; i < play.size(); i++){
        	playable.get(i).button.setBounds((20+(int)(i%4)*200), 50 + ((int)(i/4)*300), playable.get(0).getWidth()-20, playable.get(0).getHeight()+50);
        	playable.get(i).button.setVisible(true);
        }
	}
	public void eraseCards(ArrayList<Card> play){
		for ( int i =0; i < play.size(); i++){
        	playable.get(i).button.setBounds((20+(int)(i%4)*200), 50 + ((int)(i/4)*300), playable.get(0).getWidth()-20, playable.get(0).getHeight()+50);
        	playable.get(i).button.setVisible(true);
        }
	}
	public void randomize(ArrayList<Card> play){
		Collections.shuffle(play);
	}
	public boolean isThereASet(){
		boolean ans = false;
		
		
		
		return ans;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		counter++;
		//
		// Every One Second
		//
		System.out.println("Number of time ran: " + counter);
		for (int i =0; i < playable.size(); i++){
			if(playable.get(i).isHighlight() == true){
				numHighlighted++;
				System.out.println("Card " + i + " Is highlighted.");
			}			
		}
		if(numHighlighted == 3){
			System.out.println("3 Highlighted");
			// Check those highlighted if a set.
		}else{
			System.out.println("Not enough highlighted.");
			numHighlighted = 0;
		}
		
		//
		// Every 15 Seconds
		//
		if(counter%15 == 0){
			for (int i = 0; i<playable.size();i++){
				eraseCards(playable);
				randomize(playable);
				drawCards(playable);
			}
		}
		
		//
		// Every 20 Seconds
		//
		if(counter%20 == 0){
			for (int i = 0; i < playable.size();i++){
				if(playable.get(i).isHighlight() == true){
					playable.get(i).changeHighlight();
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
