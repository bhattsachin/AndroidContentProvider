package com.bhatt.content;

import android.net.Uri;
import android.provider.BaseColumns;

public class MyContents {

	public MyContents() {
	}

	public static final class MyContent implements BaseColumns {
		private MyContent() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ ChatContentProvider.AUTHORITY + "/messages");
		public static final String ID = "_id";

		public static final String KEY = "_key";

		public static final String VALUE = "_value";
	}

}
