package com.example.hoangcv2_note.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hoangcv2_note.R
import com.example.hoangcv2_note.adapter.NoteAdapter
import com.example.hoangcv2_note.database.NoteDatabase
import com.example.hoangcv2_note.database.NoteRepository
import com.example.hoangcv2_note.model.Note
import com.example.hoangcv2_note.viewmodel.NoteViewModel
import com.example.hoangcv2_note.viewmodel.NoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListNoteFragment : Fragment(), SearchView.OnQueryTextListener, View.OnClickListener {
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    var floatingActionButtonAdd: FloatingActionButton? = null
    var toolbar2: Toolbar? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        showData()
        val AppCompatActivity = activity as AppCompatActivity?
        AppCompatActivity?.setSupportActionBar(toolbar2)
        AppCompatActivity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        floatingActionButtonAdd?.setOnClickListener(this)
    }

    fun init(view: View) {
        toolbar2 = view.findViewById(R.id.toolbar2)
        floatingActionButtonAdd = view.findViewById(R.id.fltbtnAdd)
        recyclerView = view.findViewById(R.id.recylerViewNote)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        noteAdapter = NoteAdapter(viewModel)
//        val noteRepository = NoteRepository(NoteDatabase(requireContext()))
//        val factory = NoteViewModelFactory(noteRepository)
//        viewModel = ViewModelProvider(requireActivity(),factory).get(NoteViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val noteRepository = NoteRepository(NoteDatabase(requireContext()))
        val factory = NoteViewModelFactory(noteRepository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list_note, container, false)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchData(newText)
        return true
    }

    fun getToAddListNote() {
        val activity = context as AppCompatActivity?
        val recylerFragment = AddNoteFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, recylerFragment).addToBackStack(null).commit()
    }

    fun searchData(newText: String?) {
        var list: MutableList<Note>

        viewModel.searchNote(newText).observe(viewLifecycleOwner, Observer {
            list = it
            noteAdapter.getAll(list)
            recyclerView.adapter = noteAdapter
        })
    }

    fun showData() {
        var list: MutableList<Note>

        viewModel.getAllNote().observe(viewLifecycleOwner, Observer {
            list = it
            noteAdapter.getAll(list)
            recyclerView.adapter = noteAdapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.mnSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fltbtnAdd -> getToAddListNote()
            else -> {
            }
        }
    }
}