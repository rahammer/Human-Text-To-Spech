package HumanTextToSpeech;

import java.io.File;
import java.io.IOException;

//import the sound libraries to play audio
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
//import the local MaryTTS text to speech package
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
//extra MaryTTS packages to use at a later time:
//import marytts.modules.synthesis.Voice;
//import marytts.signalproc.effects.AudioEffect;
//import marytts.signalproc.effects.AudioEffects;
//import marytts.util.data.audio.MonoAudioInputStream;
//import marytts.util.data.audio.StereoAudioInputStream;


public class AudioPlayer {

    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 5000;
    
    /**
     * Takes the audio files path, gets the .wav file
     * and converts it to an AudioInputStream and then
     * plays it using the PlayAudioStream() method.
     * @param audioFilePath Path of the audio file.
     */
    void ConvertToAudioInputStream(String audioFilePath)
    {
    	File audioFile = new File(audioFilePath);	//save the file from the path
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);	//get the audio input stream from the file
            PlayAudioStream(audioStream);	//play the audio input stream
        }
        catch (UnsupportedAudioFileException | IOException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        }
    }	//end ConvertToAudioInputStream()
    
    /**
     * Plays a given audio input stream.
     * @param audioStreamInput
     */
    void PlayAudioStream(AudioInputStream audioStreamInput) {
        try {
            AudioFormat format = audioStreamInput.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);
            audioLine.start();
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = audioStreamInput.read(bytesBuffer)) != -1) 
            {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
            audioLine.drain();
            audioLine.close();
            audioStreamInput.close();
        } 
    	catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } 
    	catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
    }	//end of PlayAudioStream()
    
    /**
     * Play an audio version of the text given
     * @param textToPlay string of text to play
     * @throws MaryConfigurationException 
     * @throws SynthesisException 
     */
    void playTextAudio(String textToPlay)
    {
    	System.out.println("Text to Speech play " + textToPlay);
    	MaryInterface marytts;
		try {
			marytts = new LocalMaryInterface();	//create a local MaryTTS interface
			AudioInputStream audioStream = marytts.generateAudio(textToPlay);	//turn the given text into an AudioInputStream through MaryTTS
			//marytts.setVoice("dfki-pavoque-neutral");	//change speech to a different voice
			PlayAudioStream(audioStream);	//play the audio stream
		} catch (MaryConfigurationException | SynthesisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	//end of playTextAudio()
	
}
