package com.knilim.knilim.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.knilim.knilim.data.model.message.Message

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message)

    @Delete
    fun deleteMessage(message: Message)

    @Query("SELECT * FROM Message")
    fun selectAllMessages() : List<Message>

    @Query("select * from Message where dialogId= :dialogId")
    fun getMessagesByDialogId(dialogId: String) : List<Message>
}