package info.johnchen.foodmenu.ver3.dummy;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Order implements Serializable {
	private long id;
	private String phoneNumber;
	private String address;
	private String creditCardNumber;
	private ArrayList<Food> order = new ArrayList<>();
	private String price;
	
	public Order(String p, String a, String c, ArrayList<Food> o) {
		this.phoneNumber = p;
		this.address = a;
		this.creditCardNumber = c;
		this.order = o;
		this.price = calcualtePrice();
	}
	public Order() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public ArrayList<Food> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Food> order) {
		this.order = order;
	}
	
	
	public void setPrice(String p) {
		this.price = p;
	}
	
	public String calcualtePrice() {
		double p = 0;
		for (Food f : order) {
			p = p + Double.parseDouble(f.getPrice());
		}
		String s = String.valueOf(p);
		return s;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	
	public void addFood(Food f) {
		order.add(f);
	}
	
	@Override
	public String toString() {
		String s = String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), 
		          phoneNumber.substring(6, 10));
		return s + " | " + getAddress() + " | " + "$" + getPrice();
	}
	
	public String getOrderString() {
		String s = "";
		for (Food food : order) {
			s = s.concat("+"+food.toDBString()+"-");
			//s = s + "+" + food.toDBString() + "-";
		}
		return s;
	}
	
	public void setOrderFromString(String s) {
		int pos = 0;
		String id = "";
		String name = "";
		String price = "";
		Food food;
		for (char c : s.toCharArray()) {
			if (c == '+') {
				pos = 1;
				id = "";
				name = "";
				price = "";
			} else if (c == ',') {
				pos++;
			} else if (c == '-') {
				food = new Food(name, price, id);
				order.add(food);
				pos = 0;
			} else if (pos == 1 && c!= ',' && c != '+' && c != '-') {
				id = id + c;
			} else if (pos == 2 && c!= ',' && c != '+' && c != '-') {
				name = name + c;
			} else if (pos == 3 && c!= ',' && c != '+' && c != '-') {
				price = price + c;
			}
		}
	}
}
