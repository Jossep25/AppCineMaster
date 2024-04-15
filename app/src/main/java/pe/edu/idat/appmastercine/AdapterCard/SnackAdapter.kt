package pe.edu.idat.appmastercine.AdapterCard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.CardDetailSnacks
import pe.edu.idat.appmastercine.R
import pe.edu.idat.appmastercine.model.Snack

class SnackAdapter(private var snacks: List<Snack>) : RecyclerView.Adapter<SnackAdapter.SnackViewHolder>() {
    inner class SnackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewSnack)
        val nombreSnack : TextView = itemView.findViewById(R.id.textSnackName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_snack_layout, parent, false)
        return SnackViewHolder(view)
    }
    override fun getItemCount(): Int {
        return snacks.size
    }
    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {
        val snack = snacks[position]
        holder.nombreSnack.text = snack.nombre
        Glide.with(holder.itemView.context).load(snack.image).into(holder.imageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,CardDetailSnacks::class.java).apply {
                putExtra("Snacks", snack)
            }
            holder.itemView.context.startActivity(intent)
        }
    }
    fun setData(snacks: List<Snack>) {
        this.snacks = snacks
        notifyDataSetChanged()
    }
}