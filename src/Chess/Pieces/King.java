package Chess.Pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) { //verifica se o movimento do rei � possivel
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		p.setValues(position.getRow() - 1, position.getColumn()); //pra cima
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn()); //pra baixo
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow(), position.getColumn() - 1); //pra esquerda
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow(), position.getColumn() + 1); //pra direita
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() - 1); //pra diagonal superior esquerda
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() - 1, position.getColumn() + 1); //pra diagonal superior direita
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() - 1); //pra diagonal inferior esquerda
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + 1, position.getColumn() + 1); //pra direita inferior direita
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//ROQUE
		if(getMoveCount() == 0 && !chessMatch.isCheck()) {
			//roque curto
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			//roque grande
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		
		return mat;
	}

}