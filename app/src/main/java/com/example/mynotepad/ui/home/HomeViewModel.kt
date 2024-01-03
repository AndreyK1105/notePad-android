package com.example.mynotepad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.Day
import java.sql.Date
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

        val data = Calendar.getInstance()



        for (year in startYear..endYear){
            data.set(year,0,1)
            var months= arrayListOf<ArrayList<Day>>()
            for (month in 1..12){
                data.set(2024,month-1,1)
                var offsetDay=data.get(Calendar.DAY_OF_WEEK)
              //  Log.v("home view Model","home view Model data=$data")
                Log.v("home view Model","home view Model offsetDay=$offsetDay")
                if (offsetDay!=1){
                    offsetDay--
                }else offsetDay=7

                Log.v("home view Model","home view Model offsetDayNew=$offsetDay")
              //  var days : ArrayList<Day> = arrayListOf()
                var days = arrayListOf<Day>()
                for (str in 1..7){
                    for(col in 0..5){
                        var dayNum =(str+col*7)-offsetDay +1
                        if (dayNum<=0 || dayNum>31)  dayNum=0
                        var isWeekend = false

                        if (str>5) isWeekend=true
                        val day= Day(dayNum, isWeekend, 1, "subscr")
                        days.add (day)
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