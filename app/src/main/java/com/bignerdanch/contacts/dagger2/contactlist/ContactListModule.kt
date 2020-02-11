package com.bignerdanch.contacts.dagger2.contactlist

import com.bignerdanch.contacts.business.contactlist.ContactListInteractor
import com.bignerdanch.contacts.business.contactlist.IContactListInteractor
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import com.bignerdanch.contacts.data.providers.database.SQLiteDbProvider
import com.bignerdanch.contacts.data.repository.contactlist.ContactListRepository
import com.bignerdanch.contacts.data.repository.contactlist.IContactListRepository
import com.bignerdanch.contacts.presentation.contactlist.presenter.ContactListPresenter
import com.bignerdanch.contacts.presentation.contactlist.presenter.IContactListPresenter
import dagger.Module
import dagger.Provides

@Module
class ContactListModule {
    @Provides
    @ContactsListScope
    fun provideContactListPresenter(contactListInteractor: IContactListInteractor): IContactListPresenter{
        return ContactListPresenter(contactListInteractor as ContactListInteractor)
    }

    @Provides
    @ContactsListScope
    fun provideContactListInteractor(contactListRepository: IContactListRepository): IContactListInteractor {
        return ContactListInteractor(contactListRepository as ContactListRepository)
    }

    @Provides
    @ContactsListScope
    fun provideContactListRepository(dataBaseProvider: IDataBaseProvider): IContactListRepository {
        return ContactListRepository(dataBaseProvider as SQLiteDbProvider)
    }
}