package fr.isen.antoine.androidtoolbox

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_infos.*
import android.location.LocationManager
import android.location.Location
import android.location.LocationListener


class InfosActivity : AppCompatActivity(), LocationListener {
    lateinit var locationManager: LocationManager
    override fun onLocationChanged(location: Location?) {
        location?.let{
            refreshPosition(it)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    companion object {
        private val IMAGE_PICK_REQUESTCODE = 80
        private val CAMERA_REQUESTCODE = 81
        private val CHOOSER_REQUEST = 82
        private val CONTACT_REQUESTCODE = 83
        private val GPS_REQUESTCODE = 84
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infos)

        pictureButton.setOnClickListener{
            pictureSelect()
        }
        requestPermission(android.Manifest.permission.READ_CONTACTS,CONTACT_REQUESTCODE){
            getContact()
        }
        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, GPS_REQUESTCODE){
            getGPS()
        }

    }

    fun getContact(){
        val contactList = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null)
        while(contacts?.moveToNext() ?: false){
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
            val contactModel = ContactModel()
            contactModel.displayName = displayName.toString()
            contactList.add(contactModel)
        }
        contactRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = RecyclerAdapter(contacts = contactList)
        contactRecycler.adapter = adapter
    }

    @SuppressLint("MissingPermission")
    fun getGPS() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        loc?.let{
            refreshPosition(it)
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null)
    }

    fun refreshPosition(location: Location){
        latitude.text = "latitude : ${location?.latitude}"
        longitude.text = "longitude : ${location?.longitude}"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CONTACT_REQUESTCODE ){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getContact()
            } else {
                Toast.makeText(this,"Permission refusée sur les contacts",Toast.LENGTH_LONG).show()
            }
        }
        if(requestCode == GPS_REQUESTCODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getGPS()
            } else {
                Toast.makeText(this,"Permission refusée sur le GPS",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun requestPermission(permission: String, requestCode: Int, handler:()-> Unit){
        if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                Toast.makeText(this,"Aller aux paramètres pour " +
                        "changer les permissions",Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(this,arrayOf(permission),requestCode)
            }
        } else{
            handler()
        }
    }

    fun pictureSelect(){
        val intentGal = Intent(Intent.ACTION_PICK)
        intentGal.type = "image/"
        val intentCam = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val intentChoose = Intent.createChooser(intentGal,"Gallery")
        intentChoose.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentCam))
        startActivityForResult(intentChoose, CHOOSER_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CHOOSER_REQUEST){
                if(data?.data != null) {
                    pictureButton.setImageURI(data?.data)
                } else {
                    val bitmap = data?.extras?.get("data") as? Bitmap
                    bitmap?.let{
                        pictureButton.setImageBitmap(it)
                    }
                }
            }
        }
    }
}
