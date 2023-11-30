package com.example.mynotepad.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
private val homeViewModel by viewModel<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//val buttonHome: Button=binding.buttonHome
//        buttonHome.setOnClickListener{
//            homeViewModel.setModel(model = MyModel("www", 12))

     //   findNavController().navigate(R.id.action_navigation_home_to_myFragment ,null)
       // Navigation.createNavigateOnClickListener(R.id.navigation_dashboard  ,null )
//        }
//        val textView: TextView = binding.editTextText
//        homeViewModel.myModel.observe(viewLifecycleOwner) {
//            textView.text = it.name
//        }
        val recyclerView:RecyclerView=binding.recyclerView

        val floatingButton: FloatingActionButton=binding.floatingActionButton
        floatingButton.setOnClickListener(){
            findNavController().navigate(R.id.action_navigation_home_to_myFragment ,null)
        }

        val dataset = arrayOf("January", "February", "March", "February", "March" ,"February", "March", "February", "March")
        val customAdapter=CustomAdapter(dataset)

//        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//recyclerView.layoutManager =linearLayoutManager
        recyclerView.adapter=customAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v("HomFr", "onDestroyView")
        _binding = null
    }
}
class CustomAdapter(private val dataSet: Array<String>):
        RecyclerView.Adapter<CustomAdapter.ViewHolder >(){
            class ViewHolder(view:View):RecyclerView.ViewHolder(view){
                val textView: TextView
                val itemRow = view
                init {

                    textView=view.findViewById(R.id.textView)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  Log.v("recyclerView", "position=$position")
        holder.textView.text=dataSet[position]
        holder.textView.setOnClickListener { Log.v("recyclerView", "on click position=$position") }
        holder.itemRow.setOnClickListener{Log.v("recyclerView", "on click view position=$position")}
        holder.itemView
    }
}