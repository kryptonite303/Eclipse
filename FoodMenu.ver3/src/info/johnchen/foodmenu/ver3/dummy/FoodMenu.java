package info.johnchen.foodmenu.ver3.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodMenu {
	List<Food> foods;
	ArrayList<Category> categories;
	HashMap<String, Food> menu;
	
	public FoodMenu() {
		foods = new ArrayList<Food>();
		categories = new ArrayList<Category>();
		menu = new HashMap<>();
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
		for (Food food : foods) {
			menu.put(food.getName() + " (" + food.getPrice() + ")", food);
		}
	}
	public ArrayList<Category> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	public void addFood(Food f) {
		foods.add(f);
	}
	public HashMap<String, Food> getMenu() {
		return menu;
	}
}
