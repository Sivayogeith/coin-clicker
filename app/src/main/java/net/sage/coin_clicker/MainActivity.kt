package net.sage.coin_clicker

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import net.sage.coin_clicker.databinding.ActivityMainBinding


val UPGRADES = arrayOf(mapOf("id" to "hm-v2", "name" to "Human Maker v2", "price" to 200, "mul" to "*2", "desc" to "Adds two humans on click to multiplier"),
                        mapOf("id" to "hm-v3","name" to "Human Maker v3", "price" to 300, "mul" to "*5", "desc" to "Adds five humans on click to multiplier")
                        )
val GOAL = 100
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_upgrades, R.id.navigation_goals
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}