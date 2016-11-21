import javax.swing.JFrame; // for JFrame
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon; // for ImageIcon
import javax.swing.JButton;


class Card {
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
    protected int mouseCounter;
    protected int div = 3;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public Card(JFrame myJFrame, JLayeredPane panel, int cardNumber, int directionChangeProbability, int tree, int orn, int top, int color){
    	
    	Colors = color;
    	Topper = top;
    	Tree = tree;
    	Ornament = orn;

  	   //System.out.println(screenSize.getHeight() + " is the height in pixels");
  	   //System.out.println(screenSize.getWidth() + " is the width in pixels");
    	
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
		
       if(screenSize.getWidth() < 1800){
    	   div = 3;
       }
       else{
    	   div = 2;
       }
        
        
     	   button = new JButton();
 	       for(int i = 0; i < 3; i++){
 		        label[i] = new JLabel();
 		        Image image = icon[i].getImage(); // transform it 
 		        Image newimg = image.getScaledInstance((int)(374/div), (int)(422/div),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
 		        icon[i] = new ImageIcon(newimg);  // transform it back
 		        label[i].setIcon(icon[i]);
 		        button.add(label[i]);
 		        button.setLayout(null);
 		        button.setBackground(Color.GRAY);
 		        button.setOpaque(true);
 		        label[i].setVisible(false);
 		        label[i].setVisible(true); 
 		        //System.out.println(button.getBackground());
 		        label[i].setBounds(-10, 25, icon[0].getIconWidth(), icon[0].getIconHeight());
 		        
 		        
 		        
 		        
 	       }	
 	      button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					changeHighlight();
				}
	        	  
	        });
 	       panel.add(button);
 	       button.setVisible(true);
 	       panel.setVisible(true);
 	       myJFrame.getContentPane().repaint();
        
	    
     
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
    	return ornamentName[Ornament];
    }
    protected int getSizeint(){
    	return Tree;
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
    
    
    public boolean isHighlight(){
    	return highlight;
    }
    
  
   
    public int getHeight(){
    	return icon[0].getIconHeight();
    }
    public int getWidth(){
    	return icon[0].getIconWidth();
    }
 
}