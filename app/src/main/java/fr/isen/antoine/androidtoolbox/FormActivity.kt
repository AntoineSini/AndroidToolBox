package fr.isen.antoine.androidtoolbox

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class FormActivity : AppCompatActivity() {
    var currentDate = Date()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        birthdayEditText.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                birthdayEditText.clearFocus()
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    onDateChoose(year,month+1,dayOfMonth)
                },
                    1990,0,1).show()
            }
        }
        validateButton.setOnClickListener{
            saveToJSON()
            displayJSON()
        }
    }

    fun onDateChoose(year: Int, month: Int, dayOfMonth: Int){
        birthdayEditText.setText(String.format("%02d/%02d/%04d",dayOfMonth,month,year))
    }

    fun saveToJSON(){
        birthdayEditText.text.toString()
        val json = JSONObject()
        json.put("firstname",firstnameEditText.text.toString())
        json.put("surname",surnameEditText.text.toString())
        json.put("birthday",birthdayEditText.text.toString())
        Log.d("json", json.toString())

        val file = File(cacheDir.absolutePath+"/jsonfile.json")
        file.writeText(json.toString())
    }

    fun displayJSON(){
        val file = File(cacheDir.absolutePath+"/jsonfile.json")
        val json = JSONObject(file.readText())
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("ALERTE JSON")
            setMessage("Vous êtes ${json.get("firstname")}" +
                    " ${json.get("surname")} né(e) le ${json.get("birthday")}")
            show()
        }
    }

    fun getAge(day: Int, month: Int, year: Int) : Int{
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val dateStr = formatter.format(currentDate)
        val component = dateStr.split("/")
        val currentYear : Int = component[2].toInt()
        val currentMonth : Int = component[1].toInt()
        val currentDay : Int = component[0].toInt()

        var age = currentYear - year
        if (currentMonth < month+1 ){
            age--
        }
        if (currentMonth == (month+1)){
            if (day > currentDay){
                age--
            }
        }
        return age
    }
}
