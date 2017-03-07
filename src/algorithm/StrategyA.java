package algorithm;

import javax.swing.JTable;

import model.Board;
import model.Player;

public class StrategyA implements Strategy{

	@Override
	public boolean isEnd(Board board,Player p) {
		if(isCompleteHorizontal(board,p) || isCompleteVertical(board,p) || isCompleteDiagonal(board,p) || isCompleteAntiDiagonal(board,p))
			return true;
		return false;
	}

	@Override
	public boolean isFull(Board board) {
		for(int i = 0 ; i < Board.BOARD_SIZE ; i++){
			for(int j = 0 ; j < Board.BOARD_SIZE ; j++){
				if(board.getMap()[i][j].getValue().equals(Board.STRING_EMPTY))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean isCompleteHorizontal(Board board, Player p) {
		int c = 0;
		for (int i = 0; i < Board.BOARD_SIZE; i++) {
			for (int j = 0; j < Board.BOARD_SIZE; j++) {
				if (board.getMap()[i][j].getValue().equals(p.getSymbol().getValue())) {
					c++;
					if(c >= 5) return true;
				} else {
					c = 0;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isCompleteVertical(Board board, Player p) {
		int c = 0;
		for (int j = 0; j < Board.BOARD_SIZE; j++) {
			for (int i = 0; i < Board.BOARD_SIZE; i++) {
				if (board.getMap()[i][j].getValue().equals(p.getSymbol().getValue())) {
					c++;
					if(c >= 5) return true;
				} else 
					c = 0;
			}
		}
		return false;
	}

	@Override
	public boolean isCompleteDiagonal(Board board, Player p) {
		int c = 0;
		for (int i = 0; i < Board.BOARD_SIZE - 4; i++) {
			for (int j =i ,k = 0; j < Board.BOARD_SIZE && k < Board.BOARD_SIZE ; j++,k++) {
				if (board.getMap()[j][k].getValue().equals(p.getSymbol().getValue())) {
					c++;
					if(c >= 5) return true;
				} else 
					c = 0;
			}
		}
		for(int i = 1 ; i < Board.BOARD_SIZE - 4 ; i++){
			for(int j = 0, k = i ; j < Board.BOARD_SIZE && k < Board.BOARD_SIZE ; j++, k++){
				if(board.getMap()[j][k].getValue().equals(p.getSymbol().getValue())){
					c++;
					if(c >= 5) return true;
				}
				else
					c = 0;
			}
		}
		return false;
	}

	@Override
	public boolean isCompleteAntiDiagonal(Board board, Player p) {
		int c = 0;
		for(int i = (Board.BOARD_SIZE) - 1 ; i >= (Board.BOARD_SIZE - 5)  ; i--){
			for(int j = 0, k = i ; j < Board.BOARD_SIZE && k >=  0 ;j++,k--){
				if(board.getMap()[j][k].getValue().equals(p.getSymbol().getValue())){
					c++;
					if(c >= 5) return true;
				}
				else{
					c = 0;
				}
			}
		}
		for(int i = (Board.BOARD_SIZE - 1) ; i >= (Board.BOARD_SIZE - 4) ; i--){
			for(int j = i - 4, k = Board.BOARD_SIZE -1; j < Board.BOARD_SIZE && k >= 0 ;j++,k--){
				if(board.getMap()[j][k].getValue().equals(p.getSymbol().getValue())){
					c++;
					if(c >= 5) return true;
				}
				else{
					c = 0;
				}
			}
		}
		return false;
	}
}
