package Music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
public class Click extends JFrame {
    public Clip backgroundM;
    private InputStream A;

    public Click() {

        try {
            backgroundM = AudioSystem.getClip();//创造空的clip
            A = Click.class.getClassLoader().getResourceAsStream("Music/2.wav");//作为一个流输入，使用类加载器
            AudioInputStream B = AudioSystem.getAudioInputStream(A);//转为audioInputStream
            backgroundM.open(B);
            backgroundM.start();
            backgroundM.loop(1);

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}