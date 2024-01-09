package com.example.mynotepad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Day
import com.example.mynotepad.ui.dashboard.ItemRowCalendar
import java.util.Calendar


class HomeViewModel : ViewModel() {
    private var model = MyModel(wname = "sss", age = 222222)
    private val _text = MutableLiveData<MyModel>().apply {

        //value = "This is home Fragment"

value = model
    }


    val rowsCalendar: ArrayList<ItemRowCalendar> = arrayListOf()
    val years= arrayListOf<ArrayList<ArrayList<Day>>>()

    val myModel: LiveData<MyModel> = _text
    fun setModel(model: MyModel){
        _text.postValue(model)
       // var message:String = _text.value?.name
//        Log.v("_text", _text.value!!.name)
//        Log.v("myModel", myModel.value!!.name)
    }

    fun loadCalendars(startYear: Int, endYear: Int){

        rowsCalendar.clear()

        val date = Calendar.getInstance()

        for (year in startYear..endYear){
            date.set(year,0,1)

            rowsCalendar.add(ItemRowCalendar(arrayListOf(), 3, 0, 0, year))

            var months= arrayListOf<ArrayList<Day>>()
            for (month in 1..12){
                rowsCalendar.add(ItemRowCalendar(arrayListOf(), 2, 0, month, year))


                date.set(year,month-1,1)
                var offsetDay=date.get(Calendar.DAY_OF_WEEK)
                var lengthMonth=date.getActualMaximum(Calendar.DAY_OF_MONTH)

                if (offsetDay!=1){
                    offsetDay--
                }else offsetDay=7

                Log.v("home view Model","home view Model offsetDayNew=$offsetDay")
              //  var days : ArrayList<Day> = arrayListOf()
                var days = arrayListOf<Day>()
                var isDelLastColumn=true
                for (str in 1..7){
                   // var isNewStr=true
                    val daysRow: ArrayList<Day> = arrayListOf()


                    for(col in 1..6){


                        var isCurrentMonth =true
                        var dayNum=0
                        var dateMillis=0L


                            dayNum =(str+(col-1)*7)-offsetDay +1
                            date.set(year,month-1,dayNum)
                            if (dayNum<=0 || dayNum>lengthMonth) {
                                dayNum = date.get(Calendar.DATE)
                                isCurrentMonth=false
                            }



                        if (col==6 && isCurrentMonth)   isDelLastColumn=false

                        if (col==6 && isDelLastColumn)  continue

                             dateMillis=date.timeInMillis


                        var isWeekend = false
                        if (str>5) isWeekend=true

                        //Log.v("home view Model","home view Model dateMillis=$dateMillis")
                        val day= Day(dayNum,dateMillis, isWeekend, isCurrentMonth, 1 ,  "subscr")
                        days.add (day)

                        daysRow.add(day)
                        //date.roll(Calendar.DATE,true)
                    }
                    rowsCalendar.add(ItemRowCalendar(daysRow, 1, str, month, year))

                }

                months.add(days)





            }

            years.add(months)
           // Log.v("homeViewModel ", " years= ${years}")
        }
       // Log.v("homeViewModel ", " years= ${years}")
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