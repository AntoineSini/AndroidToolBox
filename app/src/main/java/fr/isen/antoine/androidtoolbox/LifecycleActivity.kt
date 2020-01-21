package fr.isen.antoine.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : AppCompatActivity(), LifecycleFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycleTextView.text = "onCreate\n"

        swipeFragmentButton.setOnClickListener{

        }
        //Log.d("antoine", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        lifecycleTextView.text = lifecycleTextView.text.toString() + "onStart\n"
        //Log.d("antoine", "onStart")
    }

    override fun onResume() {
        super.onResume()
        lifecycleTextView.text = lifecycleTextView.text.toString() + "onResume\n"
        //Log.d("antoine", "onResume")
    }

    override fun onPause() {
        super.onPause()
        //Log.d("antoine", "onPause")
    }

    override fun onStop() {
        super.onStop()
        //Log.d("antoine", "onStop")
    }

    override fun onDestroy() {
        Toast.makeText(this,"Lifecycle activity destroyed", Toast.LENGTH_LONG).show()
        super.onDestroy()
        //Log.d("antoine", "onDestroy")
    }

    override fun displayToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


}
