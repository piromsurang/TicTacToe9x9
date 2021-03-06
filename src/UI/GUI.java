package UI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import algorithm.Strategy;
import algorithm.StrategyA;
import main.Game;
import model.Board;

public class GUI extends JFrame implements Runnable {
		
	private JPanel gamePanel = new JPanel();
	private Game game;
	private final int SIZE = 9;
	private Strategy strategy;
	private JTable table;
	private JLabel topic;
	private boolean checkEnd;
	private JButton resetButton;
	private JButton exitButton;
	
	public GUI(Game g, Strategy strategy ){
		super("TicTacToe");
		this.game = g;
		this.strategy = strategy;
		checkEnd = false;
		setSize(400,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	private void initComponents() {
		gamePanel.setLayout(new FlowLayout() );
		resetButton = new JButton( "Reset" );
		resetButton.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				game.reset();
				resetTable();
				checkEnd = false;
				topic.setText( "GAME START!!!" );
			}
			
		});
		exitButton = new JButton( "Exit" );
		exitButton.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent e ) {
				exit();
			}
			
		});
		
		topic = new JLabel( "GAME START!!!" );
		topic.setSize( 40, 400 );

		table = new JTable( SIZE, SIZE ){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		initializeTable();
		
		gamePanel.add( topic );
		gamePanel.add( table );
		gamePanel.add( resetButton );
		gamePanel.add( exitButton );
		add(gamePanel);
		setVisible( true );
	}
	
	public void action( JTable table, int row, int column ) {
		
		if( !checkEnd ) {	
			if( table.getValueAt( row, column ) == null ) {
				table.setValueAt( game.getCurrentPlayer().getSymbol().getValue(), row, column );
				game.getBoard().placeSymbol(game.getCurrentPlayer().getSymbol(), row+1, column+1);
				if( game.isOver( game.getCurrentPlayer() ) == true ) {
					checkEnd = game.isOver(game.getCurrentPlayer());
					System.out.println( checkEnd );
					topic.setText( "Game OVER!!!\n" + game.getCurrentPlayer().getName() + " wins." );
				}
				else {
					game.changeCurrentPlayer();
				}
			}
		}
		
	}
		
	@Override
	public void run() {
		initComponents();	
	}
	
	public void exit(){
		this.dispose();
	}
	
	public void resetTable() {
		for( int i = 0 ; i < SIZE ; i++ ) {
			for( int j = 0 ; j < SIZE ; j++ ) {
				table.setValueAt( null, i, j);
			}
		}
	}
	
	public void initializeTable() {		
		table.setRowHeight(40);
		TableColumnModel model = table.getColumnModel();
		for( int i = 0 ; i < SIZE ; i++ ) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			model.getColumn(i).setCellRenderer( centerRenderer );
			model.getColumn(i).setPreferredWidth( 400/9 );
		}
		table.setCellSelectionEnabled(true);
		table.setShowGrid( true );
		table.setGridColor( Color.BLACK );
		table.addMouseListener( new MouseAdapter() {
			
			public void mouseClicked( MouseEvent e ) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				action( table, row, column );
			}
			
		});
	}

}
