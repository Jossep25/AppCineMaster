package pe.edu.idat.appmastercine.classCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.R

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
    }
    fun setData(snacks: List<Snack>) {
        this.snacks = snacks
        notifyDataSetChanged()
    }
}