package com.fortytwocups.tabsapp.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.androidbegin.absfragtabhost.R;
import com.fortytwocups.tabsapp.db.DBAdapter;
import com.fortytwocups.tabsapp.model.UserCard;
import com.fortytwocups.tabsapp.preferences.Preferences;

import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Bundle;

public class MainActivity extends SherlockFragmentActivity {
	// Declare Variables	
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the view from main_fragment.xml
		setContentView(R.layout.main_fragment);
		
		if (Preferences.getInstance().initUserInfo(this)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Error");
			builder.setMessage("Error while initializing user info from database!");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();
		}
		
		// Locate android.R.id.tabhost in main_fragment.xml
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		
		// Create the tabs in main_fragment.xml
		mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);

		// Create Tab1 with a custom image in res folder
		//mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(R.drawable.tab1)),
		//		FragmentTab1.class, null);
		
		// Create Tab2
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(getResources().getString(R.string.Tab2Title)),
				FragmentTab1.class, null);
		
		// Create Tab3
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(getResources().getString(R.string.Tab1Title)),
				FragmentTab2.class, null);		
	}
	
}
