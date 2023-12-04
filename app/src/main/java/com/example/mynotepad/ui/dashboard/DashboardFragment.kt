package com.example.mynotepad.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentDashboardBinding
import com.example.mynotepad.ui.home.HomeViewModel
import com.example.mynotepad.ui.home.MyModel
import com.example.mynotepad.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
private val myModel=MyModel(wname = "model1" , age = 12 )
//  private  val homeViewModel=
//        ViewModelProvider(this).get(HomeViewModel::class.java)
    private var _binding: FragmentDashboardBinding? = null
private val viewModel by viewModel<DashboardViewModel>()

    private val homeViewModel by viewModel<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel=
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DashboardViewModel::class.java)



        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonDashboard: Button = binding.buttonDashboard
       // buttonDashboard.setText("text")
        buttonDashboard.setOnClickListener{
//            Log.v("a","ClickListener" )
//            homeViewModel. setModel(myModel)
//findNavController().navigate(R.id.action_navigation_dashboard_to_myFragment )


           viewModel.addNote("newText")
        }
        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val textLenghtList=binding.textLenghtList
        //textLenghtList.text=
        viewModel.notes.observe( viewLifecycleOwner){
                notes->
            Log.v("a","Observe viewModel ${notes.size}" )
textLenghtList.text=notes.size.toString()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}