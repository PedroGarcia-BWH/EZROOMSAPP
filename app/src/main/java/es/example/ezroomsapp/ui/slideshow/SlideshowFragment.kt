package es.example.ezroomsapp.ui.slideshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import es.example.ezroomsapp.R
import es.example.ezroomsapp.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {
    private lateinit var mMap: GoogleMap

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
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
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mMap.uiSettings.isZoomControlsEnabled = false
            //ESI
            val point = LatLng(36.53753763868538, -6.2016160030775795)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 16f))

            val markerOptions = MarkerOptions()
                .position(point)
                .title("EZROOMS")

            mMap.addMarker(markerOptions)
        }

        val btnTren = activity?.findViewById<Button>(R.id.btnTren)
        btnTren?.setOnClickListener {
            val uri = Uri.parse("https://www.renfe.com/es/es/cercanias/cercanias-cadiz/horarios")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        val btnBus = activity?.findViewById<Button>(R.id.btnBus)
        btnBus?.setOnClickListener {
            val uri = Uri.parse("https://siu.cmtbc.es/es/horarios_lineas_tabla.php?linea=23")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}