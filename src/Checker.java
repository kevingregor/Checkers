import java.util.ArrayList;

public class Checker {
	
	public Player player;
	private boolean king = false;
	public Coordinates loc = new Coordinates(0,0);
	public ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

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

}
