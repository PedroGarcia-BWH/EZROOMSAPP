package es.example.ezroomsapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import java.text.SimpleDateFormat
import java.util.*

var TAG = "AddReservaActivity"
class AddReservaActivity : AppCompatActivity() {

    private lateinit var myCalendar: Calendar


    private lateinit var idReservaLabel: TextView
    private lateinit var idNombreLabel: EditText
    private lateinit var idApellidoLabel: EditText
    private lateinit var idComentarioLabel: EditText
    private lateinit var idEmailLabel: EditText
    private lateinit var idDNILabel: EditText
    private lateinit var idFechaLabel: EditText
    private lateinit var idNumeroDeOrasLabel: EditText
    private lateinit var idNumeroDePersonasLabel: EditText
    private lateinit var idPhoneNumberLabel: EditText
    private lateinit var idSelectSalaLabel: TextView
    private lateinit var idSubmitButton: Button
    private lateinit var idRadioGrouplaLabel: RadioGroup
    private lateinit var idSala1laLabel: EditText
    private lateinit var idSala2laLabel: EditText
    private lateinit var idSala3laLabel: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reserva)


        idFechaLabel = findViewById<EditText>(R.id.idFecha)






        idReservaLabel =findViewById<TextView>(R.id.idReserva)
        idNombreLabel =findViewById<EditText>(R.id.idNombre)
        idApellidoLabel =findViewById<EditText>(R.id.idApellido)
        idComentarioLabel =findViewById<EditText>(R.id.idComentario)
        idEmailLabel =findViewById<EditText>(R.id.idEmail)
        idDNILabel =findViewById<EditText>(R.id.idDNI)
        idNumeroDeOrasLabel =findViewById<EditText>(R.id.idNumeroDeOras)
        idNumeroDePersonasLabel =findViewById<EditText>(R.id.idNumeroDePersonas)
        idSelectSalaLabel =findViewById<EditText>(R.id.idSelectSala)
        idPhoneNumberLabel =findViewById<EditText>(R.id.idPhoneNumber)
        this.idSubmitButton =findViewById<Button>(R.id.idSubmitButton)
        idRadioGrouplaLabel =findViewById<RadioGroup>(R.id.idRadioGroup)

        //check parameters

        myCalendar = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()

            }

        this.idFechaLabel.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                this, dateSetListener, myCalendar.get(
                    Calendar.YEAR
                ), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        })

        this.idSubmitButton.setOnClickListener(){
            //set errors

            Log.i(TAG, idNombreLabel.text.toString())


            val apiService = ApiService(this)

            var selectedSala = "Sala Shangay"
            when (idRadioGrouplaLabel.checkedRadioButtonId){
                0 -> selectedSala = "Shangai"
                1 -> selectedSala = "Tokyo"
                2 -> selectedSala = "Berlin"
            }
            apiService.postReservation(


                Reserva(
                    idNombreLabel.text.toString(),
                    idApellidoLabel.text.toString(),
                    idDNILabel.text.toString(),
                    idEmailLabel.text.toString(),
                    Date().toString(),
                     selectedSala,
                    idNumeroDeOrasLabel.text.toString(),
                    idPhoneNumberLabel.text.toString(),
                    idComentarioLabel.text.toString(),
                    idNumeroDePersonasLabel.text.toString()),  onResponse = { response ->
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
        this.idFechaLabel.setText(sdf.format(myCalendar.time))
    }
}