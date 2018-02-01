package com.funnytoday.ddoniddoni.seoulzerobottle.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnytoday.ddoniddoni.seoulzerobottle.ExpanPosition;
import com.funnytoday.ddoniddoni.seoulzerobottle.R;

import java.util.ArrayList;

/**
 * Created by Owner on 2017-09-18.
 */

public class ExpanListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<ExpanPosition> position;
    private LayoutInflater inflater;

    public ExpanListAdapter(Context mContext, ArrayList<ExpanPosition> position) {

        this.mContext = mContext;
        this.position = position;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return position.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return position.get(groupPosition).items.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return position.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return position.get(groupPosition).items.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = inflater.inflate(R.layout.parent_list, null);
        }

        ExpanPosition position = (ExpanPosition) getGroup(groupPosition);

        String positionName = position.position;

        TextView textView = (TextView) convertView.findViewById(R.id.parent_text);
        textView.setText(positionName);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.parent_image);
        if(isExpanded){
            imageView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
        }
        else{
            imageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
        convertView.setBackgroundColor(Color.parseColor("#a1c4fd"));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_list, null);
        }

        String child = (String) getChild(groupPosition, childPosition);

        //set the child name
        TextView name = (TextView) convertView.findViewById(R.id.child_text);
        //get the imageView
        ImageView img = (ImageView) convertView.findViewById(R.id.child_image);

        name.setText(child);

        //get position name
        String positionName = (String) getGroup(groupPosition).toString();

        return convertView;
    }




    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
