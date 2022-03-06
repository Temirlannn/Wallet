package com.it.wallet.data.storage.db

import android.content.Context
import android.text.Editable
import androidx.room.*

@Database(
    entities = [PaymentEntity::class, CategoryEntity::class, ServiceEntity::class],
    version = 3,
    exportSchema = false
)
abstract class PaymentDB : RoomDatabase() {

    abstract fun PaymentDao(): PaymentDao

    companion object {
        private const val DB_NAME: String = "PaymentDB"
        private var instance: PaymentDB? = null

        fun getInstance(context: Context): PaymentDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaymentDB::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId")
        ),
        ForeignKey(
            entity = ServiceEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("serviceId")
        )],
    tableName = "payment"
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "account")
    var account: String,

    @ColumnInfo(name = "amount")
    var amount: Int,

    @ColumnInfo(name = "time")
    var time: String,


    @ColumnInfo(name = "comment")
    var comment: String,

    @ColumnInfo(name = "categoryId")
    var categoryId: Int,

    @ColumnInfo(name = "serviceId")
    var serviceId: Int
)

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId")
        )],
    tableName = "service"
)
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "categoryId")
    var categoryId: Int

)

data class PaymentWithServiceAndCategory(
    @Embedded val paymentTO: PaymentEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity,

    @Relation(
        parentColumn = "serviceId",
        entityColumn = "id"
    )
    val service: ServiceEntity
)

