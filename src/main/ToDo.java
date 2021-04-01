package main;

public class ToDo {
	private String title;
	private int weight;
	private String description;
	public ToDo(String title, String description, int weight) {
		// TODO Auto-generated constructor stub
		this.description=description;
		this.title=title;
		this.weight=weight;
	}
	public String getTitle() {
		return this.title;
	}
	public String getDesc() {
		return this.description;
	}
	
	public int getWeight() {
		return this.weight;
	}
}
