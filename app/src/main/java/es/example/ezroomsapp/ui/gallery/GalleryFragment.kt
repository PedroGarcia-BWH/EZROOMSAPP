package es.example.ezroomsapp.ui.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import es.example.ezroomsapp.AddReservaActivity
import es.example.ezroomsapp.R
import es.example.ezroomsapp.databinding.FragmentGalleryBinding
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import es.example.ezroomsapp.utils.ReservaAdapter
import org.json.JSONArray

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Inicializar el mapa
        (activity as AppCompatActivity).supportActionBar?.title = "Reserva tu sala"
        val addButon = view?.findViewById<View>(R.id.add)
        val rvReservas = view?.findViewById<RecyclerView>(R.id.rvReservas)
        rvReservas?.layoutManager = LinearLayoutManager(activity)
        var adapter = ReservaAdapter()
        rvReservas?.adapter = adapter
        addButon?.setOnClickListener {
            val intent = Intent(requireContext(), AddReservaActivity::class.java)
            startActivity(intent)
        }

        var  apiService = context?.let { ApiService(it) }
        var reservas: MutableList<Reserva> = mutableListOf()
        apiService?.getRequest(
            onResponse = { response ->
                // Manejar la respuesta exitosa aquí
                response?.let {
                    val jsonArray = response

                    val reservas = mutableListOf<Reserva>() // Crear una nueva lista de reservas

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
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

                        reservas.add(reserva)
                    }

                    // Actualizar los datos del adaptador con la nueva lista de reservas
                    (rvReservas?.adapter as? ReservaAdapter)?.setData(reservas)
                }
            },
            onError = { error ->
                Log.d("Error", error)
            }
        )

    }
}