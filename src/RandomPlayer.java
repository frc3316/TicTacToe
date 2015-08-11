import java.util.Collections;
import java.util.LinkedList;


public class RandomPlayer extends Player
{
	public RandomPlayer(int alignment) 
	{
		super(alignment);
	}

	@Override
	public void setName() 
	{
		this.name = "Hi I'm Random";
	}

	@Override
	public BoardMove playTurn(Board board) 
	{
		LinkedList<BoardMove> allMoves = generateAllMoves(board, symbol);
		Collections.shuffle(allMoves);
		return allMoves.stream().findAny().get();
	}

	
	private LinkedList <BoardMove> generateAllMoves (Board board, int symbol)
	{
		LinkedList<BoardMove> toReturn = new LinkedList<>();
		
		for (int i = 0; i < board.arr.length; i++)
		{
			for (int j = 0; j < board.arr[0].length; j++)
			{
				if (board.arr[i][j] == 0)
				{
					toReturn.add(new BoardMove(i, j, symbol));
				}
			}
		}
		
		return toReturn;
	}
}
