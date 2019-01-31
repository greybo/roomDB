package com.example.sbotlevskyi.myapplication

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface DeepLinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUrl(url: LinkModel)

    @Delete
    fun deleteUrl(url: LinkModel)

    @Update
    fun updateUrl(url: LinkModel)

    @Query("DELETE FROM deep_link_table")
    fun deleteAll()

    @Query("SELECT * from deep_link_table ORDER BY name ASC")
    fun getAllLinks(): LiveData<List<LinkModel>>
}

