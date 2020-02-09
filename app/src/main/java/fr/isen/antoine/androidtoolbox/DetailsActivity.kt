package fr.isen.antoine.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.antoine.androidtoolbox.classes.UserModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val json = intent.getStringExtra("14")
        val user = Gson().fromJson<UserModel>(json, UserModel::class.java)
        Picasso.get().load("${user?.picture?.large}").into(userPicture)
        nameTextview.text = "${user.name?.title} ${user.name?.first} ${user.name?.last}"
        completeAdress.text = "Adress :${user.location?.street?.number} ${user.location?.street?.name},\n" +
                "${user.location?.city} ${user.location?.state}, ${user.location?.country}"
        gender.text = "Gender : ${user.gender}"
        email.text = "Mail adress: ${user.email}"
        cell.text = "Cellphone : ${user.cell}"
    }
}
