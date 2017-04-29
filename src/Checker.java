import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Checker {
	
	public Player player;
	private boolean king = false;
	public Coordinates loc = new Coordinates(0,0);
	public ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();
	Map<Coordinates, Coordinates> predecessors = new HashMap<Coordinates, Coordinates>();

	public Checker(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	public void setKing() {
		this.king = true;
	}
	
	public boolean isKing() {
		return this.king;
	}

	public float getCheckerWeight() {
		return 1;
	}

}
