package fr.isen.antoine.androidtoolbox

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
import kotlinx.android.synthetic.main.activity_infos.*
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

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
                val adapter = RecyclerAdapterRUM(content = RUM)
                RandomUsersRecycler.adapter = adapter
            },
            Response.ErrorListener {
                Toast.makeText(this,"Erreur Requête API", Toast.LENGTH_LONG).show()
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
