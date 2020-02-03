package fr.isen.antoine.androidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.antoine.androidtoolbox.classes.ContactModel
import kotlinx.android.synthetic.main.single_item_recycler.view.*

class RecyclerAdapterContact(val content: ArrayList<ContactModel>): RecyclerView.Adapter<RecyclerAdapterContact.ContactViewHolder>() {
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(content[position].displayName)
    }

    override fun getItemCount(): Int {
        return content.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_recycler, parent,false) as View
        return ContactViewHolder(view)
    }
    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(content: String?){
            view.textTop.text = content
        }
    }
}