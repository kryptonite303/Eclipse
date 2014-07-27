package info.johnchen.foodmenu.ver3.dummy;



import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private ArrayList<Food> list;
	private List<String> stringList;
	public Category() {}
	public Category(String s) {
		this.name = s;
		list = new ArrayList<Food>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void add(Food f) {
		list.add(f);
	}
	
	public ArrayList<Food> getList() {
		return list;
	}
	
	public ArrayList<String> getStringList() {
		stringList = new ArrayList<String>();
		for (Food f : list) {
			stringList.add(f.getName() + " (" + f.getPrice() + ")");
		}
		return (ArrayList<String>) stringList;
	}
}
