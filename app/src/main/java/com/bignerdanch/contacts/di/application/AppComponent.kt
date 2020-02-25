package com.bignerdanch.contacts.di.application

import com.bignerdanch.contacts.di.addcontact.AddContactComponent
import com.bignerdanch.contacts.di.addcontact.AddContactModule
import com.bignerdanch.contacts.di.contactlist.ContactListComponent
import com.bignerdanch.contacts.di.contactlist.ContactListModule
import com.bignerdanch.contacts.di.showcontact.ShowContactComponent
import com.bignerdanch.contacts.di.showcontact.ShowContactModule
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