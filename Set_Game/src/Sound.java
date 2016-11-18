import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound {
	Clip[] clip = new Clip[1];;
	String audio;
	public Sound()
	{ 
		try {
			clip[0] = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	public void stopSound(){
		clip[0].stop();
	}
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
