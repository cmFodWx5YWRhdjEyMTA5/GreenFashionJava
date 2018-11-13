package com.zeowls.data.source.local.database;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Cart.class, Favorite.class, SearchEntity.class}, exportSchema = false, version = 5)
public abstract class ProductRoomDatabase extends RoomDatabase {
    private static ProductRoomDatabase INSTANCE;

    public abstract ProductDao cartDao();

    public abstract FavouriteDao favouriteDao();

    public abstract SearchDao searchDao();

    public static ProductRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductRoomDatabase.class, "greenfashion.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
