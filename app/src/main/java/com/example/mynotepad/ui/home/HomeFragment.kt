package com.example.mynotepad.ui.home

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.telecom.Call
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.auth.AppAuth
import com.example.data.network.models.CalendarFull
import com.example.data.network.models.SingleUser
import com.example.data.network.models.UsersTest
import com.example.data.network.retrofit.RetrofitClient
import com.example.data.network.retrofit.interfaces.RetrofitService
import com.example.domain.models.Day
import com.example.domain.models.Todo
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentHomeBinding
import com.example.mynotepad.ui.dashboard.ItemRowCalendar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.trySendBlocking

import kotlinx.coroutines.launch
import net.openid.appauth.AppAuthConfiguration
import net.openid.appauth.AuthorizationService
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date



class HomeFragment : Fragment(), CustomAdapter.RecyclerItemListener {


    private var _binding: FragmentHomeBinding? = null
private val homeViewModel by viewModel<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //var year= arrayListOf<ArrayList<ArrayList<Day>>>()
    var rowsCalendar =listOf<ItemRowCalendar>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.loadCalendars(2024, 2025)
    }
    override  fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val displayMetrics= context?.resources?.displayMetrics
        val size=Point()
       val widthBox=size.x/7
        var heightBox:Int?
        if (displayMetrics!=null){
         heightBox= displayMetrics.heightPixels/8}else{
             heightBox=null
        }
        val colorRed=resources.getColor(R.color.red,  null) ///////////////////////////////
        val color=Color.RED


//        Log.v("homeFragm", "colorRed=$colorRed")
//        Log.v("homeFragm", "color=$color")
        Log.v("homeFragm", "heightBox=$heightBox")
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val toolbarCalendar=binding.toolbarCalendar

//        val appBarConfiguration = AppBarConfiguration(findNavController().graph, drawerLayout = binding.drawerLayout)
//
//
//        toolbarCalendar.setupWithNavController(findNavController(), appBarConfiguration)

        //homeViewModel.loadCalendars(2024, 2024)
        //CoroutineScope(Job()).launch {

        //}
        //year= homeViewModel.years



      //  rowsCalendar=homeViewModel.rowsCalendar

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


/*
        val floatingButton: FloatingActionButton=binding.floatingActionButton
        floatingButton.setOnClickListener(){

            homeViewModel.openPageOauth()


            val retrofitService=RetrofitService.create()
               // .getCalendarList()


          //  retrofitService
            //    .getCalendarList()

                //.getSingleUser()
              //  .enqueue(object : Callback<CalendarFull> {
//                override fun onResponse(
//                    call: retrofit2.Call<CalendarFull>,
//                    response: Response<CalendarFull>
//                ) {
//                    Log.v("homefragment","homefragment response.body()=${response.body()}" )
//                }
//
//                override fun onFailure(call: retrofit2.Call<CalendarFull>, t: Throwable) {
//                    Log.v("homefragment","homefragment onFailure t=${t}" )
//                }

            //})
//            retrofitService.enqueue(object: Callback<List<UsersTest>> {
//               override fun onResponse(call: Call<List<UsersTest>>?, response: Res)
//            })

//            val rightNow  = Calendar.getInstance()
//rightNow.set(2023,5,1)
//            val lenghtMont=rightNow.getActualMaximum(Calendar.DAY_OF_MONTH)
//            val dayOfWeek= rightNow.get(Calendar.DAY_OF_WEEK)
//            val month= rightNow.get(Calendar.YEAR)
//            Log.v("a","homefragment lenghtMont=${lenghtMont}" )
//            Log.v("a","homefragment dayOfWeek=${dayOfWeek}" )
//            Log.v("a","homefragment month=${month}" )
        //    findNavController().navigate(R.id.action_navigation_home_to_myFragment ,null)
       }
*/
        val monthsName = arrayOf("January", "February", "March", "May", "June" ,"Jule", "March", "February", "March", )
        val monthsNameRu = listOf("Январь", "Февраль", "Март", "Апрель", "Май" ,"Июнь", "Июль", "Август", "Сентябрь","Октябрь","Ноябрь", "Декабрь" )

        homeViewModel.rowsCalendarLiveData.observe(viewLifecycleOwner, Observer<List<ItemRowCalendar>> {rowsCalendar=it

            val customAdapter= context?.let { it1 ->
                CustomAdapter(rowsCalendar, monthsNameRu, homeViewModel, this, heightBox,
                    it1
                )
            }
            recyclerView.adapter=customAdapter
        })



//        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//recyclerView.layoutManager =linearLayoutManager


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Log.v("HomFr", "onDestroyView")
        _binding = null
    }

    override fun onItemClick(idDay: Long, describe: String ) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToEditDay2(idDay, describe =describe ))
    }
}
class CustomAdapter(
    private val dataSet:List <ItemRowCalendar>,
    private val monthsNameRu:List<String>,
    private val homeViewModel: HomeViewModel,
    private val listener: RecyclerItemListener,
    private val heightBox:Int?,
    private val context: Context,



    ):
        RecyclerView.Adapter<CustomAdapter.ViewHolder >(){
    private  val colorBackgr:Int
    private  val colorBackgrWeekend:Int
    private  val colorBackgrWeekendIsNotCurrent:Int
    private  val colorBackgrIsNotCurrent:Int
    private  val colorTextWeekendIsNotCurent:Int
    private  val colorTextWeekend:Int
    private  val colorTextIsCurrent:Int
    private  val colorTextIsNotCurrent:Int
            init {
                colorBackgr=ContextCompat.getColor(context, R.color.gray )
                colorBackgrIsNotCurrent=ContextCompat.getColor(context, R.color.black50 )
                colorBackgrWeekend=ContextCompat.getColor(context, R.color.red)
               // colorBackgrWeekend=ContextCompat.getColor(context, R.color.red)
                colorBackgrWeekendIsNotCurrent=ContextCompat.getColor(context, R.color.black50 )
                colorTextWeekendIsNotCurent=ContextCompat.getColor(context, R.color.red )
                colorTextWeekend=ContextCompat.getColor(context, R.color.red )
                colorTextIsCurrent=ContextCompat.getColor(context, R.color.white )
                colorTextIsNotCurrent=ContextCompat.getColor(context, R.color.black )

            }

    interface RecyclerItemListener{
        fun onItemClick(idDay: Long, describe:String  )
    }

    private val weekDays=  listOf<String>(
        "пн","вт","ср","чт","пт","сб","вс"

//        context.getString(R.string.mondayShort),
//        context.getString(R.string.tuesdayShort),
//        context.getString(R.string.wednShort),
//        context.getString(R.string.thursdayShort),
//        context.getString(R.string.fridayShort),
//        context.getString(R.string.saturdayShort),
//        context.getString(R.string.sundayShort),
    )
         inner   class ViewHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener {
             // val recyclerView: RecyclerView
             val textViewMonth: TextView
             val textViewYear: TextView
             val textViewWeek: TextView
             //val days: TableRow
            // val cardViewDay6: CardView
             val rowDays: ConstraintLayout
             val daysView: List<TextView>
             val describesDayView: List<TextView>
             val cardViewDays: List<CardView>
             //val widthBox:Int


             init {
//                 widthBox=view.width/7
//                 Log.v("homeFragm", "widthBox=$widthBox")

                 textViewMonth = view.findViewById(R.id.month)
                 textViewYear = view.findViewById(R.id.eyar)
                 textViewWeek = view.findViewById(R.id.weekDay)
                 //days = view.findViewById(R.id.days)
                 daysView = listOf<TextView>(
                     view.findViewById(R.id.dayItemRow1),
                     view.findViewById(R.id.dayItemRow2),
                     view.findViewById(R.id.dayItemRow3),
                     view.findViewById(R.id.dayItemRow4),
                     view.findViewById(R.id.dayItemRow5),
                     view.findViewById(R.id.dayItemRow6)
                 )
//                 cardViewDay6 = view.findViewById(R.id.cardViewDay6)
//                 cardViewDay6.layoutParams.width =widthBox
                 rowDays = view.findViewById(R.id.rowDays)////////////////////
                 describesDayView = listOf(
                     view.findViewById(R.id.describeDay1),
                     view.findViewById(R.id.describeDay2),
                     view.findViewById(R.id.describeDay3),
                     view.findViewById(R.id.describeDay4),
                     view.findViewById(R.id.describeDay5),
                     view.findViewById(R.id.describeDay6),
                 )
                 cardViewDays = listOf(
                     view.findViewById(R.id.cardViewDay1),
                     view.findViewById(R.id.cardViewDay2),
                     view.findViewById(R.id.cardViewDay3),
                     view.findViewById(R.id.cardViewDay4),
                     view.findViewById(R.id.cardViewDay5),
                     view.findViewById(R.id.cardViewDay6),
                 )


             }

             override fun onClick(p0: View?) {

//                 val position = adapterPosition
//                 if (position != RecyclerView.NO_POSITION) {
//                     listener.onItemClick(position)
//                 }
             }
         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_calend_row, parent, false)



       //val textItem=LayoutInflater.from(parent.context).findViewById<TextView>(R.id.textView2)
       // val recyclerViewDaysRow = parent.findViewById<RecyclerView>(R.id.days)



        return ViewHolder(view )
    }

    override fun getItemCount(): Int =(dataSet.size)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            //var days : ArrayList<Day> = dataSet[position]


        //val recyclerViewDaysRow = holder.itemView.findViewById<RecyclerView>(R.id.days)

        // val textViewMonth = holder.itemView.findViewById<TextView>(R.id.textViewMonth)
        //Log.v("a","homeFragm  monthsName= $monthsName" )
        //textViewMonth.setText(monthsNameRu[position])

        when (dataSet[position].varTitle) {
            1 -> {
                holder.rowDays.visibility=View.VISIBLE

                holder.textViewWeek.text=weekDays[dataSet[position].dayOfWeek-1]
                holder.textViewYear.text=""
                holder.textViewMonth.text=""
//                holder. dayItemRow1.text=dataSet[position].days[0].dayNum.toString()
//                holder. dayItemRow2.text=dataSet[position].days[1].dayNum.toString()
//                holder. dayItemRow3.text=dataSet[position].days[2].dayNum.toString()
//                holder. dayItemRow4.text=dataSet[position].days[3].dayNum.toString()
//                holder. dayItemRow5.text=dataSet[position].days[4].dayNum.toString()
//                holder. dayItemRow6.text=dataSet[position].days[5].dayNum.toString()
                if (dataSet[position].days.size<6){
                    holder.cardViewDays[5].visibility=View.GONE
                }else{
                    holder.cardViewDays[5].visibility=View.VISIBLE
                }
               // val heightBox= (holder.cardViewDays.first().width*1.3).roundToInt()

                for (d in 0..dataSet[position].days.size-1){
                    holder.daysView[5].text=""
                    holder.describesDayView[5].text=""
                    holder.daysView[d].text=dataSet[position].days[d].dayNum.toString()
                    if (heightBox!=null){
                    holder.cardViewDays[d].layoutParams.height =heightBox}
                   when(dataSet[position].days[d].isWeekend) {
                       true -> {
                           when (dataSet[position].days[d].isCurrentMonth) {
                               true -> {
                                   holder.cardViewDays[d].setCardBackgroundColor(colorBackgrWeekend)
                                   holder.daysView[d].setTextColor(colorTextIsCurrent)
                               }

                               false -> {
                                   holder.cardViewDays[d].setCardBackgroundColor(colorBackgrWeekendIsNotCurrent)
                                   holder.daysView[d].setTextColor(colorTextIsNotCurrent)
                               }
                           }


                       }
                       false -> {
                           when (dataSet[position].days[d].isCurrentMonth) {
                               true -> {
                                   holder.daysView[d].setTextColor(colorTextIsCurrent)
                                   holder.cardViewDays[d].setCardBackgroundColor( colorBackgr)
                               }
                               false -> {
                                   holder.daysView[d].setTextColor(colorTextIsNotCurrent)
                                   holder.cardViewDays[d].setCardBackgroundColor( colorBackgrIsNotCurrent)
                               }
                           }
                       }
                   }
                    if (!dataSet[position].days[d].todos.isEmpty()){
                    holder.describesDayView[d].text=dataSet[position].days[d].todos.first().describe}else {
                        holder.describesDayView[d].text=""
                    }

                   // if (d==5 && !dataSet[position].days.last().isCurrentMonth) holder.daysView[d].text=""
                    holder.cardViewDays[d].setOnClickListener(){

                        val date= Date(dataSet[position].days[d].date)
                        val formattedDateAsDigitMonth = SimpleDateFormat("dd/MM/yyyy")
                        val calend = Calendar.getInstance()
                        calend.setTimeInMillis(dataSet[position].days[d].date)
                        val todos = listOf<Todo>(Todo(
                            dateLong = 2222222,
                            timeStart = 10,
                            timeEnd = 20,
                            describe = "todo describe example",
                            id = 0
                        ))
                        var day=dataSet[position].days[d]
                        CoroutineScope(Job()).launch{
                            Log.v("a","homeFragm  days[position].date= ${formattedDateAsDigitMonth.format(date)}" )


                        }
                        listener.onItemClick(dataSet[position].days[d].date, describe =dataSet[position].days[d].describe )

                    }
                }



               // val text=TextView(this, )

               // holder.days.addView(holder.day)
//              val adapter= DaysRowAdapter(dataSet[position].days, context )
//                   holder.recyclerView.adapter=adapter


            }
               // textViewMonth.setText("dd")

            2 -> {
                holder.rowDays.visibility=View.GONE

                holder.textViewMonth.text = monthsNameRu[dataSet[position].monthOfYear-1]
                holder.textViewYear.text=""
                holder.textViewWeek.text=""
//                holder.textViewWeek
//                for (d in 0..5){
//                    holder.daysView[d].text=""
//
//                }

               // holder.t.text="dynamic text"
//                val adapter= DaysRowAdapter(arrayListOf(), context )
//                holder.recyclerView.adapter=adapter
//                 val text= TextView(this.context)
//                text.text="dynamic"
            }
            3 -> {
                holder.rowDays.visibility=View.GONE
                holder.textViewYear.text = dataSet[position].year.toString()
                holder.textViewMonth.text=""
                holder.textViewWeek.text=""
//
//                for (d in 0..5){
//                    holder.daysView[d].text=""
//
//                }
//                val adapter= DaysRowAdapter(arrayListOf(), context )
//                holder.recyclerView.adapter=adapter
            }

            else -> {
                holder.textViewMonth.setText("")
            }
        }




//        holder.monthName.text=dataSet[position]
//        holder.monthName.setOnClickListener { Log.v("recyclerView", "on click position=$position") }
//        holder.itemRow.setOnClickListener{Log.v("recyclerView", "on click view position=$position")}
      //  holder.itemView






    }

}

class DaysRowAdapter  (
    private val days: ArrayList<Day>,
    private val context: Context,
    private val navController: NavController
):RecyclerView.Adapter <DaysRowAdapter.ViewHolderDays >(){
    class ViewHolderDays (view: View):RecyclerView.ViewHolder(view){
       val textViewDay: TextView
       init {
           textViewDay=view.findViewById(R.id.textView2)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDays {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.table_row_item, parent, false)
        return ViewHolderDays(view)
    }

    override fun getItemCount(): Int = (days.size)


    override fun onBindViewHolder(holder: ViewHolderDays, position: Int) {
       holder.textViewDay.setText(days[position].dayNum.toString())
        Log.v("a","homeFragm  days[position].dayNum= ${days[position].dayNum}  days.size= ${days.size}"  )
        holder.textViewDay.setOnClickListener(){
            val date= Date(days[position].date)
            val formattedDateAsDigitMonth = SimpleDateFormat("dd/MM/yyyy")



            Log.v("a","homeFragm  days[position].date= ${formattedDateAsDigitMonth.format(date)}" )
        }
    }

}

//class CustomGridAdapter (
//    private val context: Context,
//    private val days: List<Day>,
//    private val monthsName: String
//): BaseAdapter(){
//    private val weekDays=  listOf<String>(
//        context.getString(R.string.mondayShort),
//        context.getString(R.string.tuesdayShort),
//        context.getString(R.string.wednShort),
//        context.getString(R.string.thursdayShort),
//        context.getString(R.string.fridayShort),
//        context.getString(R.string.saturdayShort),
//        context.getString(R.string.sundayShort),
//    )
//
//    private var layoutInflater: LayoutInflater? = null
//    override fun getCount(): Int {
//       return days.size
//    }
//
//    override fun getItem(pisitioin: Int): Any? {
//        TODO("Not yet implemented")
//    return null
//    }
//
//    override fun getItemId(p0: Int): Long {
//      //  TODO("Not yet implemented")
//    return 0
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//       // TODO("Not yet implemented")
//        var convertView = convertView
//        if (layoutInflater == null) {
//            layoutInflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        }
//        if (convertView == null) {
//            convertView = layoutInflater!!.inflate(R.layout.table_row_item, null)
//        }
//        val textItem= convertView!!.findViewById<TextView>(R.id.textView2)
//
//        var dayNum=""
//        if (days[position].dayNum > 0){
//            dayNum = days[position].dayNum.toString()
//            textItem.setText(dayNum)
//        }else{
//            textItem.setText(weekDays[days[position].dayNum+6 ] )
//            textItem.setTextSize(15f)
//        }
//
//
//        if (days[position].isWeekend) textItem.setTextColor(Color.RED)
//        if (!days[position].isCurrentMonth) textItem.setTextColor(Color.GRAY)
//
//        convertView.setOnClickListener {
//            val date= Date(days[position].date)
//            val formattedDateAsDigitMonth = SimpleDateFormat("dd/MM/yyyy")
//
//
//            Log.v("a","homeFragm  days[position].date= ${formattedDateAsDigitMonth.format(date)}" )
//        }
//        return convertView
//
//
//    }

//}




/*

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
    private val weekDays=  listOf<String>(
        context.getString(R.string.mondayShort),
        context.getString(R.string.tuesdayShort),
        context.getString(R.string.wednShort),
        context.getString(R.string.thursdayShort),
        context.getString(R.string.fridayShort),
        context.getString(R.string.saturdayShort),
        context.getString(R.string.sundayShort),
    )

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
        if (days[position].dayNum > 0){
            dayNum = days[position].dayNum.toString()
            textItem.setText(dayNum)
        }else{
            textItem.setText(weekDays[days[position].dayNum+6 ] )
            textItem.setTextSize(15f)
        }


        if (days[position].isWeekend) textItem.setTextColor(Color.RED)
        if (!days[position].isCurrentMonth) textItem.setTextColor(Color.GRAY)

        convertView.setOnClickListener {
            val date= Date(days[position].date)
            val formattedDateAsDigitMonth = SimpleDateFormat("dd/MM/yyyy")


            Log.v("a","homeFragm  days[position].date= ${formattedDateAsDigitMonth.format(date)}" )
        }
        return convertView


    }

}


 */
