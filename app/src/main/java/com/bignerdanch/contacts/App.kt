package com.bignerdanch.contacts

import android.app.Application
import com.bignerdanch.contacts.dagger2.addcontact.AddContactComponent
import com.bignerdanch.contacts.dagger2.addcontact.AddContactModule
import com.bignerdanch.contacts.dagger2.application.AppComponent
import com.bignerdanch.contacts.dagger2.application.AppModule
import com.bignerdanch.contacts.dagger2.application.DaggerAppComponent

import com.bignerdanch.contacts.dagger2.contactlist.ContactListComponent
import com.bignerdanch.contacts.dagger2.contactlist.ContactListModule
import com.bignerdanch.contacts.dagger2.showcontact.ShowContactComponent
import com.bignerdanch.contacts.dagger2.showcontact.ShowContactModule

class App : Application() {

    init { app = this }

    companion object{
        lateinit var app :App
        fun get(): App = app
    }

     private var appComponent: AppComponent? = null
     private var contactListComponent: ContactListComponent? = null
     private var addContactComponent: AddContactComponent? = null
     private var showContactComponent: ShowContactComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

     fun getAppComponent(): AppComponent = appComponent!!

    fun plusContactListModule(contactListModule: ContactListModule): ContactListComponent {
        if (contactListComponent == null) {
            contactListComponent = getAppComponent().plus(contactListModule)
        }
        return contactListComponent as ContactListComponent
    }

    fun plusAddContactModule(addContactModule: AddContactModule): AddContactComponent{
        if (addContactComponent == null) {
            addContactComponent = getAppComponent().plus(addContactModule)
        }
        return addContactComponent as AddContactComponent
    }
    fun plusShowContactModule(showContactModule: ShowContactModule): ShowContactComponent{
        if (showContactComponent == null) {
            showContactComponent = getAppComponent().plus(showContactModule)
        }
        return showContactComponent as ShowContactComponent
    }

    fun clearContactsListComponent() {
        contactListComponent = null
    }

    fun clearAddContactComponent() {
        addContactComponent = null
    }

    fun clearShowContactComponent(){
        showContactComponent = null
    }
}