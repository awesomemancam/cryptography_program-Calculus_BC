/*
 * Camden Wagner and David Silverstein
 * Cryptography Program
 * BC Calculus - Spring 2015
 */

package main;

import java.io.IOException;
import java.util.Scanner;

public class Cryptography {

	private static Scanner menuScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Dictionary.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Welcome to the Cryptography program!");
		
		menu();
	}
	
	public static void menu() {
		System.out.print("\n\nWould like to encrypt, decrypt, or crack a message?"
				+ "\n  Encrypt (1)\n  Decrypt (2)\n  Crack a message (3)\n  Quit (4)\n  :: ");
		
		int mode = menuScanner.nextInt();

		if (mode == 1) {
			encrypt();
		} else if (mode == 2) {
			decrypt();
		} else if (mode == 3) {
			crack();
		} else if (mode == 4) {
			//terminate program
		}
		if (mode != 4) {
			menu();
		}
	}

	public static void encrypt() {
		String message = askMessage(0);
		int cipherType = askCipherType(0);

		String codedMessage = Encrypter.encrypt(message, cipherType);

		System.out.print("Your encrypted message is\n  :: " + codedMessage + "\n\n-----");
	}

	public static void decrypt() {
		String codedMessage = askMessage(1);
		int cipherType = askCipherType(1);

		String message = Decrypter.decrypt(codedMessage, cipherType);

		System.out.print("Your decrypted message is\n  :: " + message + "\n\n-----");
	}

	public static void crack() {
		String codedMessage = askMessage(2);
		int cipherType = askCipherType(1);
		
		Cracker.crack(codedMessage, cipherType);
	}

	public static int askCipherType(int i) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("How would you like to ");
		if (i == 0) {
			System.out.print("encrypt");
		} else if (i == 2) {
			System.out.print("crack");
		} else {
			System.out.print("decrypt");
		}
		System.out.print(" it?\n  Shift Cipher (1)\n  Wagstein Cipher (2)\n  Vigenère Cipher (3)\n  :: ");
		return scanner.nextInt();
	}

	public static String askMessage(int i) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("What message would you like to ");
		if (i == 0) {
			System.out.print("encrypt");
		} else if (i == 2) {
			System.out.print("crack");
		} else {
			System.out.print("decrypt");
		}
		System.out.print("? (all non-English letters will be defaulted to spaces)\n  :: ");
		return scanner.nextLine();
	}

}
