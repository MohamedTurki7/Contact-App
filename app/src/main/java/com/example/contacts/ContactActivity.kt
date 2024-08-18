package com.example.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.databinding.ActivityContactBinding
import com.example.contacts.model.Contact
import com.example.contacts.utlis.Constants

class ContactActivity : AppCompatActivity() {
    private lateinit var bindung: ActivityContactBinding
    private lateinit var name: String
    private lateinit var phone: String
    private val contactList = mutableListOf<Contact>()
    private val adapter = ContactsAdapter(contactList)
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindung = ActivityContactBinding.inflate(layoutInflater)
        setContentView(bindung.root)
        initRecycleViews()
        onSaveButtonClick()

    }

    private fun initRecycleViews() {
        bindung.rvContacts.adapter = adapter
        adapter.onItemClicked =
            OnItemClicked { contact -> navigateToContactDetailsActivity(contact) }

    }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra(Constants.CONTACT, contact)

        startActivity(intent)

    }

    private fun onSaveButtonClick() {
        bindung.saveButton.setOnClickListener {
            if (!validateTextField()) {
                return@setOnClickListener
            }
            name = bindung.nameEdit.text?.trim().toString()
            phone = bindung.phoneEdit.text?.trim().toString()
            description = bindung.descriptionEdit.text?.trim().toString()
            val contact = Contact(name, phone, description)
            contactList.add(contact)
            adapter.notifyItemInserted(contactList.size - 1)
            bindung.nameEdit.setText("")
            bindung.phoneEdit.setText("")
            bindung.descriptionEdit.setText("")


        }
    }

    private fun validateTextField(): Boolean {
        name = bindung.nameEdit.text?.trim().toString()
        phone = bindung.phoneEdit.text?.trim().toString()
        bindung.nameTittle.error = validateName(name)
        bindung.phoneTittle.error = validatePhone(phone)

        return validateName(name) == null && validatePhone(phone) == null

    }

    private fun validateName(name: String): String? {
        if (name.isEmpty()) {
            return "Required"
        }
        if (name.length < 3) {
            return " name can't be less than 3 letters"
        }
        val namePattern = "^[a-zA-Z]+$"
        if (!name.matches(namePattern.toRegex())) {
            return "name must contains letters only"
        }
        return null

    }

    private fun validatePhone(phone: String): String? {

        if (phone.isEmpty()) {
            return "Required"
        }
        if (phone.length < 11) {
            return " phone can't be less than 11 Numbers"
        }
        val phonePattern = "^[0-9]+$"
        if (!phone.matches(phonePattern.toRegex())) {
            return "phone must contains digits only"
        }
        return null


    }
}