package com.bignerdanch.contacts.dagger2.showcontact

import com.bignerdanch.contacts.presentation.showcontact.view.ShowContactFragment
import dagger.Subcomponent

@Subcomponent (modules = [ShowContactModule::class])
@ShowContactScope
interface ShowContactComponent {
    fun inject (showContactFragment: ShowContactFragment)
}