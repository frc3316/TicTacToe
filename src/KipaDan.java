
public class KipaDan extends Player
{
	
	public KipaDan(int alignment) 
	{
		super(alignment);
		if (symbol == X)
		{
			enemySymbol = O;
		}
		 else 
		{
			enemySymbol = X;
		}
	}
	int enemySymbol ;
	
	public void setName() 
	{
		this.name = "DANNNNNNN";
	}

	public BoardMove playTurn(Board board) 
	{	
		if (board.arr[1][1]==EMPTY)
		{
			return new BoardMove(1, 1, symbol);
		}
		else if (board.arr[1][1] == enemySymbol)
		{
				
		}
		else if (board.arr[0][0] == enemySymbol)
		{
			if (board.arr[2][0] == enemySymbol)
			{
				return new BoardMove(1, 0, symbol);
			}
			if(board.arr[0][2] == enemySymbol){
				return new BoardMove(0, 1, symbol);
			}
		}
		else if (board.arr[0][0]== enemySymbol && board.arr[0][1]==EMPTY)
		{
			return new BoardMove(0 ,1 ,symbol);
		}
		
		else if (board.arr[0][1]== enemySymbol && board.arr[0][2]==EMPTY)
		{
			return new BoardMove(0 ,2 ,symbol);
		}
		
		else if (board.arr[0][2]== enemySymbol && board.arr[1][2]==EMPTY)
		{
			return new BoardMove(1 ,2 ,symbol );
		}
		
		else if (board.arr[1][2]== enemySymbol && board.arr[2][2]==EMPTY)
		{
			return new BoardMove(2 ,2 ,symbol);
		}
		
		else if (board.arr[2][2]== enemySymbol && board.arr[2][1]==EMPTY)
		{
			return new BoardMove(2 ,1 ,symbol);
		}

		else if (board.arr[2][1]== enemySymbol && board.arr[2][0]==EMPTY)
		{
			return new BoardMove(2 ,0 ,symbol);
		}

		else if (board.arr[2][0]== enemySymbol && board.arr[1][0]==EMPTY)
		{
			return new BoardMove(1 ,0 ,symbol);
		}
		
		else if (board.arr[1][0]== enemySymbol && board.arr[0][0]==EMPTY)
		{
			return new BoardMove(0 ,0 ,symbol);
		}


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
