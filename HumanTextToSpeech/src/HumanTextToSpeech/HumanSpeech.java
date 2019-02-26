package HumanTextToSpeech;
/*
 * HUMAN TEXT TO SPEECH Program
 * by, Robert Hammerschmidt
 * 
 * Purpose: This program takes in a string input and then "speaks" out what was written
 * by the person by accessing audio files of each word spoken by said person.
 * This was made so that people wouldn't have to use text to speech programs
 * that sound robotic and not like the person speaking.  This way a person can
 * keep their voice without having to physically talk.  If the word that is typed
 * in is not in the AudioFiles directory, it will substitute text to speech for the
 * word instead of skipping it or saying nothing.
 * 
 * Operation: This is done by parsing each word entered in the text line and then finding 
 * the corresponding .wav file for each word in the "AudioFiles" folder.  To add
 * your words into the program, please add them to the "AudioFiles" folder and 
 * make sure that the .wav file is titled with the word being said.  MaryTTS is the
 * text to speech program used in case the word cannot be found.
 * 
 * Audio files located at: C:/Users/Robert/eclipse-workspace/HumanTextToSpeech/AudioFiles/
 * 
 * Last edit: 2/24/2019
 */

//import the needed standard libraries
//import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HumanSpeech {

	//global list of the loaded audio files
	private static ArrayList<String> loadedFiles = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		//give an introduction
		System.out.println("Welcome to the Human Text to Speech Program\nType what you want to say here:");
		LoadFiles load = new LoadFiles();	//declare an instance of LoadFiles class
		loadedFiles = load.GetWords();	//loaded the words 
		
		String audioFilePath = "C:/Users/Robert/eclipse-workspace/HumanTextToSpeech/AudioFiles/javaTestScream.wav";
        AudioPlayer HumanSpeechPlayer = new AudioPlayer();	//declare an instance of AudioPlayer class
        //player.play(audioFilePath);	//plays java test scream
        
        //get text input to translate to speech
        Scanner textInput = new Scanner(System.in);	//declare scanner
        String inputLine = textInput.nextLine();	//read input line
        String[] wordsInLine = inputLine.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");	//split by each space and remove punctuation and upper case
        
        for(int wordIndex = 0; wordIndex < wordsInLine.length; wordIndex++)
        {
        	if(loadedFiles.contains(wordsInLine[wordIndex]))
        	{	//if the word matches one of the loaded audio files
        		audioFilePath = "C:/Users/Robert/eclipse-workspace/HumanTextToSpeech/AudioFiles/" + wordsInLine[wordIndex] + ".wav";
        		System.out.println("Playback started for " + wordsInLine[wordIndex]);
        		HumanSpeechPlayer.ConvertToAudioInputStream(audioFilePath);
        	}
        	else
        	{	//else the word did not match one of the loaded audio files
        		HumanSpeechPlayer.playTextAudio(wordsInLine[wordIndex]);
        	}
        }
	}	//end of Main()

}
