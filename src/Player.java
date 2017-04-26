import java.util.ArrayList;

public class Player {
	
	private boolean ai = false;
	private char playerCode; 
	private double kingWeight;
	public ArrayList<Checker> checkers = new ArrayList<Checker>();
	

	public Player(boolean isAI, char playerCode, double kingWeight) {
		// TODO Auto-generated constructor stub
		this.ai = isAI;
		this.playerCode = playerCode;
		this.kingWeight = kingWeight;
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

}
