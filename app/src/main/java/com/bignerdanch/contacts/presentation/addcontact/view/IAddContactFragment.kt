package com.bignerdanch.contacts.presentation.addcontact.view

import com.bignerdanch.contacts.data.Contact
import java.util.*

interface IAddContactFragment {

   fun saveContact(contactId : UUID)

   fun addContact()

   fun loadContactInfo(contact : Contact)

}