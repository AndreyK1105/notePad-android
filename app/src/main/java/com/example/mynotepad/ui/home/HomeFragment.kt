package com.example.mynotepad.ui.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Day
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
private val homeViewModel by viewModel<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var year= arrayListOf<ArrayList<ArrayList<Day>>>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.loadCalendars(1, 2)
        year= homeViewModel.years
       // Log.v("a","homefragment year.size=${year}" )
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
            val rightNow  = Calendar.getInstance()
rightNow.set(2023,5,1)
            val lenghtMont=rightNow.getActualMaximum(Calendar.DAY_OF_MONTH)
            val dayOfWeek= rightNow.get(Calendar.DAY_OF_WEEK)
            val month= rightNow.get(Calendar.YEAR)
            Log.v("a","homefragment lenghtMont=${lenghtMont}" )
            Log.v("a","homefragment dayOfWeek=${dayOfWeek}" )
            Log.v("a","homefragment month=${month}" )
        //    findNavController().navigate(R.id.action_navigation_home_to_myFragment ,null)
        }

        val monthsName = arrayOf("January", "February", "March", "May", "June" ,"Jule", "March", "February", "March", )
        val monthsNameRu = listOf("Январь", "Февраль", "Март", "Апрель", "Май" ,"Июнь", "Июль", "Август", "Сентябрь","Октябрь","Ноябрь", "Декабрь" )


        val customAdapter=CustomAdapter(year[0], monthsNameRu, recyclerView.context)

//        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//recyclerView.layoutManager =linearLayoutManager

        recyclerView.adapter=customAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Log.v("HomFr", "onDestroyView")
        _binding = null
    }
}
class CustomAdapter(private val dataSet:ArrayList <ArrayList<Day>>,private val monthsNameRu:List<String> , private val context: Context ):
        RecyclerView.Adapter<CustomAdapter.ViewHolder >(){
            class ViewHolder(view:View):RecyclerView.ViewHolder(view){
                val gridView: GridView

                init {
                  gridView=view.findViewById(R.id.gridView)

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
       //val textItem=LayoutInflater.from(parent.context).findViewById<TextView>(R.id.textView2)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int =(dataSet.size)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            //var days : ArrayList<Day> = dataSet[position]

        val textViewMonth = holder.itemView.findViewById<TextView>(R.id.textViewMonth)
        //Log.v("a","homeFragm  monthsName= $monthsName" )
        textViewMonth.setText(monthsNameRu[position])
        val monthName=monthsNameRu[position]
            val customGridAdapter=CustomGridAdapter(context, dataSet[position], monthName)
            holder.gridView.adapter=customGridAdapter




//        holder.monthName.text=dataSet[position]
//        holder.monthName.setOnClickListener { Log.v("recyclerView", "on click position=$position") }
//        holder.itemRow.setOnClickListener{Log.v("recyclerView", "on click view position=$position")}
      //  holder.itemView






    }
}

class CustomGridAdapter (
    private val context: Context,
    private val days: List<Day>,
    private val monthsName: String
): BaseAdapter(){
    private var layoutInflater: LayoutInflater? = null
    override fun getCount(): Int {
       return days.size
    }

    override fun getItem(pisitioin: Int): Any? {
        TODO("Not yet implemented")
    return null
    }

    override fun getItemId(p0: Int): Long {
      //  TODO("Not yet implemented")
    return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       // TODO("Not yet implemented")
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.table_row_item, null)
        }
        val textItem= convertView!!.findViewById<TextView>(R.id.textView2)

        var dayNum=""
        if (days[position].data != 0){
            dayNum = days[position].data.toString()
        }

        textItem.setText(dayNum)
        if (days[position].isWeekend) textItem.setTextColor(Color.RED)
        convertView.setOnClickListener {

            Log.v("a","homeFragm  positionGrid= $dayNum" )
        }
        return convertView


    }

}