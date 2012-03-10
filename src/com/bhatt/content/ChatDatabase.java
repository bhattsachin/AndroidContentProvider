package com.bhatt.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabase extends SQLiteOpenHelper {

	// private static final String DBNAME = "ChatDB";
	private static final String SQL_CREATE_DB = "CREATE TABLE "
			+ ChatContentProvider.TABLE_NAME
			+ " "
			+ // Table's name
			"(" + MyContents.MyContent.ID
			+ " integer primary key autoincrement, "
			+ // The columns in the table
			MyContents.MyContent.KEY + " text, " + MyContents.MyContent.VALUE
			+ " text)";

	public ChatDatabase(Context context, String dbname) {
		super(context, dbname, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(SQL_CREATE_DB);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.d("Table", "Table already exists");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
