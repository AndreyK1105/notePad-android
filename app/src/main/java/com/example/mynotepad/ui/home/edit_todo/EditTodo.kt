package com.example.mynotepad.ui.home.edit_todo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentEditTodoBinding
import com.example.mynotepad.databinding.FragmentMyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTodo : Fragment() {
    private  var _binding: FragmentEditTodoBinding?=null
    private val binding get() = _binding!!



    private val viewModel by viewModel<EditTodoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentEditTodoBinding.inflate (inflater, container, false)
    return binding.root
    //return inflater.inflate(R.layout.fragment_edit_todo, container, false)


    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super. onActivityCreated(savedInstanceState)
//        //  super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(EditTodoViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}