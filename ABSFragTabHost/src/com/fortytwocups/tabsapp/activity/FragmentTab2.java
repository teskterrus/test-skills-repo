package com.fortytwocups.tabsapp.activity;

import com.actionbarsherlock.app.SherlockFragment;
import com.androidbegin.absfragtabhost.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentTab2 extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragmenttab2, container, false);
		return rootView;
	}

}
