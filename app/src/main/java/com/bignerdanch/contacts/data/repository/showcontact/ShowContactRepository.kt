package com.bignerdanch.contacts.data.repository.showcontact

import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import io.reactivex.Single
import java.util.*

class ShowContactRepository(var mDataBaseProvider: IDataBaseProvider) : IShowContactRepository  {

    override fun loadContact(contactid: UUID) : Single<Contact>
            = mDataBaseProvider.loadContact(contactid)
}