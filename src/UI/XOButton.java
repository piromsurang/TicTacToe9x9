package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class XOButton extends JButton implements ActionListener{
	private final int row;
	private final int column;
	public XOButton(int row,int column){
		super();
		this.row = row;
		this.column = column;
		this.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		setText("X");
		
	}
	
}
