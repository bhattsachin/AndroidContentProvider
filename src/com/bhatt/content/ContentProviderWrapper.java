package com.bhatt.content;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

public class ContentProviderWrapper {

	private static ContentProviderWrapper instance = new ContentProviderWrapper();

	private ContentProviderWrapper() {
	};

	public static ContentProviderWrapper getInstance() {
		return instance;
	}

	public void addNewMessage(ContentResolver contentResolver, String key,
			String value) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(MyContents.MyContent.KEY, key);
		contentValues.put(MyContents.MyContent.VALUE, value);
		contentResolver.insert(MyContents.MyContent.CONTENT_URI, contentValues);

	}

	public boolean isMessagePresent(ContentResolver contentResolver, String key) {
		boolean present = false;
		String projection[] = new String[] { MyContents.MyContent.ID,
				MyContents.MyContent.KEY, MyContents.MyContent.VALUE };
		Cursor cursor = contentResolver.query(MyContents.MyContent.CONTENT_URI,
				projection, MyContents.MyContent.KEY + "='" + key + "'", null,
				null);
		if (null != cursor && cursor.moveToNext()) {
			present = true;
		}
		return present;

	}

}
