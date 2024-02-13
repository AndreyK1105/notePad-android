package com.example.mynotepad.ui.home.editday

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Todo
import com.example.mynotepad.databinding.FragmentEditDayBinding
import com.example.mynotepad.databinding.FragmentEditTodoBinding
import com.example.mynotepad.ui.home.edit_todo.EditTodoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.navArgs
import com.example.domain.models.Day
import com.example.mynotepad.R
import com.example.mynotepad.ui.dashboard.AdapterNotes
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

//private const val ARG_ID_DAY="idDay"
class EditDay: Fragment(),AdapterDays.RecyclerItemListener {
    private  var _binding: FragmentEditDayBinding?=null
    private val binding get() = _binding!!
    private var idDay:Long =0
    private var describe:String=""
    private var dateText:String=""
    private val navArgs: EditDayArgs by navArgs()
    private val viewModel by viewModel<EditDayViewModel>()
    //private lateinit var day: Day
   // private val todos=viewModel.todos.observe(viewLifecycleOwner,)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            idDay=it.getInt(ARG_ID_DAY)
//        }
        idDay=navArgs.idDay
        describe=navArgs.describe

        val simpleDateFormat=SimpleDateFormat("dd-MM-yyyy")

       // dateText= DateFormat.getDateInstance().format(calendar)
        dateText= simpleDateFormat.format(idDay)
        lifecycleScope.launch {
            viewModel.getDay(idDay)

        }
    }



    //private val viewModel by viewModel<EditTodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditDayBinding.inflate (inflater, container, false)

        //return inflater.inflate(R.layout.fragment_edit_todo, container, false)
        //val recyclerView= binding.todoList
        val floatingAddTodo = binding.floatingAddTodo
        val dateEditDay:TextView=binding.dateEditDay

//        val buttonAddDay=binding.buttonAddDay
//        buttonAddDay.setOnClickListener(){
//           // viewModel.
//        }



//        if ( savedInstanceState!=null) {
//            val idDay=savedInstanceState.getInt("idDay")
//        }else val idDay= 0

        dateEditDay.text=dateText

       // viewLifecycleOwner.lifecycleScope.launch { viewModel.getDay(idDay.toLong()).collect{dayFlow-> day =dayFlow } }
        val recyclerViewTodos=binding.todoList
        val todosListener: LiveData<ArrayList<Todo>> = viewModel.todos
        todosListener.observe(viewLifecycleOwner, Observer {todos->
            val nav=findNavController()
            recyclerViewTodos.adapter=AdapterDays(todos, nav, this)
        })
        //recyclerViewTodos.adapter=AdapterDays(viewModel.todos)
        //viewModel.day
        lifecycleScope.launch {
            viewModel.getDay(idDay)


           // recyclerViewTodos.adapter=AdapterDays(viewModel.todos)
//Log.v("editDay", "getDay todos.size=${viewModel.todos.size}" +
  //      "idDay=$idDay")
        }
        floatingAddTodo.setOnClickListener(){
     //       recyclerViewTodos.adapter=AdapterDays(viewModel.todos)
//            val todo=Todo(id = 0, dateLong =idDay, timeStart = 0, timeEnd = 1, describe = idDay.toString() )
//           lifecycleScope.launch {viewModel.addTodo(todo, describe)  }
            findNavController().navigate(EditDayDirections.actionEditDayToEditTodo(date=idDay, idTodo = 0))

        }
        return binding.root


//findNavController().navigate(EditDayDirections.actionEditDayToEditTodo())
    }


    override  fun onClick(idTodo: Int) {
        lifecycleScope.launch { viewModel.delTodo(idTodo) }
    }


}

class AdapterDays(
    private val todos:List<Todo>,
    private val  nav:NavController,
    private val listener:RecyclerItemListener
) : RecyclerView.Adapter<AdapterDays.ViewHolder>() {

    interface RecyclerItemListener{
        fun onClick(idTodo:Int)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textTodo:TextView
        val buttonDelTodo: Button
        init {
            textTodo=view.findViewById(R.id.itemTodoText)
            buttonDelTodo=view.findViewById(R.id.buttoneDelTodo)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item=todos[position]
        holder.textTodo.text=item.describe
        holder.textTodo.setOnClickListener(){
            nav.navigate(EditDayDirections.actionEditDayToEditTodo( idTodo= item.id, date= item.dateLong))
                    }
        holder.buttonDelTodo.setOnClickListener(){

            listener.onClick(item.id)

        }
    }

}