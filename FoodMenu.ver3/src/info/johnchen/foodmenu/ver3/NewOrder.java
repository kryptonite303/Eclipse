package info.johnchen.foodmenu.ver3;

import info.johnchen.foodmenu.ver3.dummy.Category;
import info.johnchen.foodmenu.ver3.dummy.Food;
import info.johnchen.foodmenu.ver3.dummy.FoodMenu;
import info.johnchen.foodmenu.ver3.dummy.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

/*
 * This class is for the new order menu. Displays the whole menu and can select foods to make orders
 * */
@SuppressWarnings("serial")
public class NewOrder extends Activity implements Serializable {
	ExpandableListView expListView;
	List<String> groupList;
    Map<String, List<String>> foodCollection;
    FoodMenu fm;
    Intent intent;
    ArrayList<Food> o;
    Order order;
    
    @SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_main);
        createGroupList();
        createCollection();
        Bundle bundle = getIntent().getExtras();
		o = (ArrayList<Food>) bundle.getSerializable("key");
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        final MyExpandableAdapter expListAdapter = new MyExpandableAdapter(
        		this, groupList, foodCollection);
        expListView.setAdapter(expListAdapter);
        final HashMap<String, Food> menu = fm.getMenu();
        expListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
            	o.add(menu.get(expListAdapter.getChild(groupPosition, childPosition)));
            	Toast.makeText(NewOrder.this, menu.get(expListAdapter.getChild(groupPosition, childPosition)).toString() + " Added", Toast.LENGTH_SHORT).show();
            	return true;
            }
        });
        
        
    }
	
    // Categories
	private void createGroupList() {
    	MenuXMLParser parser = new MenuXMLParser();
		fm = new FoodMenu();
		fm = parser.parseXML(this);
        groupList = new ArrayList<String>();
		ArrayList<Category> categories = fm.getCategories();
		for (Category c : categories) {
			groupList.add(c.getName());
		}
    }
	
	// Food lists part of the categories
	private void createCollection() {
    	MenuXMLParser parser = new MenuXMLParser();
		fm = new FoodMenu();
		fm = parser.parseXML(this);
		foodCollection = new LinkedHashMap<String, List<String>>();
		for (Category c : fm.getCategories()) {
			foodCollection.put(c.getName(), c.getStringList());
		}
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.new_order_action_bar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_return_to_new_order:
	            Intent resultIntent = new Intent();
	            Bundle bundle = new Bundle();
	            bundle.putSerializable("key", o);
	            resultIntent.putExtras(bundle);
	            setResult(RESULT_OK, resultIntent);
	            finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
