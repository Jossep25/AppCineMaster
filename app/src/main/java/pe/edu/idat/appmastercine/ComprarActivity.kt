package pe.edu.idat.appmastercine

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ComprarActivity : AppCompatActivity() {
    private var edtNumeroTarjeta: EditText? = null
    private var edtFechaVencimiento: EditText? = null
    private var edtCodigoSeguridad: EditText? = null
    private var edtNombreTitular: EditText? = null
    private val btnComprar: Button? by lazy { findViewById<Button>(R.id.btnComprar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar)

        edtNumeroTarjeta = findViewById(R.id.edtNumeroTarjeta)
        edtFechaVencimiento = findViewById(R.id.edtFechaVencimiento)
        edtCodigoSeguridad = findViewById(R.id.edtCodigoSeguridad)
        edtNombreTitular = findViewById(R.id.edtNombreTitular)

        edtNumeroTarjeta?.addTextChangedListener(CardNumberTextWatcher())

        val buttonComprar = findViewById<Button>(R.id.btnComprar)
        buttonComprar.setOnClickListener {
            mostrarAlertaCompraExitosa()
        }
    }

    private fun mostrarAlertaCompraExitosa() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_compra, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
        val dialog = builder.create()
        dialogView.findViewById<Button>(R.id.buttonAceptar).setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }

    private fun validarCampos(): Boolean {
        val numeroTarjeta = edtNumeroTarjeta?.text.toString().replace(" ", "")
        val fechaVencimiento = edtFechaVencimiento?.text.toString()
        val codigoSeguridad = edtCodigoSeguridad?.text.toString()
        val nombreTitular = edtNombreTitular?.text.toString()

        if (numeroTarjeta.isEmpty() || numeroTarjeta.length != 16 ||
            fechaVencimiento.isEmpty() || fechaVencimiento.length != 5 ||
            codigoSeguridad.isEmpty() || codigoSeguridad.length != 3 ||
            nombreTitular.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    inner class CardNumberTextWatcher : TextWatcher {
        private var current = ""
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s.toString() != current) {
                val cleaned = s.toString().replace(Regex("[^\\d]"), "")
                val formatted = StringBuilder()
                for (i in cleaned.indices) {
                    if (i % 4 == 0 && i != 0) {
                        formatted.append(" ")
                    }
                    formatted.append(cleaned[i])
                }
                current = formatted.toString()
                edtNumeroTarjeta?.removeTextChangedListener(this)
                edtNumeroTarjeta?.setText(current)
                edtNumeroTarjeta?.setSelection(current.length)
                edtNumeroTarjeta?.addTextChangedListener(this)
            }
        }
    }
}