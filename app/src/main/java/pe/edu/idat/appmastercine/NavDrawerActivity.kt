package pe.edu.idat.appmastercine

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.navigation.NavigationView
import pe.edu.idat.appmastercine.fragment.HomeFragment
import pe.edu.idat.appmastercine.fragment.ProductFragment
import pe.edu.idat.appmastercine.fragment.ProfileFragment

class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        if (savedInstanceState == null){
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_profile -> replaceFragment(ProfileFragment())
            R.id.nav_product -> replaceFragment(ProductFragment())
            R.id.nav_logout -> logout()
            R.id.nav_quienessomos -> replaceFragment(QuienesSomosFragment())
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_cerrar, null)
        val animationView = dialogView.findViewById<LottieAnimationView>(R.id.animationView)
        val messageTextView = dialogView.findViewById<TextView>(R.id.txtCerrar)
        animationView.setAnimation("warning.json")
        animationView.playAnimation()
        messageTextView.text = "¿Deseas cerrar sesión?"
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
            .setPositiveButton("Si") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}