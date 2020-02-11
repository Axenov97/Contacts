package com.bignerdanch.contacts.data.repository.contactlist

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import io.reactivex.Single

class ContactListRepository(private val dataBaseProvider: IDataBaseProvider): IContactListRepository {

    override fun loadContactsList(): Single<List<Contact?>?>? {
        return dataBaseProvider.loadContactsList()
    }

}