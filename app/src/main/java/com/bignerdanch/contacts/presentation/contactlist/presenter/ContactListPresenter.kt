package com.bignerdanch.contacts.presentation.contactlist.presenter
import android.util.Log
import com.bignerdanch.contacts.business.contactlist.IContactListInteractor
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.presentation.contactlist.view.IListFragment
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class ContactListPresenter(var contactListInteractor: IContactListInteractor) : IContactListPresenter {

    private lateinit var listFragment :IListFragment
    private val disposer = CompositeDisposable()

    override fun attachView(view: IListFragment) {
        listFragment = view
    }

    override fun onClickContact(contactId: UUID) {
        listFragment.openContact(contactId)
    }

    override fun loadContactsList() {
        disposer.add(
            contactListInteractor.loadContactsList().subscribe(
                { contactList: List<Contact?>? -> onLoadSuccess(contactList as List<Contact>) },
                { throwable: Throwable? -> onError(throwable) })
        )
    }

    private fun onLoadSuccess(contactList: List<Contact>) =
        listFragment.updateContactsList(contactList)

    private fun onError(throwable: Throwable?)
            = Log.e(TAG, javaClass.simpleName + " onError ")

    companion object {
        val TAG = "ContactListPresenter"
    }
}











