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
	
	public Checker(Checker piece) {
		this.player = piece.player;
		this.king = piece.king;
		this.loc = piece.loc;
		
		// Copy possible moves
		ArrayList<Coordinates> possMoves = new ArrayList<Coordinates>();
		for (Coordinates coord : piece.possibleMoves) {
			Coordinates newCoord = new Coordinates(coord.row, coord.col);
			possMoves.add(newCoord);
		}
		this.possibleMoves = possMoves;
		
		// Copy predecessors
		Map<Coordinates, Coordinates> pred = new HashMap<Coordinates, Coordinates>();
		for (Coordinates key : piece.predecessors.keySet()) {
			Coordinates newFrom = new Coordinates(key.row, key.col);
			Coordinates newTo = new Coordinates(piece.predecessors.get(key).row, piece.predecessors.get(key).col);
			pred.put(newFrom, newTo);
		}
		this.predecessors = pred;
		
	}
	
	public void setKing() {
		this.king = true;
	}
	
	public boolean isKing() {
		return this.king;
	}
}
