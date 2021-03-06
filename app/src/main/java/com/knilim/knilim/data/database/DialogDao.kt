package com.knilim.knilim.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.knilim.knilim.data.model.dialog.Dialog

@Dao
interface DialogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDialog(dialog: Dialog)

    @Update
    fun updateDialog(dialog: Dialog)

    @Delete
    fun deleteDialog(dialog: Dialog)

    @Query("select * from Dialog")
    fun selectAllDialogs() : List<Dialog>
}