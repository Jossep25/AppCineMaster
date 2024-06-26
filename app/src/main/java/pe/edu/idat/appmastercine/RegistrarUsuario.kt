package pe.edu.idat.appmastercine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import pe.edu.idat.appmastercine.databinding.ActivityRegistrarUsuarioBinding
import pe.edu.idat.appmastercine.model.UserEntity


class RegistrarUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener {
            val name = binding.edtNombre.text.toString()
            val dni = binding.edtDNI.text.toString()
            val email = binding.edtCorreoE.text.toString()
            val password = binding.edtPassword.text.toString()
            val fechaN = binding.edtFecha.text.toString()
            if (name.isNotEmpty() && dni.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && fechaN.isNotEmpty()) {
                registrarUsuario(name, dni, email, password, fechaN)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun registrarUsuario(name: String, dni: String, email: String, password: String, fechaN: String) {
        val db = FirebaseFirestore.getInstance()
        val newUser = UserEntity(name, dni, email, password, fechaN)
        db.collection("users")
            .add(newUser)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al registrar los datos", Toast.LENGTH_SHORT).show()
                Log.e("Registrar Usuario", "Error al guardar los datos: ${e.message}", e)
            }
    }
}