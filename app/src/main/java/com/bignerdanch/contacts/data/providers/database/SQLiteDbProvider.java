package com.bignerdanch.contacts.data.providers.database;

import com.bignerdanch.contacts.data.Contact;
import com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite.IMyStorIOSQLite;
import com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite.MyStorIOSQLite;

import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SQLiteDbProvider implements IDataBaseProvider {

    private IMyStorIOSQLite mDataBase;

    public SQLiteDbProvider(MyStorIOSQLite dataBase) {
        mDataBase = dataBase;
    }

    @Override
    public Single<UUID> addContact() {
        return Single.fromCallable(() -> mDataBase.addContact());
    }

    @Override
    public Completable updateContact(Contact contactsModel) {
        return Completable.fromAction(() -> mDataBase.updateContact(contactsModel));
    }

    @Override
    public Single<Contact> loadContact(UUID contactId) {
        return Single.fromCallable(() -> mDataBase.getContact(contactId));
    }

    @Override
    public Single<List<Contact>> loadContactsList() {
        return Single.fromCallable(() -> mDataBase.getContactsList());
    }

    @Override
    public Completable deleteContact(UUID contactId) {
        return Completable.fromAction(() -> mDataBase.deleteContact(contactId));
    }

}
