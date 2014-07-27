package info.johnchen.foodmenu.ver3;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
	private Activity context;
    private Map<String, List<String>> categoryCollections;
    private List<String> categories;
 
    public MyExpandableAdapter(Activity context, List<String> categories,
            Map<String, List<String>> categoryCollections) {
        this.context = context;
        this.categoryCollections = categoryCollections;
        this.categories = categories;
    }
 
    public Object getChild(int groupPosition, int childPosition) {
        return categoryCollections.get(categories.get(groupPosition)).get(childPosition);
    }
 
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
     
     
    public View getChildView(final int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        final String category = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
         
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_menu_child, null);
        }
         
        TextView item = (TextView) convertView.findViewById(R.id.category);
         
        item.setText(category);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return categoryCollections.get(categories.get(groupPosition)).size();
    }
 
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }
 
    public int getGroupCount() {
        return categories.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String categoryName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_menu_group,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.category);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(categoryName);
        return convertView;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
