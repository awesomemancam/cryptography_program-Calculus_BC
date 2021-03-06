package main;

import java.util.Scanner;

public class Decrypter {

	private static String decryption;

	public static String decrypt(String message, int cipherType) {
		String decodedMessage = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		if (cipherType == 1) {
			// SHIFT CIPHER
			System.out.print("Shift number (no greater than 26)\n  :: ");
			int i = scanner.nextInt();
			decodedMessage = d_shiftCipher(message, i);
		} else if (cipherType == 2) {
			// WAGSTEIN CIPHER
			System.out.print("Enter the value of x (1-10)\n  :: ");
			int i = scanner.nextInt();
			decodedMessage = d_wagstein(message+" ", i);
		} else if (cipherType == 3) {
			// Vigen�re CIPHER
			System.out.print("Enter the keyword (one word)\n  :: ");
			String keyword = scanner.next();
			decodedMessage = d_vigen�re(message, keyword);
		} else {
			System.out.print("Invalid choice.");
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
		int[] value = new int[message.length()];
		int[] bytes = new int[message.length()];
		int[] coeff = new int[message.length()];
		int powerCounter = 1;
		//load in values as 0-9 numbers or as "32" a space
		for (int k = 0; k < message.length(); k++) {
			if ((int)message.charAt(k) > 96 && (int)message.charAt(k) < 107) {
				value[k] = (int)message.charAt(k)-97;
			} else if ((int)message.charAt(k) > 64 && (int)message.charAt(k) < 75) {
				value[k] = (int)message.charAt(k)-65;
			} else if ((int)message.charAt(k) == 47) {
				value[k] = 47; //space
			} else {
				value[k] = 32;
			}
		}
		int sum = 0;
		int i = 0;
		//compress sequential numbers 0-9 to multiple-digit numbers
		for (int j = 0; j < value.length; j++) {
			if (value[j] == 32) {
				bytes[i] = sum;
				i++;
				sum = 0;
			} else if (value[j] == 47) {
				bytes[i] = 0;
			} else {
				sum *= 10;
				sum += value[j];
			}
		}
		//turn into words by dividing by x^nth letter in word and then dividing again by the nth-1 letter
		//adding to return String
		for (int m = 0; m < bytes.length; m++) {
			if (bytes[m] == 0) {
				coeff[m] = 32;
				powerCounter = 1;
			} else {
				int pow = powerCounter-1;
				coeff[m] = (int)(bytes[m]/(Math.pow(x, pow)*powerCounter));
				powerCounter++;
				coeff[m] += 64;
			}
			decryption += (char)coeff[m];
		}
		return decryption;
	}
	
	public static String d_vigen�re(String message, String keyword) {
		decryption = "";
		int[] messageBytes = new int[message.length()];
		int[] keywordBytes = new int[keyword.length()];
		for (int j = 0; j < message.length(); j++) {
			if ((int)message.charAt(j) > 96 && (int)message.charAt(j) < 123) {
				//lower-case
				messageBytes[j] = ((int)message.charAt(j))-97;
			} else if ((int)message.charAt(j) > 64 && (int)message.charAt(j) < 91) {
				//upper-case
				messageBytes[j] = ((int)message.charAt(j))-65;
			} else {
				//space
				messageBytes[j] = 32;
			}
		}
		for (int j = 0; j < keyword.length(); j++) {
			if ((int)keyword.charAt(j) > 96 && (int)keyword.charAt(j) < 123) {
				//lower-case
				keywordBytes[j] = ((int)keyword.charAt(j))-96;
			} else if ((int)keyword.charAt(j) > 64 && (int)keyword.charAt(j) < 91) {
				//upper-case
				keywordBytes[j] = ((int)keyword.charAt(j))-64;
			}
		}
		int i = 0;
		for (int k = 0; k < messageBytes.length; k++) {
			if (messageBytes[k] == 32) {
				decryption += " ";
			} else {
				decryption += (char)((((messageBytes[k]-keywordBytes[i%keywordBytes.length])+26)%26)+65);
				i++;
			}
		}
		return decryption;
	}

}
