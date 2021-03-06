/*
 * Schedules_ListFragment_ArrayAdapter.java
 * Last modified on 05-05-2014 16:12-0400 by brianhmayo
 *
 * Copyright (c) 2014 SEPTA.  All rights reserved.
 */

package org.septa.android.app.adapters.schedules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.septa.android.app.R;

public class Schedules_ListFragment_ArrayAdapter  extends ArrayAdapter<String> {
    private static final String TAG = Schedules_ListFragment_ArrayAdapter.class.getName();

    private final Context context;
    private final String[] values;

    String[] resourceEndNames;
    String leftImageStartName;
    String rightImageBackgroundName;


    public Schedules_ListFragment_ArrayAdapter(Context context, String[] values) {
        super(context, R.layout.schedules_listfragment_item, values);
        this.context = context;
        this.values = values;

        resourceEndNames = context.getResources().getStringArray(R.array.schedulesfragment_listview_bothimage_endnames);
        leftImageStartName = context.getString(R.string.schedulesfragment_listview_leftimage_startname);
        rightImageBackgroundName = context.getString(R.string.schedulesfragment_listview_rightimage_startname);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TODO: this does not seem right; why am I inflating this again here when super should be taking care of it.
        View rowView = inflater.inflate(R.layout.schedules_listfragment_item, parent, false);

        ImageView leftImageView = (ImageView) rowView.findViewById(R.id.schedules_listfragment_item_leftImageView);
        ImageView rightImageBackgroundView = (ImageView) rowView.findViewById(R.id.schedules_listfragment_item_rightImageBackgroundview);
        TextView textView = (TextView) rowView.findViewById(R.id.schedules_listfragment_item_rightTextView);

        // in order to manage the route types number in the SQLlite database, we need to reorder the values and thus use this lookup.
        int listPositionValue = 0;
        switch (position) {
            case 0: {
                listPositionValue = 2;
                break;
            }
            case 1: {
                listPositionValue = 1;
                break;
            }
            case 2: {
                listPositionValue = 4;
                break;
            }
            case 3: {
                listPositionValue = 0;
                break;
            }
            case 4: {
                listPositionValue = 5;
                break;
            }
            case 5: {
                listPositionValue = 3;
                break;
            }
            default: {
                listPositionValue = 0;
                break;
            }
        }

        int id = context.getResources().getIdentifier(leftImageStartName + resourceEndNames[listPositionValue], "drawable", context.getPackageName());
        leftImageView.setImageResource(id);

        id = context.getResources().getIdentifier(rightImageBackgroundName + resourceEndNames[listPositionValue], "drawable", context.getPackageName());
        rightImageBackgroundView.setImageResource(id);

        textView.setText(values[position]);

        return rowView;
    }
}