package com.bignerdanch.contacts.presentation.contactlist.presenter

import com.bignerdanch.contacts.presentation.contactlist.view.IListFragment
import java.util.*

interface IContactListPresenter {

    fun attachView(view: IListFragment)

    fun loadContactsList()

    fun onClickContact(contactId: UUID)
}