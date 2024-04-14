package pe.edu.idat.appmastercine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.classCard.Pelicula
import pe.edu.idat.appmastercine.databinding.ActivityCardDetailBinding

class CardDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)
        val ibRegresar: ImageButton = findViewById(R.id.ibRegresar)
        ibRegresar.setOnClickListener {
            finish()
        }
        val pelicula: Pelicula = intent.getParcelableExtra("pelicula")!!
        findViewById<TextView>(R.id.textViewDescription).text = pelicula.sinopsis
        findViewById<TextView>(R.id.textViewCo2).text = pelicula.pais
        findViewById<TextView>(R.id.txtClasificacion).text = pelicula.clasificacion
        findViewById<TextView>(R.id.textViewMin1).text = pelicula.duracion
        findViewById<TextView>(R.id.textViewFecha2).text = pelicula.fecha
        findViewById<TextView>(R.id.textViewGender2).text = pelicula.genero

        Glide.with(this)
            ?.load(pelicula?.imagen_principal)
            ?.override(500, 239)
            ?.into(findViewById(R.id.imvPeli))

        if (!pelicula?.imagen_secundaria.isNullOrEmpty()) {
            Glide.with(this)
                ?.load(pelicula?.imagen_secundaria)
                ?.override(500, 239)
                ?.into(findViewById(R.id.imvPeli))
        } else {
            findViewById<ImageView>(R.id.imvPeli)?.visibility = View.GONE
        }
    }
}