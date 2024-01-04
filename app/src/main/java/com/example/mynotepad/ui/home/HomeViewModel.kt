package com.example.mynotepad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Day
import java.util.Calendar


class HomeViewModel : ViewModel() {
    private var model = MyModel(wname = "sss", age = 222222)
    private val _text = MutableLiveData<MyModel>().apply {

        //value = "This is home Fragment"

value = model
    }


    val years= arrayListOf<ArrayList<ArrayList<Day>>>()

    val myModel: LiveData<MyModel> = _text
    fun setModel(model: MyModel){
        _text.postValue(model)
       // var message:String = _text.value?.name
//        Log.v("_text", _text.value!!.name)
//        Log.v("myModel", myModel.value!!.name)
    }

    fun loadCalendars(startYear: Int, endYear: Int){

        val date = Calendar.getInstance()

        for (year in startYear..endYear){
            date.set(year,0,1)

            var months= arrayListOf<ArrayList<Day>>()
            for (month in 1..12){
                date.set(year,month-1,1)
                var offsetDay=date.get(Calendar.DAY_OF_WEEK)
                var lengthMonth=date.getActualMaximum(Calendar.DAY_OF_MONTH)

                if (offsetDay!=1){
                    offsetDay--
                }else offsetDay=7

                Log.v("home view Model","home view Model offsetDayNew=$offsetDay")
              //  var days : ArrayList<Day> = arrayListOf()
                var days = arrayListOf<Day>()
                for (str in 1..7){
                    var isNewStr=true
                    for(col in 0..6){
                        var isCurrentMonth =true
                        var dayNum=0
                        var dateMillis=0L
                        if (isNewStr) {
                            dayNum = str - 7
                            isNewStr=false
                        } else{

                            dayNum =(str+(col-1)*7)-offsetDay +1
                            date.set(year,month-1,dayNum)
                            if (dayNum<=0 || dayNum>lengthMonth) {
                                dayNum = date.get(Calendar.DATE)
                                isCurrentMonth=false
                            }
                             dateMillis=date.timeInMillis
                        }

                        var isWeekend = false
                        if (str>5) isWeekend=true

                        //Log.v("home view Model","home view Model dateMillis=$dateMillis")
                        val day= Day(dayNum,dateMillis, isWeekend, isCurrentMonth, 1 ,  "subscr")
                        days.add (day)
                        //date.roll(Calendar.DATE,true)
                    }

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