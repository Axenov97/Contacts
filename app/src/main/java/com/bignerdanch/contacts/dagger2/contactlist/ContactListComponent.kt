package com.bignerdanch.contacts.dagger2.contactlist

import com.bignerdanch.contacts.presentation.contactlist.view.ListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ContactListModule::class])
@ContactsListScope
interface ContactListComponent {
    fun inject(listFragment: ListFragment)
}