package com.example.hoangcv2_note.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hoangcv2_note.database.NoteRepository
import com.example.hoangcv2_note.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    fun insertNote(note: Note) = GlobalScope.launch { repository.insertNote(note) }

    fun updateNote(description: String?, id: Int?) =
        GlobalScope.launch { repository.updateNote(description, id) }

    fun deleteNote(note: Note) = GlobalScope.launch { repository.deleteNote(note) }

    fun searchNote(title: String?) = repository.searchNote(title)

    fun getAllNote() = repository.getAllNote()
}