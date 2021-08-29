package com.example.hoangcv2_note.adapter

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hoangcv2_note.R
import com.example.hoangcv2_note.model.Note
import com.example.hoangcv2_note.view.EditNoteFragment
import com.example.hoangcv2_note.viewmodel.NoteViewModel
import java.util.*


class NoteAdapter(val viewModel: NoteViewModel) :
    RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    var list: MutableList<Note>
    fun getAll(list: MutableList<Note>?) {
        this.list = list!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note: Note = list!![position]
        holder.txtTitle1.setText(note.title)
        holder.btnDel.setOnClickListener { v ->
            val builder1 = AlertDialog.Builder(v.context)
            builder1.setMessage("Are you sure want to delete")
            builder1.setCancelable(true)
            builder1.setPositiveButton(
                "Yes"
            ) { dialog, id ->
                viewModel.deleteNote(note)
                Toast.makeText(holder.itemView.context, "Deleted", Toast.LENGTH_LONG).show()
                list.removeAt(position)
                notifyDataSetChanged()
                dialog.cancel()
            }
            builder1.setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
            val alert11 = builder1.create()
            alert11.show()
        }
        holder.btnUpdate.setOnClickListener { v ->
            val activity = v.context as AppCompatActivity
            val miscellaneousfragment = EditNoteFragment()
            val bundle = Bundle()
            bundle.putInt("id", note.id!!)
            bundle.putString("title", note.title)
            bundle.putString("description", note.description)
            miscellaneousfragment.setArguments(bundle)
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, miscellaneousfragment).addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return if (list != null) {
            list!!.size
        } else 0
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle1: TextView
        var btnDel: Button
        var btnUpdate: Button

        init {
            txtTitle1 = itemView.findViewById(R.id.txttitle1)
            btnDel = itemView.findViewById(R.id.btndel)
            btnUpdate = itemView.findViewById(R.id.btnupdate)
        }
    }

    init {
        list = ArrayList<Note>()
    }
}