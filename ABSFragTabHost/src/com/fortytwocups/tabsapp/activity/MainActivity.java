package com.fortytwocups.tabsapp.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.androidbegin.absfragtabhost.R;
import com.fortytwocups.tabsapp.db.DBAdapter;
import com.fortytwocups.tabsapp.model.UserCard;

import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.os.Bundle;

public class MainActivity extends SherlockFragmentActivity {
	// Declare Variables	
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set the view from main_fragment.xml
		setContentView(R.layout.main_fragment);
		
		// Locate android.R.id.tabhost in main_fragment.xml
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		
		// Create the tabs in main_fragment.xml
		mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);

		// Create Tab1 with a custom image in res folder
		//mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(R.drawable.tab1)),
		//		FragmentTab1.class, null);
		
		// Create Tab2
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(getResources().getString(R.string.Tab1Title)),
				FragmentTab1.class, null);
		
		// Create Tab3
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(getResources().getString(R.string.Tab2Title)),
				FragmentTab2.class, null);
		UserCard dbUserInfo;
		try {
			UserCard user = new UserCard();
			user.mName = "Yuriy";
			user.mSurname = "Maliy";
			user.mBio = "I was born in the year 1632, in the city of York, of a good family, though not of that country, my father being a foreigner of Bremen, who settled first at Hull.";
			user.mAddress = "Ukraine, Chernihiv, Chervonogvardiiska st. 14/36";
			user.mEmail = "yuriy_maliy@mail.ru";
			user.mPhone = "+380937438411";
			
			DBAdapter db = new DBAdapter(this);
			db.openToWrite();
			db.deleteAll();
			//db.insert("Test string");
			db.insert(user);
			db.close();
			db = new DBAdapter(this);
			db.openToRead();
			dbUserInfo = db.getUserByName(user.mName);
			Log.d("Main Activity", "Got text from DB:" + dbUserInfo.mBio );
			db.close();
		} catch (Exception ex) {
			Log.d("Main Activity", "Error working with database!");
			ex.printStackTrace();
		}
	}
}
