package com.example.mynotepad.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Note
import com.example.mynotepad.R

class AdapterNotes(private val notes: List<Note>): RecyclerView.Adapter < AdapterNotes.ViewHolder >(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView:TextView
  val itemRow=view
        init {

            textView=view.findViewById(R.id.itemNote)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // Log.v("a","adapter create ${ notes[0].textNote}" )
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // Log.v("a","adapter bnind ${ notes[0].textNote}" )
       holder.textView.text=notes[position].textNote
    }

    override fun getItemCount(): Int {
       return notes.size
    }
}