package com.example.hoangcv2_note.database

import androidx.lifecycle.LiveData
import com.example.hoangcv2_note.model.Note

class NoteRepository(
    private val noteDatabase: NoteDatabase
) {

    suspend fun insertNote(note: Note) = noteDatabase.NoteDao().insertNote(note)

    suspend fun updateNote(description: String?, id: Int?) =
        noteDatabase.NoteDao().updateNote(description, id)

    suspend fun deleteNote(note: Note) = noteDatabase.NoteDao().deleteNote(note)

    fun searchNote(title: String?): LiveData<MutableList<Note>> =
        noteDatabase.NoteDao().searchNote(title)

    fun getAllNote(): LiveData<MutableList<Note>> = noteDatabase.NoteDao().getAllNote()
}