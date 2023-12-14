package com.example.mynotepad.ui.editnote

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Note
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentMyBinding
import kotlinx.coroutines.launch

import org.koin.androidx.viewmodel.ext.android.viewModel
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditNote.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNote : Fragment() {
    // TODO: Rename and change types of parameters
    private var idNote: Int? = 0
    private var param2: String? = null

    private  var _binding: FragmentMyBinding?=null
    private val navArgs : EditNoteArgs by navArgs()
    var idNoteArg:Int =0
    var editTextNote = ""

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel by viewModel<EditNoteViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            idNote = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        idNoteArg=navArgs.idNote


val callback= requireActivity().onBackPressedDispatcher.addCallback(this){
    Log.v("a", "my fr callBack button back ")
    findNavController().popBackStack()
}

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding= FragmentMyBinding.inflate(inflater, container, false)

        val  editText: EditText
        editText=binding.textInputEditText
        editTextNote=viewModel.editTextNote
        editText.setText(editTextNote)

        val toolbar=binding.toolbar
        toolbar.setTitle("my frag")
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener{

            findNavController().popBackStack()
            Log.v("a", "click listener action home ")

        }
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.action_save ->{
                    Log.v("a", "click listener action save ")
                    lifecycleScope.launch{
                        viewModel.saveNote(Note(idNoteArg,editTextNote))
                    }
                    true
                }
                R.id.action_search ->{
                    Log.v("a", "click listener action search ")
                    true
                }
                R.id.action_delete ->{
                    Log.v("a", "click listener action user ")
                    lifecycleScope.launch{
                        viewModel.delNote(idNoteArg)
                    }
                    findNavController().popBackStack()
                    true
                }

                else ->false
            }
        }


        //Log.v("a", "EditNote editTextNote=$editTextNote ")
        if (idNoteArg!=-1){
            if (editTextNote.isEmpty()){

                lifecycleScope.launch{
                     viewModel.getNoteFromId(idNoteArg)

                    editTextNote=viewModel.saveText
                    Log.v("a", "EditNote editTextNote 2 =$editTextNote ")
                    editText.setText(editTextNote)
                }

            }

        }else idNoteArg=0






//        viewModel.editTextNote.observe(viewLifecycleOwner){
//
//                value->editText.setText(value)
//
//        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                editTextNote= p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.v("a", "My fragment onTextChanged  ${p0}  p1=${p1}  p2=${p2} p3=${p3}  ")
            }
        })



        //val view = binding.root
        // Inflate the layout for this fragment
    return binding.root
    //return inflater.inflate(R.layout.fragment_my, container, false)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("a", "My fragment onDestroy  ")
    }
    override fun onDestroyView() {
        super.onDestroyView()
viewModel.saveText(  editTextNote)
        Log.v("a", "My fragment onDestroyView  ")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            EditNote().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}