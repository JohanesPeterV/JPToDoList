package view;

import java.awt.BorderLayout;

import javax.swing.*;

import com.sun.javafx.geom.AreaOp.AddOp;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
public class Desc{
	String descString="";
	public Desc(String descString) {
		this.descString=descString;
	    JLabel label = new JLabel("Details:");
	    JTextArea textArea = new JTextArea(2, 20);
	    textArea.setText(descString);
	    textArea.setWrapStyleWord(true);
	    textArea.setLineWrap(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
	    textArea.setFocusable(false);
	    textArea.setBackground(UIManager.getColor("Label.background"));
	    textArea.setFont(UIManager.getFont("Label.font"));
	    textArea.setBorder(UIManager.getBorder("Label.border"));
		
	    JFrame frame = new JFrame();
	    frame.setAlwaysOnTop(true);

	    frame.getContentPane().add(label, BorderLayout.NORTH);
	    frame.getContentPane().add(textArea, BorderLayout.CENTER);
	    frame.setSize(300,200);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				copyDesc();
			}
		});
	}
	
	
	void copyDesc() {
		StringSelection stringSelection = new StringSelection(descString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	
	
	
}