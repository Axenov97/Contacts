package com.bignerdanch.contacts.business.showcontact

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.repository.showcontact.IShowContactRepository
import io.reactivex.Single
import java.util.*

class ShowContactInteractor(var showContactReposytory: IShowContactRepository) : IShowContactInteractor {

    override fun loadContact(contactId: UUID): Single<Contact> = showContactReposytory.loadContact(contactId)

}