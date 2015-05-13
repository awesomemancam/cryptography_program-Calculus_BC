package main;

import java.util.Scanner;

public class Encrypter {

	private static String encryption;
	
	public static String encrypt(String message, int cipherType) {
		String codedMessage = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		if (cipherType == 1) {
			// SHIFT CIPHER
			System.out.print("Shift number (no greater than 26)\n  :: ");
			int i = scanner.nextInt();
			codedMessage = e_shiftCipher(message, i);
		} else if (cipherType == 2) {
			// WAGSTEIN CIPHER
			System.out.print("Enter the value of x (1-10)\n  :: ");
			int i = scanner.nextInt();
			codedMessage = e_wagstein(message, i);
		}

		return codedMessage;
	}

	public static String e_shiftCipher(String message, int i) {
		encryption = "";
		int[] bytes = new int[message.length()];
		for (int j = 0; j < message.length(); j++) {
			if ((int)message.charAt(j) > 96 && (int)message.charAt(j) < 123) {
				//lower-case
				bytes[j] = ((((int)message.charAt(j)-97)+i)%26)+65;
			} else if ((int)message.charAt(j) > 64 && (int)message.charAt(j) < 91) {
				//upper-case
				bytes[j] = ((((int)message.charAt(j)-65)+i)%26)+65;
			} else {
				bytes[j] = 32; //if unrecognized char - set to 'space'
			}
		}
		for (int k = 0; k < message.length(); k++) {
			encryption += (char)bytes[k];
		}

		return encryption;
	}
	
	public static String e_wagstein(String message, int x) {
		encryption = "";
		int[] bytes = new int[message.length()];
		int[] coeff = new int[message.length()];
		int powerCounter = 1;
		int pow;
		for (int j = 0; j < message.length(); j++) {
			if ((int)message.charAt(j) > 96 && (int)message.charAt(j) < 123) {
				//lower-case
				coeff[j] = ((int)message.charAt(j))-96;
				pow = powerCounter-1;
				bytes[j] = (int)(powerCounter*coeff[j]*Math.pow(x, pow));
				powerCounter++;
			} else if ((int)message.charAt(j) > 64 && (int)message.charAt(j) < 91) {
				//upper-case
				coeff[j] = ((int)message.charAt(j))-64;
				pow = powerCounter-1;
				bytes[j] = (int)(powerCounter*coeff[j]*Math.pow(x, pow));
				powerCounter++;
			} else {
				bytes[j] = 0; //if unrecognized char - set to ZERO to identify a space
				powerCounter = 1;
			}
		}
		for (int k = 0; k < message.length(); k++) {
			if (bytes[k] == 0) {
				encryption += "/"; //setting as forward slash for now '/'
			} else {
				int num = bytes[k];
				int numDigits = 0;
				while (num > 0) {
					numDigits++;
					num = num/10;
				}
				num = bytes[k];
				while (numDigits > 0) {
					int digit = (int)(num/Math.pow(10, numDigits-1));
					num = num%(int)Math.pow(10, numDigits-1);
					encryption += (char)(digit+65);
					numDigits--;
				}
			}
			encryption += " ";
		}
		return encryption;
	}

}
