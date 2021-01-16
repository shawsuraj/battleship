package battleship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class globalConstants {
	static Scanner scan = new Scanner(System.in);
	
	public static void heading() {
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
}
