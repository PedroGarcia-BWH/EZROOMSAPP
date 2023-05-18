package es.example.ezroomsapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import java.text.SimpleDateFormat
import java.util.*

class AddReservaActivity : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private lateinit var myCalendar: Calendar
    private lateinit var mySubmitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reserva)


        editTextDate = findViewById<EditText>(R.id.idFecha)
        mySubmitButton = findViewById<Button>(R.id.idSubmitButton)

        //check parameters

        myCalendar = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()

            }

        editTextDate.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                this, dateSetListener, myCalendar!!.get(
                    Calendar.YEAR
                ), myCalendar!!.get(Calendar.MONTH), myCalendar!!.get(Calendar.DAY_OF_MONTH)
            ).show()
        })

        mySubmitButton.setOnClickListener(){
            //set errors

            var apiService = ApiService(this)
            apiService?.postReservation(
            Reserva("pedro", "garcia", "1234V", "pedro@uca.es", Date().toString(), "Sala Shangai", "2", "123423542", "Carlos Maricon", "2"),
            onResponse = { response ->
                // Manejar la respuesta exitosa aquí
                response?.let {
                   // val message = it.getString("message")
                    //Toast.makeText(context, "Success: " + message, Toast.LENGTH_SHORT).show()
                    //textView.text = message
                    finish()

                }
            },
            onError = { error ->
                // Manejar errores aquí
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        )
        }


    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy" // Formato de fecha
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        editTextDate.setText(sdf.format(myCalendar.time))
    }
}