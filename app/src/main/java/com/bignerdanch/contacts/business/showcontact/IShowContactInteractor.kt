package com.bignerdanch.contacts.business.showcontact

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Single
import java.util.*

interface IShowContactInteractor {
    fun loadContact(contactId: UUID):Single<Contact>
}
