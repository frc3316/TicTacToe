public class GameEngine implements Symbol
{
	int player1Score = 0, player2Score = 0, totalGames = 0;
	
	//Change the number of games here:
	final int maxGames = 10;
	
	public void runGame() {
		totalGames++;
		
		Player player1 = null;
		Player player2 = null;
		
		boolean player1Turn;
		
		Board board = new Board();
		
		if (Math.random() > 0.5)
		{
			player1 = new KipaDan(X);
			player2 = new RandomPlayer(O);
			player1Turn = true;
		}
		else
		{
			player1 = new KipaDan(O);
			player2 = new RandomPlayer(X);
			player1Turn = false;
		}
		
		player1.setName();
		player2.setName();
		
		System.out.println("\nGame #" + totalGames + ":");
		System.out.println("\t" + player1 + " and " + player2);
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
				System.out.println("\t" + currentPlayer + " lost because he tried playing outside of the board");
				if (!player1Turn) player1Score++;
				else player2Score++;
				break;
			}
			else if (board.arr[next.x][next.y] != EMPTY)
			{
				System.out.println("\t" + currentPlayer + " lost because he tried playing in an already taken space");
				if (!player1Turn) player1Score++;
				else player2Score++;
				break;
			}
			else if (next.symbol != currentPlayer.symbol)
			{
				System.out.println("\t" + currentPlayer + " lost because he tried cheating - placing a symbol that is not his");
				if (!player1Turn) player1Score++;
				else player2Score++;
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
			System.out.println("\t" + currentPlayer + " wins!");
			if (!player1Turn) player1Score++;
			else player2Score++;
		}
		else if (board.isFull())
		{
			System.out.println("\tBoard is full! It's a tie!");
		}
		
		if (totalGames < maxGames) {
			runGame();
		}
		else {
			int scoreTie = totalGames - (player1Score + player2Score);
			
			System.out.println("\n" + totalGames + (totalGames != 1 ? " games were played" : " game was played") + ":");
			System.out.println("\t" + player1.name + " won in " + player1Score +  (player1Score != 1 ? " games" : " game") + " - " + ((double)player1Score / totalGames * 100) + "% of all the games.");
			System.out.println("\t" + player2.name + " won in " + player2Score +  (player2Score != 1 ? " games" : " game") + " - " + ((double)player2Score / totalGames * 100) + "% of all the games.");
			System.out.println("\tIt was a tie in " + scoreTie +  (scoreTie != 1 ? " games" : " game") + " - " + ((double)scoreTie / totalGames * 100) + "% of all the games.");
		}
	}
	public static void main (String [] args)
	{
		new GameEngine().runGame();
	}
}
