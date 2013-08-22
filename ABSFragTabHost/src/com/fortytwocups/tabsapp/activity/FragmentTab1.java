package com.fortytwocups.tabsapp.activity;

import com.actionbarsherlock.app.SherlockFragment;
import com.androidbegin.absfragtabhost.R;
import com.fortytwocups.tabsapp.db.DBAdapter;
import com.fortytwocups.tabsapp.model.UserCard;
import com.fortytwocups.tabsapp.preferences.Preferences;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentTab1 extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);
		
		initTextWithUser(rootView, Preferences.getInstance().mCurrentUser);
		
		
		return rootView;
	}
	
	private void initTextWithUser(View rootView, UserCard userInfo) {
		if (userInfo == null) {
			return;
		}
		TextView textField = (TextView) rootView.findViewById(R.id.textViewName);
		textField.setText(userInfo.mName);
		textField = (TextView) rootView.findViewById(R.id.textViewSurname);
		textField.setText(userInfo.mSurname);
		textField = (TextView) rootView.findViewById(R.id.textViewBio);
		textField.setText(userInfo.mBio);		
	}

}
