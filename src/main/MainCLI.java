package main;

import java.util.ArrayList;
import java.util.Scanner;

import utils.MainUtils;
import view.Node;

public class MainCLI implements ToDoPlatform{
	ArrayList<ToDo> toDos=new ArrayList<ToDo>();
	ArrayList<Node> nodes=new ArrayList<Node>();
	int menuInput(Scanner scanf) {
		System.out.println("1. Add To Do");
		System.out.println("2. Count Weight");
		System.out.println("3. Exit");
		
		try {			
			return Integer.parseInt(scanf.nextLine());
		}catch(Exception e) {
			return 0;
		}
	}
	public MainCLI(ArrayList<Node> nodes,ArrayList<ToDo> toDos, Scanner scanf) {
		// TODO Auto-generated constructor stub
		this.nodes=nodes;
		for (ToDo toDo : toDos) {
			nodes.add(new Node(this,toDos, nodes, toDo, "Assets/voucherpage.png"));
		}
		
		
		this.toDos=toDos;
		
		int menu=0;
		
		while(menu!=3) {
			menu=menuInput(scanf);
			switch(menu){
				case 1:
					String titleInput="";
					do {
						System.out.println("Input ToDo Title[1-18]:");
						titleInput=scanf.nextLine();
					}while(titleInput.length()<=0||titleInput.length()>=18);
					String descInput="";
					System.out.println("Input ToDo Description[>0]:");
					descInput=scanf.nextLine();
					if(descInput.length()<=0)descInput="No Description";
					
					int wInput=0;
					do {
						System.out.println("Input ToDo Weight:");
						try {
							wInput=Integer.parseInt(scanf.nextLine());
						} catch (Exception e) {
							// TODO: handle exception
							wInput=-1;
						}
					}while(wInput<=0);
					ToDo temp=new ToDo(titleInput, descInput, wInput);
					toDos.add(temp);
					nodes.add(new Node(this, toDos, nodes, temp, "Assets/voucherpage.png"));
				break;
				case 2:
					System.out.println("Your curr weight is: "+MainUtils.countW(toDos));
				break;
				case 3:
					
				break;
				default: 
					System.out.println("Wrong Input...");
				break;
			}	
		}

		
		
		MainUtils.writeResult(toDos, false);
		
		for (Node node : nodes) {
			node.dispose();
		}
		
		
	}
	public void triggerChange() {
		MainUtils.writeResult(toDos, false);
	}
}
