package model;

public class Player {
	private String name;
	private Symbol symbol;
	public Player(String name , String s){
		this.name = name;
		this.symbol = new Symbol(s);
	}
		
	public Player() {
		
	}

	public void placeSymbol(Board b, int row, int col){
		b.placeSymbol(symbol,row,col);
	}

	public String getName() {
		return name;
	}

	public Symbol getSymbol() {
		return symbol;
	}
	
}
