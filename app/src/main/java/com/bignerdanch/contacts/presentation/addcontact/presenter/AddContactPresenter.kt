package com.bignerdanch.contacts.presentation.addcontact.presenter

import android.util.Log
import com.bignerdanch.contacts.business.addcontact.AddContactInteractor
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.presentation.addcontact.view.IAddContactFragment
import com.bignerdanch.contacts.presentation.contactlist.presenter.ContactListPresenter
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class AddContactPresenter(private var addContactInteractor: AddContactInteractor) : IAddContactPresenter {

    private val disposer = CompositeDisposable()
    private lateinit var addContactFragment: IAddContactFragment

    override fun attachView(view: IAddContactFragment) {
        addContactFragment = view
    }

    override fun addContact() {
        Log.i("MY_TAG", "отработал фаб на презентере")
        disposer.add(
            addContactInteractor.addContact()!!.subscribe(
                { contactId: UUID? -> this.onAddSuccess(contactId) },
                { throwable: Throwable? -> this.onError(throwable) })
        )
    }

    private fun onAddSuccess(contactId: UUID?)
            = addContactFragment.saveContact(contactId!!)

    private fun onError(throwable: Throwable?)
            = Log.e(ContactListPresenter.TAG, javaClass.simpleName + " onError ")

    override fun updateContact(contact: Contact) {
        disposer.add(
            addContactInteractor.updateContact(contact)
                .subscribe(
                    { onUpdate() },
                    { throwable: Throwable? -> onError(throwable) })
        )
    }

    private fun onUpdate(): Int = Log.i("MY_TAG", " - контакт был успешно обновлен" )

    override fun loadContact(contactId: UUID) {
        disposer.add(
            addContactInteractor.loadContact(contactId)!!.subscribe (
                { contact: Contact -> onSuccess(contact)},
                { throwable: Throwable -> onError(throwable) })
        )
    }

    private fun onSuccess(contact: Contact)
            = addContactFragment.loadContactInfo(contact)
}