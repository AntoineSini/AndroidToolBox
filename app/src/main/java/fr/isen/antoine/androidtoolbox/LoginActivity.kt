package fr.isen.antoine.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val goodIdentifier = "admin"
    val goodPassword = "123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        needLog()
        validateButton.setOnClickListener{
            doLogin()
        }
    }

    fun doLogin() {
        if(canLog(usernameEditText.text.toString(), passwordEditText.text.toString())){
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            val sharedPref : SharedPreferences  = getSharedPreferences("logpwd", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("login",usernameEditText.text.toString())
            editor.putString("password", passwordEditText.text.toString())
            //Log.d("antoine",sharedPref.getString(loginKey,usernameEditText.text.toString()))
            //Log.d("antoine",sharedPref.getString(passKey,passwordEditText.text.toString()))
            editor.apply()

            startActivity(intent)
        }
    }

    fun canLog(identifier: String, password: String): Boolean{
        return (identifier == goodIdentifier && password == goodPassword)
    }

    fun needLog(){
        val sharedPref : SharedPreferences  = getSharedPreferences("logpwd", Context.MODE_PRIVATE)
        if(canLog(
                sharedPref.getString("login","defaultLogin") ?: "not found",
                sharedPref.getString("password", "defaultPassword") ?: "not found")){
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
