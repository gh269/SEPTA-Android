/*
 * SettingsListFragment.java
 * Last modified on 02-10-2014 17:19-0500 by brianhmayo
 *
 * Copyright (c) 2014 SEPTA.  All rights reserved.
 */

package org.septa.android.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.septa.android.app.R;
import org.septa.android.app.adapters.Settings_ListViewItem_ArrayAdapter;
import org.septa.android.app.models.adapterhelpers.IconTextPendingIntentModel;

public class SettingsListFragment extends ListFragment {
    private static final String TAG = SettingsListFragment.class.getName();

    public static Handler mainActivityHandler;
    IconTextPendingIntentModel[] _values;
    ArrayAdapter<IconTextPendingIntentModel> _adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set the divider to null in order to allow the gradient to work
        getListView().setDivider(null);
        getListView().setPadding(0,5,0,5);
        getListView().setDividerHeight(5);

        // programmatically set the background to the main background
//        getListView().setBackgroundResource(R.drawable.main_background);

        int settingsListViewItemCount = getResources().getStringArray(R.array.settings_listview_items_texts).length;
        IconTextPendingIntentModel[] values = new IconTextPendingIntentModel[settingsListViewItemCount];
        for (int i = 0; i < settingsListViewItemCount; i++) {
            String text = getResources().getStringArray(R.array.settings_listview_items_texts)[i];
            boolean enabled = true;

            String icon_ImageBase = getResources().getString(R.string.settings_icon_imageBase);
            String icon_ImageSuffix = getResources().getStringArray(R.array.settings_listview_items_iconSuffixes)[i];
            String url_toLoad = getResources().getStringArray(R.array.settings_listview_items_urls)[i];

            IconTextPendingIntentModel iconTextPendingIntentModel = new IconTextPendingIntentModel(text,
                    icon_ImageBase, icon_ImageSuffix, url_toLoad, enabled);

            values[i] = iconTextPendingIntentModel;
        }

        this._values = values;

        _adapter = new Settings_ListViewItem_ArrayAdapter(getActivity(), values);

        setListAdapter(_adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // now that we have returned from the global settings activity,
        //  notify a data set change in case the user changed the setting
        _adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FragmentTransaction fragmentTransaction;

        switch(position) {
            case 0:
                Log.d(TAG, "transition the user to the system setting to update");
                startActivityForResult(new Intent(Settings.ACTION_DATE_SETTINGS), 2332);

                break;

            case 1:
                Log.d(TAG, "transition the user to the update activity");

                break;

            default:
                Log.d(TAG, "launch default");

                break;
        }
    }
}
