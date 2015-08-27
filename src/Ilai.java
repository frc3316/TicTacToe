
public class Ilai extends Player {

	public Ilai(int alignment) {
		super(alignment);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void setName() {
		
		this.name = "Ilai";
	}

	@Override
	public BoardMove playTurn(Board board) {
	
		for (int i = 0; i < board.arr.length; i++)
		{	
			//check if can win/lose on a line
			if (	board.arr[i][0] ==  board.arr[i][1] && 
					board.arr[i][0] != EMPTY &&
					board.arr[i][2] == EMPTY)
			{
				return new BoardMove(i,2, this.symbol);
			}
			else if (board.arr[i][0] ==  board.arr[i][2] && 
					board.arr[i][0] != EMPTY &&
					board.arr[i][1] == EMPTY)
			{
				return new BoardMove(i,1, this.symbol);
			}
			
			else if (board.arr[i][1] ==  board.arr[i][2] && 
					board.arr[i][1] != EMPTY &&
					board.arr[i][0] == EMPTY)
			{
				return new BoardMove(i,0, this.symbol);
			}
			
			
			//check if can win/lose on a column
			else if (board.arr[0][i] ==  board.arr[1][i] && 
					board.arr[0][i] != EMPTY &&
					board.arr[2][i] == EMPTY)
			{
				return new BoardMove(2,i, this.symbol);
			}
			
			else if (board.arr[0][i] ==  board.arr[2][i] && 
					board.arr[0][i] != EMPTY &&
					board.arr[1][i] == EMPTY)
			{
				return new BoardMove(1,i, this.symbol);
			}
			
			else if (board.arr[1][i] ==  board.arr[2][i] && 
					board.arr[1][i] != EMPTY &&
					board.arr[0][i] == EMPTY)
			{
				return new BoardMove(0,i, this.symbol);
			}
			
	}
	
		//check if can win/lose on a diagonal(1)
		if (board.arr[0][0] == board.arr[1][1] && 
				board.arr[0][0] != EMPTY &&
				board.arr[2][2] == EMPTY)
		{
			return new BoardMove(2,2, this.symbol);
		}
		
		if (board.arr[0][0] == board.arr[2][2] && 
				board.arr[0][0] != EMPTY &&
				board.arr[1][1] == EMPTY)
		{
			return new BoardMove(1,1, this.symbol);
		}
		
		if (board.arr[2][2] == board.arr[1][1] && 
				board.arr[1][1] != EMPTY &&
				board.arr[0][0] == EMPTY)
		{
			return new BoardMove(0,0, this.symbol);
		}
		
		
		//check if can win/lose on a diagonal(2)
		if (board.arr[2][0] == board.arr[1][1] && 
				board.arr[0][2] == EMPTY &&
				board.arr[2][0] != EMPTY)
		{
			return new BoardMove(0,2, this.symbol);
		}
		
		else if (board.arr[2][0] == board.arr[0][2] && 
				board.arr[1][1] == EMPTY &&
				board.arr[2][0] != EMPTY)
		{
			return new BoardMove(1,1, this.symbol);
		}
		
		else if (board.arr[1][1] == board.arr[0][2] && 
				board.arr[2][0] == EMPTY &&
				board.arr[1][1] != EMPTY)
		{
			return new BoardMove(2,0, this.symbol);
		}
		
		
		//the open of the game.
		else if (board.arr[1][1] == EMPTY)
		{
			return new BoardMove(1,1, this.symbol);
		}
		
		//corners
		else if (board.arr[1][1] != EMPTY &&
				board.arr[0][2] == EMPTY)
		{
			return new BoardMove(0,2, this.symbol);
		}
		
		else if (board.arr[0][2] != EMPTY &&
				board.arr[2][2] == EMPTY)
		{
			return new BoardMove(2,2, this.symbol);
		}
		
		else if (board.arr[2][2] != EMPTY &&
				board.arr[0][0] == EMPTY)
		{
			return new BoardMove(0,0, this.symbol);
		}
		
		else 
		{
			return new BoardMove(2,0, this.symbol);
		}
	}
}