package info.johnchen.foodmenu.ver3;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import info.johnchen.foodmenu.ver3.dummy.Food;
import info.johnchen.foodmenu.ver3.dummy.Order;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewOrderActivity extends Activity {
	Order order;
	ArrayList<Food> o = new ArrayList<Food>();
	private static int REQUEST_CODE = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_new_order);
		EditText phoneNumberText = (EditText) findViewById(R.id.edit_phone_number);
		final String phoneNumber = phoneNumberText.getText().toString();
		EditText addressText = (EditText) findViewById(R.id.edit_address);
		final String address = addressText.getText().toString();
		EditText creditCardText = (EditText) findViewById(R.id.edit_credit_card);
		final String creditCard = creditCardText.getText().toString();
		
		Button editOrder = (Button) findViewById(R.id.edit_order_button);
		editOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NewOrderActivity.this, NewOrder.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("key", o);
				intent.putExtras(bundle);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		Button createOrder = (Button) findViewById(R.id.create_order_button);
		createOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				order = new Order(phoneNumber, address, creditCard, o);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			o = (ArrayList<Food>) bundle.getSerializable("key");
			LinearLayout linearLayout = (LinearLayout) findViewById(R.id.show_order_linear_layout);
			refreshOrders(linearLayout);
			refreshPrice();
			Toast.makeText(NewOrderActivity.this, "Order Saved", Toast.LENGTH_LONG).show();		
		}
	}
	
	private void refreshOrders(final LinearLayout l) {
		if (!o.isEmpty()) {
			for (final Food f : o) {
				Button button = new Button(NewOrderActivity.this);
				button.setText("(" + f.getId() + ") " + f.getName() + " ["
						+ f.getPrice() + "]");
				l.addView(button);
				button.setOnClickListener(new OnClickListener() {
					public void onClick(final View v) {
						new AlertDialog.Builder(NewOrderActivity.this)
						.setTitle("Remove Food?")
						.setMessage("Are you sure you want to remove this item?")
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								l.removeView(v);
								o.remove(f);
								refreshPrice();
							}
						})
						.show();
					}
				});
			}	
		}
	}
	
	private void refreshPrice() {
		TextView orderPrice = (TextView) findViewById(R.id.price_view);
		double p = 0;
		for (Food f : o) {
			p += Double.parseDouble(f.getPrice());
		}
		p = p * 1.08875;
		NumberFormat nf = new DecimalFormat("#.00");
		if (p == 0) {
			orderPrice.setText("Order empty");
			return;
		}
		orderPrice.setText(String.valueOf(nf.format(p)));
	}
}
