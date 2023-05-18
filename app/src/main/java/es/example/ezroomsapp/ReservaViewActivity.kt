package es.example.ezroomsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import java.util.*

class ReservaViewActivity : AppCompatActivity() {

    private lateinit var idNombreLabel: EditText
    private lateinit var idApellidoLabel: EditText
    private lateinit var idComentarioLabel: EditText
    private lateinit var idEmailLabel: EditText
    private lateinit var idDNILabel: EditText
    private lateinit var idFechaLabel: EditText
    private lateinit var idSalaLabel: TextView
    private lateinit var idNumeroDeOrasLabel: EditText
    private lateinit var idNumeroDePersonasLabel: EditText
    private lateinit var idPhoneNumberLabel: EditText
    private lateinit var editButton: FloatingActionButton
    private lateinit var uploadButton: FloatingActionButton
    private lateinit var idReservaLabel: TextView
    private  var editableText : Boolean = false





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_view)

        idReservaLabel=findViewById<TextView>(R.id.idReserva)
        idNombreLabel=findViewById<EditText>(R.id.idNombre)
        uploadButton=findViewById<FloatingActionButton>(R.id.upload)
        idApellidoLabel=findViewById<EditText>(R.id.idApellido)
        idComentarioLabel=findViewById<EditText>(R.id.idComentario)
        idEmailLabel=findViewById<EditText>(R.id.idEmail)
        idDNILabel=findViewById<EditText>(R.id.idDNI)
        idFechaLabel=findViewById<EditText>(R.id.idFecha)
        idNumeroDeOrasLabel=findViewById<EditText>(R.id.idNumeroDeOras)
        idNumeroDePersonasLabel=findViewById<EditText>(R.id.idNumeroDePersonas)
        idSalaLabel=findViewById<TextView>(R.id.idSala)
        idPhoneNumberLabel=findViewById<EditText>(R.id.idPhoneNumber)

        uploadButton.hide()

//         var elementList = listOf<>(
//            idReservaLabel,
//            idNombreLabel,
//            idApellidoLabel,
//            idComentarioLabel,
//            idEmailLabel,
//            idDNILabel,
//            idFechaLabel,
//            idNumeroDeOrasLabel,
//            idNumeroDePersonasLabel,
//            idPhoneNumberLabel,
//            idSalaLabel
//        )


        //get extra
        val id = intent.getStringExtra("id")
        val apiService = ApiService(this)

        //get reserva
        if (id != null) {

            apiService.getRequestById(id,
                onResponse = { response ->
                    // Manejar la respuesta exitosa aquí
                    response?.let {
                        val jsonArray = response
                        val jsonObject = jsonArray.getJSONObject(0)
                        val reserva = Reserva(
                            jsonObject.getString("_id"),
                            jsonObject.getString("nombre"),
                            jsonObject.getString("apellidos"),
                            jsonObject.getString("dni"),
                            jsonObject.getString("email"),
                            jsonObject.getString("fechaReserva"),
                            jsonObject.getString("sala"),
                            jsonObject.getString("horasReserva"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("comentario"),
                            jsonObject.getString("nPersonas")
                        )
                        Toast.makeText(this, reserva._id, Toast.LENGTH_SHORT).show()
//                        val textView = findViewById<TextView>(R.id.textView4)
//                        textView.text = reserva.nombre
                        idNombreLabel.hint = reserva.nombre
                        idApellidoLabel.hint = reserva.apellidos
                        idDNILabel.hint = reserva.dni
                        idEmailLabel.hint = reserva.email
                        idFechaLabel.hint = reserva.fechaReserva
                        idSalaLabel.hint = reserva.sala
                        idNumeroDeOrasLabel.hint = reserva.horasReserva
                        idPhoneNumberLabel.hint = reserva.phone
                        idComentarioLabel.hint = reserva.comentario
                        idNumeroDePersonasLabel.hint = reserva.nPersonas
                    }
                },
                onError = { error ->
                    Log.d("Error", error)
                }
            )
        }

        val delete = findViewById<FloatingActionButton>(R.id.delete)
        delete.setOnClickListener {
            if (id != null) {
                apiService.deleteReservation(id,
                    onResponse = { response ->
                        // Manejar la respuesta exitosa aquí
                        response?.let {
                          Toast.makeText(this, "Reserva eliminada", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    },
                    onError = { error ->
                        Log.d("Error", error)
                    }
                )
            }
        }

        editButton = findViewById<FloatingActionButton>(R.id.edit)
        editButton.setOnClickListener {

            if(editableText) {

                //view mode
                idReservaLabel.isEnabled = false
                idNombreLabel.isEnabled = false
                idApellidoLabel.isEnabled = false
                idComentarioLabel.isEnabled = false
                idEmailLabel.isEnabled = false
                idDNILabel.isEnabled = false
                idFechaLabel.isEnabled = false
                idNumeroDeOrasLabel.isEnabled = false
                idNumeroDePersonasLabel.isEnabled = false
                idPhoneNumberLabel.isEnabled = false
                idSalaLabel.isEnabled = false

                idReservaLabel.text = "Reserva"

                editableText = !editableText
                uploadButton.hide()
            }
            else {

                //editable mode
                idReservaLabel.isEnabled = true
                idNombreLabel.isEnabled = true
                idApellidoLabel.isEnabled = true
                idComentarioLabel.isEnabled = true
                idEmailLabel.isEnabled = true
                idDNILabel.isEnabled = true
                idFechaLabel.isEnabled = true
                idNumeroDeOrasLabel.isEnabled = true
                idNumeroDePersonasLabel.isEnabled = true
                idPhoneNumberLabel.isEnabled = true
                idSalaLabel.isEnabled = true

                idReservaLabel.text = "Edit Reserva!"
                uploadButton.show()

                editableText = !editableText
            }

        }
        uploadButton.setOnClickListener() {
            apiService.putReservation(
                Reserva(
                    idNombreLabel.text.toString(),
                    idApellidoLabel.text.toString(),
                    idDNILabel.text.toString(),
                    idEmailLabel.text.toString(),
                    Date().toString(),
                    idSalaLabel.text.toString(),
                    idNumeroDeOrasLabel.text.toString(),
                    idPhoneNumberLabel.text.toString(),
                    idComentarioLabel.text.toString(),
                    idNumeroDePersonasLabel.text.toString()),
                onResponse = { response ->
                    // Manejar la respuesta exitosa aquí
                    response?.let {
                        val message = it.getString("Resersa correctly updated")
                        Toast.makeText(this, "Success: " + message, Toast.LENGTH_SHORT).show()
//                        textView.text = message
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
}