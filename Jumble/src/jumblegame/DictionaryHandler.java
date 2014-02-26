package jumblegame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Dictionary Handler Class
 * @author Sarp
 *
 */
public class DictionaryHandler {
	
	private LinkedHashMap<String, Boolean> hashMap;
	
	/*
	 * Constructor, takes folder name where dictionary files are stored
	 */
	public DictionaryHandler(String folder) {
		hashMap = new LinkedHashMap<String, Boolean>();
		addDictionaries(folder);
	}
	
	/*
	 * Takes a string as a word and checks if it is existing in the hashmap
	 */
	public boolean doesWordExist (String word) {
		Boolean result = hashMap.get(word);
		if (result == null) {
			return false;
		}
		return result;
	}
	
	/*
	 * Takes parameter as dictionary folder and finds dictionary files
	 */
	private void addDictionaries(String folder) {
		File file = new File(folder);
		if (file.isDirectory()) {//Make sure folder exists
			for (File dict : file.listFiles()) {
				if(dict.isFile()) {
					String dictionaryFile = dict.getName();
					//Make sure file has an extension(avoid README)
					if (dictionaryFile.contains(".")) {
						addFileContent(dict);
					}
				}
			}
		}
	}
	
	/*
	 * Takes dictionary files and parses its contents
	 */
	private void addFileContent(File dictFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(dictFile))) {
		    for (String line; (line = br.readLine()) != null; ) {
		        addEntryToMap(line);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Takes the entry parameter from dictionary adds it to the hashmap
	 */
	private void addEntryToMap(String entry) {
		hashMap.put(entry, true);
	}
	

}
