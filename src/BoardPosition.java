/**
 * An object holding x and y coordinates of a tile in the board.
 * @author Idan
 *
 */
public class BoardPosition
{
	public int x;
	public int y;
	public int symbol;
	
	public BoardPosition (int x, int y, int symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
}
