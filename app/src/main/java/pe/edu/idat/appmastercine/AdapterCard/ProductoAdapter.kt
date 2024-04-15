package pe.edu.idat.appmastercine.AdapterCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.R
import pe.edu.idat.appmastercine.model.Producto

class ProductoAdapter(private var productos: List<Producto>): RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
    inner class ProductoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProducto)
        val nproducto: TextView = itemView.findViewById(R.id.textCombo)
        val preciopro: TextView = itemView.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_combo, parent, false)
        return ProductoViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        val priceString = producto.price.toString()
        holder.nproducto.text = producto.name
        holder.preciopro.text = priceString
        Glide.with(holder.itemView.context)
            .load(producto.image)
            .override(600, 600)
            .into(holder.imageView)
    }
    override fun getItemCount(): Int {
        return productos.size
    }
    fun setData(productos: List<Producto>){
        this.productos = productos
        notifyDataSetChanged()
    }
}