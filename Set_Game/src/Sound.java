import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

///////////////////////////////////////
///////////////////////////////////////
//////// Set Game Sound Class /////////
///////////////////////////////////////
///////////////////////////////////////

public class Sound {
	Clip[] clip = new Clip[1];;
	String audio;
	
	// Constructor to initialize a sound
	public Sound()
	{ 
		try {
			clip[0] = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Sets the file for the music to look at
	public void SetMusic(String string){
		try 
		{
			
			audio = string;
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
	        clip[0] = AudioSystem.getClip();
	        clip[0].open(audioInputStream);
	        
	    } 
		catch(Exception ex) 
		{
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	// Stops the music playing
	public void stopSound(){
		clip[0].stop();
	}
	
	// PLays the sound track defined above
	public void playSound(boolean loop){
		try{
			clip[0].start();
			if(loop == true){
				clip[0].loop(clip[0].LOOP_CONTINUOUSLY);
			}else{
				clip[0].loop(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
