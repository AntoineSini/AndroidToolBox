package fr.isen.antoine.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : AppCompatActivity(), LifecycleFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycleTextView.text = "onCreate\n"
        var cpt: Int = 0
        swipeFragmentButton.setOnClickListener{
            var newFragment : Fragment? = null
            newFragment = if(cpt%2 ==0) SwipeFragment() else LifecycleFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment).commit()
            cpt++
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
