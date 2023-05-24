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

            Sala("Sala Shanghái", "La habitación de escape room llamada Shanghái es un emocionante juego de escape diseñado para sumergir a los jugadores " +
                    "en una aventura ambientada en la vibrante y frenética ciudad de Shanghái, China. Los jugadores se encontrarán encerrados en una habitación decorada " +
                    "con elementos temáticos chinos, como farolillos rojos y dragones dorados, mientras buscan pistas y resuelven acertijos para escapar antes de que se " +
                    "acabe el tiempo. Con la música china de fondo y una variedad de desafíos ingeniosos, Shanghai ofrece una experiencia emocionante y desafiante para los" +
                    " amantes de los juegos de escape.", R.drawable.shangai, R.raw.shanghai, "Shanghai.pdf"),
            Sala("Sala Chernobyl", "La habitación de escape room llamada Chernobyl es un juego de escape emocionante que se desarrolla en el escenario de la " +
                    "central nuclear de Chernóbil en Ucrania, después del desastre nuclear ocurrido en 1986. Los jugadores se encuentran encerrados en una habitación que " +
                    "simula el interior de la central nuclear, con elementos decorativos que recrean la atmósfera post-apocalíptica del lugar. Los jugadores deberán buscar " +
                    "pistas, resolver acertijos y tomar decisiones críticas para evitar la exposición a la radiación y escapar antes de que se agote el tiempo. Con un " +
                    "ambiente tenso y desafiante, Chernobyl ofrece una experiencia única y emocionante para los amantes de los juegos de escape con temáticas de ciencia " +
                    "ficción y aventuras post-apocalípticas.", R.drawable.chernobyl, R.raw.chernobyl, "Chernobyl.pdf"),
            Sala("Sala Apocalipsis", "La habitación de escape room ambientada en un bunker en un apocalipsis zombie es un juego de escape emocionante que " +
                    "sitúa a los jugadores en un refugio subterráneo durante un brote de zombies. Los jugadores tendrán que buscar pistas, resolver acertijos y trabajar en " +
                    "equipo para encontrar una salida segura antes de que los zombies los encuentren. El escenario está decorado para parecer un refugio subterráneo, con " +
                    "elementos temáticos como suministros de emergencia y armas improvisadas para defenderse de los zombies.", R.drawable.zombie, R.raw.apocalipsis, "Apocalipsis.pdf")
        )
        var adapter = SalaAdapter(salas)

        var recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerSalas)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)
    }
}