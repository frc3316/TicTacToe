/*
 _______   ______   __   _______
|  ___  | |  ____| |  | |  ___  \
| |   | | | |____  |  | | |___| /
| |___| | |  ____| |  | |  ___  \
|_______| |_|      |__| |_|   |__\
                                                                  
*/

/**
 * An autonomous player in a tic-tac-toe game.
 * @author Ofir
 *
 */

import java.util.Collections;
import java.util.LinkedList;

public class Ofir extends Player {
	private BoardMove move;
	private int turnNum = 2;
	private int oppositeSymbol = symbol == 1 ? 2 : 1;

	public Ofir(int alignment) {
		super(alignment);
	}

	@Override
	public void setName() {
		this.name = "Ofir";
	}

	@Override
	public BoardMove playTurn(Board board) {
		LinkedList<BoardMove> allMoves = generateAllMoves(board, symbol);

		// Checks if it's the first turn.
		if (allMoves.size() == board.arr.length * board.arr[0].length) {
			// Places the symbol in one of the corners (randomly).
			turnNum = 1;
			double randomX = Math.random();
			double randomY = Math.random();
			if (randomX > 0.5) {
				randomX = board.arr.length - 1;
			} else {
				randomX = 0;
			}
			if (randomY > 0.5) {
				randomY = board.arr[(int) randomX].length - 1;
			} else {
				randomY = 0;
			}

			move = new BoardMove((int) randomX, (int) randomY, symbol);
		} else if (turnNum == 2) {
			if (!addSymbol(0, symbol, board)) {
				double randomX = Math.random();
				double randomY = Math.random();
				if (randomX > 0.5) {
					randomX = board.arr.length - 1;
				} else {
					randomX = 0;
				}
				if (randomY > 0.5) {
					randomY = board.arr[(int) randomX].length - 1;
				} else {
					randomY = 0;
				}

				move = new BoardMove((int) randomX, (int) randomY, symbol);
			}
		} else if (turnNum == 4) {
			if (board.arr[getMedianNumber(board.arr[0].length)][getMedianNumber(board.arr.length)] == symbol
					&& addSymbol(3, symbol, board))
				;
			else {
				addSymbol(2, symbol, board);
			}
		} else if (!addSymbol(2, symbol, board) && !addSymbol(3, symbol, board) && !addSymbol(0, symbol, board)) {
			Collections.shuffle(allMoves);
			move = allMoves.stream().findAny().get();
		}

		turnNum += 2;
		return move;
	}

	/**
	 * Checks if the game square is empty.
	 * 
	 * @param x
	 * @param y
	 * @param board
	 * @return boolean
	 */
	private boolean isEmpty(int x, int y, Board board) {
		if (board.arr[x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds symbol randomly (or not) in a specific area.
	 * 
	 * @param place
	 *            - integral value - 0 to place in the center, 2 to place in one
	 *            of the corners or 3 to place in one of the edges (not
	 *            corners).
	 * @param symbol
	 *            - integral value - inherited from the class Player.
	 * @param board
	 * @return boolean - true if method works, else returns false.
	 */
	private boolean addSymbol(int place, int symbol, Board board) {
		// Checks if the player can win/lose in the next turn.
		if (!tactic0(board, false) && !tactic0(board, true) && !tactic3(board)) {
			if (place == 0) {
				// Places in the center.
				if (isEmpty(getMedianNumber(board.arr.length), getMedianNumber(board.arr[0].length), board)) {
					move = new BoardMove(getMedianNumber(board.arr.length), getMedianNumber(board.arr[0].length),
							symbol);
					return true;
				} else {
					return false;
				}
			} else if (place == 1) {
				// Removed.
				return false;
			} else if (place == 2) {
				if (isEmpty(getMedianNumber(board.arr.length), getMedianNumber(board.arr[0].length), board)) {
					// Symbol wasn't placed in the center.
					if (!tactic1(board)) {
						return false;
					}
				} else if (!tactic2(board) && !tactic1(board)) {
					return false;
				}
				return true;
			} else if (place == 3) {
				// Places symbol randomly in one of the edges (not corners).
				int randomX = (int) Math.floor(Math.random() * 2);
				int randomY = (int) Math.floor(Math.random() * 3);
				if (randomY != 1) {
					randomX = 1;
				} else if (randomX == 1) {
					randomX = 2;
				}
				if (board.arr[getMedianNumber(board.arr[0].length)][getMedianNumber(board.arr.length)] == symbol) {
					if (numOfSymbols(1, true, false, board) == 1) {
						randomX = 1;

						if (numOfSymbols(2, true, true, board) == 2)
							randomY = 2;
						else
							randomY = 1;
					} else if (numOfSymbols(1, false, false, board) == 1) {
						randomY = 1;

						if (numOfSymbols(0, false, true, board) == 2)
							randomX = 0;
						else
							randomX = 2;
					} else if (diagonalNumOfSymbols(true, false, board) == 1) {
						if (board.arr[0][1] == oppositeSymbol && board.arr[1][2] == oppositeSymbol)
							randomY = 2;
						else
							randomY = 0;

						randomX = board.arr.length - randomY - 1;
					} else if (diagonalNumOfSymbols(false, false, board) == 1) {
						if (board.arr[0][1] == oppositeSymbol && board.arr[1][0] == oppositeSymbol)
							randomY = 0;
						else
							randomY = 2;

						randomX = randomY;
					}
				}
				if (board.arr[randomX][randomY] == 0) {
					move = new BoardMove(randomX, randomY, symbol);
					return true;
				} else if (board.arr[0][1] == 0) {
					move = new BoardMove(0, 1, symbol);
					return true;
				} else if (board.arr[1][0] == 0) {
					move = new BoardMove(1, 0, symbol);
					return true;
				} else if (board.arr[1][2] == 0) {
					move = new BoardMove(1, 2, symbol);
					return true;
				} else if (board.arr[2][1] == 0) {
					move = new BoardMove(2, 1, symbol);
					return true;
				}
				return false;
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns the median number in a certain range.
	 * 
	 * @param max
	 *            - the max number in the range.
	 * @return int - the median number in a range.
	 */
	private int getMedianNumber(int max) {
		if (max % 2 == 0) {
			return max / 2;
		} else {
			return (int) Math.ceil(max / 2);
		}
	}

	/**
	 * Returns the opposite square in the board.
	 * 
	 * @param square
	 * @param board
	 * @return int
	 */
	private int getOppositeSquare(int square, Board board) {
		if (square == 0) {
			return board.arr.length - 1;
		} else {
			return 0;
		}
	}

	// The following methods check the number of the first/second player symbols
	// in a
	// line.

	private int numOfSymbols(int rowOrColumn, boolean isColumn, boolean oppSymbol, Board board) {
		int toReturn = 0;
		if (!isColumn) {
			for (int i = 0; i < board.arr.length; i++) {
				if (board.arr[i][rowOrColumn] == (oppSymbol ? oppositeSymbol : symbol)) {
					toReturn++;
				} else if (board.arr[i][rowOrColumn] == (!oppSymbol ? oppositeSymbol : symbol)) {
					return 0;
				}
			}
		} else {
			for (int i = 0; i < board.arr[rowOrColumn].length; i++) {
				if (board.arr[rowOrColumn][i] == (oppSymbol ? oppositeSymbol : symbol)) {
					toReturn++;
				} else if (board.arr[rowOrColumn][i] == (!oppSymbol ? oppositeSymbol : symbol)) {
					return 0;
				}
			}
		}
		return toReturn;
	}

	private int diagonalNumOfSymbols(boolean fromRight, boolean oppSymbol, Board board) {
		int toReturn = 0;
		if (!fromRight) {
			for (int i = 0; i < board.arr.length; i++) {
				if (board.arr[i][i] == (oppSymbol ? oppositeSymbol : symbol)) {
					toReturn++;
				} else if (board.arr[i][i] == (!oppSymbol ? oppositeSymbol : symbol)) {
					return 0;
				}
			}
		} else {
			for (int i = 0; i < board.arr.length; i++) {
				if (board.arr[i][board.arr.length - i - 1] == (oppSymbol ? oppositeSymbol : symbol)) {
					toReturn++;
				} else if (board.arr[i][board.arr.length - i - 1] == (!oppSymbol ? oppositeSymbol : symbol)) {
					return 0;
				}
			}
		}
		return toReturn;

	}

	// Makes a list of all the empty squares.
	private LinkedList<BoardMove> generateAllMoves(Board board, int symbol) {
		LinkedList<BoardMove> toReturn = new LinkedList<>();

		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[0].length; j++) {
				if (board.arr[i][j] == 0) {
					toReturn.add(new BoardMove(i, j, symbol));
				}
			}
		}

		return toReturn;
	}

	// The following methods check if there are opposite symbols in row/column.

	private boolean checkRow(int row, int symbol, Board board) {
		for (int i = 0; i < board.arr.length; i++) {
			if (board.arr[i][row] != 0 && board.arr[i][row] != symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean checkColumn(int column, int symbol, Board board) {
		for (int i = 0; i < board.arr[0].length; i++) {
			if (board.arr[column][i] != 0 && board.arr[column][i] != symbol) {
				return false;
			}
		}
		return true;
	}

	// Tactics

	// Checks potential winning/losing.
	private boolean tactic0(Board board, boolean oppSymbol) {
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[0].length; j++) {
				if (board.arr[i][j] == (oppSymbol ? oppositeSymbol : symbol)) {
					if (numOfSymbols(j, false, oppSymbol, board) == 2) {
						for (int k = 0; k < board.arr.length; k++) {
							if (board.arr[k][j] == 0) {
								move = new BoardMove(k, j, symbol);
								return true;
							}
						}
					} else if (numOfSymbols(i, true, oppSymbol, board) == 2) {
						for (int k = 0; k < board.arr[0].length; k++) {
							if (board.arr[i][k] == 0) {
								move = new BoardMove(i, k, symbol);
								return true;
							}
						}
					} else if (diagonalNumOfSymbols(false, oppSymbol, board) == 2) {
						for (int k = 0; k < board.arr[0].length; k++) {
							if (board.arr[k][k] == 0) {
								move = new BoardMove(k, k, symbol);
								return true;
							}
						}
					} else if (diagonalNumOfSymbols(true, oppSymbol, board) == 2) {
						for (int k = 0; k < board.arr[0].length; k++) {
							if (board.arr[k][board.arr.length - k - 1] == 0) {
								move = new BoardMove(k, board.arr.length - k - 1, symbol);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	// This method is supposed to be used on the second turn of the first
	// player.
	private boolean tactic1(Board board) {
		if (checkRow(move.y, symbol, board)) {
			if (isEmpty(getOppositeSquare(move.x, board), move.y, board)) {
				move = new BoardMove(getOppositeSquare(move.x, board), move.y, symbol);
				return true;
			} else
				if (!checkColumn(move.x, symbol, board) || !isEmpty(getOppositeSquare(move.y, board), move.x, board)) {
				return false;
			} else {
				move = new BoardMove(move.x, getOppositeSquare(move.y, board), symbol);
				return true;
			}
		} else if (checkColumn(move.x, symbol, board)) {
			if (isEmpty(move.x, getOppositeSquare(move.y, board), board)) {
				move = new BoardMove(move.x, getOppositeSquare(move.y, board), symbol);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// This method is supposed to be used when the the second player places the
	// symbol in the center.
	private boolean tactic2(Board board) {
		if (board.arr[getOppositeSquare(move.x, board)][getOppositeSquare(move.y, board)] == 0) {
			move = new BoardMove(getOppositeSquare(move.x, board), getOppositeSquare(move.y, board), symbol);
			return true;
		}
		return false;
	}

	/*
	 * The next two methods are for making sure that the second player won't win
	 * when the board looks like that:
	 */
	// X EMPTY EMPTY
	// EMPTY EMPTY EMPTY
	// EMPTY X EMPTY
	private boolean tactic3(Board board) {
		if (inTactic3(new int[] { 0, 0 }, board)) {
			move = new BoardMove(0, 0, symbol);
			return true;
		}
		if (inTactic3(new int[] { 0, 2 }, board)) {
			move = new BoardMove(0, 2, symbol);
			return true;
		}
		if (inTactic3(new int[] { 2, 0 }, board)) {
			move = new BoardMove(2, 0, symbol);
			return true;
		}
		if (inTactic3(new int[] { 2, 2 }, board)) {
			move = new BoardMove(2, 2, symbol);
			return true;
		}
		return false;
	}

	private boolean inTactic3(int[] startingPoint, Board board) {
		if (board.arr[startingPoint[0]][startingPoint[1]] == 0
				&& board.arr[Math.abs(startingPoint[0] - 1)][startingPoint[1]] == 0
				&& board.arr[Math.abs(startingPoint[0] - 2)][startingPoint[1]] == oppositeSymbol
				&& board.arr[startingPoint[0]][Math.abs(startingPoint[1] - 1)] == oppositeSymbol) {
			return true;
		}
		return false;
	}
}