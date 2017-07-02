package encryption;

//Encryption program
/*
* Allows user to input text to encrypt(limited to the alphabet)
* To end program, user must input an empty string.
* Afterwards the encryption key will be shown to the user
*/


import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Encryption {
	
	public static void main(String args[]) throws IOException{
		String[] text = "abcdefghijklmnopqrstuvwxyz123456789.!?".split("");
		List<String> textList = new ArrayList<String>();
		Map<String, String> encryptionKey = new HashMap<String, String>();
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
		}
		
		BufferedReader encryption = new BufferedReader(new InputStreamReader(System.in));
		String encryptionText = "Placeholder";
		while(true){
			System.out.println("Begin new encryption");
			encryptionText = encryption.readLine();
			if(encryptionText.equals("")){
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
			System.out.println("Encryption complete: ");
			System.out.println(newEncryption);
		}
		System.out.println("Encryption ended");
		System.out.println("Here is the encryption key:");
		for(String key: encryptionKey.keySet()){
			System.out.println(key + " = " + encryptionKey.get(key));
		}
		return;
	}

}
