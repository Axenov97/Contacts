package com.bignerdanch.contacts.presentation.showcontact.view

import com.bignerdanch.contacts.data.Contact
import java.util.*

interface IShowContactFragment {

    fun loadContactInfo(contact : Contact)

    fun updateContact(contactId : UUID)

}