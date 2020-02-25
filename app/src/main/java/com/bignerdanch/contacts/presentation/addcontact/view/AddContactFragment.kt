package com.bignerdanch.contacts.presentation.addcontact.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.bignerdanch.contacts.App
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.data.Contact
import com.bignerdanch.contacts.databinding.ContactFragmentBinding
import com.bignerdanch.contacts.di.addcontact.AddContactModule
import com.bignerdanch.contacts.presentation.addcontact.presenter.IAddContactPresenter
import com.bignerdanch.contacts.presentation.host.OnListFragmentDataListener
import kotlinx.android.synthetic.main.contact_fragment.*
import java.util.*
import javax.inject.Inject


class AddContactFragment : Fragment(), IAddContactFragment, View.OnClickListener {

    @Inject
    lateinit var addContactPresenter: IAddContactPresenter
    private var contactId: UUID? = null
    private var uri: Uri? = null
    private lateinit var listener: OnListFragmentDataListener
    private var binding: ContactFragmentBinding? = null

    init { App.get().plusAddContactModule(AddContactModule()).inject(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null)
            contactId = UUID.fromString(arguments!!.getString(ARG_CONTACT_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = ContactFragmentBinding.inflate(inflater).apply { binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addImage.setOnClickListener(this)
        addContactPresenter.attachView(this)
        if (contactId != null){
            addContactPresenter.loadContact(contactId!!)
        }
    }

    override fun loadContactInfo(contact: Contact) {
        first_name_edit_text.setText(contact.firstName)
        last_name_edit_text.setText(contact.lastName)
        midle_name_edit_text.setText(contact.midleName)
        phone_edit_text.setText(contact.telNumber)
        addImage.setImageURI(Uri.parse(contact.photoUri))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
            = inflater.inflate(R.menu.contact_fragment_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_new_contact -> {
                if (phone_edit_text.text.toString() == "") {
                    phone_edit_text.setHintTextColor(Color.RED)
                    true
                } else {
                    if(contactId == null) {
                        addContact()
                    }
                    else
                        saveContact(contactId!!)
                    true
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addContact() = addContactPresenter.addContact()

    override fun saveContact(contactId: UUID){
        val contact = Contact(contactId)
        contact.firstName = first_name_edit_text.text.toString()
        contact.lastName = last_name_edit_text.text.toString()
        contact.midleName = midle_name_edit_text.text.toString()
        contact.telNumber = phone_edit_text.text.toString()
        contact.photoUri = uri.toString()
        addContactPresenter.updateContact(contact)
        listener.onSaveContact(contactId)
    }

    override fun onClick(v: View?) = startActivityForResult(Intent(Intent.ACTION_PICK,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), CAMERA_REQUEST)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST && data != null) {
            uri = data.data
            addImage.setImageURI(uri)
            Log.i("MY_TAG","set ${data.data}")
        }
    }

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
        const val TAG = "ContactFragment"
        const val ARG_CONTACT_ID = "contact_id"
        const val CAMERA_REQUEST = 100
    }

    override fun onDestroy() {
        App.get().clearAddContactComponent()
        super.onDestroy()
    }
}
