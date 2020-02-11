package com.bignerdanch.contacts.presentation.showcontact.presenter

import android.util.Log
import com.bignerdanch.contacts.business.showcontact.ShowContactInteractor
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.presentation.showcontact.view.IShowContactFragment
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ShowContactPresenter(var showContactInteractor: ShowContactInteractor) : IShowContactPresenter {

    private lateinit var showFragment: IShowContactFragment
    private val disposer = CompositeDisposable()

    override fun attachView(view: IShowContactFragment) {
        showFragment = view
    }

    override fun updateContact(contactId: UUID) {
//        disposer.add(
//            showContactInteractor.updateContact(contactId)
//        )
    }

    override fun loadContact(contactId: UUID) {
        disposer.add(
            showContactInteractor.loadContact(contactId).subscribe (
            { contact: Contact -> onSuccess(contact)},
            { throwable: Throwable -> onError(throwable) })
        )
    }

    private fun onSuccess(contact: Contact)
            = showFragment.loadContactInfo(contact)

    private fun onError(throwable: Throwable)
            = Log.e(TAG, javaClass.simpleName + " onError ")

    companion object {
        const val TAG = "ShowContactPresenter"
    }
}