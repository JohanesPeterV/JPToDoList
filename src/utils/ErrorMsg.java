package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ErrorMsg extends JOptionPane implements ActionListener{
	JButton ok=new JButton("OK");
	
	public ErrorMsg(String msg) {
		// TODO Auto-generated constructor stub
		add(new JLabel(msg));
		add(ok);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
