package info.johnchen.foodmenu.ver3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		setUpButtons();
	}
	
	private void setUpButtons() {
		Button customerList = (Button) findViewById(R.id.customer_list_button);
		customerList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CustomerListActivity.class);
				startActivity(intent);
			}
		});
		
		Button newOrder = (Button) findViewById(R.id.new_order_button);
		newOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NewOrderActivity.class);
				startActivity(intent);
			}
		});
		
		Button menu = (Button) findViewById(R.id.menu_button);
		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
		
		Button orderList = (Button) findViewById(R.id.orders_button);
		orderList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
				startActivity(intent);
			}
		});
	}
}
