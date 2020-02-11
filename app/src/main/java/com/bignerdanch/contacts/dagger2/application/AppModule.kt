package com.bignerdanch.contacts.dagger2.application

import android.content.Context
import com.bignerdanch.contacts.data.providers.database.IDataBaseProvider
import com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite.IMyStorIOSQLite
import com.bignerdanch.contacts.data.providers.database.MyStorIOSQLite.MyStorIOSQLite
import com.bignerdanch.contacts.data.providers.database.SQLiteDbProvider
import com.bignerdanch.contacts.data.providers.database.databaseutils.ContactsBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
    @Provides
    @Singleton
    fun provideMyStorIOSQLite(context: Context): IMyStorIOSQLite {
        return MyStorIOSQLite(ContactsBaseHelper(context))
    }

    @Provides
    @Singleton
    fun provideDateBaseProvider(myStorIOSQLite: IMyStorIOSQLite): IDataBaseProvider {
        return SQLiteDbProvider(myStorIOSQLite as MyStorIOSQLite)
    }

}