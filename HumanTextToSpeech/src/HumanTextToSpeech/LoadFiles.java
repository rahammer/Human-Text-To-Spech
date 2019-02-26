package HumanTextToSpeech;
/*
 * LoadFiles
 * The LoadFiles class reads all of the audio files names and saves them as an
 * ArrayList of string without the .wav identifier
 * 
 * @author Robert Hammerschmidt
 */
import java.util.ArrayList;
import java.io.File;

public class LoadFiles {
	/**
     * returns an ArrayList of each name of each audio file in
     * the folder "AudioFiles"
     */
	public ArrayList<String> GetWords()
	{
		ArrayList<String> listOfAudioSaying = new ArrayList<String>();
		File folder = new File("C:/Users/Robert/eclipse-workspace/HumanTextToSpeech/AudioFiles/");
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        String fileName = file.getName().substring(0, file.getName().length() - 4);	//remove ".wav"
		        listOfAudioSaying.add(fileName);	//add the name of the file (word to be spoken) to the list
		        //System.out.println(fileName);	//debug if name is being added to the list properly
		    }
		}
		return listOfAudioSaying;
	}
}
