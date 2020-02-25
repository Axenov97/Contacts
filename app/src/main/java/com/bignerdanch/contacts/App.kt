package com.bignerdanch.contacts

import android.app.Application
import com.bignerdanch.contacts.di.addcontact.AddContactComponent
import com.bignerdanch.contacts.di.addcontact.AddContactModule
import com.bignerdanch.contacts.di.application.AppComponent
import com.bignerdanch.contacts.di.application.AppModule
import com.bignerdanch.contacts.di.application.DaggerAppComponent

import com.bignerdanch.contacts.di.contactlist.ContactListComponent
import com.bignerdanch.contacts.di.contactlist.ContactListModule
import com.bignerdanch.contacts.di.showcontact.ShowContactComponent
import com.bignerdanch.contacts.di.showcontact.ShowContactModule
import com.facebook.drawee.backends.pipeline.Fresco

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
        Fresco.initialize(this)
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