package es.example.ezroomsapp.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.example.ezroomsapp.R
import es.example.ezroomsapp.ReservaViewActivity
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class ReservaAdapter() : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {
    private var reservas: List<Reserva> = emptyList()
    private lateinit var context: Context


    fun setData(list: List<Reserva>) {
        reservas = list
        notifyDataSetChanged()
    }

    fun setContext(context: Context) {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reserva_cardview, parent, false)
        setContext(parent.context)
        return ReservaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = reservas[position]

        holder.nombre.text = "Nombre: ${reserva.nombre} ${reserva.apellidos}"
        holder.sala.text = "Sala: ${reserva.sala}"

        val formatoActual = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH)
        val fechaReserva = formatoActual.parse(reserva.fechaReserva)

        val formatoDeseado = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaFormateada = formatoDeseado.format(fechaReserva)

        val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
        val horaFormateada = formatoHora.format(fechaReserva)

        val horasReserva = reserva.horasReserva.toLong()
        val horaFinal = formatoHora.format(Date(fechaReserva.time + horasReserva * 3600000))

        holder.fecha.text = "Fecha: $fechaFormateada"
        holder.hora.text = "Hora Inicio / Salida: $horaFormateada / $horaFinal"

        holder.seeReserva.setOnClickListener {
            val intent = Intent(context, ReservaViewActivity::class.java)
            intent.putExtra("id", reserva._id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return reservas.size
    }

    class ReservaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nombre = itemView.findViewById<TextView>(R.id.nombre)
        val sala = itemView.findViewById<TextView>(R.id.sala)
        val fecha = itemView.findViewById<TextView>(R.id.fecha)
        val hora = itemView.findViewById<TextView>(R.id.inicioFin)
        val seeReserva = itemView.findViewById<Button>(R.id.see)
    }


}