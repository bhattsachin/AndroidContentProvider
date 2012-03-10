package com.bhatt.content;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.util.Log;

public class AndroidContentProviderActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ContentResolver cr = getContentResolver();
        
        ContentProviderWrapper wrap = ContentProviderWrapper.getInstance();
        wrap.addNewMessage(cr, "hash1", "value1");
        wrap.addNewMessage(cr, "hash2", "value2");
        
        boolean exists = wrap.isMessagePresent(cr, "hash1");
        Log.d("Exists???", "" + exists);
        
        
    }
}