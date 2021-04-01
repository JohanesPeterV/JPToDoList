package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.Console;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.ToDo;
import main.ToDoPlatform;
import utils.MainUtils;

public class Node extends JFrame implements ActionListener{
	JPanel mainPanel;
	JButton detailButton=new JButton("Details");
	JButton doneButton=new JButton("Done");
	private String fileName;
	int id;
	ToDo toDo;
	ArrayList<ToDo> toDos;
	ArrayList<Node> nodes;
	ToDoPlatform platform;
	public Node(ToDoPlatform platform,ArrayList<ToDo>toDos, ArrayList<Node> nodes,ToDo toDo, String fileName) {
		// TODO Auto-generated constructor stub
		this.toDo=toDo;
		this.platform=platform;
		this.toDos=toDos;
		this.nodes=nodes;
		try {
			Image bgImg=ImageIO.read(new File(fileName));
			
			mainPanel=new JPanel(new BorderLayout()) {
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, 1200, 750, null);
				}
			};
				
		} catch (Exception e) {
			// TODO: handle exception
			mainPanel=new JPanel(new BorderLayout());
		}
		setSize(new Dimension(240, 225));
		this.fileName=fileName;
		setVisible(true);
		setLocationRelativeTo(null);
		initPanels();
		setResizable(false);
		add(mainPanel);
		this.setAlwaysOnTop(true);
		

	}
	public void initPanels() {

		setTitle("To Do");
		JPanel buyPanel=new JPanel(new GridLayout(2,1));
		buyPanel.setSize(new Dimension(90,120));
		buyPanel.setPreferredSize(new Dimension(90,120));
		buyPanel.setBackground(new Color(0f,0f,0f,.0f ));
		buyPanel.add(MainUtils.encoat(MainUtils.tLabel(toDo.getTitle(), true)));

		
		
		
		buyPanel.add(MainUtils.encoat(MainUtils.tLabel("Weight: "+toDo.getWeight(), true)));
				
		
		
		

		mainPanel.add(buyPanel,BorderLayout.NORTH);
		
		
		
		

		JPanel tempGrid=new JPanel(new GridLayout(2,1));
		tempGrid.setBackground(new Color(0f,0f,0f,.0f ));
		tempGrid.add(MainUtils.encoat(detailButton,75,25));
		tempGrid.add(MainUtils.encoat(doneButton,75,25));
		mainPanel.add(tempGrid,BorderLayout.SOUTH);
		detailButton.addActionListener(this);
		doneButton.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			
			 @Override
			 public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//				 	System.out.println("Testing");
				 	nodes.remove(this);
					toDos.remove(toDo);
					platform.triggerChange();
					
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource()==doneButton) {
			nodes.remove(this);
			toDos.remove(toDo);
			platform.triggerChange();
		 	dispose();
			
		}
		if(e.getSource()==detailButton) {
//			final String html = "<html><body style='width: %1spx'>%1s";
//			JOptionPane.showMessageDialog(null, String.format(html, 20, this.toDo.getDesc()));
			new Desc(this.toDo.getDesc());
		}
	}

}
