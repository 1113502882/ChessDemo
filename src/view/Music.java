package view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread {
    private final String fileName;

    public Music(String wavFile) {
        this.fileName = wavFile;
    }

    public static Music audioPlayWave = new Music("jaycd - 贝多芬 欢乐颂（第九交响曲钢琴曲）_1.WAV");
    @SuppressWarnings("unused")
    public void run() {
        File soundFile = new File(fileName); // 播放音乐的文件名

        while (true) { // 设置循环播放
            AudioInputStream audioInputStream = null; // 创建音频输入流对象
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile); // 创建音频对象
            } catch (UnsupportedAudioFileException | IOException e1) {
                e1.printStackTrace();
                return;
            }
            AudioFormat format = audioInputStream.getFormat(); // 音频格式
            SourceDataLine auline = null; // 源数据线
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            }
            auline.start();
            int nBytesRead = 0;
            int EXTERNAL_BUFFER_SIZE = 524288;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

