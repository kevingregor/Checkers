import sun.jvm.hotspot.code.ConstantOopReadValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	public Checker[][] grid = new Checker[8][8];  // Top left is [0][0], TR is [0][7], BL is [7][0], BR is [7,7]
	private boolean finished = false;
	private Player winner = new Player();
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void addChecker(Checker piece, int r, int c) {
		if (grid[r][c] == null) {
			grid[r][c] = piece;
			Coordinates location = new Coordinates(r,c);
			piece.loc = location;
		}
	}
	
	public void getJumps(Checker piece, Coordinates loc, int[][] jumpGrid) {
		char code = piece.player.getCode();
		if ((piece.isKing() && piece.player.getCode() == CheckersApp.PLAYER1)
				|| code == CheckersApp.PLAYER2) {
			if (loc.row < 6) {
				if (loc.col > 1) {
					// Look for left jumps
					if (grid[loc.row + 1][loc.col - 1] != null && grid[loc.row + 2][loc.col - 2] == null
							&& grid[loc.row + 1][loc.col - 1].player.getCode() != code
							&& jumpGrid[loc.row + 1][loc.col - 1] != 1) {
						Coordinates jump = new Coordinates(loc.row + 2, loc.col - 2);
						piece.possibleMoves.add(jump);
						piece.predecessors.put(jump, loc);
						
						int[][] newGrid = jumpGrid.clone();
						newGrid[loc.row + 1][loc.col - 1] = 1;
						getJumps(piece, jump, newGrid);
					}
				}
				if (loc.col < 6) {
//						// Look for right jumps
					if (grid[loc.row + 1][loc.col + 1] != null && grid[loc.row + 2][loc.col + 2] == null
							&& grid[loc.row + 1][loc.col + 1].player.getCode() != code
							&& jumpGrid[loc.row + 1][loc.col + 1] != 1) {
						Coordinates jump = new Coordinates(loc.row + 2, loc.col + 2);
						piece.possibleMoves.add(jump);
						piece.predecessors.put(jump, loc);
						
						int[][] newGrid = jumpGrid.clone();
						newGrid[loc.row + 1][loc.col + 1] = 1;
						getJumps(piece, jump, newGrid);
					}
				}
			}
		}
		
		if (((piece.isKing() && piece.player.getCode() == CheckersApp.PLAYER2)
				|| code == CheckersApp.PLAYER1)) {
			if (loc.row > 1) {
				if (loc.col > 1) {
					// Look for left jumps
					if (grid[loc.row - 1][loc.col - 1] != null && grid[loc.row - 2][loc.col - 2] == null
							&& grid[loc.row - 1][loc.col - 1].player.getCode() != code
							&& jumpGrid[loc.row - 1][loc.col - 1] != 1) {
						Coordinates jump = new Coordinates(loc.row - 2, loc.col - 2);
						piece.possibleMoves.add(jump);
						piece.predecessors.put(jump, loc);
						
						int[][] newGrid = jumpGrid.clone();
						newGrid[loc.row - 1][loc.col - 1] = 1;
						getJumps(piece, jump, newGrid);
					}
				}
				if (loc.col < 6) {
					// Look for right jumps
					if (grid[loc.row - 1][loc.col + 1] != null && grid[loc.row - 2][loc.col + 2] == null
							&& grid[loc.row - 1][loc.col + 1].player.getCode() != code
							&& jumpGrid[loc.row - 1][loc.col + 1] != 1) {
						Coordinates jump = new Coordinates(loc.row - 2, loc.col + 2);
						piece.possibleMoves.add(jump);
						piece.predecessors.put(jump, loc);
						
						int[][] newGrid = jumpGrid.clone();
						newGrid[loc.row - 1][loc.col + 1] = 1;
						getJumps(piece, jump, newGrid);
					}
				}
			}
		}
	}
	
	
	public void getValidMoves(Checker piece) {
		piece.possibleMoves = new ArrayList<Coordinates>();
		piece.predecessors = new HashMap<Coordinates, Coordinates>();
		Coordinates loc = piece.loc;
		if ((piece.isKing() && piece.player.getCode() == CheckersApp.PLAYER2)
				|| piece.player.getCode() == CheckersApp.PLAYER1) {
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
		}
			
		if ((piece.isKing() && piece.player.getCode() == CheckersApp.PLAYER1)
				|| piece.player.getCode() == CheckersApp.PLAYER2) {
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
			
		getJumps(piece, loc, new int[8][8]);
		
		// Print moves
//		System.out.println("Piece at: " + (char) ((char) 'A' + loc.row) + (loc.col + 1));
//		for (Coordinates move : piece.possibleMoves) {
//			char r = (char) ((char) 'A' + move.row);
//			System.out.println("(" + r + ", " + (move.col + 1) + ")");
//		}
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

	public void removeCheckers(Checker toMove, Coordinates from, Coordinates to, boolean lookAhead) {
		Player opponent = new Player();
		Coordinates pred = toMove.predecessors.containsKey(to) ? toMove.predecessors.get(to) : null;
		while (pred != null) {
			Checker toRemove = grid[(pred.row + to.row)/2][(pred.col + to.col)/2];
			opponent = toRemove.player;
			if (!lookAhead) {
				opponent.checkers.remove(toRemove);
			}
			grid[(pred.row + to.row)/2][(pred.col + to.col)/2] = null;
			
			if (pred.equals(from)) break;
			
			to = pred;
			pred = toMove.predecessors.get(pred);
		}
		
		if (!lookAhead && opponent.checkers.size() == 0 && pred != null) {
			// End Game
			finished = true;
			winner = toMove.player;
		}
	}
	

	public void moveChecker(Coordinates from, Coordinates to, boolean lookAhead){
		Checker toMove = grid[from.row][from.col];
		toMove.loc = to;
		
		// Find path
		removeCheckers(toMove, from, to, lookAhead);
		
		toMove.possibleMoves = new ArrayList<Coordinates>();
		toMove.predecessors = new HashMap<Coordinates, Coordinates>();
		grid[from.row][from.col] = null;
		grid[to.row][to.col] = toMove;
		if (to.row == 0 || to.row == 7){
			toMove.setKing();
		}
	}


	public int[] getHeuristicNums(Checker[][] grd){

		// Index 0: Number of pawns
		// Index 1: Number of kings
		// Index 2: Number in back row
		// Index 3: Number in middle box
		// Index 4: Number in middle 2 rows, not box
		// Index 5: Number that can be taken this turn
		// Index 6: Number that are protected
		int[] p1Nums = new int[7];
		int[] p2Nums = new int[7];
		


		for (Checker[] chkrs : grd) {
			for (Checker chkr : chkrs) {
				if (chkr != null) {
					if (chkr.player.getCode() == CheckersApp.PLAYER1) {
						// Check for pawns
						if (!chkr.isKing()) {
							p1Nums[0]++;
						}
						
						// Check for king
						else {
							p1Nums[1]++;
						}
						
						
						int r = chkr.loc.row;
						int c = chkr.loc.col;
						// Check for back row
						if (r == 7) {
							p1Nums[2]++;
							p1Nums[6]++;
						}
						else {
							// Check for middle rows
							if (r == 3 || r == 4) {
								// Mid box
								if (c >= 2 && c <= 5) {
									p1Nums[3]++;
								}
								// Non-box
								else {
									p1Nums[4]++;
								}
							}
							
							// Check if can be taken this turn
							if (r > 0) {
								if (c > 0 && c < 7) {
									if (grd[r - 1][c - 1] != null && 
											grd[r-1][c-1].player.getCode() == CheckersApp.PLAYER2
											&& grd[r + 1][c + 1] == null) {
										p1Nums[5]++;
									}
									if (grd[r - 1][c + 1] != null && 
											grd[r-1][c+1].player.getCode() == CheckersApp.PLAYER2
											&& grd[r + 1][c - 1] == null) {
										p1Nums[5]++;
									}
								}
							}
							
							// Check for protected checkers
							if (r < 7) {
								if (c == 0 || c == 7) {
									p1Nums[6]++;
								}
								else {
									if ((grd[r + 1][c - 1] != null &&
											(grd[r+1][c-1].player.getCode() == CheckersApp.PLAYER1 ||
											 !grd[r+1][c-1].isKing())) &&
										(grd[r + 1][c + 1] != null &&
												(grd[r+1][c+1].player.getCode() == CheckersApp.PLAYER1 ||
												 !grd[r+1][c+1].isKing()))) {
										p1Nums[6]++;
									}
								}
							}
						}
					}
					if (chkr.player.getCode() == CheckersApp.PLAYER2) {
						// Check for pawns
						if (!chkr.isKing()) {
							p2Nums[0]++;
						}
						
						// Check for king
						else {
							p2Nums[1]++;
						}
						
						
						int r = chkr.loc.row;
						int c = chkr.loc.col;
						// Check for back row
						if (r == 0) {
							p2Nums[2]++;
							p2Nums[6]++;
						}
						else {
							// Check for middle rows
							if (r == 3 || r == 4) {
								// Mid box
								if (c >= 2 && c <= 5) {
									p2Nums[3]++;
								}
								// Non-box
								else {
									p2Nums[4]++;
								}
							}
							
							// Check if can be taken this turn
							if (r < 7) {
								if (c > 0 && c < 7) {
									if (grd[r + 1][c - 1] != null && 
											grd[r+1][c-1].player.getCode() == CheckersApp.PLAYER1
											&& grd[r - 1][c + 1] == null) {
										p2Nums[5]++;
									}
									if (grd[r + 1][c + 1] != null && 
											grd[r+1][c+1].player.getCode() == CheckersApp.PLAYER1
											&& grd[r - 1][c - 1] == null) {
										p2Nums[5]++;
									}
								}
							}
							
							// Check for protected checkers
							if (r > 0) {
								if (c == 0 || c == 7) {
									p2Nums[6]++;
								}
								else {
									if ((grd[r - 1][c - 1] != null &&
											(grd[r-1][c-1].player.getCode() == CheckersApp.PLAYER2 ||
											 !grd[r-1][c-1].isKing())) &&
										(grd[r - 1][c + 1] != null &&
												(grd[r-1][c+1].player.getCode() == CheckersApp.PLAYER2 ||
												 !grd[r-1][c+1].isKing()))) {
										p2Nums[6]++;
									}
								}
							}
						}
					}
				}
			}
		}
		
//		System.out.println("Number of pawns:" + p2Nums[0]);
//		System.out.println("Number of kings:" + p2Nums[1]);
//		System.out.println("Number in back row:" + p2Nums[2]);
//		System.out.println("Number in mid box:" + p2Nums[3]);
//		System.out.println("Number in mid row non-box:" + p2Nums[4]);
//		System.out.println("Number can be taken immediately:" + p2Nums[5]);
//		System.out.println("Number of protected:" + p2Nums[6]);
		
		for (int i = 0; i < p1Nums.length; i++) {
//			System.out.println(p1Nums[i] + " " + p2Nums[i] + "\n");
			p1Nums[i] = p1Nums[i] - p2Nums[i];
		}
		return p1Nums;

	}
	
	
	public Checker[][] copyGrid() {
		Checker[][] temp = new Checker[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
			    Checker piece = grid[r][c];
			    if (piece != null) {
			        temp[r][c] = new Checker(piece);
			        if (piece.player.checkers.contains(piece)) {
			        	piece.player.checkers.set(piece.player.checkers.indexOf(piece), temp[r][c]);
			        }
			    }
			    else {
			    	temp[r][c] = null;
			    }
			}
		}
		return temp;
	}
	
	public void manuallySetWinner(Player player) {
		finished = true;
		winner = player;
	}


}
