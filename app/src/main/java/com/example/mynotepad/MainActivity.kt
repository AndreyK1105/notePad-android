package com.example.mynotepad

import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mynotepad.app.App
import com.example.mynotepad.databinding.ActivityMainBinding
import com.example.mynotepad.ui.dashboard.DashboardViewModel
import com.example.mynotepad.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity()  {
   // private val dashboardViewModel by viewModel<DashboardViewModel>()
   //  val  homeViewModel=
     //   ViewModelProvider(this).get(HomeViewModel::class.java)
    private lateinit var binding: ActivityMainBinding
private val currFr: Fragment
    get() = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val toolbar=binding.toolbar2
//        toolbar.setTitle("title")
//
//       // toolbar.inflateMenu(R.menu.toolbar_menu)
//        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.notificationsFragment
            )
        )

       // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
       // appBarConfiguration.fallbackOnNavigateUpListener
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}