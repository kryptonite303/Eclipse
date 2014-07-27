package info.johnchen.foodmenu.ver3;

import info.johnchen.foodmenu.ver3.dummy.Order;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderListAdapter extends ArrayAdapter<Order> {
	Context context;
	List<Order>	orders;
	
	public OrderListAdapter(Context context, List<Order> orders) {
		super(context, android.R.id.content, orders);
		this.context = context;
		this.orders = orders;
	}
	
	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = vi.inflate(R.layout.layout_listitem_order, null);
		
		Order order = orders.get(position);
		
		TextView tv = (TextView) view.findViewById(R.id.phoneText);
		tv.setText(order.getPhoneNumber());
		
		tv = (TextView) view.findViewById(R.id.addressText);
		tv.setText(order.getAddress());
		
		tv = (TextView) view.findViewById(R.id.addressText);
		tv.setText(order.getAddress());

		tv = (TextView) view.findViewById(R.id.creditText);
		tv.setText(order.getCreditCardNumber());
		
		tv = (TextView) view.findViewById(R.id.orderText);
		tv.setText(order.getOrderString());
				
		tv = (TextView) view.findViewById(R.id.priceText);
		tv.setText(order.getPrice());
		
		return view;
	}

}
