package es.example.ezroomsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import es.example.ezroomsapp.utils.ReservaAdapter

class ReservaViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_view)

        //get extra
        val id = intent.getStringExtra("id")
        var  apiService = ApiService(this)

        //get reserva
        if (id != null) {

            apiService.getRequestById(id,
                onResponse = { response ->
                    // Manejar la respuesta exitosa aquí
                    response?.let {
                        val jsonArray = response
                        val jsonObject = jsonArray.getJSONObject(0)
                        var reserva = Reserva(
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
                        var textView = findViewById<TextView>(R.id.textView4)
                        textView.text = reserva.nombre
                    }
                },
                onError = { error ->
                    Log.d("Error", error)
                }
            )
        }

        var delete = findViewById<FloatingActionButton>(R.id.delete)
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

        var edit = findViewById<FloatingActionButton>(R.id.edit)
        edit.setOnClickListener {

        }

    }
}