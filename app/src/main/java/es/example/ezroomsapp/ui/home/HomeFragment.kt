package es.example.ezroomsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.example.ezroomsapp.R
import es.example.ezroomsapp.databinding.FragmentHomeBinding

import es.example.ezroomsapp.utils.ApiService
import es.example.ezroomsapp.utils.Reserva
import es.example.ezroomsapp.utils.Sala
import es.example.ezroomsapp.utils.SalaAdapter
import java.util.Date

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var textView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        (activity as AppCompatActivity).supportActionBar?.title = "Nuestras salas"


        var salas = listOf(
            Sala("Sala Shanghái", "Descripción de sala shanghai", "@drawable/shangai.png", "enlace"),
            Sala("Sala Chernobyl", "Descripción de sala chernobyl", "@drawable/chernobyl.png", "enlace"),
            Sala("Sala Apocalipsis", "Descripción de sala apocalipsis", "@drawable/zombie.png", "enlace")
        )
        var adapter = SalaAdapter(salas)

        var recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerSalas)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)




        //Tengo que llamar al
        /*var  apiService = context?.let { ApiService(it) }
        apiService?.getRequest(
            onResponse = { response ->
                // Manejar la respuesta exitosa aquí
                response?.let {
                    val message = it.getString("message")
                    Toast.makeText(context, "Success: " + message, Toast.LENGTH_SHORT).show()
                    textView.text = message
                }
            },
            onError = { error ->
                // Manejar errores aquí
                   Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    textView.text = error
            }
        )

       /* apiService?.postReservation(
            Reserva("pedro", "garcia", "1234V", "pedro@uca.es", Date().toString(), "Sala Shangai", "2", "123423542", "Carlos Maricon", "2"),
            onResponse = { response ->
                // Manejar la respuesta exitosa aquí
                response?.let {
                   // val message = it.getString("message")
                    //Toast.makeText(context, "Success: " + message, Toast.LENGTH_SHORT).show()
                    //textView.text = message
                }
            },
            onError = { error ->
                // Manejar errores aquí
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                textView.text = error
            }
        )*/
    }
}