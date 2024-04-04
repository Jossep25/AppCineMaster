package pe.edu.idat.appmastercine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import pe.edu.idat.appmastercine.databinding.ActivityRegistrarUsuarioBinding


class RegistrarUsuario : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.btnRegister.setOnClickListener {
            val name = binding.edtNombre.text.toString()
            val dni = binding.edtDNI.text.toString()
            val email = binding.edtCorreoE.text.toString()
            val password = binding.edtPassword.text.toString()
            if (name.isNotEmpty() && dni.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registrarUsuario(name, dni, email, password)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
    private fun registrarUsuario(name: String, dni: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val db = FirebaseFirestore.getInstance()
                    val userData = hashMapOf(
                        "name" to name,
                        "dni" to dni
                    )
                    user?.uid?.let {
                        db.collection("users").document(it)
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Registro con éxito", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error al registrar los datos adicionales", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(baseContext, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
    }
}