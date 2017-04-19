
public class CheckersApp {

	public static char PLAYER1 = 'X';
	public static char PLAYER2 = 'O';
	
	private static Board board;
	private static Player player1;
	private static Player player2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		board = new Board();
		player1 = new Player(false, PLAYER1, 1);
		player2 = new Player(false, PLAYER2, 1);
		initBoard(board);
		
		board.printBoard();
		
		for (Checker checker : player2.checkers) {
			board.getValidMoves(checker);
		}
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

}
