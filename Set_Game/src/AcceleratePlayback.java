import javax.swing.JOptionPane;
import javax.sound.sampled.*;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.util.Date;

class AcceleratePlayback {

    public static void main(String[] args) throws Exception {
        int playBackSpeed = 2;
        int skip = playBackSpeed-1;
        System.out.println("Playback Rate: " + playBackSpeed);

        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("merryChristmas.wav").getAbsoluteFile());
        AudioFormat af = ais.getFormat();

        int frameSize = af.getFrameSize();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[2^16];
        int read = 1;
        while( read>-1 ) {
            read = ais.read(b);
            if (read>0) {
                baos.write(b, 0, read);
            }
        }
        System.out.println("End entire: \t" + new Date());

        byte[] b1 = baos.toByteArray();
        byte[] b2 = new byte[b1.length/playBackSpeed];
        for (int ii=0; ii<b2.length/frameSize; ii++) {
            for (int jj=0; jj<frameSize; jj++) {
                b2[(ii*frameSize)+jj] = b1[(ii*frameSize*playBackSpeed)+jj];
            }
        }
        System.out.println("End sub-sample: \t" + new Date());

        ByteArrayInputStream bais = new ByteArrayInputStream(b2);
        AudioInputStream aisAccelerated =
            new AudioInputStream(bais, af, b2.length);
        Clip clip = AudioSystem.getClip();
        clip.open(aisAccelerated);
        clip.loop(2*playBackSpeed);
        clip.start();

        JOptionPane.showMessageDialog(null, "Exit?");
    }
}