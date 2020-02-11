package com.bignerdanch.contacts.presentation.showcontact.presenter

import com.bignerdanch.contacts.presentation.showcontact.view.IShowContactFragment
import java.util.*

interface IShowContactPresenter {
    fun loadContact(contactId:UUID)

    fun attachView(view: IShowContactFragment)

    fun updateContact(contactId: UUID)

}
