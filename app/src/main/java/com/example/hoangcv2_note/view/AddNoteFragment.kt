package com.example.hoangcv2_note.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hoangcv2_note.R
import com.example.hoangcv2_note.database.NoteDatabase
import com.example.hoangcv2_note.database.NoteRepository
import com.example.hoangcv2_note.model.Note
import com.example.hoangcv2_note.viewmodel.NoteViewModel
import com.example.hoangcv2_note.viewmodel.NoteViewModelFactory

class AddNoteFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: NoteViewModel
    private lateinit var buttonCustomView: ButtonCustomView
    var edtTitle: EditText? = null
    var edtDes: EditText? = null
    var toolbar1: Toolbar? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        val AppCompatActivity = activity as AppCompatActivity?
        AppCompatActivity?.setSupportActionBar(toolbar1)
        AppCompatActivity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        buttonCustomView.setOnClickListener(this)
    }

    fun init(view: View) {
        edtTitle = view.findViewById(R.id.edttitle)
        edtDes = view.findViewById(R.id.edtdes)
        toolbar1 = view.findViewById(R.id.toolbar1)
        buttonCustomView = view.findViewById(R.id.btnAdd)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val noteRepository = NoteRepository(NoteDatabase(requireContext()))
        val factory = NoteViewModelFactory(noteRepository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    fun addData() {
        var title: String
        var des: String
        title = edtTitle?.text.toString()
        des = edtDes?.text.toString()
        val note = Note(id = null, title, des)
        viewModel.insertNote(note)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAdd -> addData()
            else -> {

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}