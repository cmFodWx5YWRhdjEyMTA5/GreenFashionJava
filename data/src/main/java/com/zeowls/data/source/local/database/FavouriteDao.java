package com.zeowls.data.source.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zeowls.data.entity.FavCart_data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class FavouriteDao {
    @Query("DELETE FROM Favorite WHERE id = :id")
    public abstract void delete(int id);

    @Query("SELECT Favorite.id FROM Favorite WHERE id = :id")
    public abstract Flowable<List<Integer>> findFavorite(int id);

    @Query("SELECT Favorite.*, Cart.id AS cartId FROM Favorite LEFT JOIN Cart ON Favorite.id=Cart.id")
    public abstract Flowable<List<FavCart_data>> getFavCart();

    @Query("SELECT Favorite.id FROM Favorite ")
    public abstract Single<List<Integer>> getAllIds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllFavorite(List<Favorite> cart);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addFavorite(Favorite favorite);
}
