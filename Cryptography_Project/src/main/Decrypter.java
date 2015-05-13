package main;

import java.util.Scanner;

public class Decrypter {
	
	private static String decryption;
	
	public static String decrypt(String message, int cipherType) {
		String decodedMessage = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		if (cipherType == 1) {
			System.out.print("Shift number (no greater than 26)\n  :: ");
			int i = scanner.nextInt();
			decodedMessage = d_shiftCipher(message, i);
		} else if (cipherType == 2) {
			// WAGSTEIN CIPHER
			System.out.print("Enter the value of x (1-10)\n  :: ");
			int i = scanner.nextInt();
			decodedMessage = d_wagstein(message, i);
		}
		
		return decodedMessage;
	}
	
	public static String d_shiftCipher(String message, int i) {
		String decryption = "";
		int[] bytes = new int[message.length()];
		for (int j = 0; j < message.length(); j++) {
			if ((int)message.charAt(j) > 96 && (int)message.charAt(j) < 123) {
				bytes[j] = ((((int)message.charAt(j)-71)-i)%26)+65;
			} else if ((int)message.charAt(j) > 64 && (int)message.charAt(j) < 91) {
				bytes[j] = ((((int)message.charAt(j)-39)-i)%26)+65;
			} else {
				bytes[j] = 32; //if unrecognized char - set to 'space'
			}
		}
		for (int k = 0; k < message.length(); k++) {
			decryption += (char)bytes[k];
		}
		
		return decryption;
	}
	
	public static String d_wagstein(String message, int x) {
		decryption = "";
		int[] bytes = new int[message.length()];
		int[] coeff = new int[message.length()];
		int powerCounter = 1;
		for (int k = 0; k < message.length(); k++) {
			if ((int)message.charAt(k) > 96 && (int)message.charAt(k) < 107) {
				coeff[k] = (int)message.charAt(k)-95;
				powerCounter++;
			} else if ((int)message.charAt(k) > 64 && (int)message.charAt(k) < 75) {
				coeff[k] = (int)message.charAt(k)-63;
			} else if ((int)message.charAt(k) == 32) {
				
				powerCounter = 1;
			}
		}
		
		return decryption;
	}

}
