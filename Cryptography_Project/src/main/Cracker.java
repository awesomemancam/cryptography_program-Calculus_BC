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
		//TODO
	}
	
}
