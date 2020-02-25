package com.bignerdanch.contacts.business.addcontact

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class AddContactInteractor(private var addContactRepository: AddContactRepository) : IAddContactInteractor {

    override fun addContact(): Single<UUID?>? = addContactRepository.addContact()

    override fun updateContact(contact: Contact): Completable = addContactRepository
        .updateContact(contact)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun loadContact(contactId: UUID): Single<Contact>? = addContactRepository.loadContact(contactId)

}