package com.bignerdanch.contacts.business.contactlist

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Single

interface IContactListInteractor {

    fun loadContactsList(): Single<List<Contact?>?>
}