package com.example.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.ItemContactBinding
import com.example.contacts.model.Contact

class ContactsAdapter(val contactList: MutableList<Contact>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    class ViewHolder(val itemViewBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(contact: Contact) {
            itemViewBinding.contactName.text = contact.name
            itemViewBinding.contactNumber.text = contact.phone


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        holder.itemViewBinding.root.setOnClickListener {
            onItemClicked?.onClick(contact)

        }
    }

    var onItemClicked: OnItemClicked? = null
}

fun interface OnItemClicked {
    fun onClick(contact: Contact)
}




