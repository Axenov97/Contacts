package com.bignerdanch.contacts.presentation.host

import java.util.*

interface OnListFragmentDataListener {

    fun onOpenAddContact(contactId: UUID?)

    fun onOpenContact(contactId:UUID?)
    
    fun onSaveContact(contactId:UUID?)
}
