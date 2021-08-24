package Chess;

import BoardGame.Board;
import BoardGame.Piece;

public abstract class ChessPiece extends Piece { //responsavel por identificar de qual time é a peça

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}