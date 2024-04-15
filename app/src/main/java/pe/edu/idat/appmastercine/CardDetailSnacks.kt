package pe.edu.idat.appmastercine

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import pe.edu.idat.appmastercine.classCard.Snack

class CardDetailSnacks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack_card_detail)
        val ibRegresar: ImageButton = findViewById(R.id.ibRegresar1)
        ibRegresar.setOnClickListener {
            finish()
        }
        val linearLayout = findViewById<LinearLayout>(R.id.ln2)
        linearLayout.setOnClickListener{
            finish()
        }
        val snack: Snack = intent.getParcelableExtra("Snacks") !!
        findViewById<TextView>(R.id.textViewSnackName).text = snack.nombre
        findViewById<TextView>(R.id.textViewDescripcionSnack).text = snack.descripcion
        findViewById<TextView>(R.id.textViewPrecio).text = snack.price.toString()

        Glide.with(this)
            ?.load(snack?.image)
            ?.override(640,639)
            ?.into(findViewById(R.id.imViewSnack))

        if (!snack?.image.isNullOrEmpty()) {
            Glide.with(this)
                ?.load(snack?.image)
                ?.override(640,639)
                ?.into(findViewById(R.id.imViewSnack))
        }else {
            findViewById<ImageView>(R.id.imViewSnack)?.visibility = View.GONE
        }
    }
}