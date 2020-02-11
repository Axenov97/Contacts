package com.bignerdanch.contacts.presentation.contactlist.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdanch.contacts.App
import com.bignerdanch.contacts.dagger2.contactlist.ContactListModule
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.databinding.ListFragmentBinding
import com.bignerdanch.contacts.presentation.contactlist.adapter.ContactAdapter
import com.bignerdanch.contacts.presentation.contactlist.adapter.IOnItemClick
import com.bignerdanch.contacts.presentation.contactlist.presenter.IContactListPresenter
import com.bignerdanch.contacts.presentation.host.OnListFragmentDataListener
import kotlinx.android.synthetic.main.list_fragment.*
import java.util.*
import javax.inject.Inject

class ListFragment : Fragment(), IListFragment, View.OnClickListener, IOnItemClick {

    private var binding: ListFragmentBinding? = null
    private lateinit var recyclerAdapter: ContactAdapter
    private lateinit var listener: OnListFragmentDataListener
    private val contacts = ArrayList<Contact>()

    @Inject
    lateinit var listPresenter: IContactListPresenter

    init { App.get().plusContactListModule(ContactListModule()).inject(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = ListFragmentBinding.inflate(inflater).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPresenter.attachView(this)
        fab.setOnClickListener(this)

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = ContactAdapter(this, contacts)
        recycler_view.adapter = recyclerAdapter
        listPresenter.loadContactsList()
    }

    override fun onClick(v: View?)
            = openAddContact()

    override fun openAddContact()
            = listener.onOpenAddContact()

    override fun updateContactsList(contactsList: List<Contact>) {
        contacts.clear()
        contacts.addAll(contactsList)
        recyclerAdapter.updateList(contacts)
    }

    override fun onClickContact(contactId: UUID)
            = listPresenter.onClickContact(contactId)

    override fun openContact(contactId: UUID?)
            = listener.onOpenContact(contactId)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener")
        }
    }

    override fun onDestroy() {
        App.get().clearContactsListComponent()
        super.onDestroy()
    }

    companion object {
        const val TAG = "ListFragment"
    }
}