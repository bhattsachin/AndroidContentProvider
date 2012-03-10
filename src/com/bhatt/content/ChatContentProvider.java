package com.bhatt.content;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ChatContentProvider extends ContentProvider {
	
	private ChatDatabase dbHelper;
	public static String AUTHORITY = "edu.buffalo.cse586.provider";
    //public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/messages");

	
	// Defines the database name
    private static final String DATABASE_NAME = "SampleDB";
    
    public static final String TABLE_NAME = "SampleTable";
    
    //creates map of all columns
    private static final HashMap<String,String> mColumnMap = buildColumnMap();
   

    // Holds the database object
    private SQLiteDatabase db;

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long row = db.insert(
				TABLE_NAME, "", values);
				
				if (row>0)
				{
				Uri _uri = ContentUris.withAppendedId(MyContents.MyContent.CONTENT_URI, row);
				getContext().getContentResolver().notifyChange(_uri, null);
				return _uri;
				}
				throw new SQLException("Failed to insert row into " + uri);
				}

	

	@Override
	public boolean onCreate() {
		dbHelper = new ChatDatabase(getContext(), DATABASE_NAME); 
		db = dbHelper.getWritableDatabase();
		dbHelper.onCreate(db);
		
		return true;
	}
	
	 private static HashMap<String,String> buildColumnMap() {
	        HashMap<String,String> map = new HashMap<String,String>();
	       map.put(MyContents.MyContent.ID,MyContents.MyContent.ID);
	       map.put(MyContents.MyContent.KEY,MyContents.MyContent.KEY);
	       map.put(MyContents.MyContent.VALUE,MyContents.MyContent.VALUE);
	       
	       
	        return map;
	    }

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
		
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME);
        builder.setProjectionMap(mColumnMap);

        Cursor cursor = builder.query(dbHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		throw new UnsupportedOperationException();
	}

}
