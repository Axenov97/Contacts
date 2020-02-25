package com.bignerdanch.contacts.business.contactlist

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.repository.contactlist.IContactListRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ContactListInteractor(private var contactListRepository: IContactListRepository):IContactListInteractor {

    override fun loadContactsList(): Single<List<Contact?>?> = contactListRepository
        .loadContactsList()!!
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    override fun deleteContact(contactId: UUID?): Completable = contactListRepository
        .deleteContact(contactId!!)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    override fun loadContact(contactId: UUID?): Single<Contact> = contactListRepository
        .loadContact(contactId!!)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}