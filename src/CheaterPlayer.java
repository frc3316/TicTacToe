
public class CheaterPlayer extends Player 
{
	public CheaterPlayer(int alignment)
	{
		super(alignment);
	}

	@Override
	public void setName() 
	{
		this.name = "Cheater";
	}

	@Override
	public BoardMove playTurn(Board board) 
	{
		return new BoardMove (3, 3, this.symbol);
	}
}
