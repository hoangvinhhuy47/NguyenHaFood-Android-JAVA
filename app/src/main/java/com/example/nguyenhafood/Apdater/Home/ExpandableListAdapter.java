package com.example.nguyenhafood.Apdater.Home;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nguyenhafood.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter  extends BaseExpandableListAdapter {

    private Context context;
    private List<String> ParentItem;
    private HashMap<String, List<String>> ChildItem;


    public ExpandableListAdapter(Context context, List<String> ParentItem,
                                 HashMap<String, List<String>> ChildItem) {
        this.context = context;
        this.ParentItem = ParentItem;
        this.ChildItem = ChildItem;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_evaluatenguyenhafood_view, null);
        }
        RatingBar RatingBar = (RatingBar) convertView.findViewById(R.id.raiting);
        Button btn1 = (Button) convertView.findViewById(R.id.btnd_danhgia);
        Button btn2 = (Button) convertView.findViewById(R.id.btn_gui);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.ParentItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ParentItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_nameevaluationnguyenhafood, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.textdanhgia);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }


    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> ParentItem = new HashMap<String, List<String>>();

        List<String> Colors = new ArrayList<String>();


        List<String> Animals = new ArrayList<String>();

        List<String> Sports = new ArrayList<String>();



        ParentItem.put("Về chất lượng sản phẩm", Colors);
        ParentItem.put("Về đội ngũ giao hàng", Animals);
        ParentItem.put("Về APP NguyenHaFood", Sports);

        return ParentItem;



    }
}
