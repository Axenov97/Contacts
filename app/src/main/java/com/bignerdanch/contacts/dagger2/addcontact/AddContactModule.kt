package com.bignerdanch.contacts.dagger2.addcontact

import com.bignerdanch.contacts.business.addcontact.AddContactInteractor
import com.bignerdanch.contacts.business.addcontact.AddContactRepository
import com.bignerdanch.contacts.business.addcontact.IAddContactInteractor
import com.bignerdanch.contacts.business.addcontact.IAddContactRepository
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import com.bignerdanch.contacts.data.providers.database.SQLiteDbProvider
import com.bignerdanch.contacts.presentation.addcontact.presenter.AddContactPresenter
import com.bignerdanch.contacts.presentation.addcontact.presenter.IAddContactPresenter
import dagger.Module
import dagger.Provides

@Module
class AddContactModule {

    @Provides
    @AddContactsScope
    fun provideAddContactPresenter(addContactInteractor: IAddContactInteractor): IAddContactPresenter {
        return AddContactPresenter(addContactInteractor as AddContactInteractor )
    }

    @Provides
    @AddContactsScope
    fun provideAddContactInteractor(addContactRepository: IAddContactRepository): IAddContactInteractor{
        return AddContactInteractor(addContactRepository as AddContactRepository)
    }

    @Provides
    @AddContactsScope
    fun provideAddContactrepository(dataBaseProvider: IDataBaseProvider): IAddContactRepository{
        return AddContactRepository(dataBaseProvider as SQLiteDbProvider)
    }
}