package com.bignerdanch.contacts.presentation.addcontact.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bignerdanch.contacts.App
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.dagger2.addcontact.AddContactModule
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.databinding.ContactFragmentBinding
import com.bignerdanch.contacts.presentation.addcontact.presenter.IAddContactPresenter
import com.bignerdanch.contacts.presentation.host.OnListFragmentDataListener
import kotlinx.android.synthetic.main.contact_fragment.*
import java.util.*
import javax.inject.Inject

class AddContactFragment : Fragment(), IAddContactFragment {

    @Inject
    lateinit var addContactPresenter: IAddContactPresenter

    private lateinit var listener: OnListFragmentDataListener
    private var binding : ContactFragmentBinding? = null

    init { App.get().plusAddContactModule(AddContactModule()).inject(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = ContactFragmentBinding.inflate(inflater).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContactPresenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.contact_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_new_contact -> {
                if (phone_edit_text.text.toString() == "") {
                    phone_edit_text.setHintTextColor(Color.RED)
                    true
                } else {
                    addContact()
                    true
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun saveContact(contactId : UUID){
        val contact = Contact(contactId)
        contact.firstName = first_name_edit_text.text.toString()
        contact.lastName = last_name_edit_text.text.toString()
        contact.midleName = midle_name_edit_text.text.toString()
        contact.telNumber = phone_edit_text.text.toString()
        addContactPresenter.updateContact(contact)
        listener.onSaveContact(contactId)
    }

    override fun addContact() = addContactPresenter.addContact()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement MainActivity")
        }
    }

    companion object {
        val TAG = "ContactFragment"
        val ARG_CONTACT_ID = "contact_id"
    }

    override fun onDestroy() {
        App.get().clearAddContactComponent()
        super.onDestroy()
    }
}
