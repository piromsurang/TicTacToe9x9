package algorithm;

import javax.swing.JTable;

import model.Board;
import model.Player;

public interface Strategy {
	public boolean isEnd(Board board,Player p);
	public boolean isFull(Board board);
	boolean isCompleteHorizontal(Board board,Player p);
	boolean isCompleteVertical(Board board,Player p);
	boolean isCompleteDiagonal(Board board,Player p);
	boolean isCompleteAntiDiagonal(Board board,Player p);
	
}
