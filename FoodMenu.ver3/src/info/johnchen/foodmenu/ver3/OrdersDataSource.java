package info.johnchen.foodmenu.ver3;

import java.util.ArrayList;
import java.util.List;

import info.johnchen.foodmenu.ver3.dummy.Order;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrdersDataSource {
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		OrdersDBOpenHelper.COLUMN_ID,
		OrdersDBOpenHelper.COLUMN_PHONE,
		OrdersDBOpenHelper.COLUMN_ADDRESS,
		OrdersDBOpenHelper.COLUMN_CREDITCARD,
		OrdersDBOpenHelper.COLUMN_ORDER,
		OrdersDBOpenHelper.COLUMN_PRICE};
	
	public OrdersDataSource(Context context) {
		dbhelper = new OrdersDBOpenHelper(context);
	}
	
	public void open() {
		database = dbhelper.getWritableDatabase();
	}
	
	public void close() {
		dbhelper.close();
	}
	
	public Order create(Order order) {
		ContentValues values = new ContentValues();
		values.put(OrdersDBOpenHelper.COLUMN_PHONE, order.getPhoneNumber());
		values.put(OrdersDBOpenHelper.COLUMN_ADDRESS, order.getAddress());
		values.put(OrdersDBOpenHelper.COLUMN_CREDITCARD, order.getCreditCardNumber());
		values.put(OrdersDBOpenHelper.COLUMN_ORDER, order.getOrderString());
		values.put(OrdersDBOpenHelper.COLUMN_PRICE, order.getPrice());
		long insertid = database.insert(OrdersDBOpenHelper.TABLE_ORDERS, null, values);
		order.setId(insertid);
		return order;
	}
	
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		Cursor cursor = database.query(OrdersDBOpenHelper.TABLE_ORDERS, allColumns, null, null, null, null, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Order order = new Order();
				order.setId(cursor.getLong(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_ID)));
				order.setPhoneNumber(cursor.getString(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_PHONE)));
				order.setAddress(cursor.getString(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_ADDRESS)));
				order.setCreditCardNumber(cursor.getString(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_CREDITCARD)));
				order.setOrderFromString(cursor.getString(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_ORDER)));				
				order.setPrice(cursor.getString(cursor.getColumnIndex(OrdersDBOpenHelper.COLUMN_PRICE)));
				orders.add(order);
			}
		}
		return orders;
	}
}
