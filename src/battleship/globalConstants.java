package battleship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// This class contains all the global constants
public class globalConstants {
	static Scanner scan = new Scanner(System.in);
	public static void clrscreen(){
		// Clear screen
		try {
			final String os = System.getProperty("os.name");
	        if (os.contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// The main heading of the game
	public static void heading() {
		try (BufferedReader br = new BufferedReader(new FileReader("heading.txt"))) {
			   String line;
			   while ((line = br.readLine()) != null) {
			       System.out.println(line);
			   }
			} catch (FileNotFoundException e) {
				System.out.println("Error : No heading file found.");
				// e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error : I/O options inturrupted.");
				// e.printStackTrace();
			}
	}

	// Slow print the string
	static void slowPrint(String message, int delay) {
        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            try {
                Thread.sleep(delay);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
	
	// Integer input with error handling (try/catch)
	public static int inputInt() {
		int x;
		while (true) {
			try {
				x = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				x = 0;
				System.out.print("Please Enter a valid integer : ");
			}	
		}
		return x;
	}
	
	// Integer input with error handling (regex)
	public static String inputOnlyString() {
		String x;
		String pattern =  "[a-zA-Z]+";
		while (true) {
			x = scan.nextLine();
			if (x.matches(pattern)) {
				return x;
			} else {
				System.out.print("Invalid Input. Enter a valid string : ");
			}
		}
	}
}
