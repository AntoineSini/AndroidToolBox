package fr.isen.antoine.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.antoine.androidtoolbox.classes.RandomUserModel
import fr.isen.antoine.androidtoolbox.classes.UserModel
import kotlinx.android.synthetic.main.activity_infos.*
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity(), RecyclerAdapterRUM.OnUserRecycleListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)
        requestAPI()
    }

    fun requestAPI(){
        // Instantiate the RequestQueue.
        RandomUsersRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=20"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val RUM = Gson().fromJson(response, RandomUserModel::class.java)
                val adapter = RecyclerAdapterRUM(content = RUM, listener = this)
                RandomUsersRecycler.adapter = adapter
            },
            Response.ErrorListener {
                Toast.makeText(this,"Erreur Requête API", Toast.LENGTH_LONG).show()
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    override fun onSelectUser(user: UserModel?) {
        val intent = Intent(this, DetailsActivity::class.java)
        val jsonString = Gson().toJson(user)
        intent.putExtra("14", jsonString)
        startActivity(intent)
    }
}
