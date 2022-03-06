package com.it.wallet.data.storage.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PaymentDao {

    @Query("SELECT * FROM payment")
    suspend fun fetchAllPayments(): List<PaymentEntity>

    @Query("SELECT * FROM payment where id =:id")
    suspend fun fetchOnePayment(id:Int): List<PaymentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPayment(paymentEntity: PaymentEntity)

    @Query("DELETE  FROM payment where id=:id")
    suspend fun deletePaymentById(id:Int)

    @Query("DELETE FROM payment")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addService(serviceEntity: ServiceEntity)

    @Query("SELECT * FROM category")
    suspend fun fetchAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM service where categoryId==:id ")
    suspend fun fetchAllServices(id:Int): List<ServiceEntity>

    @Transaction
    @Query("SELECT * FROM payment")
    fun getAllData(): List<PaymentWithServiceAndCategory>

}