package com.bignerdanch.contacts.data.repository.showcontact

import com.bignerdanch.contacts.data.Contact
import io.reactivex.Single
import java.util.*

interface IShowContactRepository {
    fun loadContact(contactid: UUID) : Single<Contact>
}
