package com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bignerdanch.contacts.data.Contact;
import com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsBaseHelper;
import com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsCursorWrapper;
import com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsDbSchema;
import com.bignerdanch.contacts.data.providers.database.databaseutils.Queries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsDbSchema.ContactsTable;

public class MyStorIOSQLite implements IMyStorIOSQLite {

    private SQLiteDatabase mDb;

    public MyStorIOSQLite(ContactsBaseHelper contactsBaseHelper) {
        mDb = contactsBaseHelper.getWritableDatabase();
    }

    @Override
    public UUID addContact() {
        Log.i("MY_TAG", "отработал фаб в MyStoreIO");
        Contact contact = new Contact();
        ContentValues values = Queries.getContentValues(contact);
        mDb.insert(ContactsDbSchema.ContactsTable.NAME, null, values);
        return contact.getContactId();
    }

    @Override
    public void updateContact(Contact contact) {
        String uuidString = contact.getContactId().toString();
        ContentValues values = Queries.getContentValues(contact);
        mDb.update(ContactsTable.NAME,
                values,
                ContactsTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    @Override
    public Contact getContact(UUID contactId) {
        String uuidString = contactId.toString();
        ContactsCursorWrapper cursor = Queries.queryContacts(mDb,
                ContactsTable.Cols.UUID + " = ?",
                new String[]{uuidString});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getContactsModel();
        } finally {
            cursor.close();
        }
    }

    @Override
    public List<Contact> getContactsList() {
        ContactsCursorWrapper cursor = Queries.queryContacts(mDb,
                null,
                null);
        List<Contact> contactsList = new ArrayList<>();
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                contactsList.add(cursor.getContactsModel());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return contactsList;
    }

    @Override
    public void deleteContact(UUID contactId) {
        String uuidString = contactId.toString();
        mDb.delete(ContactsTable.NAME,
                ContactsTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
}
