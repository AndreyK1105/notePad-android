package com.example.mynotepad.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Note
import com.example.mynotepad.databinding.FragmentDashboardBinding
import com.example.mynotepad.ui.editnote.EditNoteArgs
import com.example.mynotepad.ui.home.HomeViewModel
import com.example.mynotepad.ui.home.MyModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), AdapterNotes.RecyclerItemListener {
private val myModel=MyModel(wname = "model1" , age = 12 )
//  private  val homeViewModel=
//        ViewModelProvider(this).get(HomeViewModel::class.java)
    private var _binding: FragmentDashboardBinding? = null
private val viewModel by viewModel<DashboardViewModel>()

    private val homeViewModel by viewModel<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.

    var notes: List<Note> = listOf(Note(1,"qqq",0))
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

val arg= EditNoteArgs(-1)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var idLast:Int=0

        val notesAdapter=AdapterNotes(notes,this)
        val recyclerViewNotes:RecyclerView=binding.recyclerViewNotes

        recyclerViewNotes.adapter=notesAdapter
        viewModel.notes.observe(viewLifecycleOwner){
                value->notes=value
            Log.v("a","dashboarFr List Notes size ${notes.size}" )
            recyclerViewNotes.adapter=AdapterNotes(value, this)

           // idLast=notes[notes.lastIndex].id
        }
       // recyclerViewNotes.adapter.

        val bundle=Bundle()


        val floatingButton=binding.floatingAddNote
        floatingButton.setOnClickListener{
           // val action=
           // bundle.putInt("idNote", -1)
            findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToMyFragment())
        }
//        val buttonDashboard: Button = binding.button
//       // buttonDashboard.setText("text")
//        buttonDashboard.setOnClickListener{
////            Log.v("a","ClickListener" )
////            homeViewModel. setModel(myModel)
////
////
//          viewModel.addNote("newText")
////          //  viewModel.delNote(1)
//        }
//
//        val buttonDel: Button = binding.button2
//        buttonDel.text = "delLast"
//        buttonDel.setOnClickListener{
////            Log.v("a","ClickListener" )
////            homeViewModel. setModel(myModel)
////findNavController().navigate(R.id.action_navigation_dashboard_to_myFragment )
////
//            //viewModel.addNote("newText")
//
//            viewModel.delNote(idLast)
//        }








//        val textLenghtList=binding.textLenghtList
//        //textLenghtList.text=
//        viewModel.notes.observe( viewLifecycleOwner){
//                notes->
//            Log.v("a","Observe viewModel ${notes.size}" )
//textLenghtList.text=notes.size.toString()
//
//        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
val idNote=notes[position].id
        Log.v("a","Click item position=${position}" )
        findNavController().navigate(DashboardFragmentDirections.actionNavigationDashboardToMyFragment(idNote))
    }
}