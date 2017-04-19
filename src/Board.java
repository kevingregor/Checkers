
public class Board {
	private Checker[][] grid = new Checker[8][8];  // Top left is [0][0], TR is [0][7], BL is [7][0], BR is [7,7]
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public void addChecker(Checker piece, int r, int c) {
		if (grid[r][c] == null) {
			grid[r][c] = piece;
			Coordinates location = new Coordinates(r,c);
			piece.loc = location;
		}
	}
	
	public void getJumps(Checker piece, Coordinates loc) {
		if (piece.player.getCode() == CheckersApp.PLAYER1) {
			if (piece.isKing()) {
				
			}
			else {
				if (loc.row > 1) {
					if (loc.col > 1) {
						// Look for left jumps
						if (grid[loc.row - 1][loc.col - 1] != null && grid[loc.row - 2][loc.col - 2] == null
								&& grid[loc.row - 1][loc.col - 1].player.getCode() == CheckersApp.PLAYER2) {
							Coordinates jump = new Coordinates(loc.row - 2, loc.col - 2);
							piece.possibleMoves.add(jump);
							getJumps(piece, jump);
						}
					}
					if (loc.col < 6) {
						// Look for right jumps
						if (grid[loc.row - 1][loc.col + 1] != null && grid[loc.row - 2][loc.col + 2] == null
								&& grid[loc.row - 1][loc.col + 1].player.getCode() == CheckersApp.PLAYER2) {
							Coordinates jump = new Coordinates(loc.row - 2, loc.col + 2);
							piece.possibleMoves.add(jump);
							getJumps(piece, jump);
						}
					}
				}
			}
		}
	}
	
	public void getValidMoves(Checker piece) {
		Coordinates loc = piece.loc;
		if (piece.player.getCode() == CheckersApp.PLAYER1) {
			getJumps(piece, loc);
			if (loc.row > 0) {
				if (loc.col > 0) {
					// Check left move
					if (grid[loc.row - 1][loc.col - 1] == null) {
						piece.possibleMoves.add(new Coordinates(loc.row - 1, loc.col - 1));
					}
				}
				if (loc.col < 7) {
					// Check right move
					if (grid[loc.row - 1][loc.col + 1] == null) {
						piece.possibleMoves.add(new Coordinates(loc.row - 1, loc.col + 1));
					}
				}
			}
			
			
			if (piece.isKing()) {
				// Add more
				if (loc.row < 7) {
					if (loc.col > 0) {
						// Check left move
						if (grid[loc.row + 1][loc.col - 1] == null) {
							piece.possibleMoves.add(new Coordinates(loc.row + 1, loc.col - 1));
						}
					}
					if (loc.col < 7) {
						// Check right move
						if (grid[loc.row + 1][loc.col + 1] == null) {
							piece.possibleMoves.add(new Coordinates(loc.row + 1, loc.col + 1));
						}
					}
				}
			}
		}
		
		System.out.println("Piece at: " + (char) ((char) 'A' + loc.row) + (loc.col + 1));
		for (Coordinates move : piece.possibleMoves) {
			char r = (char) ((char) 'A' + move.row);
			System.out.println("(" + r + ", " + (move.col + 1) + ")");
		}
	}
	
	public void printBoard() {
		// Draw numbers at top
		System.out.print(" ");
		for (int i = 0; i < 8; i++) {
			System.out.print("    " + (i+1) + " ");
		}
		System.out.print("\n  ");
		
		// Draw top line
		for (int i = 0; i < 8; i++) {
			System.out.print(" ");
			System.out.print("_____");
		}
		System.out.print("\n");
		
		// Print actual board
		for (int r = 0; r < 8; r++) {
			for (int i = 0; i < 5; i++) {
				if (i == 2) {
					System.out.print((char)('A' + r) + " |");
				}
				else {
					System.out.print("  |");
				}
				for (int c = 0; c < 8; c++) {
					if (i < 4 && i != 0) {
						if (grid[r][c] == null) {
							System.out.print("     |");
						}
						else {
							char code = grid[r][c].player.getCode();
							if (i == 2 && !grid[r][c].isKing()) {
								System.out.print(" " + code + " " + code + " |");
							}
							else {
								System.out.print(" " + code + code + code + " |");
							}
						}
					}
					else if (i == 0) {
						System.out.print("     |");
					}
					else {
						System.out.print("_____|");
					}
				}
				System.out.print("\n");
			}
		}
	}

}
