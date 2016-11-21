import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    private int newCounter = 0;
    private ArrayList<Card> playable;
    private int numHighlighted = 0;
    private boolean gameReady = false;
    public int mouseCount =0;
    private int pauseTimer = 0;
    private boolean stopRandom = false;
    private JLabel playerName;
    private JLabel scoreKeeper;
    private JLabel numsets;
    private JLabel info;
    private Card[] checkables = new Card[3];
    private int playerScore =0;
    private final int MIN_CARDS = 12;
    private final int MAX_DECKS = 1;
    private boolean CHEAT = false;
    private final int GOAL = 4;
    private int div;
    protected static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected double w;
    protected double h;
    private static boolean LOOP = true;
    private static boolean NO_LOOP = false;
    JLabel background;
    protected String fatColorName[] = new String[3];
    protected String mediumColorName[] = new String[3];
    protected String skinnyColorName[] = new String[3];
    protected String ornamentName[] = new String[3];
    protected String topperName[] = new String[3];
    protected JPanel panel = new JPanel();
    protected JLayeredPane gamePanel = new JLayeredPane();
    protected JButton pauseButton;
    protected Sound santa;
    protected Sound bells = new Sound(); 
    protected Sound jingle = new Sound(); 
    protected JButton quit;
	JFrame gameStart = new JFrame();
	JFrame gameMenu = new JFrame(); 

	
	public Controller(String passedInWindowTitle, 
	        int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
		
		gameJFrame = new JFrame(passedInWindowTitle);
		gameJFrame.setVisible(false);
        gameJFrame.setSize(gameWindowWidth, gameWindowHeight);
        gameJFrame.setLocation(gameWindowX, gameWindowY);
        gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(screenSize.getWidth() < 1800){
        	background = new JLabel(new ImageIcon("winterGround1.png"));

        	background.setBounds(0, -100, gamePanel.getWidth(), gamePanel.getHeight());
        }else{
        	background = new JLabel(new ImageIcon("winterGround.png"));
        }
        
        panel.setBounds(0, 0, 380, gameWindowHeight);
        gamePanel.setBounds(380, 0, gameWindowWidth - 380, gameWindowHeight);
        panel.setBackground(Color.GRAY);
        background.setSize(gamePanel.getWidth(), 1150);
        gamePanel.add(background); 
       
        
        gamePanel.setVisible(true);
        panel.setVisible(true);
        gamePanel.setLayout(null);
        panel.setLayout(null);
        
        
        gameJFrame.add(panel);
        gameJFrame.add(gamePanel);
        gameJFrame.setResizable(false);
        gameContentPane = gameJFrame.getContentPane();
        gameContentPane.setLayout(null); 
        gameContentPane.setBackground(Color.white);
        
        int borderWidth = (gameWindowWidth - gameContentPane.getWidth())/2;  // 2 since border on either side
        xMouseOffsetToContentPaneFromJFrame = borderWidth;
        yMouseOffsetToContentPaneFromJFrame = gameWindowHeight - gameContentPane.getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
        
        playable = new ArrayList();
        
        pauseButton = new JButton("PAUSE CARDS (15 Seconds)");
        panel.add(pauseButton);
        pauseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pauseTimer = 15;
				newCounter = 0;
				pauseButton.setEnabled(false);
				pauseButton.setText("Disabled for 25 seconds");
				
			}});
        
        pauseButton.setBounds(50,panel.getHeight()/7*2,panel.getWidth()-100,40);
        pauseButton.setVisible(true);
        panel.setLayout(null);
        playerName = new JLabel("", SwingConstants.CENTER);
        panel.add(playerName);
        quit = new JButton("QUIT");
        quit.setBounds(50,panel.getHeight()/7*6,panel.getWidth()-100,40);
        quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
		}});
        playerName.setFont(new Font("Serif", Font.BOLD, 45));
        playerName.setBounds(50, panel.getHeight()/7*0+50, panel.getWidth()-100, 60);
        playerName.setVisible(true);
        scoreKeeper = new JLabel(Integer.toString(playerScore), SwingConstants.CENTER);
        numsets = new JLabel("", SwingConstants.CENTER);
        info = new JLabel("", SwingConstants.CENTER);
        panel.add(quit);
        panel.add(scoreKeeper);
        panel.add(numsets);
        panel.add(info);
        scoreKeeper.setFont(new Font("Serif", Font.PLAIN, 65));
        scoreKeeper.setBounds(0,panel.getHeight()/7*1,panel.getWidth(),100);
        scoreKeeper.setVisible(true);
        numsets.setFont(new Font("Serif", Font.PLAIN, 30));
        numsets.setBounds(0, panel.getHeight()/7*5, panel.getWidth(), 45);
        numsets.setVisible(true);
        info.setText("Have Fun");
        info.setFont(new Font("Serif", Font.PLAIN, 30));
        info.setBounds(0, panel.getHeight()/7*3, panel.getWidth(), 45);
        info.setVisible(true);
        
        
        panel.repaint();
        gameJFrame.getContentPane().repaint();
        gameTimer.schedule(this, 0, 250);

        gameJFrame.setVisible(false);
        
        
        wannaPlay();
  
        //drawCards(playable);
        
	}
	public static void main( String args[]){
		if(screenSize.getWidth()<1800){				
			Controller myController = new Controller("Advanced Set", 20,0, /*(int)screenSize.getWidth()*/1280 - 40, /*(int)screenSize.getHeight()*/800 - 120);// window title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
		}else{
			Controller myController = new Controller("Advanced Set", 60,10, 1920-120, 1080-80);// window title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
			
		}
	}
	public void restart(){
		
		jingle.stopSound();
		jingle.SetMusic("merryChristmas.wav");
		jingle.playSound(LOOP);
		gameJFrame.setVisible(true);
		
		gameDeck = new Deck(gameJFrame,gamePanel,MAX_DECKS);
		playerScore =0;
		scoreKeeper.setText("Score: " + Integer.toString(playerScore));
		playerName.setText("Player 1");
		CHEAT = false;
		
		
		/*while(!gameReady){
			int dialogResult = JOptionPane.showConfirmDialog(null, "Are you ready?");
			if(dialogResult == JOptionPane.YES_OPTION){
				gameReady = true;
				break;
			}if(dialogResult == JOptionPane.CANCEL_OPTION || dialogResult == JOptionPane.NO_OPTION){
				System.exit(1);
			}
			
		}*/
		
		String player;
		player = JOptionPane.showInputDialog(null, "Enter name :  ");
		if ((player == null)) {
			System.exit(1);
		}
		
		playerName.setText(player);
		if(player.equals("cheat")){
			CHEAT = true;
		}
		
		makeDeck();
		counter = 0;
		
		
		
	}
	public void makeDeck(){
		if(playable.isEmpty() && !gameDeck.list.isEmpty()){
			for (int j =0; j < MIN_CARDS; j++){
	        	playable.add(gameDeck.list.get(0));
	        	gameDeck.list.remove(0);
	        	
	        }
		}
		for(int i =0; i < playable.size();i++){
			playable.get(i).button.setBorder(null);
		}
		
		//System.out.println(playable.size() + " on the field");
		//System.out.println(gameDeck.list.size() + " Cards left in Deck");
		int sets = 0;
		int size = playable.size();
		for (int i =0; i < size;i++){
			for (int j = i+1; j<size;j++){
				for (int k = j+1; k < size;k++){
					
					sets += isThereASet(playable.get(i),playable.get(j),playable.get(k));
					
				}
			}
		}
		if(sets == 1){
			numsets.setText("There is " + sets + " set on the table!");
		}else{
			numsets.setText("There are " + sets + " sets on the table!");
		}
		//System.out.println("There is/are " + sets + " set(s) on the table.");
		if(sets ==0 || playable.size() < MIN_CARDS){
			//System.out.println("The size of the gameDeck is " + gameDeck.list.size());
			if(!gameDeck.list.isEmpty()){
				for(int h = 0; h < 3; h++){
					playable.add(gameDeck.list.get(0));
					gameDeck.list.remove(0);
				}
				makeDeck();
			}
		}
		
		drawCards(playable);
		
	}
	public void drawCards(ArrayList<Card> play){
		//System.out.print("BEFORE DRAWING, THERE ARE : " + play.size() + "IN THE ARRAY");
		
		
		for ( int i =0; i < play.size(); i++){
			playable.get(i).button.setVisible(false);
			if (screenSize.getWidth()< 1800){
				playable.get(i).button.setBounds((20+(int)(i%(play.size()/3))*(int)((double)120)), 10 + ((int)(i/(play.size()/3))*(int)((double)(220))), playable.get(0).getWidth()-20, playable.get(0).getHeight()+50);
			}else{
				playable.get(i).button.setBounds((20+(int)(i%(play.size()/3))*(int)((double)200)), 40 + ((int)(i/(play.size()/3))*(int)((double)(300))), playable.get(0).getWidth()-20, playable.get(0).getHeight()+50);
				
			}
        	playable.get(i).button.setVisible(true);
        	/*System.out.println(play.get(i));
        	System.out.println(play.get(i).getOrn());
        	System.out.println(play.get(i).getTop());
        	System.out.println(play.get(i).getColors());
        	System.out.println(play.get(i).getSize());*/
        }

		gamePanel.setComponentZOrder(background,gamePanel.getComponentCount()-1);
		gamePanel.repaint();
		gameJFrame.getContentPane().repaint();
		
	}
	public void eraseCards(ArrayList<Card> play){
		for ( int i =0; i < play.size(); i++){
        	play.get(0).button.setVisible(false);
        	
        }
	}
	public void eraseCard(int cardnum){
		playable.get(cardnum).button.setVisible(false);
	}
	public void randomize(ArrayList<Card> play){
		Collections.shuffle(play);
	}
	public int isThereASet(Card a, Card b, Card c){
		boolean colorAns = false;
		boolean sizeAns = false;
		boolean ornAns = false;
		boolean topAns = false;
		int sets = 0;
		
		/*
		//check size
		if(a.getSize().equals(b.getSize()) && b.getSize().equals(c.getSize())){
			sizeAns = true;
			System.out.println("ALL SAME SIZE");
		}else if(!a.getSize().equals(b.getSize())  && !a.getSize().equals(c.getSize()) && !b.getSize().equals(c.getSize())){
			sizeAns = true;
			System.out.println("ALL DIFFERENT SIZES");
			System.out.println(a.getSize());
			System.out.println(b.getSize());
			System.out.println(c.getSize());
			
		}else{
			sizeAns = false;
			System.out.println("SIZES NOT SET");
		}
		*/
		
		//check Sizes
		if(a.getSizeint() == b.getSizeint() && b.getSizeint() == c.getSizeint()){
			sizeAns = true;
		}else if(a.getSizeint() != b.getSizeint()  && a.getSizeint() != c.getSizeint() && b.getSizeint() != c.getSizeint()){
			sizeAns = true;
			
		}else{
			sizeAns = false;
		}
		
		//check  ornaments
		if(a.getOrn().equals(b.getOrn()) && b.getOrn().equals(c.getOrn())){
			ornAns = true;
		}else if(!a.getOrn().equals(b.getOrn())  && !a.getOrn().equals(c.getOrn()) && !b.getOrn().equals(c.getOrn())){
			ornAns = true;
			
		}else{
			ornAns = false;
		}
		
		//check top
		if(a.getTop().equals(b.getTop()) && b.getTop().equals(c.getTop())){
			topAns = true;
		}else if(!a.getTop().equals(b.getTop())  && !a.getTop().equals(c.getTop()) && !b.getTop().equals(c.getTop())){
			topAns = true;
			
		}else{
			topAns = false;
		}
		
		//check color
		if(a.getColors() == b.getColors() && b.getColors() == c.getColors()){
			colorAns = true;
		}else if(a.getColors() != b.getColors() && a.getColors() != c.getColors() && b.getColors() != c.getColors()){
			colorAns = true;
			
		}else{
			colorAns = false;
		}
		
		if(colorAns == true && topAns == true && ornAns == true && sizeAns == true){
			sets++;
			if(CHEAT){
				a.button.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
				b.button.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
				c.button.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
				System.out.println("Next Set");
				System.out.println(a.getColors() + ", " + a.getTop() + ", " + a.getSize() + ", " + a.getOrn());
				System.out.println(b.getColors() + ", " + b.getTop() + ", " + b.getSize() + ", " + b.getOrn());
				System.out.println(c.getColors() + ", " + c.getTop() + ", " + c.getSize() + ", " + c.getOrn());
			}
		}
		
		return sets;
	}
	public void restartMouseCount(){
		mouseCount =0;
	}
	public void DidIWin(){
		if (playerScore == GOAL){
			for(int j =0; j< playable.size();j++){
				playable.get(j).button.setVisible(false);
			}
			for(int i =0; i < gameDeck.list.size();i++){
				gameDeck.list.get(i).button.setVisible(false);
				
			}
			if(!playable.isEmpty()){
				int playsize = playable.size();
				for(int i =0; i < playsize; i++){
					playable.remove(0);
				}
			}
			if(!gameDeck.list.isEmpty()){
				for(int i =0; i < gameDeck.list.size();i++){
					gameDeck.list.remove(0);
					
				}
			}
			gameReady = false;
			JOptionPane.showMessageDialog(null, "YOU WON");
			int gameRestart = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
			if (gameRestart == JOptionPane.NO_OPTION || gameRestart == JOptionPane.CANCEL_OPTION){
				System.exit(1);
			}else{
				restart();
			}
		}
	}
	@Override
	public void run() {
		counter++;
		newCounter++;
		DidIWin();
		//
		// Every One Second
		//
		//System.out.println("Number of time ran: " + counter);
		if(counter%4 == 0){
			if(pauseTimer != 0){
				pauseTimer--;
				info.setText("Time Remaining: " + pauseTimer);
				if (pauseTimer < 1){
					playerScore--;
					scoreKeeper.setText("Score: " + Integer.toString(playerScore));
					info.setText("You ran out of time!");
					
				}
				//System.out.println(pauseTimer);
			}
		}
		if(!pauseButton.isEnabled()){
			if(newCounter%100 == 0){
				pauseButton.setEnabled(true);
				pauseButton.setText("PAUSE CARDS (15 Seconds)");
			}
		}
		for (int i =0; i < playable.size(); i++){
			if(playable.get(i).isHighlight() == true){
				numHighlighted++;
				checkables[numHighlighted-1] = playable.get(i);
				if(numHighlighted == 3){
					if(isThereASet(checkables[0],checkables[1],checkables[2])==1)
					{
						try 
						{
							santa = new Sound();
						    santa.SetMusic("santaLaugh.wav");
							santa.playSound(NO_LOOP);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						for(int q =0; q < checkables.length; q++){
							
							for( int h = 0; h < playable.size();h++){
								if(playable.get(h).equals(checkables[q])){
									eraseCard(h);
									playable.remove(h);
									break;
								}
								
							}
							
						}
						if(!pauseButton.isEnabled()){
							pauseButton.setEnabled(true);
							pauseButton.setText("PAUSE CARDS (15 Seconds)");
						}
						numHighlighted =0;
						pauseTimer = 0;
						info.setText("That was a Set!");
						playerScore++;
						scoreKeeper.setText("Score: " + Integer.toString(playerScore));
						makeDeck();
						
					}
					else{
						for(int q =0; q < checkables.length; q++){
							
							checkables[q].changeHighlight();
						}
						pauseTimer = 0;
						//System.out.println("That was not a set!");
						info.setText("That was not a set!");
					}
					for(int w = 0; w < 3; w++){
						checkables[w] = null;
					}
				}
				//System.out.println("Card " + i + " Is highlighted.");
			}			
		}
		numHighlighted = 0;
		
		//
		// Every 8 Seconds
		//
		
		if(counter%16 == 0 && pauseTimer == 0){
			if(!CHEAT){
				if(!stopRandom){
					
						//eraseCards(playable);
						randomize(playable);
						drawCards(playable);
					
				}
			}
		}
		/*
		//
		// Every 20 Seconds (ADD WITHOUT MOUSE PRESS)
		//
		if(counter%80 == 0 && pauseTimer == 0){
			if(!CHEAT){
				for (int i = 0; i < playable.size();i++){
					if(playable.get(i).isHighlight() == true){
						playable.get(i).changeHighlight();
					}
				}
			}
		}
		*/
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mouseCount=0;
		
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
	
	
	private void wannaPlay(){
	
        bells.SetMusic("bells.wav");
        bells.playSound(LOOP);
		gameStart.setBounds(1000,700, 1000, 678);
		gameStart.setLocationRelativeTo(null);
		gameStart.setLayout(null);
		gameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon background1 = new ImageIcon("background.png"); 
		JLabel background2 = new JLabel(background1); 
		
		background2.setBounds(0, 0, gameStart.getWidth(), gameStart.getHeight());
		JButton start = new JButton("Play"); 
		JButton quit = new JButton("Quit"); 
		JButton instructions = new JButton("Instructions");
		gameStart.add(instructions);
		gameStart.getContentPane().add(start); 
		gameStart.getContentPane().add(quit); 
		gameStart.add(background2);
		start.setBounds(gameStart.getWidth()/2-70,gameStart.getHeight()/2+150,150,30);
		quit.setBounds(gameStart.getWidth()/2-70,gameStart.getHeight()/2+230,150,30);
		instructions.setBounds(gameStart.getWidth()/2-70,gameStart.getHeight()/2+190,150,30);
		quit.setVisible(true);
		start.setVisible(true);
		
		gameStart.setVisible(true);
		gameStart.getContentPane().repaint();
		start.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				bells.stopSound();
				gameReady = true;
				gameStart.setVisible(false);
			    restart();
			}	  
	    });
		instructions.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				gameStart.setVisible(false);
				LearnYa(0);
			}	  
	    });
		quit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(1);
			}	  
	    });
	}
	
	ImageIcon background1 = new ImageIcon("instruction1.jpg"); 
	JLabel background2 = new JLabel(background1); 
	ImageIcon background3 = new ImageIcon("instruction2.jpg"); 
	JLabel background4 = new JLabel(background3); 
	int Instructcount = 0;
	
	private void LearnYa(int starter){
		gameMenu.setBounds(1000,700, 1017, 590);
		gameMenu.setLocationRelativeTo(null);
		gameMenu.setTitle("Instructions");
		gameMenu.setLayout(null);
		gameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		background2.setBounds(0, -60, gameStart.getWidth(), gameStart.getHeight());
		background4.setBounds(0, -60, gameStart.getWidth(), gameStart.getHeight());
		JButton start = new JButton("Next ====>"); 
		JButton quit = new JButton("Quit"); 
		JButton menu = new JButton("Menu");
		gameMenu.add(menu);
		gameMenu.getContentPane().add(start); 
		gameMenu.getContentPane().add(quit); 
		gameMenu.add(background2);
		gameMenu.add(background4);
		start.setBounds(gameMenu.getWidth()/2+90,gameMenu.getHeight()/2+212,150,30);
		quit.setBounds(gameMenu.getWidth()/2-70,gameMenu.getHeight()/2+212,150,30);
		menu.setBounds(gameMenu.getWidth()/2-230,gameMenu.getHeight()/2+212,150,30);
		quit.setVisible(true);
		start.setVisible(true);
		
		if(starter == 0){
			background2.setVisible(true);
			background4.setVisible(false);
			Instructcount= 0;
			
		}
		
		gameMenu.setVisible(true);
		gameMenu.getContentPane().repaint();
		start.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(Instructcount == 0){
					background2.setVisible(false);
					background4.setVisible(true);
					Instructcount++;
					start.setText("Menu");
					menu.setText("<==== Previous");
				}
				else if(Instructcount == 1){
					gameMenu.setVisible(false);
					bells.stopSound();
					start.setText("Next  ====>");
					menu.setText("Menu");
					wannaPlay();
					
				}
				
			}	  
	    });
		menu.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(Instructcount == 0){
					gameMenu.setVisible(false);
					bells.stopSound();
					start.setText("Next  ====>");
					menu.setText("Menu");
					wannaPlay();
					
				}
				else if(Instructcount == 1){
					background2.setVisible(true);
					background4.setVisible(false);
					Instructcount--;
					start.setText("Next  ====>");
					menu.setText("Menu");
				}
			}	  
	    });
		quit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(1);
			}	  
	    });
		
	}
}
