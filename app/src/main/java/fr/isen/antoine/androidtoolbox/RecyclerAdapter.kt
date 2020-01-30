package fr.isen.antoine.androidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item_recycler.view.*
import java.util.zip.Inflater

class RecyclerAdapter(val contacts: List<String>): RecyclerView.Adapter<RecyclerAdapter.ContactViewHolder>() {
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_recycler,
            parent,false) as View
        return ContactViewHolder(view)
    }
    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(contact: String){
            view.name.text = contact
        }
    }
}