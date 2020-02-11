package com.bignerdanch.contacts.presentation.contactlist.view

import com.bignerdanch.contacts.data.Contact
import java.util.*

interface IListFragment {

    fun openContact(contactId: UUID?)

    fun openAddContact()

    fun updateContactsList(contactsList: List<Contact>)


}