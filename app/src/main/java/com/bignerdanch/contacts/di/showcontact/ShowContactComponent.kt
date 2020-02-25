package com.bignerdanch.contacts.di.showcontact

import com.bignerdanch.contacts.presentation.showcontact.view.ShowContactFragment
import dagger.Subcomponent

@Subcomponent (modules = [ShowContactModule::class])
@ShowContactScope
interface ShowContactComponent {
    fun inject (showContactFragment: ShowContactFragment)
}