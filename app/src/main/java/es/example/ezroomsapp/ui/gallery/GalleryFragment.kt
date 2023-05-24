package es.example.ezroomsapp.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.example.ezroomsapp.AddReservaActivity
import es.example.ezroomsapp.R
import es.example.ezroomsapp.databinding.FragmentGalleryBinding
import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import es.example.ezroomsapp.utils.ReservaAdapter

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
        val addButon = view?.findViewById<View>(R.id.edit)
        val rvReservas = view?.findViewById<RecyclerView>(R.id.rvReservas)
        rvReservas?.layoutManager = LinearLayoutManager(activity)
        var adapter = ReservaAdapter()
        rvReservas?.adapter = adapter
        addButon?.setOnClickListener {
            val intent = Intent(requireContext(), AddReservaActivity::class.java)
            startActivity(intent)
        }

        val apiService = context?.let { ApiService(it) }
        var reservas: MutableList<Reserva> = mutableListOf()

        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                apiService?.getRequest(
                    onResponse = { response ->
                        // Manejar la respuesta exitosa aquí
                        response?.let {
                            val jsonArray = response

                            val nuevasReservas = mutableListOf<Reserva>() // Crear una nueva lista de reservas

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

                                nuevasReservas.add(reserva)
                            }

                            // Actualizar los datos del adaptador con la nueva lista de reservas
                            (rvReservas?.adapter as? ReservaAdapter)?.setData(nuevasReservas)
                        }
                    },
                    onError = { error ->
                        Log.d("Error", error)
                        Toast.makeText(context, "Error $error", Toast.LENGTH_SHORT).show()
                    }
                )

                handler.postDelayed(this, 5000) // Ejecutar el código cada 5 segundos
            }
        }

        handler.postDelayed(runnable,1)

    }
}