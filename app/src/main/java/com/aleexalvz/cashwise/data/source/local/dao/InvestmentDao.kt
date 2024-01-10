package com.aleexalvz.cashwise.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aleexalvz.cashwise.data.source.local.model.LocalInvestment

@Dao
interface InvestmentDao {

    @Query("SELECT * FROM `LOCALINVESTMENT` WHERE userID == (:userID)")
    fun getByUserID(userID: Long): List<LocalInvestment>

    @Query("SELECT * FROM `LOCALINVESTMENT` WHERE id == (:id)")
    fun getByID(id: Long): LocalInvestment

    @Insert
    fun insert(investment: LocalInvestment)

    @Delete
    fun delete(investment: LocalInvestment)

    @Update
    fun update(investment: LocalInvestment)

}