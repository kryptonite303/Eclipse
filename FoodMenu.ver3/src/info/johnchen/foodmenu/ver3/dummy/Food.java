package info.johnchen.foodmenu.ver3.dummy;

import java.io.Serializable;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class Food implements Serializable {
	private String name;
	private String price;
	private String id;
	
	public Food(String n, String p, String i) {
		this.name = n;
		this.price = p;
		this.id = i;
	}
	
	public Food() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		double d = Double.parseDouble(price);
		DecimalFormat df = new DecimalFormat("#.00");
		String s = df.format(d);
		this.price = s;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.name;
	}
	public String toDBString() {
		return id + "," + name + "," + price;
	}
}
