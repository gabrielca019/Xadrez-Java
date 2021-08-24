package Chess;

import BoardGame.Board;
import BoardGame.Piece;

public abstract class ChessPiece extends Piece { //responsavel por identificar de qual time � a pe�a

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}