package com.example.moohn.contactsactivity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView contactview = (TextView) findViewById(R.id.contactview);

        Cursor cursor = getAllContact();

        while (cursor.moveToNext()){
           String displayname = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
           contactview.append("Name : ");
            contactview.append(displayname);
            contactview.append("\n");
        }
    }

    private Cursor getAllContact(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + "= '" + ("1") + "'";
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return getContentResolver().query(uri, projection, selection, null, sortOrder);
    }
}
