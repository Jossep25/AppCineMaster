package pe.edu.idat.appmastercine.classCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appmastercine.R

class CardAdapter (private val cardList: List<CardModel>, private val onItemClick: (CardModel) -> Unit) : RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
        holder.itemView.setOnClickListener {
            onItemClick(card)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: CardModel) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
            imageView.setImageResource(card.imageResId)
            textViewTitle.text = card.nombrePeli
        }
    }
}
