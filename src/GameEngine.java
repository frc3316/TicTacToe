
public class GameEngine implements Symbol
{
	public static void main (String [] args)
	{
		Player player1 = null;
		Player player2 = null;
		
		boolean player1Turn;
		
		Board board = new Board();
		
		if (Math.random() > 0.5)
		{
			player1 = new Idan(X);
			player2 = new RandomPlayer(O);
			player1Turn = true;
		}
		else
		{
			player1 = new Idan(O);
			player2 = new RandomPlayer(X);
			player1Turn = false;
		}
		
		player1.setName();
		player2.setName();
		
		System.out.println(player1 + " and " + player2);
		
		Player currentPlayer = null;
		
		while (board.winner() == EMPTY && !board.isFull())
		{
			System.out.println(board);
			
			currentPlayer = player1Turn ? player1 : player2;
			
			BoardMove next = currentPlayer.playTurn(new Board(board));
			
			if (	next.x > 2 || 
					next.y > 2 || 
					next.x < 0 || 
					next.y < 0)
			{
				System.out.println(currentPlayer + " lost because he tried playing outside of the board");
				break;
			}
			else if (board.arr[next.x][next.y] != EMPTY)
			{
				System.out.println(currentPlayer + " lost because he tried playing in an already taken space");
				break;
			}
			else if (next.symbol != currentPlayer.symbol)
			{
				System.out.println(currentPlayer + " lost because he tried cheating - placing a symbol that is not his");
				break;
			}
			else
			{
				board.insert(next);
			}
			
			player1Turn = !player1Turn;
		}
			
		System.out.println(board);
			
		if (board.winner() != EMPTY)
		{
			System.out.println(currentPlayer + " wins!");
		}
		else if (board.isFull())
		{
			System.out.println("Board is full! It's a tie!");
		}
	}
}
