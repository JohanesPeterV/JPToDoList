package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.sun.org.glassfish.gmbal.Description;

import utils.MainUtils;
import view.Node;


public class MainForm extends JFrame implements ActionListener, ToDoPlatform{
	JPanel superPanel, mainPanel, gridPanel;
	JButton createButton=new JButton("Create");
	JButton exitButton=new JButton("exit");
	
	JTextField titleField= new JTextField();
	JTextField descField= new JTextField();
	ArrayList<ToDo> toDos;
	ArrayList<Node> nodes;
	public MainForm(ArrayList<ToDo> toDos,ArrayList<Node> nodes) {
		// TODO Auto-generated constructor stub
//		super("To Do List");
		this.toDos=toDos;
		this.nodes=nodes;
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
		
	}
	private void init() {
		this.addWindowListener(new WindowAdapter() {
			
			 @Override
			 public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				 MainUtils.writeResult(toDos, false);
				 for (Node node : nodes) {
					node.dispose();
				}
			 }
		});
		try {
			Image bgImg=ImageIO.read(new File("Assets/400x300.png"));
			superPanel= new JPanel(new GridBagLayout()){
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, 900, 600, null);
				}
			};
		}catch (Exception e) {
			// TODO: handle exception
			superPanel= new JPanel();
		}
		titleField.setPreferredSize(new Dimension(2,20));
		
		
		initGrid();
		
		initMain();
		setSize(new Dimension(900,600));
//		setLocation(400-100, 300-150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mainPanel.setSize(new Dimension(400,300));
		mainPanel.setPreferredSize(new Dimension(400,300));
		superPanel.add(mainPanel);
		add(superPanel);
//		setOpacity(0);
		setVisible(true);
//		setTitle("ToDoList");
//		UIDefaults uiDefaults = UIManager.getDefaults();

	}
	private void initGrid() {
		gridPanel=new JPanel(new GridLayout(4,1));
		gridPanel.setBackground(new Color(0f,0f,0f,.0f ));
		
	

		gridPanel.setPreferredSize(new Dimension(400,100));
		
		JPanel container=new JPanel(new BorderLayout());
		container.add(MainUtils.leftTextPanel("Title",200,20),BorderLayout.NORTH);
		container.add(MainUtils.encoat(titleField,200,30),BorderLayout.SOUTH);
		container.setBackground(Color.decode("#fff9f4"));
		gridPanel.add(container);
		
		JPanel container2=new JPanel(new BorderLayout());
		container2.setBackground(Color.decode("#fff9f4"));
		container2.add(MainUtils.leftTextPanel("Description",200,20),BorderLayout.NORTH);
		container2.add(MainUtils.encoat(descField,200,30),BorderLayout.SOUTH);		
		
		
		
		gridPanel.add(container2);
		JPanel container3=new JPanel(new BorderLayout());
		
		

		
		container3.add(MainUtils.encoat(createButton,200,30),BorderLayout.SOUTH);
		container3.setBackground(Color.decode("#fff9f4"));
		gridPanel.add(container3);
		JPanel container4=new JPanel(new GridLayout(2,1));
		JPanel tempe1=new JPanel();
		JPanel tempe2=new JPanel();
		tempe1.setBackground(Color.decode("#fff9f4"));
		tempe2.setBackground(new Color(0f,0f,0f,.0f ));
		container4.add(tempe1);
		container4.add(tempe2);
		container4.setBackground(new Color(0f,0f,0f,.0f ));
		gridPanel.add(container4);
		
	}
	private void initMain() {
		Image bgImg;
		mainPanel= new JPanel(new BorderLayout());
		
		//Color.decode("#a94457")
		mainPanel.setBackground(new Color(0f,0f,0f,.0f ));
		
		JPanel temp=new JPanel();
		JPanel comp = new JPanel();
		temp.setBackground(Color.decode("#fff9f4"));
		comp.setBackground(Color.decode("#fff9f4"));
	    comp.setLayout(new BoxLayout(comp, BoxLayout.LINE_AXIS));
	    comp.add(exitButton);
	    comp.add(Box.createHorizontalGlue());
	    JPanel tempTitle=MainUtils.titlePanel("Add New ToDo",false,190,26);
	    tempTitle.setSize(new Dimension(300, 30));
	    tempTitle.setPreferredSize(new Dimension(300, 30));
	    comp.add(tempTitle);
		temp.add(comp);
//		add(temp);
		mainPanel.add(temp,BorderLayout.NORTH);
		
		mainPanel.add(gridPanel,BorderLayout.CENTER);

		createButton.addActionListener(this);
		exitButton.addActionListener(this);
	}
	
	
	public void triggerChange() {
		MainUtils.writeResult(toDos, false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==createButton) {
			String titleInput=titleField.getText();
			String descInput=descField.getText();
			
			if(titleInput.length()<=0) {
				JOptionPane.showMessageDialog(this, "Please do not leave the title empty...");
				return;
			}
			if(descInput.length()<=0) {
				descInput="No Description";
			}
			if(titleInput.length()>18) {
				JOptionPane.showMessageDialog(this, "To keep keep it clean, please input a title less than 18 characters");
				return;
			}

			boolean numberInput=false;
			int weightInput=0;
			while(!numberInput) {
				try {
					weightInput=Integer.parseInt(JOptionPane.showInputDialog("Input ToDo's weight"));					
					numberInput=true;
				} catch (Exception e2) {
					// TODO: handle exception
					numberInput=false;
				}
			}
			
			ToDo temp=new ToDo(titleInput, descInput,weightInput);
			toDos.add(temp);
			nodes.add(new Node(this,toDos,nodes,temp,"Assets/voucherpage.png"));

		}
		else if (e.getSource()==exitButton) {
			MainUtils.writeResult(toDos, false);
			for (Node node : nodes) {
				node.dispose();
			}
			dispose();
		}
	}
	
}



