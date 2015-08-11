/**
 * Subclasses of this class can be players in a tic-tac-toe game.
 * @author Idan
 *
 */
public abstract class Player implements Symbol
{
	String name = ""; //the name of the player
	public final int symbol; //what symbol is this player playing - 1 means X, 2 means O
	
	public Player (int alignment)
	{
		this.symbol = alignment;
	}
	
	/**
	 * Method that is called by the engine at the start of the game to set the name of the player.
	 */
	public abstract void setName();
	
	/**
	 * Method that is called each turn by the game engine
	 * @param board The current board state. This board is a copy of the current board, so it can be changed without harming the game.
	 * @return The position where the player wants to play. A player can not play in a position that is already filled (i.e. not 0). 
	 */
	public abstract BoardMove playTurn (Board board);
	
	/**
	 * Returns the string representation of this player.
	 */
	public String toString ()
	{	
		String s = "";
		if (symbol == X) s = "X";
		else if (symbol == O) s = "O";
		
		return name + " playing as " + s;
	}
}
