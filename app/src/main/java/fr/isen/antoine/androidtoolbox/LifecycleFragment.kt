package fr.isen.antoine.androidtoolbox

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_lifecycle.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LifecycleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LifecycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LifecycleFragment : Fragment() {
    private var delegate: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("antoine", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("antoine", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //fragmentTextView.text = fragmentTextView.text.toString()+ "onAttach"0
        if(context is OnFragmentInteractionListener) {
            delegate = context
        }
        Log.d("antoine", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("antoine", "onDetach")
    }

    override fun onStart() {
        super.onStart()
        fragmentTextView.text = fragmentTextView.text.toString()+ "onStart\n"
        Log.d("antoine", "onStart")
    }

    override fun onResume() {
        super.onResume()
        fragmentTextView.text = fragmentTextView.text.toString()+ "onResume\n"
        Log.d("antoine", "onResume")
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
        Log.d("antoine", "onDestroy")
    }

    override fun onDestroyView() {
        delegate?.displayToast("DestroyView")
        super.onDestroyView()
        Log.d("antoine", "onDestroyView")
    }

    var fragment : Fragment? = null


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun displayToast(text: String)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LifecycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LifecycleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
