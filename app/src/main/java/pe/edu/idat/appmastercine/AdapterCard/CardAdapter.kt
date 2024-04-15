package pe.edu.idat.appmastercine.AdapterCard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.CardDetailActivity
import pe.edu.idat.appmastercine.R
import pe.edu.idat.appmastercine.model.Pelicula

class CardAdapter(private var peliculas: List<Pelicula>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.textViewTitle.text = pelicula.titulo
        Glide.with(holder.itemView.context).load(pelicula.imagen_principal).into(holder.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CardDetailActivity::class.java).apply {
                putExtra("pelicula", pelicula)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    fun setData(peliculas: List<Pelicula>) {
        this.peliculas = peliculas
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }
}