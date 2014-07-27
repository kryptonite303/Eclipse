package info.johnchen.foodmenu.ver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrdersDBOpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "orders.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_ORDERS = "orders";
	public static final String COLUMN_ID = "orderId";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CREDITCARD = "creditCard";
	public static final String COLUMN_ORDER = "foodOrder";
	public static final String COLUMN_PRICE = "price";
	
	public static final String TABLE_CREATE = 
			"CREATE TABLE " + TABLE_ORDERS + " (" + 
			COLUMN_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			COLUMN_PHONE      + " TEXT, " + 
			COLUMN_ADDRESS    + " TEXT, " + 
			COLUMN_CREDITCARD + " TEXT, " +
			COLUMN_ORDER      + " TEXT, " +
			COLUMN_PRICE      + " TEXT " +
			")";
	
	
	public OrdersDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
		onCreate(db);
	}

}
