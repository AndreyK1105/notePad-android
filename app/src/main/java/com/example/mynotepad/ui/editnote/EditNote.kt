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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Note
import com.example.mynotepad.R
import com.example.mynotepad.databinding.FragmentMyBinding
import kotlinx.coroutines.launch

import org.koin.androidx.viewmodel.ext.android.viewModel

import net.objecthunter.exp4j.ExpressionBuilder
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditNote.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNote : Fragment() {
    // TODO: Rename and change types of parameters
    //private var idNote: Int? = null
    private var param2: String? = null

    private  var _binding: FragmentMyBinding?=null
    private val navArgs : EditNoteArgs by navArgs()
    var idNote:Int =0
    var editTextNote = ""
    val tokens= arrayListOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
        '+', '-', '*', '/', '=', '÷',  ' ', '·', '×', ',', '.', '(', ')')

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel by viewModel<EditNoteViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        arguments?.let {
//            idNote = it.getInt(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }

        idNote=navArgs.idNote


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
//        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
//        toolbar.setupWithNavController(findNavController(), appBarConfiguration)


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

                    lifecycleScope.launch{

                       val currentData=System.currentTimeMillis()/1000


                     //   Log.v("a", "click listener action save current millisec/1000.toInt= $currentDataInt")
                        viewModel.saveNote(Note(idNote, editTextNote, currentData.toLong() ))
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
                        viewModel.delNote(idNote)
                    }
                    findNavController().popBackStack()
                    true
                }

                else ->false
            }
        }


        //Log.v("a", "EditNote editTextNote=$editTextNote ")
        if (idNote!=-1){
            if (editTextNote.isEmpty()){

                lifecycleScope.launch{
                     viewModel.getNoteFromId(idNote)

                    editTextNote=viewModel.saveText
                    Log.v("a", "EditNote editTextNote 2 =$editTextNote ")
                    editText.setText(editTextNote)
                }

            }

        }else idNote=0






//        viewModel.editTextNote.observe(viewLifecycleOwner){
//
//                value->editText.setText(value)
//
//        }
//        editText.setOnClickListener(View.OnClickListener {
//
//            val selStart =   editText.selectionStart
//            val selEnd =   editText.selectionEnd
//            Log.v("a", "My fragment setOnClickListener  selStart=${selStart}  selEnd=${selEnd}  ")
//
//        })
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                editTextNote= p0.toString()


//                Log.v("a", "My fragment   afterTextChanged  $editTextNote")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                val selStart =   editText.selectionStart
//                val selEnd =   editText.selectionEnd
////
//                if (p0 != null) {
//                    val p0String = p0.toString()
//                    val dropList= p0String.take(p1)// dropLast(p1)
//                    val listStrings=  dropList.lines()
//                    val lastLine=listStrings[listStrings.lastIndex]
//                    Log.v("a", "My fragment onTextChanged  lastLine=$lastLine  ")
//                }
//                Log.v("a", "My fragment beforeTextChanged   ${p0}   p1=${p1}  p2=${p2} p3=${p3}   ")
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               // val dropList
                if(p2==0) {
                    if (p0 != null) {
                        val dropList = p0.toString().take(p1+1 )
                        val listStrings = dropList.lines()
                        val lastLine = listStrings[listStrings.lastIndex]
                        Log.v("a", "My fragment onTextChanged  lastLine=$lastLine  ")

                        if (lastLine.isNotEmpty() && lastLine.last() == '=') {
                            var mathOper=" "
                            var resultString = " "
                            mathOper= lastLine.take(lastLine.length-1)
                            for( i in lastLine.length downTo 1){
                                if(lastLine[i-1] !in tokens){
                                mathOper=lastLine.takeLast(lastLine.length-i)
                                mathOper= mathOper.take(mathOper.length-1)

                                    break
                                }

                            }
                           mathOper= mathOper.replace('×', '*')
                            mathOper=mathOper.replace('÷', '/')
                            mathOper=mathOper.replace(',', '.')
                            try{
                                val ex=ExpressionBuilder(mathOper.toString()).build()
                                 val result = ex.evaluate()
                                val longRes = result.toLong()
                                if(result==longRes.toDouble())
                                    resultString=longRes.toString()
                                else
                                    resultString=  String.format("%.3f",result )
                            } catch (e:Exception){
                                Log.v("a", "My fragment onTextChanged error= ${e.message} ")
                            }

                            var newText = dropList + resultString  + p0.takeLast(p0.length - p1 - 1)
                            if (newText.first() == '=') {
                                newText = " " + newText
                            }
                            editText.setText(newText)
                            editText.setSelection(p1+1+resultString.length)
                        }
                    }
                }
                Log.v("a", "My fragment onTextChanged  ${p0}   p1=${p1}  p2=${p2} p3=${p3}   ")
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

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment MyFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: Int, param2: String) =
//            EditNote().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}