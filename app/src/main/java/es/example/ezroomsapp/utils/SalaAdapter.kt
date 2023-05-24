package es.example.ezroomsapp.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import es.example.ezroomsapp.R
import java.io.File
import java.io.FileOutputStream

class SalaAdapter(private val salas: List<Sala>) :

    RecyclerView.Adapter<SalaAdapter.SalaViewHolder>() {

        class SalaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tituloTextView: TextView = itemView.findViewById(R.id.title)
            val descripcionTextView: TextView = itemView.findViewById(R.id.description)
            val imagenImageView: ImageView = itemView.findViewById(R.id.roomImage)
            val boton: Button = itemView.findViewById(R.id.button)

        }

        // El método onCreateViewHolder() se encarga de crear una nueva instancia de MascotaViewHolder, inflando el diseño de vista desde
        // el archivo de diseño XML que se proporciona en el parámetro viewType.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalaViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.sala_cardview, parent, false)
            return SalaViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: SalaAdapter.SalaViewHolder, position: Int) {
        val currentSala = salas[position]

        holder.tituloTextView.text = currentSala.titulo
        holder.descripcionTextView.text = currentSala.descripcion


        try {
            holder.imagenImageView.setImageResource(currentSala.img)
        } catch (e: NumberFormatException) {
            // Manejar el error cuando el nombre de archivo no es un número válido
        }

        holder.boton.setOnClickListener {
            val resourceId = currentSala.enlace
            val inputStream = holder.itemView.context.resources.openRawResource(resourceId)
            val outputStream = FileOutputStream(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), currentSala.nombreEnlace))

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            // Opcional: Puedes mostrar una notificación al usuario para indicar que la descarga ha terminado.
            Toast.makeText(holder.itemView.context.applicationContext, "Descarga completada", Toast.LENGTH_SHORT).show()
        }


    }



        // El método getItemCount() devuelve el número de elementos en la lista de Mascotas proporcionado en el constructor de
        // MascotaAdapter.
        override fun getItemCount() = salas.size


    }

