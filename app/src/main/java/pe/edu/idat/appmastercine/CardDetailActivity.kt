package pe.edu.idat.appmastercine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.idat.appmastercine.databinding.ActivityCardDetailBinding

class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cardImage = intent.getIntExtra("CARD_IMAGE", R.drawable.logo)
        val cardSino = intent.getStringExtra("CARD_SINOPSIS")
        val cardDescription = intent.getStringExtra("CARD_DESCRIP")

        binding.imvPeli.setImageResource(cardImage)
        binding.textViewSinopsis.text = cardSino
        binding.textViewDescription.text = cardDescription

        binding.ibRegresar.setOnClickListener {
            finish()
        }
    }
}