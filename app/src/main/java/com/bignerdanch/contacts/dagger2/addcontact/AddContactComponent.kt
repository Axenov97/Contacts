package com.bignerdanch.contacts.dagger2.addcontact

import com.bignerdanch.contacts.presentation.addcontact.view.AddContactFragment
import dagger.Subcomponent

@Subcomponent(modules = [AddContactModule::class])
@AddContactsScope
interface AddContactComponent {
    fun inject(addContactFragment: AddContactFragment)
}