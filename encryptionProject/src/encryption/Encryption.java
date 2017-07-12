package encryption;

//Encryption program
/*
* This program allows the user to write encrypted
* text to a newly created text file.
* Note: .txt extension will automatically be given
* at the end of the file name.
* 
* To end program, user must input an empty string.
* Afterwards the encryption key will be written to a
* new text file. This allows the user cipher
* the encrypted text.
*/


import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class Encryption {
	
	public static void main(String args[]) throws IOException{
		String[] text = "abcdefghijklmnopqrstuvwxyz0123456789.!?".split("");
		List<String> textList = new ArrayList<String>();
		Map<String, String> encryptionKey = new HashMap<String, String>();
		Map<String, String> answerKey = new HashMap<String, String>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i = 0; i < text.length; i++){
			textList.add(text[i]);
			index.add(i);
		}
		List<String> shuffleList = textList;
		Collections.shuffle(shuffleList);
		String[] newText = new String[text.length];
		for(int j = 0; j < text.length; j++){
			newText[j] = shuffleList.get(j);
		}
		for(int k = 0; k < newText.length; k++){
			encryptionKey.put(text[k].toUpperCase(), newText[k].toUpperCase());
			answerKey.put(newText[k].toUpperCase(), text[k].toUpperCase());
		}
		
		BufferedReader encryption = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter name for encrypted file: ");
		String filename = encryption.readLine();
		if(filename.equals("")){
			System.out.println("File name cannot be empty");
			return;
		}
		if(new File(filename + ".txt").exists() == true){
			System.out.println("File already exist");
			return;
		}
		File newFile = new File(filename + ".txt");
		BufferedWriter writeToFile = new BufferedWriter(new FileWriter(newFile));
		
		while(true){
			System.out.println("Begin new encryption");
			String encryptionText = encryption.readLine();
			if(encryptionText.equals("")){
				writeToFile.close();
				break;
			}
			System.out.println("Encrypting: " + encryptionText);
			String newEncryption = new String();
			for(int l = 0; l < encryptionText.length(); l++){
				String letter = Character.toString(encryptionText.charAt(l)).toUpperCase();
				if(encryptionKey.containsKey(letter)){
					newEncryption = newEncryption + encryptionKey.get(letter);
				}else{
					newEncryption = newEncryption + letter;
				}
			}
			writeToFile.write(newEncryption + "\n");
			System.out.println("Encryption complete: ");
			System.out.println(newEncryption);
		}
		System.out.println("Encryption ended");
		System.out.println("Here is the encryption key:");
		File cipherKey = new File(filename + "CipherKey.txt");
		BufferedWriter writeToCipherKey = new BufferedWriter(new FileWriter(cipherKey));
		writeToCipherKey.write("Encrypted value = Actual value" + "\n");
		for(String key: answerKey.keySet()){
			writeToCipherKey.write(key + " = " + answerKey.get(key) + "\n");
		}
		writeToCipherKey.close();
		return;
		
	}

}
