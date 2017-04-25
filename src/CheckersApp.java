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
				Integer rowNum = 0;
				if (infoStringFrom[0].equals("a")){
					rowNum = 0;
				} else if (infoStringFrom[0].equals("b")){
					rowNum = 1;
				} else if (infoStringFrom[0].equals("c")){
					rowNum = 2;
				} else if (infoStringFrom[0].equals("d")){
					rowNum = 3;
				} else if (infoStringFrom[0].equals("e")){
					rowNum = 4;
				} else if (infoStringFrom[0].equals("f")){
					rowNum = 5;
				} else if (infoStringFrom[0].equals("g")){
					rowNum = 6;
				} else if (infoStringFrom[0].equals("h")){
					rowNum = 7;
				}

				Coordinates coordFrom = new Coordinates(rowNum, Integer.parseInt(infoStringFrom[1]) - 1); // create coordinate for with the input r,c


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

						Integer rowNumTo = 0;
						if (infoStringTo[0].equals("a")){
							rowNumTo = 0;
						} else if (infoStringTo[0].equals("b")){
							rowNumTo = 1;
						} else if (infoStringTo[0].equals("c")){
							rowNumTo = 2;
						} else if (infoStringTo[0].equals("d")){
							rowNumTo = 3;
						} else if (infoStringTo[0].equals("e")){
							rowNumTo = 4;
						} else if (infoStringTo[0].equals("f")){
							rowNumTo = 5;
						} else if (infoStringTo[0].equals("g")){
							rowNumTo = 6;
						} else if (infoStringTo[0].equals("h")){
							rowNumTo = 7;
						}



						Coordinates coordTo = new Coordinates(rowNumTo, Integer.parseInt(infoStringTo[1])-1);

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

						Integer rowNumTo = 0;
						if (infoStringTo[0].equals("a")){
							rowNumTo = 0;
						} else if (infoStringTo[0].equals("b")){
							rowNumTo = 1;
						} else if (infoStringTo[0].equals("c")){
							rowNumTo = 2;
						} else if (infoStringTo[0].equals("d")){
							rowNumTo = 3;
						} else if (infoStringTo[0].equals("e")){
							rowNumTo = 4;
						} else if (infoStringTo[0].equals("f")){
							rowNumTo = 5;
						} else if (infoStringTo[0].equals("g")){
							rowNumTo = 6;
						} else if (infoStringTo[0].equals("h")){
							rowNumTo = 7;
						}

						Coordinates coordTo = new Coordinates(rowNumTo, Integer.parseInt(infoStringTo[1])-1);

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
