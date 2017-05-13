import java.util.ArrayList;

public class Player {
	
	private boolean ai = false;
	private char playerCode;
	private double[] weights = new double[7];
	public ArrayList<Checker> checkers = new ArrayList<Checker>();
	

	public Player(boolean isAI, char playerCode, double[] weights) {
		// TODO Auto-generated constructor stub
		this.ai = isAI;
		this.playerCode = playerCode;
		this.weights = weights;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public boolean isAI() {
		return this.ai;
	}
	
	public char getCode() {
		return this.playerCode;
	}
	
	public double calcHeuristic(Board board) {
		int[] heuristicNums = board.getHeuristicNums(board.grid);
		double sum = 0.0;
		for (int i = 0; i < 7; i++) {
			sum += heuristicNums[i]*this.weights[i];
		}
		return sum;
	}

}
