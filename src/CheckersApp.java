import com.sun.tools.javac.comp.Check;

import java.util.Scanner;

public class CheckersApp {

	public static char PLAYER1 = 'X';
	public static char PLAYER2 = 'O';
	
	private static Board board;
	private static Player player1;
	private static Player player2;
	private static Player currPlayer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new Board();
		player1 = new Player(false, PLAYER1, 1);
		player2 = new Player(false, PLAYER2, 1);
		currPlayer = player1;
		initBoard(board);
		
		board.printBoard();

//		for (Checker checker : player2.checkers) {
//			board.getValidMoves(checker);
//		}

		//board.moveChecker(new Coordinates(5,0), new Coordinates(4, 1));
		//board.printBoard();

		playGame();


	}
	
	public static void initBoard(Board board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				Checker toAdd = new Checker(player1);
				board.addChecker(toAdd, 7-i, 2*j + (i % 2));
				player1.checkers.add(toAdd);
				
				Checker toAdd2 = new Checker(player2);
				board.addChecker(toAdd2, i, 2*j + ((7-i) % 2));
				player2.checkers.add(toAdd2);
			}
		}
	}


	public static void playGame() {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			if (currPlayer == player1) {
				System.out.println("Player1's Turn\n");
			} else {
				System.out.println("Player2's Turn\n");
			}


			System.out.println("(r, c) to move from: ");

			String lineFrom = scanner.nextLine();
			String[] infoStringFrom = lineFrom.split("\\s+");

			if (infoStringFrom.length == 2) {


				boolean found = false;    //variable representing if the coordinates input is found/valid

				Integer[] infoInt = new Integer[2];

				Coordinates coordFrom = new Coordinates(Integer.parseInt(infoStringFrom[0]), Integer.parseInt(infoStringFrom[1])); // create coordinate for with the input r,c


				if (currPlayer == player1) {
					int indexOfChecker = 0;
					int i = 0;
					for (Checker checker : player1.checkers) {
						if (checker.loc.row == coordFrom.row && checker.loc.col == coordFrom.col) {
							found = true;
							indexOfChecker = i;
						}
						i += 1;
					}
					System.out.println(found);
					if (found) {
						System.out.println("Valid moves: ");
						Checker pieceToMove = player1.checkers.get(indexOfChecker);
						board.getValidMoves(pieceToMove);

						System.out.println("(r, c) to move to: ");

						String lineTo = scanner.nextLine();
						String[] infoStringTo = lineTo.split("\\s+");

						Coordinates coordTo = new Coordinates(Integer.parseInt(infoStringTo[0]), Integer.parseInt(infoStringTo[1]));

						boolean toFound = false;
						for (Coordinates move : pieceToMove.possibleMoves) {
							if (move.row == coordTo.row && move.col == coordTo.col) {
								toFound = true;
								board.moveChecker(coordFrom, coordTo);
								board.printBoard();
							}
						}


					}
					currPlayer = player2;
				} else if (currPlayer == player2) {
					int indexOfChecker = 0;
					int i = 0;
					for (Checker checker : player2.checkers) {
						if (checker.loc.row == coordFrom.row && checker.loc.col == coordFrom.col) {
							found = true;
							indexOfChecker = i;
						}
						i += 1;
					}
					System.out.println(found);
					if (found) {
						System.out.println("Valid moves: ");
						Checker pieceToMove = player2.checkers.get(indexOfChecker);
						board.getValidMoves(pieceToMove);

						System.out.println("(r, c) to move to: ");

						String lineTo = scanner.nextLine();
						String[] infoStringTo = lineTo.split("\\s+");

						Coordinates coordTo = new Coordinates(Integer.parseInt(infoStringTo[0]), Integer.parseInt(infoStringTo[1]));

						boolean toFound = false;
						for (Coordinates move : pieceToMove.possibleMoves) {
							if (move.row == coordTo.row && move.col == coordTo.col) {
								toFound = true;
								board.moveChecker(coordFrom, coordTo);
								board.printBoard();
							}
						}


					}
					currPlayer = player1;
				}
			}
			else{
				System.out.println("Quit");
				return;
			}

		}
	}

}
