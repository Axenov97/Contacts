package com.bignerdanch.contacts.presentation.host

import java.util.*

interface OnListFragmentDataListener {

    fun onOpenAddContact()

    fun onOpenContact(contactId:UUID?)
    
    fun onSaveContact(contactId:UUID?)
}
