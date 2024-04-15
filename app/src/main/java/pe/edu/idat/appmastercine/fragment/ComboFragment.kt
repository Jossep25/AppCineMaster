package pe.edu.idat.appmastercine.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.idat.appmastercine.AdapterCard.ProductoAdapter
import pe.edu.idat.appmastercine.databinding.FragmentComboBinding
import pe.edu.idat.appmastercine.model.Producto


class ComboFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentComboBinding
    private lateinit var productoAdapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComboBinding.inflate(inflater, container, false)
        recyclerView = binding.rviewProductos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        productoAdapter = ProductoAdapter(emptyList())
        recyclerView.adapter = productoAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        obtenerProductos()
        return binding.root
    }

    private fun obtenerProductos() {
        val db = FirebaseDatabase.getInstance().reference.child("Producto")
        val productos = mutableListOf<Producto>()
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val producto = childSnapshot.getValue(Producto::class.java)
                    producto?.let {
                        productos.add(it)
                    }
                }
                productoAdapter.setData(productos)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("ComboFragment", "Error al cargar datos")
            }
        })
    }
}