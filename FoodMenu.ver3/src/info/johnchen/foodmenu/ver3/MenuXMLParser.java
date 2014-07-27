package info.johnchen.foodmenu.ver3;


import info.johnchen.foodmenu.ver3.dummy.Category;
import info.johnchen.foodmenu.ver3.dummy.Food;
import info.johnchen.foodmenu.ver3.dummy.FoodMenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources.NotFoundException;

public class MenuXMLParser {
	private Food food;
	private Category category;
	private String currentTag;
	List<Food> foods;
	List<Category> categories;
	FoodMenu fm;

	public FoodMenu parseXML(Context context) {
		fm = new FoodMenu();
		foods = new ArrayList<Food>();
		categories = new ArrayList<Category>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			InputStream stream = context.getResources().openRawResource(R.raw.menu4);
			parser.setInput(stream, null);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (tagname.equalsIgnoreCase("food")) {
						food = new Food();
					} else if (tagname.equalsIgnoreCase("category")) {
						category = new Category(parser.getAttributeValue(null, "name"));
					}
					break;
				case XmlPullParser.TEXT:
					currentTag = parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if (tagname.equalsIgnoreCase("food")) {
						category.add(food);
						foods.add(food);
					} else if (tagname.equalsIgnoreCase("name")){
						food.setName(currentTag);
					} else if (tagname.equalsIgnoreCase("price")) {
						food.setPrice(currentTag);
					} else if (tagname.equalsIgnoreCase("id")) {
						food.setId(currentTag);
					} else if (tagname.equalsIgnoreCase("category")) {
						categories.add(category);
					}
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (NotFoundException e) {
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		fm.setFoods(foods);
		fm.setCategories((ArrayList<Category>) categories);
		return fm;
	}
}
