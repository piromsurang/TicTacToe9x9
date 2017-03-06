package control;

import UI.GUI;
import algorithm.StrategyA;
import main.Game;

public class Application {
	public static void main(String[] args){
		StrategyA strategy = new StrategyA();
		Game g = new Game(strategy);
		Console console = new Console(g, strategy);
		GUI gui = new GUI( g, strategy );
		
		//run game on GUI
		gui.run();
		
		//run game on console
		//console.run();
	}

}
