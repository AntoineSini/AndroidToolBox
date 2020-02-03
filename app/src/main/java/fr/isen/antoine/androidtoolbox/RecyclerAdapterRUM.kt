package fr.isen.antoine.androidtoolbox

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.antoine.androidtoolbox.classes.RandomUserModel
import fr.isen.antoine.androidtoolbox.classes.UserModel
import kotlinx.android.synthetic.main.single_item_recycler.view.*

class RecyclerAdapterRUM(val content: RandomUserModel): RecyclerView.Adapter<RecyclerAdapterRUM.ContactViewHolder>() {
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        content.results?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return content.results?.count()?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_recycler, parent,false) as View
        return ContactViewHolder(view)
    }
    class ContactViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bind(content: UserModel?){
            view.textTop.text = "${content?.name?.title}. ${content?.name?.first} ${content?.name?.last}"
            view.textMiddle.text = "${content?.location?.street?.number} ${content?.location?.street?.name}" +
                    " ${content?.location?.city}, ${content?.location?.country}"
            view.textBot.text = "${content?.email}"
            Picasso.get().load("${content?.picture?.large}").into(view.imageRandomUser);

        }
    }
}