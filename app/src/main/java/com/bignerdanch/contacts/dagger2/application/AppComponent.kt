package com.bignerdanch.contacts.dagger2.application

import com.bignerdanch.contacts.dagger2.addcontact.AddContactComponent
import com.bignerdanch.contacts.dagger2.addcontact.AddContactModule
import com.bignerdanch.contacts.dagger2.contactlist.ContactListComponent
import com.bignerdanch.contacts.dagger2.contactlist.ContactListModule
import com.bignerdanch.contacts.dagger2.showcontact.ShowContactComponent
import com.bignerdanch.contacts.dagger2.showcontact.ShowContactModule
import com.bignerdanch.contacts.presentation.host.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component (modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject (mainActivity: MainActivity)
    fun plus(contactsListModule: ContactListModule): ContactListComponent
    fun plus(addContactModule: AddContactModule): AddContactComponent
    fun plus(showContactModule: ShowContactModule): ShowContactComponent
}