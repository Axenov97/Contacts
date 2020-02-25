package com.bignerdanch.contacts.presentation.contactlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.data.Contact
import java.util.*

class ContactAdapter(private var onItemClick: IOnItemClick, private var contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context), parent)

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(contacts[position])

    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder
        (inflater.inflate(R.layout.item_view, parent, false)), View.OnClickListener {

        private val name: TextView = itemView.findViewById(R.id.contact_name)
        private val dots: ImageView = itemView.findViewById(R.id.dots_menu)
        private val ring: ImageView = itemView.findViewById(R.id.ring)
        private lateinit var contacts: Contact

        init {
            dots.setOnClickListener(this)
            ring.setOnClickListener(this)
            itemView.setOnClickListener(this)
        }

        fun bind(contact: Contact) {
            contacts = contact
            name.text = contact.firstName
        }

        override fun onClick(v: View?) {
            when(v!!.id) {
                R.id.item_view ->
                    onItemClick.onClickContact(contacts.contactId)

                R.id.dots_menu ->
                    onItemClick.onClickContactMenu(contacts.contactId, dots)

                R.id.ring ->
                    onItemClick.onClickRing(contacts.contactId, ring)
            }
        }
    }
}

interface IOnItemClick {

    fun onClickContact(contactId : UUID)

    fun onClickContactMenu(contactId : UUID, view : View)

    fun onClickRing(contactId : UUID, view : View)
}
