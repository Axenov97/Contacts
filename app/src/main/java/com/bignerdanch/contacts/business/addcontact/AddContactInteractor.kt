package com.bignerdanch.contacts.business.addcontact

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class AddContactInteractor(private var addContactRepository: AddContactRepository):IAddContactInteractor {

    override fun addContact(): Single<UUID?>? =
        addContactRepository.addContact()

    override fun updateContact(contact: Contact): Completable{
        return addContactRepository.updateContact(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}