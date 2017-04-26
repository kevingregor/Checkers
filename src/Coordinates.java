import java.util.Objects;

public class Coordinates {
	public final int row;
	public final int col;

	public Coordinates(int r, int c) {
		// TODO Auto-generated constructor stub
		this.row = r;
		this.col = c;
	}
	
	@Override
    public boolean equals(Object obj) {
 
        // If the object is compared with itself then return true  
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Coordinates)) {
            return false;
        }
         
        Coordinates c = (Coordinates) obj;
       
        return (c.col == col && c.row == row);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

}
