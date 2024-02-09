package com.example.mynotepad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Day
import com.example.domain.models.Todo
import com.example.domain.usecase.AddDayUseCase
import com.example.domain.usecase.GetDayUseCase
import com.example.domain.usecase.GetDaysUseCase
import com.example.mynotepad.ui.dashboard.ItemRowCalendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach


class HomeViewModel (
    private val addDayUseCase: AddDayUseCase,
    private val getDayUseCase: GetDayUseCase,
    private val getDaysUseCase: GetDaysUseCase
): ViewModel() {
    private var model = MyModel(wname = "sss", age = 222222)
    private val _rowsCalendarLiveData=MutableLiveData<List<ItemRowCalendar>>()
    val rowsCalendarLiveData: LiveData<List<ItemRowCalendar>>
        get() = _rowsCalendarLiveData

    private val _text = MutableLiveData<MyModel>().apply {

        //value = "This is home Fragment"

value = model
    }


    val rowsCalendar: ArrayList<ItemRowCalendar> = arrayListOf()
    //val years= arrayListOf<ArrayList<ArrayList<Day>>>()

    val myModel: LiveData<MyModel> = _text
    fun setModel(model: MyModel){
        _text.postValue(model)
       // var message:String = _text.value?.name
//        Log.v("_text", _text.value!!.name)
//        Log.v("myModel", myModel.value!!.name)
    }

     fun suspFun() = viewModelScope.launch {
        for(i in 0..5){
            delay(400L)
            println(i)

    }

        println("coroutines ")
    }
   // @OptIn(InternalCoroutinesApi::class)
    fun loadCalendars(startYear: Int, endYear: Int){




            rowsCalendar.clear()

        val date = Calendar.getInstance()
        val dateS=date.timeInMillis/1000*1000 // обнуление миллисекунд
        date.timeInMillis=dateS

        //viewModelScope.launch {

            for (year in startYear..endYear) {
                date.set(year, 0, 1, 0, 0, 0)

                rowsCalendar.add(ItemRowCalendar(arrayListOf(), 3, 0, 0, year))

               // var months = arrayListOf<ArrayList<Day>>()
                for (month in 1..12) {
                    rowsCalendar.add(ItemRowCalendar(arrayListOf(), 2, 0, month, year))


                    date.set(year, month - 1, 1)
                    var offsetDay = date.get(Calendar.DAY_OF_WEEK)
                    var lengthMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH)

                    if (offsetDay != 1) {
                        offsetDay--
                    } else offsetDay = 7

                   // Log.v("home view Model", "home view Model offsetDayNew=$offsetDay")
                    //  var days : ArrayList<Day> = arrayListOf()
                   // var days = arrayListOf<Day>()
                    var isDelLastColumn = true
                    for (str in 1..7) {
                        // var isNewStr=true
                        val daysRow: ArrayList<Day> = arrayListOf()


                        for (col in 1..6) {


                            var isCurrentMonth = true
                            var dayNum = 0
                            //var dateMillis=0L

                            dayNum = (str + (col - 1) * 7) - offsetDay + 1
                            date.set(year, month - 1, dayNum, 0, 0, 0)

                            if (dayNum <= 0 || dayNum > lengthMonth) {
                                dayNum = date.get(Calendar.DATE)
                                isCurrentMonth = false
                            }



                            if (col == 6 && isCurrentMonth) isDelLastColumn = false

                            if (col == 6 && isDelLastColumn) continue

                            val dateMillis = date.timeInMillis/1000*1000

                            //val id = dateMillis/10000
                            var isWeekend = false
                            if (str > 5) isWeekend = true

                            var day :Day


                          //  val dayRoom = getDayUseCase.execute(dateMillis)



//                            if(day!=null){
//                               // day= dayRoom
//
//                            } else {

                                // Log.v("homeViewModel ", " dayRoom= ${dayRoom}")

                              //  Log.v("homeViewModel ", " dateMillis= ${dateMillis}")


                                //Log.v("home view Model","home view Model dateMillis=$dateMillis")
                                day = Day(
                                    dayNum,
                                    dateMillis,
                                    isWeekend,
                                    isCurrentMonth,
                                    1,
                                    "subscr",
                                    arrayListOf<Todo>(
//                                        Todo(
//                                            dateLong = 2222222,
//                                            timeStart = 10,
//                                            timeEnd = 20,
//                                            describe = "todo describe example"
//                                        )
                                    )
                                )




                           // days.add(day)

                            daysRow.add(day)
                            //date.roll(Calendar.DATE,true)
                        }
                        rowsCalendar.add(ItemRowCalendar(daysRow, 1, str, month, year))


                    }

                  //  months.add(days)


                }

                //years.add(months)
                // Log.v("homeViewModel ", " years= ${years}")
            }
       CoroutineScope(Job()).launch() {
           val flow = getDaysUseCase.execute()
           flow.map {days->

               for (rowCalend in rowsCalendar){
                   for(day in rowCalend.days){
                       for (dayFlow in days){
                           if (day.date== dayFlow.date){
                               day.describe=dayFlow.describe
                               day.todos=dayFlow.todos
                               Log.v("homeViewModel ", " dayFlow.date=${dayFlow.date}")
                               Log.v("homeViewModel ", " dayFlow.todos.size=${dayFlow.todos.size}")
                           }
                       }
                   }
               }

           }.collect(){

               _rowsCalendarLiveData.postValue(rowsCalendar)}
       }

           _rowsCalendarLiveData.postValue(rowsCalendar)




       // Log.v("homeViewModel ", " years= ${years}")
    }

     fun addDay(day: Day){

        viewModelScope.launch {
            addDayUseCase.execute(day)
        }

    }
}
class MyModel(wname:String, age:Int){
    var name: String
   var _age: Int
init {
    name=wname
    _age=age
}
}

