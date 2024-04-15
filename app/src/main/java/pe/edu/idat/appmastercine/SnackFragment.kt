package pe.edu.idat.appmastercine

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.idat.appmastercine.classCard.Snack
import pe.edu.idat.appmastercine.classCard.SnackAdapter
import pe.edu.idat.appmastercine.databinding.FragmentSnackBinding

class SnackFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var snackAdapter: SnackAdapter
    private lateinit var binding: FragmentSnackBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSnackBinding.inflate(inflater, container, false)
        recyclerView = binding.rviewSnacks
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val snacks = obtenerSnacks()
        snackAdapter = SnackAdapter(snacks)
        recyclerView.adapter = snackAdapter

        return binding.root
    }
    private fun obtenerSnacks(): List<Snack>{
        val db = FirebaseDatabase.getInstance().reference.child("Snack")
        val snacks = mutableListOf<Snack>()
        db.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children){
                    val snack = childSnapshot.getValue(Snack::class.java)
                    snack?.let {
                        snacks.add(it)
                    }
                }
                snackAdapter.setData(snacks)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("SnackFragment", "Error al cargar los datos de los snacks: ${error.message}")
            }
        })
        return snacks
    }
}