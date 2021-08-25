package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPiece extends Piece { //responsavel por identificar de qual time é a peça

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public ChessPosition getChessPosition() { //retorna a posição em forma de xadrez e não em forma de posição de matriz
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) { //verifica se a peça escolhida pelo jogar é realmente dele
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	
}