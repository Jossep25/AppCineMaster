package pe.edu.idat.appmastercine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import pe.edu.idat.appmastercine.database.UserDbHelper
import pe.edu.idat.appmastercine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIngresar.setOnClickListener {
            val email = binding.edtCorreo.text.toString()
            val password = binding.edtPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                if (verificarCredenciales(email, password)){
                    val intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Correo o contraseña son incorrectos", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Por favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRegister.setOnClickListener { startActivity(Intent(this, RegistrarUsuario::class.java)) }
    }
    private fun verificarCredenciales(email: String, password: String): Boolean {
        try {
            val dbHelper = UserDbHelper(this)
            return dbHelper.checkUser(email, password)
        } catch (e: Exception) {
            Log.e("UserDbHelper", "Error al verificar credenciales: ${e.message}")
            return false
        }
    }
}