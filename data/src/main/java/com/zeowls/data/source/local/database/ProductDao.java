package com.zeowls.data.source.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class ProductDao {

    @Query("DELETE FROM cart WHERE id = :id")
    public abstract void deleteCart(int id);

    @Query("DELETE FROM cart")
    public abstract void deleteAllCart();

    @Query("UPDATE cart SET cartQuantity= :count WHERE id = :id")
    public abstract void edit(int id, int count);

    @Query("SELECT * FROM cart ")
    public abstract Flowable<List<Cart>> getAllCart();

    @Query("SELECT cart.id FROM cart ")
    public abstract Single<List<Integer>> getAllIds();

    @Query("SELECT COUNT(*) FROM cart ")
    public abstract Flowable<Integer> getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void addCart(Cart cart);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllCart(List<Cart> cart);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void moveFavorite(Favorite favorite);

    @Transaction
    public void moveToFavoriteInTransaction(Favorite favorite) {
        deleteCart(favorite.getId());
        moveFavorite(favorite);
    }
}
