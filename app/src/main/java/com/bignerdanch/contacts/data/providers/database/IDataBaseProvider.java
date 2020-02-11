package com.bignerdanch.contacts.data.providers.database;

import com.bignerdanch.contacts.data.Contact;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IDataBaseProvider {

    Single<UUID> addContact();

    Completable updateContact(Contact contactsModel);

    Single<Contact> loadContact(UUID contactId);

    Single<List<Contact>> loadContactsList();

    Completable deleteContact(UUID contactId);


}
