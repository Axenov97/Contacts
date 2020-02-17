package com.bignerdanch.contacts.data.repository.contactlist

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IContactListRepository {

    fun loadContactsList(): Single<List<Contact?>?>?

    fun deleteContact(contactId : UUID) : Completable

    fun loadContact(contactId: UUID): Single<Contact>
}