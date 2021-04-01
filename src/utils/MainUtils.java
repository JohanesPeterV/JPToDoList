package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.ToDo;

public class MainUtils {

	public static JLabel wLabel(String string){
		JLabel temp=new JLabel(string);
		temp.setForeground(Color.WHITE);
		
		return temp;
	}
	
	public static JLabel bLabel(String string){
		JLabel temp=new JLabel(string);
		temp.setForeground(Color.BLACK);
		
		return temp;
	}
	
	public static JPanel textPanel(String text){
		JPanel temp=new JPanel();
//		temp.setSize(new Dimension(20,10));
//		temp.setPreferredSize(new Dimension(20,20));
//		temp.setBackground(Color.red);
		temp.setBackground(Color.decode("#a94457"));
//		temp.setBackground(null);
		temp.setVisible(true);
		temp.add(wLabel(text));
		return temp;
	}
	public static JPanel textPanel(String text,int width,int height){
		JPanel temp=new JPanel();
		temp.setSize(new Dimension(width,height));
		temp.setPreferredSize(new Dimension(width, height));
//		temp.setBackground(Color.red);
		temp.setBackground(new Color(0f,0f,0f,.0f ));
//		temp.setBackground(null);
//		temp.setVisible(true);
		temp.add(wLabel(text));
		return temp;
	}
	public static JPanel leftTextPanel(String text,int width,int height){
		JPanel tempe=new JPanel();
		JPanel temp=new JPanel(new BorderLayout());
		
		temp.setSize(new Dimension(width,height));
		temp.setPreferredSize(new Dimension(width, height));
//		temp.setBackground(Color.red);
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		tempe.setBackground(new Color(0f,0f,0f,.0f ));
//		temp.setBackground(null);
//		temp.setVisible(true);
		temp.add(bLabel(text),BorderLayout.WEST);
		tempe.add(temp);
		return tempe;
	}
	public static JLabel tLabel(String string,boolean isWhite){
		JLabel temp=new JLabel(string);
		if(isWhite)temp.setForeground(Color.WHITE);
		else temp.setForeground(Color.BLACK);
		temp.setFont(new Font("", 0, 20));
		
		return temp;
	}
	
	public static JPanel titlePanel(String text,boolean isWhite){
		JPanel tempe=new JPanel();
		JPanel temp=new JPanel();
		temp.add(tLabel(text,isWhite));
		temp.setVisible(true);
		
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		tempe.setBackground(new Color(0f,0f,0f,.0f ));
		return tempe;
	}
	public static JPanel titlePanel(String text,boolean isWhite,int width,int height){
		JPanel tempe=new JPanel();
		JPanel temp=new JPanel(new BorderLayout());
		temp.setSize(new Dimension(width,height));
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		tempe.setBackground(new Color(0f,0f,0f,.0f ));
		temp.add(tLabel(text,isWhite),BorderLayout.WEST);
		
		tempe.add(temp);
		return tempe;
	}
	public static JPanel encoat(Component comp,int width,int height,String colorCode) {
		JPanel temp=new JPanel();
		comp.setSize(new Dimension(width, height));
		comp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(Color.decode(colorCode));
		temp.add(comp);
		return temp;
	}
	public static JPanel encoat(Component comp,int width,int height) {
		JPanel temp=new JPanel();
		comp.setSize(new Dimension(width, height));
		comp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		temp.add(comp);
		return temp;
	}
	public static JPanel encoat2(Component comp,int width,int height) {
		JPanel temp=new JPanel();
		temp.setSize(new Dimension(width, height));
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		temp.add(comp);
		return temp;
	}
	public static JPanel encoat(Component comp) {
		JPanel temp=new JPanel();
		temp.add(comp);
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		return temp;
	}
	public static JPanel encoatMid(Component comp) {
		JPanel temp=new JPanel(new GridBagLayout());
		temp.add(comp);
		return temp;
	}
	public static JPanel encoatMid(Component comp, int width, int height) {
		JPanel temp=new JPanel(new GridBagLayout());
		temp.add(comp);
		temp.setSize(new Dimension(width, height));
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(new Color(0f,0f,0f,.0f ));
		return temp;
	}
	
	
	public static boolean readFile(ArrayList<ToDo> toDos, String filePath) {
		try {
			FileReader read=new FileReader(filePath);
			BufferedReader buffRead=new BufferedReader(read);
			String readString=null;
			String tempString[]= {"",""};
			try {
				while((readString=buffRead.readLine())!=null) {
					tempString=readString.split("#");
						toDos.add(new ToDo(tempString[0], tempString[1], Integer.parseInt(tempString[2])));
				}					
				buffRead.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					buffRead.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				toDos.clear();
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public static boolean writeResult(ArrayList<ToDo> toDos, boolean append) {
		File write=new File(".\\saveFile.txt");
		FileWriter writer;
		try {
			writer=new FileWriter(write, append);
			for (ToDo toDo : toDos) {
				writer.write(toDo.getTitle()+"#"+toDo.getDesc()+"#"+toDo.getWeight()+"\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static int countW(ArrayList<ToDo> toDos) {
		int count=0;
		for (ToDo toDo : toDos) {
			count+=toDo.getWeight();
		}
		return count;
	}
}
