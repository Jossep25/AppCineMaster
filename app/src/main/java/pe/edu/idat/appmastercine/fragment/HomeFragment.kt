package pe.edu.idat.appmastercine.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import pe.edu.idat.appmastercine.AdapterCard.CardAdapter
import pe.edu.idat.appmastercine.model.Pelicula
import pe.edu.idat.appmastercine.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var cardAdapter: CardAdapter
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = FirebaseDatabase.getInstance().reference.child("peliculas")
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        cardAdapter = CardAdapter(emptyList())
        recyclerView.adapter = cardAdapter
        loadCardData()
    }
    private fun loadCardData() {
        val cardList = mutableListOf<Pelicula>()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val pelicula = childSnapshot.getValue(Pelicula::class.java)
                    pelicula?.let {
                        cardList.add(it)
                    }
                }
                cardAdapter.setData(cardList)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error loading card data: ${error.message}")
            }
        })
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}