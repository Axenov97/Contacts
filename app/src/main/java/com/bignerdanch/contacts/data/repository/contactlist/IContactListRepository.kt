package com.bignerdanch.contacts.data.repository.contactlist

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Single

interface IContactListRepository {

    fun loadContactsList(): Single<List<Contact?>?>?
}