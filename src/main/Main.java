package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.UIManager;

import utils.MainUtils;
import view.Desc;
import view.Node;

public class Main {
	
	
	
	public Main() {
		// TODO Auto-generated constructor stub
		ArrayList<ToDo> toDos= new ArrayList<ToDo>();
		MainUtils.readFile(toDos, "./saveFile.txt");
		String input="";
		Scanner scanf=new Scanner(System.in);
		ArrayList<Node> nodes=new ArrayList<Node>();
	
		
		do {
			System.out.println("Use GUI ?");
			input=scanf.nextLine();
		}while(!input.equalsIgnoreCase("yes")&&!input.equalsIgnoreCase("y")&&!input.equalsIgnoreCase("no")&&!input.equalsIgnoreCase("n"));
		if(input.equalsIgnoreCase("yes")||input.equalsIgnoreCase("y")) {
			new MainForm(toDos,nodes);
		}
		else {
			new MainCLI(nodes,toDos,scanf);
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
