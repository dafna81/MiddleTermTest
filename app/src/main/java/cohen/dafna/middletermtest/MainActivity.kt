package cohen.dafna.middletermtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import cohen.dafna.middletermtest.databinding.ActivityMainBinding
import cohen.dafna.middletermtest.ui.SplashScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.fragment_splash_screen, R.id.fragment_main
        ))
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, SplashScreen())
//                .commitNow()
//        }

        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}