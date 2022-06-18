package com.plcoding.instagramui.saveplace.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.plcoding.instagramui.saveplace.data.db.entities.ContactItem;



@Database(
        entities = {ContactItem.class},
        version=1
)


public abstract  class ContactDatabase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    private static ContactDatabase INSTANCE;

    public static ContactDatabase invoke(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"ContactDB.db")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

}

