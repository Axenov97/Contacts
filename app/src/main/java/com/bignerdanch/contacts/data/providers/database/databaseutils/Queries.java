package com.bignerdanch.contacts.data.providers.database.databaseutils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdanch.contacts.data.Contact;

import static com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsDbSchema.ContactsTable;

//настройка курсора и модели
public class Queries {

    public static ContactsCursorWrapper queryContacts(SQLiteDatabase db, String whereClause, String[] whereArgs) {
        Cursor cursor = db.query(
                ContactsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new ContactsCursorWrapper(cursor);
    }

    public static ContentValues getContentValues(Contact contactsModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsTable.Cols.UUID, contactsModel.getContactId().toString());
        contentValues.put(ContactsTable.Cols.FIRST_NAME, contactsModel.getFirstName());
        contentValues.put(ContactsTable.Cols.LAST_NAME, contactsModel.getLastName());
        contentValues.put(ContactsTable.Cols.MIDLE_NAME, contactsModel.getMidleName());
        contentValues.put(ContactsTable.Cols.TEL_NUMBER, contactsModel.getTelNumber());
        contentValues.put(ContactsTable.Cols.PHOTO_URI, contactsModel.getPhotoUri());
        contentValues.put(ContactsTable.Cols.ADDRESS, contactsModel.getAddress());
        return contentValues;
    }
}
