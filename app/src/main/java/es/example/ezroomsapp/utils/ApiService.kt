package es.example.ezroomsapp.utils

import android.content.Context
import android.net.http.HttpResponseCache.install
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import org.json.JSONArray
import org.json.JSONObject

class ApiService(private val context: Context) {
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    private var url = "http://10.182.109.110:8080/"
    private val gson = Gson()

    fun getRequest(onResponse: (response: JSONArray?) -> Unit, onError: (error: String) -> Unit) {
        val request = JsonArrayRequest(
            Request.Method.GET, url + "reservas/", null,
            Response.Listener { response ->
                onResponse(response)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Unknown error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun getRequestById(_id: String, onResponse: (response: JSONArray?) -> Unit, onError: (error: String) -> Unit) {
        val request = JsonArrayRequest(
            Request.Method.GET, url + "reservas/$_id", null,
            Response.Listener { response ->
                onResponse(response)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Unknown error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun postReservation(
        reserva: Reserva,
        onResponse: (response: JSONObject?) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val requestBody = JSONObject().apply {
            put("nombre", reserva.nombre)
            put("apellidos", reserva.apellidos)
            put("dni", reserva.dni)
            put("phone", reserva.phone)
            put("sala", reserva.sala)
            put("fechaReserva", reserva.fechaReserva)
            put("horasReserva", reserva.horasReserva)
            put("nPersonas", reserva.nPersonas)
            put("comentario", reserva.comentario)
            put("email", reserva.email)
        }

        val request = JsonObjectRequest(Request.Method.POST, url + "reservas/add", requestBody,
            Response.Listener { response ->
                onResponse(response)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Unknown error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun putReservation(
        reserva: Reserva,
        onResponse: (response: JSONObject?) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val requestBody = JSONObject().apply {
            put("nombre", reserva.nombre)
            put("apellidos", reserva.apellidos)
            put("dni", reserva.dni)
            put("phone", reserva.phone)
            put("sala", reserva.sala)
            put("fechaReserva", reserva.fechaReserva)
            put("horasReserva", reserva.horasReserva)
            put("nPersonas", reserva.nPersonas)
            put("comentario", reserva.comentario)
            put("email", reserva.email)
        }

        val request = JsonObjectRequest(Request.Method.PUT, url + "reservas/update/${reserva._id}", requestBody,
            Response.Listener { response ->
                onResponse(response)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Unknown error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun deleteReservation(
        _id: String,
        onResponse: (response: JSONObject?) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val request = JsonObjectRequest(Request.Method.DELETE, url + "reservas/delete/$_id", null,
            Response.Listener { response ->
                onResponse(response)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "Unknown error occurred")
            }
        )
        requestQueue.add(request)
    }

}
