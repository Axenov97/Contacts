package com.bignerdanch.contacts.presentation.host

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.bignerdanch.contacts.R
import com.bignerdanch.contacts.presentation.addcontact.view.AddContactFragment
import com.bignerdanch.contacts.presentation.contactlist.view.ListFragment
import com.bignerdanch.contacts.presentation.showcontact.view.ShowContactFragment
import java.util.*

class MainActivity : AppCompatActivity(), OnListFragmentDataListener {

    private var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (savedInstanceState == null) {
            fragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment as ListFragment, ListFragment.TAG)
                .commit()
        }
    }

    override fun onOpenAddContact() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddContactFragment(), AddContactFragment.TAG)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(AddContactFragment.TAG)
            .commit()
    }

    override fun onOpenContact(contactId: UUID?) {
        val fragmentReplace =
            ShowContactFragment()
        val bundle = Bundle()
        bundle.putString(ShowContactFragment.ARG_CONTACT_ID, contactId.toString())
        fragmentReplace.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentReplace, ShowContactFragment.TAG)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(ShowContactFragment.TAG)
            .commit()
    }

    override fun onSaveContact(contactId: UUID?) = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            Log.i("MainActivity", "popping backstack")
            supportFragmentManager.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            super.onBackPressed()
        }
    }
}



