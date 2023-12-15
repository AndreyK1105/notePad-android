package com.example.mynotepad.ui.dashboard

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Note
import com.example.mynotepad.R
import java.text.DateFormat

class AdapterNotes(private val notes: List<Note>,
                   private val listener: RecyclerItemListener
    ): RecyclerView.Adapter < AdapterNotes.ViewHolder >(){

    interface RecyclerItemListener{
        fun onItemClick(position: Int)
    }
   inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val textNote:TextView
        val textDate:TextView
        val textDateLong:TextView

 // val itemRow=view
        init {
view.setOnClickListener(this)
            textNote=view.findViewById(R.id.itemNote)
     textDate=view.findViewById(R.id.dateNote)
     textDateLong=view.findViewById(R.id.dateLong)

     //textView.textSize= 20.0F
        }

        override fun onClick(p0: View?) {
            val position= adapterPosition
            if (position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    private var onClickListener: OnClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // Log.v("a","adapter create ${ notes[0].textNote}" )
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // Log.v("a","adapter bnind ${ notes[0].textNote}" )
        val item=notes[position]
       holder.textNote.text="${item.textNote.trimEnd() }"
        holder.textDate.text=item.dateLong.toString()
        holder.textDateLong.text=item.id.toString()
//        holder.itemView.setOnClickListener{
//           if (onClickListener!=null){
//               onClickListener!!.onClick(position, item)
//           }
//        }
    }

    override fun getItemCount(): Int {
       return notes.size
    }
}


