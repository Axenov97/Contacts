package com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite;

import com.bignerdanch.contacts.data.Contact;

import java.util.List;
import java.util.UUID;

public interface IMyStorIOSQLite {
    UUID addContact();

    void updateContact(Contact contactsModel);

    Contact getContact(UUID contactId);

    List<Contact> getContactsList();

    void deleteContact(UUID contactId);



}
