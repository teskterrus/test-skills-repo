package com.fortytwocups.tabsapp.db;


import com.fortytwocups.tabsapp.model.UserCard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBAdapter {

	public static final String MYDATABASE_NAME = "DATABASE_TAB_APP";
	public static final String MYDATABASE_TABLE = "TABLE_USER_INFO";
	public static final int MYDATABASE_VERSION = 1;
	public static final String COL_ID = "userId";
	public static final String COL_NAME = "Name";
	public static final String COL_SURNAME = "Surname";
	public static final String COL_BIO = "Bio";
	public static final String COL_ADDRESS = "Adress";
	public static final String COL_EMAIL = "Email";
	public static final String COL_PHONE = "Phone";	
	
	//create table MY_DATABASE (ID integer primary key, Content text not null);
	private static final String SCRIPT_CREATE_DATABASE =
			"create table " + MYDATABASE_TABLE + " (" +
			COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " text not null," +
			COL_SURNAME + " text," +
			COL_BIO + " text," +
			COL_ADDRESS + " text," +
			COL_EMAIL + " text," +
			COL_PHONE + " text" + ");";
 
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;
 
 public DBAdapter(Context c){
	 context = c;
 }
 
 public DBAdapter openToRead() throws android.database.SQLException {
	 sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	 sqLiteDatabase = sqLiteHelper.getReadableDatabase();
	 return this; 
 }
 
 public DBAdapter openToWrite() throws android.database.SQLException {
	 sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
	 sqLiteDatabase = sqLiteHelper.getWritableDatabase();
	 return this; 
 }
 
 public void close(){
	 sqLiteDatabase.close();
	 sqLiteHelper.close();
 }
 
public long insert(String content){
	 ContentValues contentValues = new ContentValues();
	 contentValues.put(COL_NAME, content);
	 return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
}

public long insert(UserCard content){
	 ContentValues contentValues = new ContentValues();
	 contentValues.put(COL_NAME, content.mName);
	 contentValues.put(COL_SURNAME, content.mSurname);
	 contentValues.put(COL_BIO, content.mBio);
	 contentValues.put(COL_ADDRESS, content.mAddress);
	 contentValues.put(COL_EMAIL, content.mEmail);
	 contentValues.put(COL_PHONE, content.mPhone);
	 
	 return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
}
 
 public int deleteAll(){
	 return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
 }
 
 public String queueAll(){
	 String[] columns = new String[]{COL_NAME};
	 Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, 
			 null, null, null, null, null);
	 String result = "";
	 int index_CONTENT = cursor.getColumnIndex(COL_NAME);
	 for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
		 result = result + cursor.getString(index_CONTENT) + "\n";
	 }
	 cursor.close();
	 return result;
}
	 
 public UserCard getUserByName(String name){
	 String[] columns = new String[]{COL_NAME, COL_SURNAME, COL_BIO, COL_ADDRESS, COL_EMAIL, COL_PHONE};
	 Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, 
			 null, null, null, null, null);
	 UserCard result = new UserCard();
	 int index_CONTENT = cursor.getColumnIndex(COL_NAME);
	 for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
		 if (cursor.getString(index_CONTENT).compareTo(name) == 0) {
			 result.mName = cursor.getString(index_CONTENT);
			 result.mSurname = cursor.getString(index_CONTENT+1);
			 result.mBio = cursor.getString(index_CONTENT+2);
			 result.mAddress = cursor.getString(index_CONTENT+3);
			 result.mEmail = cursor.getString(index_CONTENT+4);
			 result.mPhone = cursor.getString(index_CONTENT+5);
			 cursor.close();
			 return result;
		 }			 
	 
	 }
	 cursor.close();
  return null;
 }
 
 public class SQLiteHelper extends SQLiteOpenHelper {

  public SQLiteHelper(Context context, String name,
    CursorFactory factory, int version) {
   super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
   // TODO Auto-generated method stub
   db.execSQL(SCRIPT_CREATE_DATABASE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   // TODO Auto-generated method stub

  }

 }
 
}