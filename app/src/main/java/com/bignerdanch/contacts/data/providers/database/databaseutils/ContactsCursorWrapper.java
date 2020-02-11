package com.bignerdanch.contacts.data.providers.database.databaseutils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdanch.contacts.data.Contact;

import java.util.UUID;

import static com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsDbSchema.ContactsTable;

//Обертка курсора
public class ContactsCursorWrapper extends CursorWrapper {

    ContactsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Contact getContactsModel() {
        //Получам данные модели контакта из курсора
        String uuidString = getString(getColumnIndex(ContactsTable.Cols.UUID));
        String firstName = getString(getColumnIndex(ContactsTable.Cols.FIRST_NAME));
        String lastName = getString(getColumnIndex(ContactsTable.Cols.LAST_NAME));
        String midleName = getString(getColumnIndex(ContactsTable.Cols.MIDLE_NAME));
        String telNumber = getString(getColumnIndex(ContactsTable.Cols.TEL_NUMBER));
        String photoUri = getString(getColumnIndex(ContactsTable.Cols.PHOTO_URI));
        String address = getString(getColumnIndex(ContactsTable.Cols.ADDRESS));

        //Создаем модель
        Contact contactsModel = new Contact(UUID.fromString(uuidString));
        contactsModel.setFirstName(firstName != null ? firstName : "");
        contactsModel.setLastName(lastName != null ? lastName : "");
        contactsModel.setMidleName(midleName != null ? midleName: "");
        contactsModel.setTelNumber(telNumber != null ? telNumber : "");
        contactsModel.setPhotoUri(photoUri);
        contactsModel.setAddress(address != null ? address : "");

        return contactsModel;
    }
}
