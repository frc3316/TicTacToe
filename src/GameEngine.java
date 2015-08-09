
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
			//player1 = new ??? (X);
			//player2 = new ??? (O);
			player1Turn = true;
			System.out.println("Starting player is " + player1);
		}
		else
		{
			//player1 = new ??? (O);
			//player2 = new ??? (X);
			player1Turn = false;
			System.out.println("Starting player is " + player2);
		}
		
		player1.setName();
		player2.setName();
		
		Player currentPlayer = null;
		
		while (board.winner() == EMPTY && !board.isFull())
		{
			System.out.println(board);
			
			currentPlayer = player1Turn ? player1 : player2;
			
			BoardPosition next = currentPlayer.playTurn(new Board(board));
			
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
			else
			{
				board.arr[next.x][next.y] = currentPlayer.symbol;
			}
			
			player1Turn = !player1Turn;
		}
		
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
