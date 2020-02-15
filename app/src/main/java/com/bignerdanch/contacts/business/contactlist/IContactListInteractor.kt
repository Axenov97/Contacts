package com.bignerdanch.contacts.business.contactlist

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface IContactListInteractor {

    fun loadContactsList(): Single<List<Contact?>?>

    fun deleteContact(contactId: UUID?): Completable
}