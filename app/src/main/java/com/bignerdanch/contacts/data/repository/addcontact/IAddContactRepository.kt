package com.bignerdanch.contacts.business.addcontact

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IAddContactRepository {
    fun addContact(): Single<UUID?>?

    fun updateContact(contact: Contact): Completable

    fun loadContact(contactId: UUID): Single<Contact>?
}