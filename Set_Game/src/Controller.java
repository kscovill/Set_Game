import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        gameContentPane = gameJFrame.getContentPane();
        gameContentPane.setLayout(null); // not need layout, will use absolute system
        gameContentPane.setBackground(Color.white);
        
        // Event mouse position is given inside JFrame, where bug's image in JLabel is given inside ContentPane,
        //  so adjust for the border and frame
        int borderWidth = (gameWindowWidth - gameContentPane.getWidth())/2;  // 2 since border on either side
        xMouseOffsetToContentPaneFromJFrame = borderWidth;
        yMouseOffsetToContentPaneFromJFrame = gameWindowHeight - gameContentPane.getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
        
        JLabel label[] = new JLabel[3];
        String name[] = new String[3];
        name[0] = "yellowOrnaments.png";
        name[1] = "angel.png";
        name[2] = "mediumNormal.png";
        
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
        
        
        label[0] = new JLabel();
        label[0].setIcon(new ImageIcon(name[0]));
        
        JButton button[] = new JButton[4];
        ImageIcon icon[] = new ImageIcon[3];
        
        
        
       for (int j = 0; j < 4; j++){
    	   button[j] = new JButton();
	       for(int i = 0; i < 3; i++){
		        label[i] = new JLabel();
		        icon[i] = new ImageIcon(name[i]);
		        Image image = icon[i].getImage(); // transform it 
		        Image newimg = image.getScaledInstance((int)(374/2), (int)(422/2),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		        icon[i] = new ImageIcon(newimg);  // transform it back
		        label[i].setIcon(icon[i]);
		        button[j].add(label[i]);
		        button[j].setLayout(null);
		        button[j].setBackground(Color.GRAY);
		        label[i].setBounds(-10, 25, icon[0].getIconWidth(), icon[0].getIconHeight());
		        gameJFrame.setVisible(true);
		        button[j].setBounds(50 + (j*200), 50, icon[0].getIconWidth()-20, icon[0].getIconHeight()+50);
		        button[j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						button[0].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
					}
		        	
		        });
	       }
	       gameJFrame.getContentPane().add(button[j]);
       }
        
        gameJFrame.setVisible(true);
	}
	public static void main( String args[]){
        Controller myController = new Controller("Advanced Set", 50,50, 1000, 800);// window title, int gameWindowX, int gameWindowY, int gameWindowWidth, int gameWindowHeight){
        
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
