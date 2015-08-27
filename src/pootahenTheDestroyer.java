
public class pootahenTheDestroyer extends Player
{
	public pootahenTheDestroyer(int alignment)
	{
		super(alignment);
	}

	public void setName() 
	{
		this.name = "poot poot";
	}

	public BoardMove playTurn(Board board) 
	{	
		for (int i = 0; i < board.arr.length; i++)
		{
			for (int j = 0; j < board.arr.length; j++)
			{
				if (board.arr[i][j] == EMPTY){
					return new BoardMove(i,j,symbol);
				}
			}
		}
		return null;
	}

}
