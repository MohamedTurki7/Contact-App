package com.example.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contacts.databinding.ActivityContactDetailsBinding
import com.example.contacts.model.Contact
import com.example.contacts.utlis.Constants

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActivityViews()
        navigateBack()
    }

    private fun navigateBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setActivityViews() {
        val contact =
            IntentCompat.getParcelableExtra(intent, Constants.CONTACT, Contact::class.java)
        contact?.let { contact ->
            binding.editName.text = contact.name
            binding.editPhoneNumber.text = contact.phone
            binding.editDescription.text = contact.description


        }
    }
}