package info.johnchen.foodmenu.ver3;

import info.johnchen.foodmenu.ver3.dummy.Food;
import info.johnchen.foodmenu.ver3.dummy.Order;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OrderListActivity extends ListActivity {
	
	OrdersDataSource datasource;
	List<Order> orders;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_order_main);
		
		datasource = new OrdersDataSource(this);
		datasource.open();
		orders = datasource.findAll();
		if (orders.size() == 0) {
			createData();
			orders = datasource.findAll();
		}
		refreshDisplay();
		
		/*
		ArrayAdapter<Order> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orders);
		setListAdapter(adapter);
		*/
	}
	
	private void createData() {
		Order order = new Order();
		order.setAddress("123 fake st");
		order.setCreditCardNumber("1234 4444 1111 4200");
		order.setPhoneNumber("7185558923");
		Food food = new Food("soup", "2.30", "1");
		Food food1 = new Food("soup2", "1.32", "2");
		ArrayList<Food> foods = new ArrayList<>();
		foods.add(food1);
		foods.add(food);
		order.setOrder(foods);
		order.setPrice(order.calcualtePrice());
		order = datasource.create(order);
		
		order = new Order();
		order.setAddress("125 fake st");
		order.setCreditCardNumber("1234 4444 1212 4200");
		order.setPhoneNumber("1234567890");
		food = new Food("soup3", "5.35", "3");
		food1 = new Food("soup4", "1.36", "4");
		foods = new ArrayList<>();
		foods.add(food1);
		foods.add(food);
		order.setOrder(foods);
		order.setPrice(order.calcualtePrice());
		order = datasource.create(order);
	}
	
	public void refreshDisplay() {
		
		ArrayAdapter<Order> adapter = new OrderListAdapter(this, orders);
		setListAdapter(adapter);
		
		/*
		ArrayAdapter<Order> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orders);
		setListAdapter(adapter);
		*/
	}
	
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
}
