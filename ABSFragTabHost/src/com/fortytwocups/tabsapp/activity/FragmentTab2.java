package com.fortytwocups.tabsapp.activity;

import com.actionbarsherlock.app.SherlockFragment;
import com.androidbegin.absfragtabhost.R;
import com.fortytwocups.tabsapp.model.UserCard;
import com.fortytwocups.tabsapp.preferences.Preferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentTab2 extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragmenttab2, container, false);
		initTextWithUser(rootView, Preferences.getInstance().mCurrentUser);
		return rootView;
	}
	
	private void initTextWithUser(View rootView, UserCard userInfo) {
		if (userInfo == null) {
			return;
		}
		TextView textField = (TextView) rootView.findViewById(R.id.textPhone);
		textField.setText(userInfo.mPhone);
		textField = (TextView) rootView.findViewById(R.id.textEmail);
		textField.setText(userInfo.mEmail);
		textField = (TextView) rootView.findViewById(R.id.textAddress);
		textField.setText(userInfo.mAddress);		
	}

}
