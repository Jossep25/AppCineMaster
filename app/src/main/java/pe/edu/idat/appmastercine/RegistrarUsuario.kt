package pe.edu.idat.appmastercine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pe.edu.idat.appmastercine.database.UserDbHelper
import pe.edu.idat.appmastercine.databinding.ActivityRegistrarUsuarioBinding
import java.util.Date

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
            if(name.isNotEmpty() && dni.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                registrarUsuario(name, dni,email, password)
            }else{
                Toast.makeText(this, "Por favos complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCancelar.setOnClickListener { finish() }
    }
    private fun registrarUsuario(name: String, dni: String, email: String, password: String){
        val dbHelper = UserDbHelper(this)
        val data = Date()
        val userId = dbHelper.insertUser(name,dni, email,  password, data)
        if(userId != -1L){
            Toast.makeText(this, "Registro con Exito", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Error al registrar datos", Toast.LENGTH_SHORT).show()
        }
    }
}