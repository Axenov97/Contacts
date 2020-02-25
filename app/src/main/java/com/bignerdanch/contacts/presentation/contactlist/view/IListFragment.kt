package com.bignerdanch.contacts.presentation.contactlist.view

import android.view.View
import com.bignerdanch.contacts.data.Contact
import java.util.*

interface IListFragment {

    fun openAddContact(contactId: UUID?)

    fun updateContactsList(contactsList: List<Contact>)

    fun showPopupMenu(view: View, contactId: UUID)

    fun ring(contact: Contact)
}