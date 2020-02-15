package com.bignerdanch.contacts.presentation.showcontact.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bignerdanch.contacts.App
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.dagger2.showcontact.ShowContactModule
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.presentation.host.OnListFragmentDataListener
import com.bignerdanch.contacts.presentation.showcontact.presenter.IShowContactPresenter
import kotlinx.android.synthetic.main.show_contact.*
import java.util.*
import javax.inject.Inject

class ShowContactFragment : Fragment(), IShowContactFragment {

    @Inject
    lateinit var showContactPresenter: IShowContactPresenter
    private lateinit var listener: OnListFragmentDataListener
    private var contactId : UUID? = null

    init { App.get().plusShowContactModule(ShowContactModule()).inject(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            contactId = UUID.fromString(arguments!!.getString(ARG_CONTACT_ID))

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.show_contact, container , false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showContactPresenter.attachView(this)
        showContactPresenter.loadContact(contactId!!)
    }

    override fun loadContactInfo(contact : Contact) {
        first_name_text.text = contact.firstName.toString()
        last_name_text.text = contact.lastName.toString()
        midle_name_text.text = contact.midleName.toString()
        phone_text.text = contact.telNumber.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
            = inflater.inflate(R.menu.show_contact_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_contact -> {
                listener.onOpenAddContact(contactId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun updateContact(contactId: UUID) {
       showContactPresenter.updateContact(contactId)
    }

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
        App.get().clearShowContactComponent()
        super.onDestroy()
    }

    companion object {
        const val TAG = "ShowContactFragment"
        const val ARG_CONTACT_ID = "contact_id"
    }
}