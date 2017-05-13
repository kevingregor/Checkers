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
		
		// Weights: pawns, kings, back row, mid box, mid rows, vulnerable, protected
		double[] player1Weights = {1.0, 1, 1, 1, 1, -1, 1};
		double[] player2Weights = {1.0, 1, 1, 1, 1, -1, 1};
		player1 = new Player(false, PLAYER1, player1Weights);
		player2 = new Player(false, PLAYER2, player2Weights);
		currPlayer = player1;
		initBoard(board);
		
		board.printBoard();

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

		while (!board.isFinished()) {

//			System.out.println("Heuristic Val of Board = " + board.getHeuristicVal());
			double heur = currPlayer.calcHeuristic(board);
			System.out.println("Heuristc value: " + heur);

			if (currPlayer == player1) {
				System.out.println("Player1's Turn\n");
			} else {
				System.out.println("Player2's Turn\n");
			}


			System.out.println("(r, c) to move from: ");

			String lineFrom = scanner.nextLine();
			String[] infoStringFrom = lineFrom.split("\\s+");
			if (infoStringFrom[0].length() == 2) {
				String str = infoStringFrom[0];
				infoStringFrom = new String[2];
				infoStringFrom[0] = str.substring(0, 1);
				infoStringFrom[1] = str.substring(1);
			}

			if (infoStringFrom.length == 2) {

				boolean found = false;    //variable representing if the coordinates input is found/valid

				Integer[] infoInt = new Integer[2];
				
				char rowChar = infoStringFrom[0].charAt(0);
				int rowNum = Character.toUpperCase(rowChar) - 'A';

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
//					System.out.println(found);
					if (found) {
						System.out.println("Valid moves: ");
						Checker pieceToMove = player1.checkers.get(indexOfChecker);
						board.getValidMoves(pieceToMove);
						
						Coordinates loc = pieceToMove.loc;
						System.out.println("\nHeuristic values: ");
						System.out.println("Piece at: " + (char) ((char) 'A' + loc.row) + (loc.col + 1));
						for (Coordinates moves : pieceToMove.possibleMoves) {
							double heuristic = 0;
							char r = (char) ((char) 'A' + moves.row);
							System.out.println("(" + r + ", " + (moves.col + 1) + "): " + heuristic);
						}

						System.out.println("(r, c) to move to: ");

						String lineTo = scanner.nextLine();
						String[] infoStringTo = lineTo.split("\\s+");
						if (infoStringTo[0].length() == 2) {
							String str = infoStringTo[0];
							infoStringTo = new String[2];
							infoStringTo[0] = str.substring(0, 1);
							infoStringTo[1] = str.substring(1);
						}

						char rowCharTo = infoStringTo[0].charAt(0);
						int rowNumTo = Character.toUpperCase(rowCharTo) - 'A';

						Coordinates coordTo = new Coordinates(rowNumTo, Integer.parseInt(infoStringTo[1])-1);

						boolean toFound = false;
						for (Coordinates move : pieceToMove.possibleMoves) {
							if (move.row == coordTo.row && move.col == coordTo.col) {
								toFound = true;
								board.moveChecker(coordFrom, coordTo, false);
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
						
						Coordinates loc = pieceToMove.loc;
						System.out.println("\n Heuristic values: ");
						System.out.println("Piece at: " + (char) ((char) 'A' + loc.row) + (loc.col + 1));
						for (Coordinates moves : pieceToMove.possibleMoves) {
							double heuristic = 0;
							char r = (char) ((char) 'A' + moves.row);
							System.out.println("(" + r + ", " + (moves.col + 1) + "): " + heuristic);
						}

						System.out.println("(r, c) to move to: ");

						String lineTo = scanner.nextLine();
						String[] infoStringTo = lineTo.split("\\s+");
						if (infoStringTo[0].length() == 2) {
							String str = infoStringTo[0];
							infoStringTo = new String[2];
							infoStringTo[0] = str.substring(0, 1);
							infoStringTo[1] = str.substring(1);
						}

						char rowCharTo = infoStringTo[0].charAt(0);
						int rowNumTo = Character.toUpperCase(rowCharTo) - 'A';

						Coordinates coordTo = new Coordinates(rowNumTo, Integer.parseInt(infoStringTo[1])-1);

						boolean toFound = false;
						for (Coordinates move : pieceToMove.possibleMoves) {
							if (move.row == coordTo.row && move.col == coordTo.col) {
								toFound = true;
								board.moveChecker(coordFrom, coordTo, false);
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
		
		Player winner = board.getWinner();
		if (winner.equals(player1)) {
			System.out.println("Player 1 wins");
		}
		else {
			System.out.println("Player 2 wins");
		}
	}

}
