package com.example.mynotepad.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynotepad.databinding.FragmentNotificationsBinding
import com.google.android.material.chip.ChipGroup

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val drawView:DrawView=binding.drawView
        val buttonMoveMarkLeft=binding.buttonMoveMarkLeft
        val buttonMoveMarkRight=binding.buttonMoveMarkRight

        buttonMoveMarkLeft.setOnClickListener(){drawView.moveMarkerToLeft()}
        buttonMoveMarkRight.setOnClickListener(){drawView.moveMarkerToRight()}

        val editSizeTile=binding.editSizeTile

        editSizeTile.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2?.action==KeyEvent.ACTION_DOWN&&
                    p1==KeyEvent.KEYCODE_ENTER){
                    val enterSize=editSizeTile.text.toString()
                    Log.v("a","NotificationsFragment  enterSize=$enterSize  ")
                    drawView.changeTile(enterSize.toFloat())

                   editSizeTile.clearFocus()
                    //editSizeTile.isCursorVisible=false
                    return true
                }
                return false
            }
        })
        val editSizeSquare=binding.editSizeSquare

        editSizeSquare.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2?.action==KeyEvent.ACTION_DOWN&&
                    p1==KeyEvent.KEYCODE_ENTER){
                    val enterSize=editSizeSquare.text.toString()

                    drawView.changeSquare(enterSize.toFloat())

                    editSizeSquare.clearFocus()
                    //editSizeSquare.isCursorVisible=false
                    return true
                }
                return false
            }
        })

        val editSizeGap=binding.editSizeGap

        editSizeGap.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2?.action==KeyEvent.ACTION_DOWN&&
                    p1==KeyEvent.KEYCODE_ENTER){
                    val enterSize=editSizeGap.text.toString()

                     drawView.changeGap(enterSize.toFloat())

                    editSizeGap.clearFocus()
                  //  editSizeGap.isCursorVisible=false
                    return true
                }
                return false
            }
        })
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        Log.v("a","NotificationsFragment  setOnCheckedStateChangeListener chekedChip = 000  ")




        val chipGroup:ChipGroup = binding.chipGroup

        val chipStart=binding.chipStart
        chipStart.isChecked=true
//        chipStart.setOnClickListener(){
//            value->
//            Log.v("a","NotificationsFragment  setOnClickListener chekedChip = ")
//        }
//        chipStart.setOnCheckedChangeListener(){
//            chipStart, isChecked->
//            Log.v("a","NotificationsFragment  setOnCheckedChangeListener isChecked =$isChecked ")
//        }
        val chekedChip=chipGroup.checkedChipId
        Log.v("a","NotificationsFragment  setOnCheckedStateChangeListener chekedChip = $chekedChip ")

            chipGroup.setOnCheckedStateChangeListener{group, chekedChip ->
if (chekedChip!=null && chekedChip.last()==binding.chipStart.id ){
    drawView.setAlignmentStart()
}else if (chekedChip!=null && chekedChip.last()==binding.chipCenter.id ){
    drawView.setAlignmentCenter()
}else if (chekedChip!=null && chekedChip.last()==binding.chipMidle.id ){
    drawView.setAlignmentMiddle()
}
            Log.v("a","NotificationsFragment  setOnCheckedStateChangeListener chekedChip = $chekedChip  chipGroup=$group "
            )

            }



        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }


}

