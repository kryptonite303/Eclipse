package info.johnchen.foodmenu.ver3.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CustomerContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<CustomerItem> ITEMS = new ArrayList<CustomerItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, CustomerItem> ITEM_MAP = new HashMap<String, CustomerItem>();

	static {
		// Add 3 sample items.
		addItem(new CustomerItem("718-727-8888", "838 Bay St", "4207-6700-0629-2966", "Large Chicken and Broccoli", "10"));
		addItem(new CustomerItem("718-727-7286", "993 Bay St", "1234-1234-1234-1234", "Small Chicken and Broccoli", "11"));
		addItem(new CustomerItem("718-876-9651", "289 Deal Ct", "1111-2222-3333-4444", "Steamed Chicken and Broccoli", "12"));
	}

	private static void addItem(CustomerItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.phoneNumber, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class CustomerItem {
		public String phoneNumber;
		public String address;
		public String creditCard;
		public String order;
		public String price;

		public CustomerItem(String phoneNumber, String address, String creditCard, String order, String price) {
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.creditCard = creditCard;
			this.order = order;
			this.price = price;
		}

		@Override
		public String toString() {
			return phoneNumber;
		}
	}
}
