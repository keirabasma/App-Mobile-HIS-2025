package com.example.contact;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DetailActivity extends Activity {
    String contactId;
    EditText edtPhone;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        contactId = getIntent().getStringExtra("contactId");
        edtPhone = findViewById(R.id.edit_phone);
        imgPhoto = findViewById(R.id.contact_photo);
        Button btnSave = findViewById(R.id.button_save);

        loadDetails();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhone();
            }
        });
    }

    private void loadDetails() {

        Cursor pCur = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{contactId}, null);
        if (pCur != null && pCur.moveToFirst()) {
            String phone = pCur.getString(
                    pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            edtPhone.setText(phone);
            pCur.close();
        }

        Uri photoUri = Uri.withAppendedPath(
                ContactsContract.Contacts.CONTENT_URI, contactId);
        imgPhoto.setImageURI(photoUri);
    }

    private void savePhone() {
        String newNumber = edtPhone.getText().toString();

        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, newNumber);
        getContentResolver().update(
                ContactsContract.Data.CONTENT_URI,
                values,
                ContactsContract.Data.CONTACT_ID + " = ? AND " +
                        ContactsContract.Data.MIMETYPE + " = ?",
                new String[]{contactId,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE});
        finish();
    }
}