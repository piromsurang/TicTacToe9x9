package control;

import java.util.Scanner;

import UI.GUI;
import algorithm.Strategy;
import algorithm.StrategyA;
import main.Game;

public class Console implements Runnable {
	private Scanner scan = new Scanner(System.in);
	private Game game;
	private Strategy strategy;
	
	public Console( Game game, Strategy strategy ) {
		this.game = game;
		this.strategy = strategy;
	}
	
	public int[] readInputRowColumn(Game game){
		int x = -1,y = -1;
		int output[] = new int [2];
		boolean in = false;
		do{
			in = false;
			try{
				System.out.print("Please select row: ");
				x = Integer.parseInt(scan.nextLine());
				System.out.print("Please select column: ");				
				y = Integer.parseInt(scan.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Please input number!!!");
				in = true;
			}
			if(!game.getBoard().isEmpty(x, y) && !in){
				System.out.println("Try again this index not empty!!!");
			}
		}while (!game.getBoard().isEmpty(x, y));
		output[0] = x;
		output[1] = y;
		return output;
	}

	@Override
	public void run() {
		int round = 0,turn;
		int input[] = new int[2];
		do{
			turn = round % 2 ;
			System.out.println("Player " + game.getPlayers()[turn].getName() + " turn.");
			game.changeCurrentPlayer();
			input = readInputRowColumn(game);
			game.getBoard().placeSymbol(game.getPlayers()[turn].getSymbol(), input[0],input[1]);
			game.getBoard().printBoard();
			if(game.isOver(game.getPlayers()[turn]))
				System.out.println(game.getPlayers()[turn].getName()+" wins in round "+round + "!!!!");
			else if(strategy.isFull(game.getBoard())){
				System.out.println("Draw");
			}
			else{
				System.out.println("You placed " + game.getPlayers()[turn].getName() + " on (" + input[0] + "," + input[1] + ")");
			}
			round++;
				
		}while(!game.isOver(game.getPlayers()[turn]));		
	}
	
}
