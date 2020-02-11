package com.bignerdanch.contacts.presentation.contactlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.data.Contact
import java.util.*

class ContactAdapter(private var onItemClick: IOnItemClick, private var contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder( LayoutInflater.from(parent.context), parent)

    override fun getItemCount()
            = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(contacts[position])

    fun updateList(contactsList: List<Contact?>?)
            = notifyDataSetChanged()


    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder
        (inflater.inflate(R.layout.item_view, parent, false)), View.OnClickListener {

        private val name: TextView = itemView.findViewById(R.id.contact_name)
        private lateinit var contacts :Contact

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(contact: Contact) {
            contacts = contact
            name.text = contact.firstName
        }
        override fun onClick(v: View?) {
            onItemClick.onClickContact(contacts.contactId)
        }
    }
}

interface IOnItemClick{
    fun onClickContact(contactId : UUID)
}
