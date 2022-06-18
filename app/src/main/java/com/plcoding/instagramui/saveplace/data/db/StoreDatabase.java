package com.plcoding.instagramui.saveplace.data.db;

import android.content.Context;
import com.plcoding.instagramui.saveplace.data.db.entities.StoreItem;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(
        entities = {StoreItem.class},
        version=1
)


public abstract  class StoreDatabase extends RoomDatabase {

    public abstract StoreDao getStoreDao();

    private static StoreDatabase INSTANCE;

    public static StoreDatabase invoke(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),StoreDatabase.class,"storeDB.db")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

}
