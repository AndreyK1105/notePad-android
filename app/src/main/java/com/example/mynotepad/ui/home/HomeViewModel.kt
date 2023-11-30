package com.example.mynotepad.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private var model = MyModel(wname = "sss", age = 222222)
    private val _text = MutableLiveData<MyModel>().apply {

        //value = "This is home Fragment"

value = model
    }
    val myModel: LiveData<MyModel> = _text
    fun setModel(model: MyModel){
        _text.postValue(model)
       // var message:String = _text.value?.name
        Log.v("_text", _text.value!!.name)
        Log.v("myModel", myModel.value!!.name)
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