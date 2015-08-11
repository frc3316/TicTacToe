/**
 * An object holding x and y coordinates of a tile in the board.
 * @author Idan
 *
 */
public class BoardMove
{
	public int x;
	public int y;
	public int symbol;
	
	public BoardMove (int x, int y, int symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public String toString ()
	{
		return x + ", " + y + ", " + symbol;
	}
}
