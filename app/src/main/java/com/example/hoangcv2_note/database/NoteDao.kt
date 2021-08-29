package com.example.hoangcv2_note.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hoangcv2_note.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("UPDATE note SET description = :description WHERE id=:id")
    suspend fun updateNote(description: String?, id: Int?)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE title LIKE '%' || :title || '%'")
    fun searchNote(title: String?): LiveData<MutableList<Note>>

    @Query("SELECT * FROM note")
    fun getAllNote(): LiveData<MutableList<Note>>
}