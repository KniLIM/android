package com.knilim.knilim.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.knilim.knilim.data.database.DialogDao;
import com.knilim.knilim.data.database.MessageDao;
import com.knilim.knilim.data.model.dialog.Dialog;
import com.knilim.knilim.data.model.message.Message;

@Database(entities = {Message.class, Dialog.class}, version = 2, exportSchema = false)
public abstract class ImDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
    public abstract DialogDao dialogDao();
}
