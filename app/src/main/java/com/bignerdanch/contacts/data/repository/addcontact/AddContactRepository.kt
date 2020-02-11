package com.bignerdanch.contacts.business.addcontact

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class AddContactRepository(private var dataBaseProvider : IDataBaseProvider) : IAddContactRepository {

    override fun addContact(): Single<UUID?>?
            = dataBaseProvider.addContact()

    override fun updateContact(contact: Contact): Completable
            = dataBaseProvider.updateContact(contact)

    override fun loadContact(contactId: UUID): Single<Contact>? {
        return dataBaseProvider.loadContact(contactId)
    }

}