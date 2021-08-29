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
import com.example.hoangcv2_note.viewmodel.NoteViewModel
import com.example.hoangcv2_note.viewmodel.NoteViewModelFactory

class EditNoteFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: NoteViewModel
    private lateinit var buttonCustomView: ButtonCustomView
    var edtTitle: EditText? = null
    var edtDes: EditText? = null
    var toolbar3: Toolbar? = null
    var id: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        passData()
        val AppCompatActivity = activity as AppCompatActivity?
        AppCompatActivity?.setSupportActionBar(toolbar3)
        AppCompatActivity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        buttonCustomView.setOnClickListener(this)
    }

    fun init(view: View) {
        edtTitle = view.findViewById(R.id.edttitle1)
        edtDes = view.findViewById(R.id.edtdes1)
        toolbar3 = view.findViewById(R.id.toolbar3)
        buttonCustomView = view.findViewById(R.id.btnSave)
    }

    fun passData() {
        val bundle = this.arguments
        val title = bundle!!.getString("title")
        val description = bundle!!.getString("description")
        id = bundle!!.getInt("id")
        edtTitle?.setText(title)
        edtDes?.setText(description)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val noteRepository = NoteRepository(NoteDatabase(requireContext()))
        val factory = NoteViewModelFactory(noteRepository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    fun updateData() {
        val desChange: String = edtDes?.getText().toString()
        viewModel.updateNote(desChange, id)
    }

    fun backToListNote() {
        val activity = context as AppCompatActivity?
        val recylerFragment = ListNoteFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, recylerFragment).commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSave -> {
                updateData()
                backToListNote()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}