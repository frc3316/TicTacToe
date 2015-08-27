import java.util.HashSet;


public class Idan extends Player
{
	public Idan(int alignment)
	{
		super(alignment);
	}
	
	public void setName()
	{
		this.name = "Idan";
	}
	
	public BoardMove playTurn(Board board)
	{
		System.out.println(this + " starting a turn!");
		
		/*
		 * Normally with this code, Idan is forced to place his first move at the bottom right corner
		 * 
		 * Without it, he calculates and finds that all moves are equal (meaning that against a perfect 
		 * player they will all lead to a tie), so he chooses the right edge 
		 * 
		 * Found out that Ofir can't handle this unusual starting move, so this code is currently disabled
		 */
		
		
		/*
		boolean firstTurn = true;
		
		for (int i = 0; i < board.arr.length; i++)
		{
			for (int j = 0; j < board.arr[0].length; j++)
			{
				if (board.arr[i][j] != EMPTY)
				{
					firstTurn = false;
				}
			}
		}
		
		if (firstTurn) return new BoardMove(2, 2, this.symbol);
		*/
		
		BoardMove[] allMoves = generateAllMoves(board, symbol);
		int[] scores = new int[allMoves.length];
		
		for (int i = 0; i < allMoves.length; i++)
		{
			scores[i] = assessMove(new Board(board), allMoves[i], this.symbol, 0);
		}
		
		System.out.println("Final scores are: ");
		
		for (int i = 0; i < allMoves.length; i++)
		{
			System.out.println(allMoves[i] + " scored at " + scores[i]);
		}
		
		int index = 0, currentHighestScore = 0;
		
		for (int i = 0; i < scores.length; i++)
		{
			if (scores[i] >= currentHighestScore)
			{
				currentHighestScore = scores[i];
				index = i;
			}
		}
		System.out.println("");
		System.out.println("Final move chosen: " + allMoves[index]);
		
		return allMoves[index];
	}
	
	/*
	 * Uses minimax in order to return a score for the sent move
	 * board - the current board (without the move)
	 * move - the move to assess
	 * symbol - who's turn it is (X or O), note that this variable isn't really necessary (this information is already provided in move)
	 * depth - A variable that is incremented with each turn, to make Idan prefer choosing the victory that will come the fastest
	 */
	private int assessMove (Board board, BoardMove move, int symbol, int depth)
	{
		
		//If board score can be assessed immediately, return it
		if (board.isFull() || board.winner() != EMPTY)
		{
			return score(board, depth);
		}
		
		else
		{
			//Inserts the current move and checks if now the board can be assessed immediately
			board.insert(move);
			if (board.isFull() || board.winner() != EMPTY)
			{
				return score(board, depth);
			}
			else //If the board cannot be assessed immediately
			{
				//Generates all possible moves in the next turn, and an array of scores for them
				int nextSymbol;
				if (symbol == X) nextSymbol = O;
				else nextSymbol = X;
				
				BoardMove[] allMoves = generateAllMoves(board, nextSymbol);
				int[] scores = new int[allMoves.length];
				
				//Assesses their scores recursively - calls this method again
				for (int i = 0; i < allMoves.length; i++)
				{
					scores[i] = assessMove(new Board(board), allMoves[i], nextSymbol, depth + 1);
				}
				
				//If it's my turn, return the score of the move that has the maximum score - the one that I would choose 
				if (nextSymbol == this.symbol)
				{
					int index = 0, currentHighestScore = 0;
					for (int i = 0; i < scores.length; i++)
					{
						if (scores[i] >= currentHighestScore)
						{
							currentHighestScore = scores[i];
							index = i;
						}
					}
					return scores[index];
				}
				//If it's the opponent's turn, return the score of the move that has the minimum score - the one that he would choose
				else 
				{
					int index = 0, currentLowestScore = 0;
					for (int i = 0; i < scores.length; i++)
					{
						if (scores[i] <= currentLowestScore)
						{
							currentLowestScore = scores[i];
							index = i;
						}
					}
					return scores[index];
				}
			}
		}
	}
	
	/*
	 * Return an array with all the possible moves of a player in the board
	 */
	private BoardMove[] generateAllMoves (Board board, int symbol)
	{
		HashSet<BoardMove> allMoves = new HashSet<>();
		
		for (int i = 0; i < board.arr.length; i++)
		{
			for (int j = 0; j < board.arr[0].length; j++)
			{
				if (board.arr[i][j] == EMPTY)
				{
					allMoves.add(new BoardMove(i, j, symbol));
				}
			}
		}
		
		BoardMove[] a = new BoardMove[0];
		return allMoves.toArray(a);
	}
	
	/*
	 * Gives an immediate score to a board - as depth is increased, the score returned gets closer to 0
	 */
	private int score (Board board, int depth)
	{
		if (board.winner() == this.symbol) return 10 - depth;
		else if (board.winner() != EMPTY) return depth - 10;
		else return 0;
	}
}
