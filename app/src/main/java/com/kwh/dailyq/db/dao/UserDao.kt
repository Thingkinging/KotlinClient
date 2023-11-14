package com.kwh.dailyq.db.dao

import androidx.room.*
import com.kwh.dailyq.db.entiry.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(vararg users: UserEntity)

    @Update
    suspend fun update(vararg users: UserEntity)

    @Delete
    suspend fun delete(vararg userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE id=:uid")
    suspend fun get(uid: String): UserEntity?
}