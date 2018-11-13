package com.zeowls.data.source.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class SearchDao {
    @Query("UPDATE search SET recent = 0 WHERE recent = 1")
    public abstract void deleteAllRecent();

    @Query("UPDATE search SET recent = 0 WHERE id = :id ")
    public abstract void deleteRecent(int id);

    @Query("SELECT * FROM search ORDER BY name ASC")
    public abstract Single<List<SearchEntity>> getSuggestions();

    @Query("SELECT * FROM search WHERE recent = 1")
    public abstract Flowable<List<SearchEntity>> getRecentSearch();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertAll(List<SearchEntity> list);

    @Query("UPDATE search SET recent = 1 WHERE name = :name ")
    public abstract void addRecent(String name);
}
