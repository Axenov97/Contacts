package com.bignerdanch.contacts.business.contactlist

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.repository.contactlist.IContactListRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactListInteractor(var contactListRepository: IContactListRepository):IContactListInteractor {

    override fun loadContactsList(): Single<List<Contact?>?> {
        return contactListRepository.loadContactsList()!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}