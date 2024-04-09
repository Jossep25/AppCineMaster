package pe.edu.idat.appmastercine
import android.content.Intent
import androidx.navigation.fragment.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appmastercine.classCard.CardAdapter
import pe.edu.idat.appmastercine.classCard.CardModel
import pe.edu.idat.appmastercine.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = CardAdapter(getCardData()) { card: CardModel ->
            showCardDetailDialog(card)
        }
        recyclerView.adapter = adapter
    }
    private fun getCardData(): List<CardModel> {
        val cardList = mutableListOf<CardModel>()
        cardList.add(CardModel("Shang-Chi y la leyenda de los Diez Anillos","Sinopsis ", "El maestro de artes marciales Shang-Chi se enfrenta al pasado que creía haber dejado atrás cuando se ve envuelto en la red de la misteriosa organización de los Diez Anillos.", R.drawable.shang,R.drawable.shang2))
        cardList.add(CardModel("Batman: el caballero de la noche","Sinopsis ", "Bruce Wayne regresa para continuar su guerra contra el crimen. Con la ayuda del teniente Jim Gordon y del Fiscal Harvey Dent, Batman se propone destruir el crimen organizado en la ciudad de Gotham. El triunvirato demuestra su eficacia, pero, de repente, aparece Joker, un nuevo criminal que desencadena el caos y tiene aterrados a los ciudadanos.", R.drawable.batman,R.drawable.batmann))
        cardList.add(CardModel("Guasón","Sinopsis ", "Arthur Fleck adora hacer reír a la gente, pero su carrera como comediante es un fracaso. El repudio social, la marginación y una serie de trágicos acontecimientos lo conducen por el sendero de la locura y, finalmente, cae en el mundo del crimen.", R.drawable.guason,R.drawable.joker))
        cardList.add(CardModel("Nosotros","Sinopsis ", "Adelaide Wilson, su marido y sus dos hijos visitan la casa en la que ella creció junto a la playa. Allí, Adelaide tiene un presentimiento siniestro que precede a un encuentro espeluznante: cuatro enmascarados se presentan ante su casa. Cuando se quitan las máscaras, la familia puede ver que los individuos son idénticos a ellos.", R.drawable.nosotros,R.drawable.us))
        return cardList
    }
    private fun showCardDetailDialog(card: CardModel) {
        val intent = Intent(requireContext(), CardDetailActivity::class.java).apply {
            putExtra("CARD_IMAGE", card.imageResId2)
            putExtra("CARD_SINOPSIS", card.sinopsis)
            putExtra("CARD_DESCRIP", card.description)
        }
        startActivity(intent)
    }
}