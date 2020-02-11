package com.bignerdanch.contacts.dagger2.showcontact

import com.bignerdanch.contacts.business.showcontact.IShowContactInteractor
import com.bignerdanch.contacts.business.showcontact.ShowContactInteractor
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import com.bignerdanch.contacts.data.providers.database.SQLiteDbProvider
import com.bignerdanch.contacts.data.repository.showcontact.IShowContactRepository
import com.bignerdanch.contacts.data.repository.showcontact.ShowContactRepository
import com.bignerdanch.contacts.presentation.showcontact.presenter.IShowContactPresenter
import com.bignerdanch.contacts.presentation.showcontact.presenter.ShowContactPresenter
import dagger.Module
import dagger.Provides

@Module
class ShowContactModule {
    @Provides
    @ShowContactScope
    fun provideShowContactPresenter(showContactInteractor: IShowContactInteractor): IShowContactPresenter{
        return ShowContactPresenter(showContactInteractor as ShowContactInteractor)
    }

    @Provides
    @ShowContactScope
    fun provideShowContactInteractor(showContactReposytory: IShowContactRepository): IShowContactInteractor{
        return ShowContactInteractor(showContactReposytory as ShowContactRepository)
    }

    @Provides
    @ShowContactScope
    fun provideShowContactRepository(dataBaseProvider: IDataBaseProvider): IShowContactRepository{
        return ShowContactRepository(dataBaseProvider as SQLiteDbProvider)
    }
}