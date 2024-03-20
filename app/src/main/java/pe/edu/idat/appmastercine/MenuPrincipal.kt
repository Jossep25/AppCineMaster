package pe.edu.idat.appmastercine

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.appmastercine.classCard.CardAdapter
import pe.edu.idat.appmastercine.classCard.CardModel
import pe.edu.idat.appmastercine.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityMenuPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
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
        val intent = Intent(this, CardDetailActivity::class.java)
        intent.putExtra("CARD_IMAGE", card.imageResId2)
        intent.putExtra("CARD_SINOPSIS", card.sinopsis)
        intent.putExtra("CARD_DESCRIP", card.description)
        val options = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        startActivity(intent, options)
    }
}