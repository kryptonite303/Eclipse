package info.johnchen.foodmenu.ver3;

import info.johnchen.foodmenu.ver3.dummy.Category;
import info.johnchen.foodmenu.ver3.dummy.FoodMenu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MenuActivity extends Activity {
	ExpandableListView expListView;
	List<String> groupList;
    Map<String, List<String>> foodCollection;
    FoodMenu fm;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        setContentView(R.layout.layout_menu_main);
        createGroupList();
        createCollection();
        expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        final MyExpandableAdapter expListAdapter = new MyExpandableAdapter(
        		this, groupList, foodCollection);
        expListView.setAdapter(expListAdapter);
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
}
