package fr.isen.antoine.androidtoolbox

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_infos.*
import java.util.jar.Manifest

class InfosActivity : AppCompatActivity() {
    companion object {
        private val IMAGE_PICK_REQUESTCODE = 80
        private val CAMERA_REQUESTCODE = 81
        private val CHOOSER_REQUEST = 82
        private val CONTACT_REQUESTCODE = 83
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
        contactRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val listeContact = listOf("Antoine", "Guillaume", "Emeric","rjg","Momo")
        val adapter = RecyclerAdapter(contacts = listeContact)
        contactRecycler.adapter = adapter
    }

    fun getContact(){
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null)
        while(contacts?.moveToNext() ?: false){
            val contact = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /*if(requestCode == //test ){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //fun
            } else {
                Toast.makeText(this,"Permission refusée sur la caméra",Toast.LENGTH_LONG).show()
            }
        }*/
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
