/**
 * Class representing a tic-tac-toe board of 3x3
 * each square holds a number - 0 means empty, 1 means X, 2 means O
 * @author Idan
 *
 */
public class Board implements Symbol
{
	public int [][] arr;
	
	/**
	 * Constructor of an empty board.
	 */
	public Board ()
	{
		arr = new int [3][3];
		
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				arr[i][j] = EMPTY;
			}
		}
	}
	
	/**
	 * Copy constructor - creates a new board that it identical to other.
	 * @param other the board to copy.
	 */
	public Board (Board other)
	{
		arr = new int [3][3];
		
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				arr[i][j] = other.arr[i][j];
			}
		}
	}
	
	public void insert (BoardMove bm)
	{
		arr[bm.x][bm.y] = bm.symbol;
	}
	
	/**
	 * @return Whether the board is full (has no empty spaces). A player calling this method will always receive false.
	 */
	public boolean isFull ()
	{
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				if (arr[i][j] == EMPTY)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * @return The symbol number of the winner. If there is currently no winner, this method will return EMPTY. A player calling this method will always receive false.
	 */
	public int winner ()
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (	arr[i][0] ==  arr[i][1] && 
					arr[i][1] == arr[i][2] && 
					arr[i][0] != EMPTY)
			{
				return arr[i][0];
			}
		}
		
		for (int i = 0; i < arr.length; i++)
		{
			if (	arr[0][i] ==  arr[1][i] && 
					arr[1][i] == arr[2][i] &&
					arr[0][i] != EMPTY)
			{
				return arr[0][i];
			}
		}
		
		if (	arr[0][0] == arr[1][1] && 
				arr[1][1] == arr[2][2] &&
				arr[0][0] != EMPTY)
		{
			return arr[0][0];
		}
		
		if (	arr[2][0] == arr[1][1] && 
				arr[1][1] == arr[0][2] &&
				arr[2][0] != EMPTY)
		{
			return arr[0][0];
		}
		
		return EMPTY;
	}
	
	/**
	 * Return the string representation of this board.
	 */
	public String toString ()
	{
		String toReturn = "";
		toReturn += "Board containing:\n";
		
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[0].length; j++)
			{
				if (arr[i][j] == EMPTY)	toReturn += "EMPTY";
				if (arr[i][j] == X)	toReturn += "  X  ";
				if (arr[i][j] == O)	toReturn += "  O  ";
				
				if (j != (arr[0].length - 1))
				{
					toReturn += "\t";
				}
			}
			
			if (i != (arr.length - 1))
			{
				toReturn += "\n";
			}
		}
		
		return toReturn;
	}
}