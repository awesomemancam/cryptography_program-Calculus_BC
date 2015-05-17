package main;

public class Cracker {

	public static void crack(String codedMessage, int cipherType) {
		if (cipherType == 1) {
			// SHIFT CIPHER
			c_shiftCipher(codedMessage);
		} else if (cipherType == 2) {
			//TODO
			System.out.print("Currently not in function.");
		} else if (cipherType == 3) {
			//TODO
			System.out.print("Currently not in function.");
		} else {
			System.out.print("Invalid choice.");
		}
	}
	
	public static void c_shiftCipher(String codedMessage) {
		System.out.println();
		int bestMatch = 0;
		int x = 0;
		String bestDecryption = "";
		for (int i = 1; i < 26; i++) {
			String next = Decrypter.d_shiftCipher(codedMessage, i);
			System.out.println("Shift " + i + ": " + next);
			x = scanForWords(Decrypter.d_shiftCipher(codedMessage, i));
			if (x > bestMatch) {
				bestMatch = x;
				bestDecryption = next;
			}
		}
		System.out.println("\nBest Decryption with " + bestMatch + " matching words:\n  :: " + bestDecryption);
	}
	
	public static int scanForWords(String message) {
		message += " ";
		int numWords = 0;
		String word = "";
		for (int i = 0; i < message.length(); i++) {
			if ((int)message.charAt(i) == 32) {
				if (Dictionary.searchDictionary(word)) {
					numWords++;
				}
				word = "";
			} else {
				word += (char)((int)message.charAt(i)+32);
			}
		}
		
		return numWords;
	}
	
}
