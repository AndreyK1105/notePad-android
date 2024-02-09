package com.example.mynotepad.ui.home.edit_todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.mynotepad.databinding.FragmentEditTodoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTodo : Fragment() {
    private  var _binding: FragmentEditTodoBinding?=null
    private val binding get() = _binding!!



    private val viewModel by viewModel<EditTodoViewModel>()
    private var idTodoArg:Int=0
    private val navArgs: EditTodoArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idTodoArg=navArgs.idTodo



        lifecycleScope.launch {
            viewModel.getTodo(idTodoArg)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEditTodoBinding.inflate (inflater, container, false)

         val textTodo= binding.textTodo

        viewModel.todo.observe(viewLifecycleOwner, Observer {
            textTodo.text=it.describe
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