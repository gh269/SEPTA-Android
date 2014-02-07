/*
 * RealtimeMenuFragment.java
 * Last modified on 02-04-2014 07:53-0500 by brianhmayo
 *
 * Copyright (c) 2014 SEPTA.  All rights reserved.
 */

package org.septa.android.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.septa.android.app.R;
import org.septa.android.app.activities.PlaceholderActionBarActivity;
import org.septa.android.app.activities.SettingsActionBarActivity;

public class RealtimeMenuFragment extends Fragment {
    public static final String TAG = RealtimeMenuFragment.class.getName();

    View.OnClickListener menuItem_OnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            Log.d("RealtimeMenuFragment", "the view clicked is "+v.getId());

            switch(v.getId()) {
                case R.id.splashscreen_icons_gridlayout:
                    //Inform the user the button1 has been clicked
                    Toast.makeText(getActivity(), "1 clicked.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.realtime_menu_icons_gridlayout:
                    //Inform the user the button2 has been clicked
                    Toast.makeText(getActivity(), "2 clicked.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //On click event for button1
    public void clickedthis(View v) {
        //Inform the user the button has been clicked
        Toast.makeText(getActivity(), "yes clicked.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.realtime_menu_fragment, container, false);

        final String[] realtime_menu_icons = getResources().getStringArray(R.array.realtime_menu_icons_inorder);
        final String[] realtime_menu_strings = getResources().getStringArray(R.array.realtime_menu_strings_inorder);
        final String[] realtime_menu_classnames = getResources().getStringArray(R.array.realtime_menu_classnames_inorder);

        GridLayout gridLayout = (GridLayout) view.findViewById(R.id.realtime_menu_icons_gridlayout);

        int position = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                View itemView = inflater.inflate(R.layout.realtime_menu_icontext_item, container, false);

                ImageView imageView = (ImageView)itemView.findViewById(R.id.realtime_menu_icontext_item_imageView);
                TextView textView = (TextView)itemView.findViewById(R.id.realtime_menu_icontext_item_textView);

                GridLayout.LayoutParams param =new GridLayout.LayoutParams();
                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(col);
                param.rowSpec = GridLayout.spec(row);

                itemView.setLayoutParams (param);

                String resourceName = "realtime_menu_".concat(realtime_menu_icons[position].toLowerCase());
                Context context = imageView.getContext();

                int id = getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
                imageView.setImageResource(id);

                try {
                    imageView.setTag(Class.forName(realtime_menu_classnames[position]));
                } catch (ClassNotFoundException cnfe) {
                    Log.e(TAG, "the class with the name "+realtime_menu_classnames[position]+" was not found exception.");
                }

                // since the onClickListener is an inner class
                final String titleText = "| " + realtime_menu_strings[position];
                final String iconText = realtime_menu_icons[position];

                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(final View v) {
                        Log.d("RealtimeMenuFragment", "the view clicked is "+v.getTag());
                        Class intentClass = (Class)v.getTag();
                        Intent intent = new Intent(getActivity(), intentClass);
                        intent.putExtra("titleText", titleText);
                        intent.putExtra("iconText", iconText);
                        startActivity(intent);
                    }
                });

                textView.setText(realtime_menu_strings[position]);

                gridLayout.addView(itemView);

                position++;
            }
        }

        return view;
    }
}