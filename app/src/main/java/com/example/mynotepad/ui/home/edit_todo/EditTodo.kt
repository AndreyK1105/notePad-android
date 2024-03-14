package com.example.mynotepad.ui.home.edit_todo

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.Action
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotepad.databinding.FragmentEditTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTodo : Fragment() {
    private  var _binding: FragmentEditTodoBinding?=null
    private val binding get() = _binding!!



    private val viewModel by viewModel<EditTodoViewModel>()
    private var idTodoArg:Int=0
    private var date=0L
    private val navArgs: EditTodoArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idTodoArg=navArgs.idTodo
        date=navArgs.date



        lifecycleScope.launch {
            viewModel.getTodo(idTodoArg, date)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEditTodoBinding.inflate (inflater, container, false)


         val editTodoText= binding.editTodoText

        viewModel.todo.observe(viewLifecycleOwner, Observer {

            editTodoText.setText(it.describe)
        })
        editTodoText.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                Log.v("editTodo", " p0=$p0")
                Log.v("editTodo", " p1=$p1")
                if (p2 != null) {
                    if (p2.action==KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_ENTER){
                        Log.v("editTodo", " p2=$p2")
                        lifecycleScope.launch {
                            viewModel.addDescribeTodo( editTodoText.text.toString(), date)
                            findNavController().popBackStack()
                        }

                    }
                    if(p2.action==KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_BACK){
                        findNavController().popBackStack()
                    }
                }

              return true
            }
        })


        return binding.root




    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super. onActivityCreated(savedInstanceState)
//        //  super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(EditTodoViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}