package fr.isen.antoine.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycleView.text = "onCreate\n"
        //Log.d("antoine", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        lifecycleView.text = lifecycleView.text.toString() + "onStart\n"
        //Log.d("antoine", "onStart")
    }

    override fun onResume() {
        super.onResume()
        lifecycleView.text = lifecycleView.text.toString() + "onResume\n"
        //Log.d("antoine", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("antoine", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("antoine", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"Lifecycle activity destroyed", Toast.LENGTH_LONG).show()
        //Log.d("antoine", "onDestroy")
    }


}
