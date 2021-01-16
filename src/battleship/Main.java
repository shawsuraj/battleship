package battleship;

public class Main extends globalConstants{
	public static void menu() {
		String menu_options = "1) New Game\n"
							+ "2) Continue\n" 
							+ "3) Options\n"
							+ "4) Help\n"
							+ "5) About Creater\n";
		System.out.println(menu_options);
		System.out.printf("Select an option : ");
	}
	
	public static void main(String[] args) {
		
		heading();
		menu();
		
		int option = scan.nextInt();
		
		if (option == 1) {
			Game game = new Game();
			game.start();
			while (true) {
				game.play();
				break;
			}
			String toCont;
			System.out.println("Do you want to continue :\n");
			toCont = scan.nextLine();
			
			if (toCont == "Y") {
				System.out.println("Do you want to continue :");
			} else {
				scan.close();
				System.out.println("Exit");
				//				game.exit();
			}
			
//			scan.close();
		}
		
		
//		cBoard.print();
		
		
		
	}
	  

}