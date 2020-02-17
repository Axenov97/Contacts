package com.bignerdanch.contacts.data.repository.contactlist

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class ContactListRepository(private val dataBaseProvider: IDataBaseProvider): IContactListRepository {

    override fun loadContactsList(): Single<List<Contact?>?>?
            = dataBaseProvider.loadContactsList()

    override fun deleteContact(contactId: UUID): Completable
            = dataBaseProvider.deleteContact(contactId)

    override fun loadContact(contactId: UUID): Single<Contact>
            = dataBaseProvider.loadContact(contactId)

}